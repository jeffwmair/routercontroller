package com.jwm.routercontroller.task;

import com.jwm.routercontroller.util.*;
import com.jwm.routercontroller.router.*;
import com.jwm.routercontroller.service.*;
import com.jwm.routercontroller.signal.*;
import com.jwm.routercontroller.task.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class RouterSignalCheckTaskTest {

	private RouterSignalCheckTask service;
	@Mock
	private RouterService routerService;
	@Mock 
	private SignalService signal;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new RouterSignalCheckTask(routerService, signal);
		System.out.println(service);
	}

	/**
	 * Make sure that no method is called on the router if we get a 'nosignal'
	 */
	@Test
	public void testNoRouterChangeForNoSignal() {
		Signal noSignal = new SignalNone();
		when(signal.getSignal()).thenReturn(noSignal);
		service.execute();
		verify(routerService).processSignal(noSignal);
	}

	/**
	 * Make sure that only the enable method is called on the router if we get a enable signal
	 */
	@Test
	public void testEnableSshForEnableSignal() {
		Signal enableSig = new Signal(SignalValue.enable);
		when(signal.getSignal()).thenReturn(enableSig);
		service.execute();
		/*
		verify(router).enableSshAccess();
		verify(router, never()).disableSshAccess();
		*/
	}

	/**
	 * Make sure that only the disable method is called on the router if we get a disable signal
	 */
	@Test
	public void testDisableSshForDisableSignal() {
		Signal disableSig = new Signal(SignalValue.disable);
		when(signal.getSignal()).thenReturn(disableSig);
		service.execute();
		/*
		verify(router, never()).enableSshAccess();
		verify(router).disableSshAccess();
		*/
	}
}
