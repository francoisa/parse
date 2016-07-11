package net.jmf.app;

import java.util.List;

import org.antlr.v4.Tool;
import org.antlr.v4.codegen.CodeGenPipeline;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.tool.ErrorType;
import org.antlr.v4.tool.Grammar;
import org.antlr.v4.tool.ast.GrammarAST;
import org.stringtemplate.v4.ST;

public class InMemoryCodeGenPipeline extends CodeGenPipeline {
	private Grammar g;
	private Tool tool;
	
	public InMemoryCodeGenPipeline(Tool t, Grammar g) {
		super(g);
		this.g = g;
		this.tool = t;
	}

	@Override
	public void process() {
		InMemoryCodeGenerator gen = new InMemoryCodeGenerator(tool, g, "JavaScript");
		IntervalSet idTypes = new IntervalSet();
		idTypes.add(ANTLRParser.ID);
		idTypes.add(ANTLRParser.RULE_REF);
		idTypes.add(ANTLRParser.TOKEN_REF);
		List<GrammarAST> idNodes = g.ast.getNodesWithType(idTypes);
		for (GrammarAST idNode : idNodes) {
			if (gen.getTarget().grammarSymbolCausesIssueInGeneratedCode(idNode)) {
				g.tool.errMgr.grammarError(ErrorType.USE_OF_BAD_WORD,
										   g.fileName, idNode.getToken(),
										   idNode.getText());
			}
		}

		// all templates are generated in memory to report the most complete
		// error information possible, but actually writing output files stops
		// after the first error is reported
		int errorCount = g.tool.errMgr.getNumErrors();

		if (g.isLexer()) {
			ST lexer = gen.generateLexer();
			if (g.tool.errMgr.getNumErrors() == errorCount) {
				writeRecognizer(lexer, gen);
			}
		}
		else {
			ST parser = gen.generateParser();
			if (g.tool.errMgr.getNumErrors() == errorCount) {
				writeRecognizer(parser, gen);
			}
			if (g.tool.gen_listener) {
				ST listener = gen.generateListener();
				if (g.tool.errMgr.getNumErrors() == errorCount) {
					gen.writeListener(listener);
				}
				if (gen.getTarget().wantsBaseListener()) {
					ST baseListener = gen.generateBaseListener();
					if (g.tool.errMgr.getNumErrors() == errorCount) {
						gen.writeBaseListener(baseListener);
					}
				}
			}
			if (g.tool.gen_visitor) {
				ST visitor = gen.generateVisitor();
				if (g.tool.errMgr.getNumErrors() == errorCount) {
					gen.writeVisitor(visitor);
				}
				if (gen.getTarget().wantsBaseVisitor()) {
					ST baseVisitor = gen.generateBaseVisitor();
					if (g.tool.errMgr.getNumErrors() == errorCount) {
						gen.writeBaseVisitor(baseVisitor);
					}
				}
			}
			gen.writeHeaderFile();
		}
		gen.writeVocabFile();
	}	
}
