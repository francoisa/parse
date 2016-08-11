package net.jmf.app;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.antlr.v4.semantics.SemanticPipeline;
import org.antlr.v4.tool.ErrorType;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.GrammarTransformPipeline;
import org.antlr.v4.tool.LexerGrammar;
import org.antlr.v4.tool.ast.GrammarAST;
import org.antlr.v4.tool.ast.GrammarRootAST;

public class ParserTool extends Tool {
	private static final Logger log = Logger.getLogger(ParserTool.class.toString());
	private Map<String, String> grammarOptions;
	private GrammarRootAST grammarRootAST;
	private Map<String, String> fileMap;
	private final Map<String, Grammar> importedGrammars;
	private final Map<String, String> grammarMap;
	
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
		if ( ast.grammarType==ANTLRParser.LEXER ) {
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
		final Grammar g = createGrammar(grammarRootAST);
		g.fileName = grammarRootAST.fileName;
		process(g);
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
		if ( g.isLexer() ) factory = new LexerATNFactory((LexerGrammar)g);
		else factory = new ParserATNFactory(g);
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
	
	public ParseData parse(String toParse) {
		ANTLRInputStream input = null;
		ParseData pd =  new ParseData("not implemented");
		try {
			input = new ANTLRInputStream(new StringReader(toParse));
			Map<String, String> values = new HashMap<String, String>();			
		}
		catch (IOException ioe) {
			log.log(Level.SEVERE, "ioe: " + ioe.getMessage(), ioe);
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
}
