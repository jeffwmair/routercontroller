package com.jwm.routercontroller.service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.util.*;

import com.jwm.routercontroller.signal.*;

public class SignalServiceFileImpl implements SignalService {

	private static Logger log = LogManager.getLogger(SignalServiceFileImpl.class);
	private String pathToFile;

	public SignalServiceFileImpl(String pathToFile) {
		Assert.hasLength(pathToFile);
		this.pathToFile = pathToFile;
	}

	/**
	 * Get the signal
	 */
	@Override
	public Signal getSignal() {

		log.info("Checking for signal in file '"+pathToFile+"'");

		InputStream is = null;
		try {
			is = new FileInputStream(pathToFile);
		}
		catch(Exception ex) {
			log.error(ex.getMessage(), ex);
			return new SignalNone();
		}

		// input stream is loaded now
		Properties prop = new Properties();
		try {
			prop.load(is);
			SignalServicePropertiesParser parser = new SignalServicePropertiesParser(prop);
			return parser.getSignal();
		}
		catch(Exception ex) {
			log.error(ex.getMessage(), ex);
			return new SignalNone();
		}
	}
}
