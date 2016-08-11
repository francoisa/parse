package net.jmf.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

public class ParserToolTest {
	@BeforeClass
	public static void loggerFormat() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$s - %2$s %5$s%6$s%n");		
	}
	
	@Test
	public void testParsingGrammarWithoutImport() {
		String expression = "a = 8 + 9;";
		Map<String, String> values = new HashMap<String, String>();
		ParseData expected = new ParseData(values);
		Map<String, String> grammarMap = new HashMap<String, String>();
		String grammarName = "arithmetic";
		grammarMap.put(grammarName, Grammar.ARITHMETIC);
		ParserTool parser = new ParserTool(grammarName, grammarMap);
		ParseData actual = parser.parse(expression);
		assertThat(actual.getErrorMessage(), is(equalTo(expected.getErrorMessage())));
	}
}
