package net.jmf.app;

import java.util.logging.Logger;

import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.Parser;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.TokenStream;
import org.antlr.v4.parse.ANTLRParser;
import org.antlr.v4.parse.v4ParserException;
import org.antlr.v4.tool.ErrorType;

public class LoggingParser extends ANTLRParser {
	private static final Logger log = Logger.getLogger(LoggingParser.class.toString());
	
	public LoggingParser(TokenStream input) {
		super(input);
	}

	@Override
	public void displayRecognitionError(String[] tokenNames, RecognitionException e) {
		String msg = getParserErrorMessage(this, e);
		log.info(ErrorType.SYNTAX_ERROR + " source: " + getSourceName() + " line: " + e.line + " token: " + e.token + 
				" '" + e.getMessage() + "' " + msg);
	}
	

	public String getParserErrorMessage(Parser parser, RecognitionException e) {
		String msg;
		if ( e instanceof NoViableAltException) {
			String name = parser.getTokenErrorDisplay(e.token);
			msg = name+" came as a complete surprise to me";
		}
		else if ( e instanceof v4ParserException) {
			msg = ((v4ParserException)e).msg;
		}
		else {
			msg = parser.getErrorMessage(e, parser.getTokenNames());
		}
		return msg;
	}

	@Override
	public void grammarError(ErrorType etype, org.antlr.runtime.Token token, Object... args) {
		log.info(etype + " " + getSourceName() + " " + token + " " + args);
	}	
}
