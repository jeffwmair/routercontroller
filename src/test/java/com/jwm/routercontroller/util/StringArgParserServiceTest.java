package com.jwm.routercontroller.util;

import java.util.AbstractMap;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class StringArgParserServiceTest {

	private StringArgParserService service;
	@Mock
	private StringArgParser parser;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(parser.parse("arg1")).thenReturn(new AbstractMap.SimpleEntry<String, String>("arg1", ""));
		when(parser.parse("arg2")).thenReturn(new AbstractMap.SimpleEntry<String, String>("arg2", ""));
	}

	/**
	 * Does not accept a null argument
	 */
	@Test
	public void nullArgumentFailsTest() {
		try {
			service = new StringArgParserService(new String[] {"arg1=foo", "arg2=bar"}, null);
			Assert.fail("should not reach here");
		}
		catch(Exception ex) {
		}
		try {
			service = new StringArgParserService(null, parser);
			Assert.fail("should not reach here");
		}
		catch(Exception ex) {
		}
		try {
			service = new StringArgParserService(null, null);
			Assert.fail("should not reach here");
		}
		catch(Exception ex) {
		}

	}

	/**
	 * middle of the road test; make sure that the service asks the parser to parse each argument
	 */
	@Test
	public void basicTest() {
		String[] args = { "arg1", "arg2" };
		service = new StringArgParserService(args, parser);
		service.getArgs();
		verify(parser).parse("arg1");
		verify(parser).parse("arg2");
	}

	/**
	 * If the same arg is provided multiple times, an exception should be thrown 
	 */
	@Test
	public void duplicateArgsErrorTest() {
		try {
			String[] args = { "arg1=", "arg1=" };
			service = new StringArgParserService(args, parser);
			service.getArgs();
			Assert.fail("should not reach here");
		}
		catch(Exception ex) {
		}
	}
}
