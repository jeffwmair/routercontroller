package com.jwm.routercontroller.util;

import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;

/**
 * Parses '=' separated key-value pairs
 */
public class StringArgParserService {

	private static Logger LOG = LogManager.getLogger(StringArgParserService.class);
	private StringArgParser parser;
	private String[] args;
	private Map<String, String> parsedArgs;

	public StringArgParserService(String[] args, StringArgParser parser) {
		Assert.notNull(parser, "Must provide a parser");
		Assert.notNull(args, "Must provide a parser");
		this.parser = parser;
		this.args = args;
		parseArgs();
	}

	/**
	 * Get the parsed args
	 */
	public Map<String,String> getArgs() {
		return parsedArgs;
	}

	/**
	 * Parse the args
	 */
	private void parseArgs() {
		parsedArgs = new HashMap<String,String>();
		for(String arg : args) {
			AbstractMap.SimpleEntry<String, String> parsed = parser.parse(arg);
			Assert.isTrue(!parsedArgs.containsKey(parsed.getKey()), "The argument with key '"+parsed.getKey()+"' has been provided multiple times");
			parsedArgs.put(parsed.getKey(), parsed.getValue());
		}
	}
}
