package com.jwm.routercontroller.router;

import com.jwm.routercontroller.util.*;
import com.jwm.routercontroller.test.BaseTest;
import java.util.Properties;
import java.io.FileReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.File;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class RouterAdapterHttpImplTest extends BaseTest {

	private RouterAdapterHttpImpl adapter;

	@Mock
	private IpAddressValidator ipvalidator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(ipvalidator.validate(anyString())).thenReturn(true);
		adapter = new RouterAdapterHttpImpl(getRouterIp(), getRouterUser(), getRouterPass(), ipvalidator);
	}

	/**
	 * Should validate the ipaddress
	*/
	@Test
	public void ensureIpValidationTest() {
		verify(ipvalidator).validate(anyString());
	}

	/**
	 * Actually enable ssh
	 */
	@Test
	public void enableSshLiveTest() {
		//adapter.enableSshAccess();
	}

	/**
	 * Actually disable ssh
	 */
	@Test
	public void disableSshLiveTest() {
		//adapter.disableSshAccess();
	}
}
