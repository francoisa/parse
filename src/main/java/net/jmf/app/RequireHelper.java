package net.jmf.app;

import java.io.IOException;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequireHelper {
	private static final Logger log = Logger.getLogger(RequireHelper.class.toString());
	
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
	
	private static Map<String, String> loadFilesIntoCache(int absolutePath, Path path, Map<String, String> fileMap) {
		String fileName = null;
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
		    for (Path entry : stream) {
		    	fileName = entry.toString();
		    	if (entry.toFile().isFile()) {
		    		String contents = new String(Files.readAllBytes(entry));
		    		String file = fileName.substring(absolutePath, fileName.length() - 3);
		    		fileMap.put(file.replace('\\', '/'), contents);
		    	}
		    	else {
		    		loadFilesIntoCache(absolutePath, entry, fileMap);
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
		return fileMap;
	}
	
	public static Map<String, String> loadFilesIntoCache(String path) {
		Map<String, String> fileMap = new HashMap<>();
		return loadFilesIntoCache(path.length()+1, Paths.get(path), fileMap);
	}
}
