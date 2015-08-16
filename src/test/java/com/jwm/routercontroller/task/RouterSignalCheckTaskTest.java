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
	private RouterService routerServiceTemporal;
	@Mock 
	private SignalService signal;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new RouterSignalCheckTask(routerService, routerServiceTemporal, signal);
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
		verify(routerServiceTemporal).processSignal(noSignal);
		verify(signal).clearSignal();
	}

}
