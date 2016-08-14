package net.jmf.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class NashornRequireTest {
	private static final String FS = System.getProperty("file.separator");
	private static final String baseDirectory = System.getProperty("user.dir") + FS + "src" + FS + "test" +
				FS + "resources";
	
	@BeforeClass
	public static void loggerFormat() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$s - %2$s %5$s%6$s%n");	
	}

	private ScriptEngine engine;

	@Before
	public void setup() {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		engine = engineManager.getEngineByName("nashorn");
	}
	
	@Test
	public void runJavascriptLoadsAndRunsAScript() {
		String script = baseDirectory + FS + "returnAString.js";
		try {
			engine.eval(new FileReader(script));
		}
		catch (FileNotFoundException | ScriptException e) {
			Assert.fail("Could not evaluate '" + script + "': " + e.getMessage());
		}
		Invocable invocable = (Invocable) engine;

		try {
			Object result =  invocable.invokeFunction("hello");
			assertThat(result.toString(), is(equalTo("Hello World.")));
		}
		catch (NoSuchMethodException | ScriptException e) {
			Assert.fail("Could not invoke function 'hello': " + e.getMessage());
		}
	}
}
