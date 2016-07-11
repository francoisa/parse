package net.jmf.app;

import java.util.logging.Logger;

import org.antlr.runtime.CharStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.Token;
import org.antlr.v4.parse.ANTLRLexer;
import org.antlr.v4.tool.ErrorType;

public class LoggingLexer extends ANTLRLexer {
	private static final Logger log = Logger.getLogger(LoggingLexer.class.toString());
	
	public LoggingLexer(CharStream input) {
		super(input);
	}
	
	@Override
	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
		String msg = getErrorMessage(e, tokenNames);
		log.info(getSourceName() + " line: " + e.line + " token: " + e.token + " '" + e.getMessage() + "' " + msg);
	}

	@Override
	public void grammarError(ErrorType etype, Token token, Object... args) {
		log.info(etype + "source:  " + getSourceName() + " token: " + token + " " + args);
	}
}
