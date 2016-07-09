package net.jmf.app;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class AppTest {
	@Test
	public void testParse() {
		String expression = "a = 8 + 9;";
		ParseData expected = new ParseData();
		Parser parser = new Parser(Grammar.ARITHMETIC);
		ParseData actual = parser.parse(expression);
		assertThat(actual.getErrorMessage(), is(equalTo(expected.getErrorMessage())));
	}
}
