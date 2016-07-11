package net.jmf.app;

import org.antlr.v4.Tool;
import org.antlr.v4.codegen.CodeGenerator;
import org.antlr.v4.tool.Grammar;

public class InMemoryCodeGenerator extends CodeGenerator {
	public InMemoryCodeGenerator(Tool tool, Grammar g, String language) {
		super(tool, g, language);
	}

}
