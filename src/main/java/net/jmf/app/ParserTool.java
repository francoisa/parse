package net.jmf.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.ParserRuleReturnScope;
import org.antlr.runtime.RecognitionException;
import org.antlr.v4.Tool;
import org.antlr.v4.analysis.AnalysisPipeline;
import org.antlr.v4.automata.ATNFactory;
import org.antlr.v4.automata.LexerATNFactory;
import org.antlr.v4.automata.ParserATNFactory;
import org.antlr.v4.codegen.CodeGenerator;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.parse.GrammarASTAdaptor;
import org.antlr.v4.parse.v3TreeGrammarException;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.LexerInterpreter;
import org.antlr.v4.runtime.ParserInterpreter;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.semantics.SemanticPipeline;
import org.antlr.v4.tool.ErrorType;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarTransformPipeline;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarAST;
import org.antlr.v4.tool.ast.GrammarRootAST;

import com.google.gson.Gson;

public class ParserTool extends Tool {
	private static final Logger log = Logger.getLogger(ParserTool.class.toString());
	private Map<String, String> grammarOptions;
	private GrammarRootAST grammarRootAST;
	protected Map<String, String> fileMap;
	private final Map<String, Grammar> importedGrammars;
	private final Map<String, String> grammarMap;
	private final Grammar grammar;
	
	private GrammarRootAST parse(ANTLRStringStream in) {
		try {
			GrammarASTAdaptor adaptor = new GrammarASTAdaptor(in);
			LoggingLexer lexer = new LoggingLexer(in);
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			lexer.tokens = tokens;
			LoggingParser p = new LoggingParser(tokens);
			p.setTreeAdaptor(adaptor);
			try {
				ParserRuleReturnScope r = p.grammarSpec();
				GrammarAST root = (GrammarAST)r.getTree();
				if ( root instanceof GrammarRootAST) {
					((GrammarRootAST)root).hasErrors = lexer.getNumberOfSyntaxErrors()>0 || p.getNumberOfSyntaxErrors()>0;
					assert ((GrammarRootAST)root).tokenStream == tokens;
					if ( grammarOptions !=null ) {
						((GrammarRootAST)root).cmdLineOptions = grammarOptions;
					}
					return ((GrammarRootAST)root);
				}
			}
			catch (v3TreeGrammarException e) {
				log.log(Level.SEVERE, "v3TreeGrammarException:  " + e.getMessage(), e);
			}
			return null;
		}
		catch (RecognitionException re) {
			log.log(Level.SEVERE, "RecognitionException: " + re.getMessage(), re);
		}
		return null;
	}
	
	@Override
	public Grammar createGrammar(GrammarRootAST ast) {
		final Grammar g;
		if (ast.grammarType == ANTLRParser.LEXER) {
			g = new LexerGrammar(this, ast);
		}
		else {
			g = new Grammar(this, ast);
		}

		// ensure each node has pointer to surrounding grammar
		GrammarTransformPipeline.setGrammarPtr(g, ast);
		return g;
	}
	
	public ParserTool(String name, Map<String, String> grammarMap) {
		this.grammarMap = grammarMap;
		fileMap = new HashMap<String, String>();
		importedGrammars = new HashMap<String, Grammar>();
		ANTLRStringStream stream = new ANTLRStringStream(grammarMap.get(name));
		grammarOptions = new HashMap<String, String>();
		grammarRootAST = parse(stream);
		grammarRootAST.fileName = name;
		grammar = createGrammar(grammarRootAST);
		grammar.fileName = grammarRootAST.fileName;
		process(grammar);
	}
	
	private void process(Grammar g) {
		g.loadImportedGrammars();

		GrammarTransformPipeline transform = new GrammarTransformPipeline(g, this);
		transform.process();

		LexerGrammar lexerg;
		GrammarRootAST lexerAST;
		if (g.ast != null && g.ast.grammarType == ANTLRParser.COMBINED &&
			 !g.ast.hasErrors) {
			lexerAST = transform.extractImplicitLexer(g); // alters g.ast
			if ( lexerAST!=null ) {
				if (grammarOptions != null) {
					lexerAST.cmdLineOptions = grammarOptions;
				}

				lexerg = new LexerGrammar(this, lexerAST);
				lexerg.fileName = g.fileName;
				lexerg.originalGrammar = g;
				g.implicitLexer = lexerg;
				lexerg.implicitLexerOwner = g;
				processNonCombinedGrammar(lexerg);
			}
		}
		if (g.implicitLexer != null) g.importVocab(g.implicitLexer);
		processNonCombinedGrammar(g);
	}
	
