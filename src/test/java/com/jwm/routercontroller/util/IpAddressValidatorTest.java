package com.jwm.routercontroller.util;

import org.junit.Test;
import org.junit.Assert;

public class IpAddressValidatorTest { 

	private IpAddressValidator validator = new IpAddressValidator();

	/**
	 * Simple real ip addresses should be fine
	 */
	@Test
	public void basicIpTest() {
		Assert.assertEquals(true, validator.validate("192.168.1.1"));
		Assert.assertEquals(true, validator.validate("192.168.100.1"));
		Assert.assertEquals(true, validator.validate("192.168.1.100"));
		Assert.assertEquals(true, validator.validate("1.1.100.1"));
		Assert.assertEquals(true, validator.validate("1.1.1.100"));
		Assert.assertEquals(true, validator.validate("1.1.100.100"));
	}

	/**
	 * null should fail
	 */
	@Test
	public void nullIpIsBad() {
		Assert.assertEquals(false, validator.validate(null));
	}

	/**
	 * Bad IP addresses that should fail
	 */
	@Test
	public void badIpsTest() {
		Assert.assertEquals(false, validator.validate("x.168.1.1"));
		Assert.assertEquals(false, validator.validate("168.1.1"));
	}
}
