package net.jmf.app;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.antlr.v4.Tool;
import org.antlr.v4.codegen.CodeGenerator;
import org.antlr.v4.tool.ErrorType;
import org.antlr.v4.tool.Grammar;
import org.stringtemplate.v4.AutoIndentWriter;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STWriter;

public class InMemoryCodeGenerator extends CodeGenerator {
	private Map<String, String> fileMap;
	
	public Map<String, String> getFileMap() {
		return fileMap;
	}

	public InMemoryCodeGenerator(Tool tool, Grammar g, String language) {
		super(tool, g, language);
		fileMap = new HashMap<String, String>();
	}

	@Override
	public void write(ST code, String fileName) {
		try {
			Writer w = new StringWriter();
			STWriter wr = new AutoIndentWriter(w);
			wr.setLineWidth(lineWidth);
			code.write(wr);
			fileMap.put(fileName, w.toString());
		}
		catch (IOException ioe) {
			tool.errMgr.toolError(ErrorType.CANNOT_WRITE_FILE,
								  ioe,
								  fileName);
		}
	}
}
