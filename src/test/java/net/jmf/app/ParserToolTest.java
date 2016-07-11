package net.jmf.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.BeforeClass;
import org.junit.Test;

public class ParserToolTest {
	@BeforeClass
	public static void loggerFormat() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$s - %2$s %5$s%6$s%n");		
	}
	
	@Test
	public void testParse() {
		String expression = "a = 8 + 9;";
		ParseData expected = new ParseData();
		ParserTool parser = new ParserTool(Grammar.ARITHMETIC);
		ParseData actual = parser.parse(expression);
		assertThat(actual.getErrorMessage(), is(equalTo(expected.getErrorMessage())));
	}
}
