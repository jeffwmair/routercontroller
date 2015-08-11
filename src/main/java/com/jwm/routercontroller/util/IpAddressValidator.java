package com.jwm.routercontroller.util;

import java.util.regex.Pattern;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class IpAddressValidator { 

	/**
	 * Courtesy of stackoverflow: http://stackoverflow.com/questions/5667371/validate-ipv4-address-in-java
	 */
	private final Pattern PATTERN = Pattern.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");

	/**
	 * Validates that it is indeed a valid IP address
	 */
	public boolean validate(String ipaddress) {
		if (ipaddress == null) return false;
		return PATTERN.matcher(ipaddress).matches();
	}

}
