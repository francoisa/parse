package net.jmf.app;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RequireHelperTest {
	private static final String FS = System.getProperty("file.separator");
	
	@BeforeClass
	public static void loggerFormat() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$s - %2$s %5$s%6$s%n");	
		String baseDirectory = System.getProperty("user.dir") + FS + "js";
		RequireHelper.loadFilesIntoCache(baseDirectory);
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
		assertThat(TestableRequireHelper.lookupPath("fs", true), is(equalTo("fs/fs")));
		assertThat(TestableRequireHelper.lookupPath("./../error/Errors", true), is(equalTo("antlr4/error/Errors")));
		assertThat(TestableRequireHelper.lookupPath("antlr4/index", true), is(equalTo("antlr4/index")));
		assertThat(TestableRequireHelper.lookupPath("antlr4/index.js", true), is(equalTo("antlr4/index")));
		assertThat(TestableRequireHelper.lookupPath("./atn/index", true), is(equalTo("antlr4/atn/index")));
	}
	
	@Test
	public void hasContentsReturnsCorrectValue() {
		assertThat(RequireHelper.containsContent("fs"), is(true));
		assertThat(RequireHelper.containsContent("./../error/Errors"), is(true));
		assertThat(RequireHelper.containsContent("antlr4/index"), is(true));
		assertThat(RequireHelper.containsContent("antlr4/index.js"), is(true));
	}
	
	@Test
	public void resolveReturnsFileContents() {
		String baseDirectory = System.getProperty("user.dir") + FS + "js";
		Path path = Paths.get(baseDirectory + FS + "fs" + FS + "fs.js");
		try {
			String actualContents = new String(Files.readAllBytes(path));
			assertThat(RequireHelper.getContents("fs"), is(equalTo(actualContents)));
		} 
		catch (IOException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void addToCacheUpdatesCacheContents() {
		String testFile = "testFile.js";
		String testContents = "Ipsum Lorem";
		String nullContents = RequireHelper.getContents(testFile);
		assertThat(nullContents, isEmptyOrNullString());
		Map<String, String> fileMap = TestableRequireHelper.addContents(testFile, testContents, true);
		assertThat(fileMap.get(TestableRequireHelper.fileFromPath(testFile)), is(equalTo(testContents)));
	}
	
	static class TestableRequireHelper extends RequireHelper {
		public static void loadFilesIntoCache(String path, Map<String, String> fileMap) {
			fileMap.putAll(FILEMAP);
		}
		
		public static String lookupPath(String path, boolean value) {
			return lookupPath(path);
		}
		
		public static Map<String, String> addContents(String name, String contents, boolean flag) {
			
			addContents(name, contents);
			return FILEMAP;
		}
	}
}
