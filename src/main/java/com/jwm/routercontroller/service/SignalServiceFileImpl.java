package com.jwm.routercontroller.service;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;

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
		File f = new File(pathToFile);

		if (!f.exists()) {
			if (log.isDebugEnabled()) log.debug("No signal found");
			return new SignalNone();
		}


		/* read the file content */
		/*
		try(BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
			String line = br.readLine();

		}
		*/

		log.warn("NOT IMPLEMENTED, SO RETURNING SIGNALNONE");
		return new SignalNone();
	}
}
