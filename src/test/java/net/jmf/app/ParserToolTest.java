package net.jmf.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ParserToolTest {
	private static final String FS = System.getProperty("file.separator");
	private static final String baseDirectory = System.getProperty("user.dir") + FS + "src" + FS + "test" +
				FS + "resources";
	
	@BeforeClass
	public static void loggerFormat() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$s - %2$s %5$s%6$s%n");	
	}

	private ScriptEngine engine;
	private Invocable invocable;

	@Before
	public void setup() {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		engine = engineManager.getEngineByName("nashorn");
		invocable = (Invocable) engine;
	}
	
	private void loadRequire() {
		String requireScript = System.getProperty("user.dir") + FS + "js"  + FS + "nashorn-require.js";
		try {
			engine.eval(new FileReader(requireScript));
		}
		catch (FileNotFoundException | ScriptException e) {
			Assert.fail("Could not evaluate '" + requireScript + "': " + e.getMessage());
		}
	}
	
	private void loadScript(final String script) {
		try {
			engine.eval(new FileReader(script));
		}
		catch (FileNotFoundException | ScriptException e) {
			Assert.fail("Could not evaluate '" + script + "': " + e.getMessage());
		}
	}
	
	private void addContents(final String script) {
		try {
			Path path = Paths.get(baseDirectory + FS + script);
			String scriptContents = new String(Files.readAllBytes(path));
			RequireHelper.addContents(script, scriptContents);
		}
		catch (IOException e) {
			Assert.fail("Could not read '" + script + "': " + e.getMessage());
		}
	}
	
	private void initializeRequireHelper() {
		RequireHelper.loadFilesIntoCache(System.getProperty("user.dir") + FS + "js");
		addContents("arithmeticLexer.js");
		addContents("arithmeticListener.js");
		addContents("arithmeticParser.js");
	}
	
	@Test
	public void parseShouldReturnAParseArray() {
		final String toParse = "a = 4 + 5";
		final String expectedResult = "[{\"rule\":\"expression\",\"value\":\"a\"}," + 
				"{\"rule\":\"relop\",\"value\":\"=\"},{\"rule\":\"expression\",\"value\":\"4+5\"}]";
		initializeRequireHelper();
		// load nashorn-require
		loadRequire();
		loadScript(baseDirectory + FS + "arithmetic.js");
		try {
			Object result =  invocable.invokeFunction("parse", toParse);
			assertThat(result.toString(), is(equalTo(expectedResult)));
		}
		catch (NoSuchMethodException | ScriptException e) {
			Assert.fail("Could not invoke function 'parse': " + e.getMessage());
		}
	}
	
	@Test
	public void grammarShouldReturnJavascript() {
		final String name = "arithmetic";
		Map<String, String> grammarMap = new HashMap<>();
		grammarMap.put(name, Grammar.ARITHMETIC);
		TestableParserTool pt = new TestableParserTool(name, grammarMap);
		Map<String, String> fileMap = pt.getFileMap();
		assertThat(fileMap.size(), is(greaterThan(0)));
		assertThat(fileMap, hasKey(name + "Lexer.js"));
		assertThat(fileMap, hasKey(name + "Parser.js"));
		assertThat(fileMap, hasKey(name + "Listener.js"));
	}

	@Test
	@Ignore
	public void expressionAndreGrammarShouldReturnAParseArray() {
		final String name = "arithmetic";
		Map<String, String> grammarMap = new HashMap<>();
		grammarMap.put(name, Grammar.ARITHMETIC);
		ParserTool pt = new ParserTool(name, grammarMap);
		ParseData pd = pt.parse("a = 4 + 5");
		assertThat(pd.getTokens(), hasKey("expression"));
		assertThat(pd.getTokens(), hasKey("relop"));
	}
	
	class TestableParserTool extends ParserTool {

		public TestableParserTool(String name, Map<String, String> grammarMap) {
			super(name, grammarMap);
		}
		
		public Map<String, String> getFileMap() {
			return fileMap;
		}
	}
}
