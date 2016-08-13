package net.jmf.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ParserToolTest {
	private static final String FS = System.getProperty("file.separator");
	@BeforeClass
	public static void loggerFormat() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$s - %2$s %5$s%6$s%n");		
	}
	
	@Test
	@Ignore
	public void parseShouldReturnMapOfRules() {
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
	
	@Test
	public void fileFromPathReturnsFileForIndex() {
		String path = "antlr4/index.js";
		String expectedFile = "antlr4/index";
		String  actualFile = RequireHelper.fileFromPath(path);
		assertThat(actualFile, is(equalTo(expectedFile)));
	}
	
	@Test
	public void fileFromPathReturnsFileWithRelativePath() {
		String path = "../Utils.js";
		String expectedFile = "Utils";
		String  actualFile = RequireHelper.fileFromPath(path);
		assertThat(actualFile, is(equalTo(expectedFile)));
	}
	
	@Test
	public void loadFilesIntoCacheReturnsMapOfUniquePaths() {
		String baseDirectory = System.getProperty("user.dir") + FS + "js";
		String[] expectedKeys  = {"fs/fs", "antlr4/atn/ATN"};
		Map<String, String> fileMap = new HashMap<String, String>();
		TestableRequireHelper.loadFilesIntoCache(baseDirectory, fileMap);
		for (String expectedKey : expectedKeys)
			assertThat(fileMap, hasKey(expectedKey));
	}
	
	@Test
	public void lookupPathForFileReturnsPath() {
		String baseDirectory = System.getProperty("user.dir") + FS + "js";
		TestableRequireHelper.loadFilesIntoCache(baseDirectory);
		assertThat(TestableRequireHelper.lookupPath("fs", true), is(equalTo("fs/fs")));
		assertThat(TestableRequireHelper.lookupPath("./../error/Errors", true), is(equalTo("antlr4/error/Errors")));
		assertThat(TestableRequireHelper.lookupPath("antlr4/index", true), is(equalTo("antlr4/index")));
	}
	
	@Test
	public void resolveReturnsFileContents() {
		String baseDirectory = System.getProperty("user.dir") + FS + "js";
		Path actualPath = Paths.get(baseDirectory + FS + "fs" + FS + "fs.js");
		try {
			String actualContents = new String(Files.readAllBytes(actualPath));
			assertThat(RequireHelper.getContents("fs"), is(equalTo(actualContents)));
		} 
		catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	static class TestableRequireHelper extends RequireHelper {
		public static void loadFilesIntoCache(String path, Map<String, String> fileMap) {
			RequireHelper.loadFilesIntoCache(path);
			fileMap.putAll(FILEMAP);
		}
		
		public static String lookupPath(String path, boolean value) {
			return lookupPath(path);
		}
	}
}
