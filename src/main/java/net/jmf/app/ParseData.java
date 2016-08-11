package net.jmf.app;

import java.util.Map;


public class ParseData {
	public ParseData(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public ParseData(Map<String, String> values) {
		this.values = values;
	}
	
	public Map<String, String> getTokens() {
		return values;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	private String errorMessage;
	private Map<String, String> values;
}
