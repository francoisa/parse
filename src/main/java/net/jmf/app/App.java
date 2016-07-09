package net.jmf.app;

import java.util.logging.Logger;

public class App {
	private static final Logger log = Logger.getLogger(App.class.toString());
	
	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tc %4$s - %2$s %5$s%6$s%n");		
	}
	
    public static void main( String[] args) {
    	if (args.length != 1) {
    		log.severe("Usage  : net.jmf.app.App <grammar>");
    		log.severe("Example: net.jmf.app.App src/resources/arithmetic.g4");
    		System.exit(-1);
    	}
        log.info("Parsing grammar: '" + args[0] + "'");
    }
}