	private void processNonCombinedGrammar(Grammar g) {
		if (g.ast == null || g.ast.hasErrors) return;
		if (internalOption_PrintGrammarTree) log.info(g.ast.toStringTree());

		boolean ruleFail = checkForRuleIssues(g);
		if (ruleFail) return;

		int prevErrors = errMgr.getNumErrors();
		// MAKE SURE GRAMMAR IS SEMANTICALLY CORRECT (FILL IN GRAMMAR OBJECT)
		SemanticPipeline sem = new SemanticPipeline(g);
		sem.process();

		String language = g.getOptionString("language");
		if (!CodeGenerator.targetExists(language)) {
			errMgr.toolError(ErrorType.CANNOT_CREATE_TARGET_GENERATOR, language);
			return;
		}

		if (errMgr.getNumErrors() > prevErrors) return;

		// BUILD ATN FROM AST
		ATNFactory factory;
		if (g.isLexer()) {
			factory = new LexerATNFactory((LexerGrammar)g);
		}
		else {
			factory = new ParserATNFactory(g);
		}
		g.atn = factory.createATN();

		if (generate_ATN_dot) generateATNs(g);

		// PERFORM GRAMMAR ANALYSIS ON ATN: BUILD DECISION DFAs
		AnalysisPipeline anal = new AnalysisPipeline(g);
		anal.process();

		if ( g.tool.getNumErrors()>prevErrors ) return;

		InMemoryCodeGenPipeline gen = new InMemoryCodeGenPipeline(this, g);
		gen.process();
		fileMap.putAll(gen.getFileMap());
	}
	
	private static final String FS = System.getProperty("file.separator");
	
	private void loadScripts(String baseDir, ScriptEngine engine, String... scripts) {
		for (String script : scripts) {
			try {
				engine.eval(new FileReader(baseDir  + FS + script));
			}
			catch (FileNotFoundException | ScriptException e) {
				log.log(Level.SEVERE, "Exception loading " + script + ": '" + e.getMessage() + "'", e);
			}
		}
	}
	
	class RuleValue {
		String rule;
		String value;
	}
	
	protected Map<String, String> toRuleMap(String json) {
		Map<String, String> ruleMap = new HashMap<>();
		Gson gson = new Gson();
		RuleValue[] ruleValues = gson.fromJson(json, RuleValue[].class);
		for (RuleValue rv : ruleValues) {
			ruleMap.put(rv.rule, rv.value);
		}
		return ruleMap;
	}
	
	public ParseData parse(String toParse) {
		ParseData pd =  new ParseData("not implemented");
		final String baseDir = System.getProperty("user.dir") + FS + "js";
		RequireHelper.loadFilesIntoCache(baseDir);
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Invocable invocable = (Invocable) engine;
		loadScripts(baseDir, engine, "nashorn-require.js", "arithmetic.js");
		try {
			Object result =  invocable.invokeFunction("parse", toParse);
			pd = new ParseData(toRuleMap(result.toString()));
		}
		catch (NoSuchMethodException | ScriptException e) {
			log.log(Level.SEVERE, "Exception calling javascript parse('" + 
					toParse + "'): '" + e.getMessage() + "'", e);
		}
		return pd;
	}
	
	@Override
	public Grammar loadImportedGrammar(Grammar g, GrammarAST nameNode) {
		String name = nameNode.getText();
		Grammar imported = importedGrammars.get(name);
		ANTLRStringStream in = new ANTLRStringStream(grammarMap.get(name));
		GrammarRootAST root = parse(g.fileName, in);
		if (root == null) {
			return null;
		}

		imported = createGrammar(root);
		imported.fileName = name;
		importedGrammars.put(root.getGrammarName(), imported);
		return imported;
	}
	
	public ParseData parse(String startRule, String input) {
		ParseData pd =  new ParseData("not implemented");

		LexerInterpreter lexEngine = grammar.createLexerInterpreter(new ANTLRInputStream(input));
		org.antlr.v4.runtime.CommonTokenStream tokens = new org.antlr.v4.runtime.CommonTokenStream(lexEngine);
	    ParserInterpreter parser = grammar.createParserInterpreter(tokens);
	    ParseTree parseTree = parser.parse(grammar.getRule(startRule).index);
		RuleMapListener rml = new RuleMapListener(parser.getRuleNames());
		ParseTreeWalker walker = new ParseTreeWalker();
	    walker.walk(rml, parseTree);
		pd = new ParseData(rml.getRuleMap());
		return pd;
	}
	
	public class RuleMapListener implements ParseTreeListener {
		private String[] ruleArray;
		Map<String, String> ruleMap;
		
		public RuleMapListener(String[] ruleArray) {
			this.ruleArray = ruleArray;
			this.ruleMap = new HashMap<>();
		}
		
		public Map<String, String> getRuleMap() {
			return ruleMap;
		}
		
		@Override
		public void enterEveryRule(ParserRuleContext ctx) {
			String key = ruleArray[ctx.getRuleIndex()];
			String value = ctx.getText();
			ruleMap.put(key, value);
		}

		@Override
		public void visitTerminal(TerminalNode node) { }

		@Override
		public void visitErrorNode(ErrorNode node) { }

		@Override
		public void exitEveryRule(ParserRuleContext ctx) { }
	}
}
