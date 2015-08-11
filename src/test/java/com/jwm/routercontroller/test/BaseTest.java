package com.jwm.routercontroller.test;

import org.junit.BeforeClass;
import java.util.Properties;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;

public class BaseTest {

	private final static String propsFilename = "unittest.properties";
	private static String ip = "";
	private static String pass = "";
	private static String user = "";
	private static String signalFile = "";

	@BeforeClass
	public static void classsetup() {

		Properties props = new Properties();
		InputStream is = ClassLoader.getSystemResourceAsStream(propsFilename);
		try {
			  props.load(is);
			  ip = props.getProperty("routerip");
			  user = props.getProperty("routeruser");
			  pass = props.getProperty("routerpass");
			  pass = props.getProperty("signalfile");
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to load properties file: '"+propsFilename+"'");
	  }
	}

	protected String getRouterUser() {
		return BaseTest.user;	
	}
	protected String getRouterPass() {
		return BaseTest.pass;	
	}
	protected String getRouterIp() {
		return BaseTest.ip;	
	}
	protected String getSignalFile() {
		return BaseTest.signalFile;	
	}


}
