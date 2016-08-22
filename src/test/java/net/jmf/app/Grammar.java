package net.jmf.app;

public interface Grammar {
	public static final String NL = System.lineSeparator();
	public static final String ARITHMETIC =
			"grammar arithmetic;" + NL +
			"equation   : expression relop expression   ;" + NL +
			"expression   : multiplyingExpression ((PLUS | MINUS) multiplyingExpression)*   ;" + NL +
			"multiplyingExpression   : powExpression ((TIMES | DIV) powExpression)*   ;" + NL +
			"powExpression   : atom (POW expression)?   ;" + NL +
			"atom   : scientific   | variable   | LPAREN expression RPAREN   ;" + NL +
			"scientific   : number (E number)?   ;" + NL +
			"relop   : EQ   | GT   | LT   ;" + NL +
			"number   : MINUS? DIGIT + (POINT DIGIT +)?   ;" + NL +
			"variable   : MINUS? LETTER (LETTER | DIGIT)*   ;" + NL +
			"LPAREN   : '('   ;" + NL +
			"RPAREN   : ')'   ;" + NL +
			"PLUS   : '+'   ;" + NL +
			"MINUS   : '-'   ;" + NL +
			"TIMES   : '*'   ;" + NL +
			"DIV   : '/'   ;" + NL +
			"GT   : '>'   ;" + NL +
			"LT   : '<'   ;" + NL +
			"EQ   : '='   ;" + NL +
			"POINT   : '.'   ;" + NL +
			"E   : 'e' | 'E'   ;" + NL +
			"POW   : '^'   ;" + NL +
			"LETTER   : ('a' .. 'z') | ('A' .. 'Z')   ;" + NL +
			"DIGIT   : ('0' .. '9')   ;" + NL +
			"WS   : [ \\r\\n\\t] + -> channel (HIDDEN)   ;" + NL;
	
	public static final String ARITHMETICRULES =
			"grammar arithmetic;" + NL +
			"import aLexer;" + NL +
			"equation   : expression relop expression   ;" + NL +
			"expression   : multiplyingExpression ((PLUS | MINUS) multiplyingExpression)*   ;" + NL +
			"multiplyingExpression   : powExpression ((TIMES | DIV) powExpression)*   ;" + NL +
			"powExpression   : atom (POW expression)?   ;" + NL +
			"atom   : scientific   | variable   | LPAREN expression RPAREN   ;" + NL +
			"scientific   : number (E number)?   ;" + NL +
			"relop   : EQ   | GT   | LT   ;" + NL +
			"number   : MINUS? DIGIT + (POINT DIGIT +)?   ;" + NL +
			"variable   : MINUS? LETTER (LETTER | DIGIT)*   ;";
	
	public static final String ARITHMETICLEXER =
			"lexer grammar aLexer;" + NL +
			"LPAREN   : '('   ;" + NL +
			"RPAREN   : ')'   ;" + NL +
			"PLUS   : '+'   ;" + NL +
			"MINUS   : '-'   ;" + NL +
			"TIMES   : '*'   ;" + NL +
			"DIV   : '/'   ;" + NL +
			"GT   : '>'   ;" + NL +
			"LT   : '<'   ;" + NL +
			"EQ   : '='   ;" + NL +
			"POINT   : '.'   ;" + NL +
			"E   : 'e' | 'E'   ;" + NL +
			"POW   : '^'   ;" + NL +
			"LETTER   : ('a' .. 'z') | ('A' .. 'Z')   ;" + NL +
			"DIGIT   : ('0' .. '9')   ;" + NL +
			"WS   : [ \\r\\n\\t] + -> channel (HIDDEN)   ;" + NL;	
}
