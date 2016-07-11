package net.jmf.app;

import java.util.Map;
import java.util.TreeMap;


public class ParseData {
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private String errorMessage;
	private Map<String, String> tokens;
	
	public ParseData() {
		tokens = new TreeMap<String, String>();
	}
	
	public Map<String, String> getTokens() {
		return tokens;
	}
	
	public void addToken(String token, String value) {
		tokens.put(token, value);
	}
}
