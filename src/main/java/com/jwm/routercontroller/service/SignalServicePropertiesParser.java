package com.jwm.routercontroller.service;

import com.jwm.routercontroller.signal.*;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class SignalServicePropertiesParser {

	private static Logger log = LogManager.getLogger(SignalServicePropertiesParser.class);
	private Properties props;
	public SignalServicePropertiesParser(Properties props) {
		this.props = props;
	}

	/**
	 * Get the signal from the properties file
	 */
	public Signal getSignal() {
		
		if (props.isEmpty()) {
			log.warn("No properties found");
			return new SignalNone();
		}

		SignalValue newState = parseSignalValue(props.getProperty("state", "none"));

		if (props.containsKey("duration")) {
			try {
				int duration = Integer.parseInt(props.getProperty("duration"));
				return new SignalTemporary(newState, duration);
			}
			catch(Exception ex) {
				log.error("Failed to parse duration property:"+ex.getMessage(), ex);
			}
		}

		return new Signal(newState);
	}

	/**
	 * Parse the signal value from the file
	 */
	private SignalValue parseSignalValue(String signalVal) {

		if (signalVal.equals("enable")) {
			return SignalValue.enable;
		}
		else if (signalVal.equals("disable")) {
			return SignalValue.disable;
		}
		else if (signalVal.equals("none")) {
			return SignalValue.none;
		} 

		throw new RuntimeException("Unknown signal value:" + signalVal);
	}
}
