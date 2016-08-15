package net.jmf.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
	private Invocable invocable;

	@Before
	public void setup() {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		engine = engineManager.getEngineByName("nashorn");
		invocable = (Invocable) engine;
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
		try {
			Object result =  invocable.invokeFunction("hello");
			assertThat(result.toString(), is(equalTo("Hello World.")));
		}
		catch (NoSuchMethodException | ScriptException e) {
			Assert.fail("Could not invoke function 'hello': " + e.getMessage());
		}
	}
	
	@Test
	public void aJavascriptFunctionCallsAJavaFunction() {
		String script = baseDirectory + FS + "callAJavaStaticMethod.js";
		String testFile = "testFile.js";
		String testContents = "Ipsum Lorem";
		RequireHelper.addContents(testFile, testContents);
		try {
			engine.eval(new FileReader(script));
		}
		catch (FileNotFoundException | ScriptException e) {
			Assert.fail("Could not evaluate '" + script + "': " + e.getMessage());
		}
		try {
			Object result =  invocable.invokeFunction("hello", testFile);
			assertThat(result.toString(), is(equalTo(testContents)));
		}
		catch (NoSuchMethodException | ScriptException e) {
			Assert.fail("Could not invoke function 'hello': " + e.getMessage());
		}
	}
	
	@Test
	public void requireUsesTheRequireHelper() {
		String scriptFile =  "returnContentsOfFile.js";
		String script = baseDirectory + FS + scriptFile;
		String scriptContents = null;
		try {
			Path path = Paths.get(script);
			scriptContents = new String(Files.readAllBytes(path));
		}
		catch (IOException e) {
			Assert.fail("Could not read '" + script + "': " + e.getMessage());
		}
		// add the fs.js script to the RequireHelper cache
		try {
			String fsScript = System.getProperty("user.dir") + FS + "js"  + FS + "fs" + FS + "fs.js";
			Path path = Paths.get(fsScript);
			String fsContents = new String(Files.readAllBytes(path));
			RequireHelper.addContents("fs/fs", fsContents);
		}
		catch (IOException e) {
			Assert.fail("Could not read '" + script + "': " + e.getMessage());
		}
		// load the nashorn version of require (which uses RequireHelper)
		String requireScript = System.getProperty("user.dir") + FS + "js"  + FS + "nashorn-require.js";
		try {
			engine.eval(new FileReader(requireScript));
		}
		catch (FileNotFoundException | ScriptException e) {
			Assert.fail("Could not evaluate '" + requireScript + "': " + e.getMessage());
		}		
		try {
			engine.eval(new FileReader(script));
		}
		catch (FileNotFoundException | ScriptException e) {
			Assert.fail("Could not evaluate '" + script + "': " + e.getMessage());
		}		
		try {
			Object result =  invocable.invokeFunction("contents", script);
			assertThat(result.toString(), is(equalTo(scriptContents)));
		}
		catch (NoSuchMethodException | ScriptException e) {
			Assert.fail("Could not invoke function 'contents': " + e.getMessage());
		}
	}
}
