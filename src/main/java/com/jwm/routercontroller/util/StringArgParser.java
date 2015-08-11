package com.jwm.routercontroller.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import java.util.AbstractMap;

public class StringArgParser {

	private static Logger LOG = LogManager.getLogger(StringArgParser.class);

	public AbstractMap.SimpleEntry<String,String> parse(String arg) {
		Assert.notNull(arg, "Must provide arg");
		Assert.isTrue(arg.contains("="), "Must contain '=' character in the argument");
		Assert.isTrue(!arg.startsWith("="), "Cannot start with '=' character");

		String[] keyvalSplit = arg.split("=");
		return new AbstractMap.SimpleEntry<String, String>(keyvalSplit[0], keyvalSplit[1]);
	}

}
