package net.jmf.app;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequireHelper {
	private static final Logger log = Logger.getLogger(RequireHelper.class.toString());
	protected static final Map<String, String> FILEMAP = new HashMap<String, String>(); 
	protected static final Map<String, String> REQUIREMAP = new HashMap<String, String>(); 
	
	public static String fileFromPath(String path) {
		StringBuilder file = new StringBuilder(path);
		if (path.endsWith(".js")) {
			int length = file.length();
			file.delete(length - 3, length);
		}
		int lastRelativePath = file.lastIndexOf("./");
		if (lastRelativePath > 0) {
			file.delete(0, lastRelativePath + 2);
		}
		return file.toString();
	}
	
	private static Map<String, String> loadFilesIntoCache(int absolutePath, Path path) {
		String fileName = null;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
		    for (Path entry : stream) {
		    	fileName = entry.toString();
		    	if (entry.toFile().isFile()) {
		    		String contents = new String(Files.readAllBytes(entry));
		    		String file = fileName.substring(absolutePath, fileName.length() - 3);
		    		FILEMAP.put(file.replace('\\', '/'), contents);
		    	}
		    	else {
		    		loadFilesIntoCache(absolutePath, entry);
		    	}
		    }
		}
		catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
			log.log(Level.SEVERE, "loadFilesIntoCache(" + absolutePath + ", " + path + ") entry=" + fileName + " " + 
					x.getMessage(), x);
		    System.err.println(x);
		}
		return FILEMAP;
	}
	
	public static void loadFilesIntoCache(String path) {
		loadFilesIntoCache(path.length()+1, Paths.get(path));
	}
	
	protected static boolean matchPath(String requirePath, String path) {
		if (path.endsWith(requirePath)) {
			if (path.charAt(path.length() - requirePath.length() - 1) == '/') {
				return true;
			}
		}
		return false;
	}
	
	protected static String lookupPath(String requirePath) {
		if (FILEMAP.containsKey(requirePath)) {
			return requirePath;
		}
		else if (REQUIREMAP.containsKey(requirePath)) {
			return REQUIREMAP.get(requirePath);
		}
		else {
			final String cookedPath = fileFromPath(requirePath);
			if (FILEMAP.containsKey(cookedPath)) {
				return cookedPath;
			}
			else {
				Optional<String> actualPath = FILEMAP.keySet().parallelStream().filter(p -> matchPath(cookedPath, p))
							.findFirst();
				if (actualPath.isPresent()) {
					REQUIREMAP.put(requirePath, actualPath.get());
					return actualPath.get();
				}
				else {
					log.severe("lookupPath(" + requirePath + ") not found");
					return null;
				}
			}
		}
	}
	
	public static String getContents(String path) {
		String actualPath = lookupPath(path);
		if (actualPath != null) {
			return FILEMAP.get(actualPath);
		}
		else {
			return "";
		}
	}
	
	public static void addContents(String name, String contents) {
		FILEMAP.put(fileFromPath(name), contents);
	}	
}
