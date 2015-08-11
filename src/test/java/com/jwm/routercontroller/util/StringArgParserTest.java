package com.jwm.routercontroller.util;

import org.junit.Test;
import org.junit.Assert;
import java.util.AbstractMap;

public class StringArgParserTest {

	StringArgParser parser;

	/**
	 * Can we make an instance?
	 */
	@Test
	public void newTest() {
		parser = new StringArgParser();
	}

	/**
	 * Does not accept a null argument
	 */
	@Test
	public void nullArgumentFailsTest() {
		parser = new StringArgParser();
		try {
			parser.parse(null);
			Assert.fail("should not reach here");
		}
		catch(Exception ex) {
		}

	}

	/**
	 * Does not accept string without '=' sign in it
	 */
	@Test
	public void noEqualsSignTest() {
		parser = new StringArgParser();
		try {
			parser.parse("no_equals_sign");
			Assert.fail("should not reach here");
		}
		catch(Exception ex) {
		}
	}
	
	/**
	 * Does not accept string that starts with '=' sign 
	 */
	@Test
	public void startsWithEqualsSignTest() {
		parser = new StringArgParser();
		try {
			parser.parse("=value");
			Assert.fail("should not reach here");
		}
		catch(Exception ex) {
		}
	}

	/**
	 * Basic test
	 */
	@Test
	public void containsCorrectFormatKeyValueTest() {
		parser = new StringArgParser();
		AbstractMap.SimpleEntry<String, String> parsed = parser.parse("mykey=myvalue");
		Assert.assertEquals("mykey", parsed.getKey());
		Assert.assertEquals("myvalue", parsed.getValue());
	}
	

}
