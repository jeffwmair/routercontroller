package com.jwm.routercontroller.service;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import com.jwm.routercontroller.router.*;
import com.jwm.routercontroller.signal.*;

public class RouterServiceImplTest {

	private RouterService service;
	@Mock
	private RouterAdapter router;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new RouterServiceImpl(router);
	}

	@Test
	public void processEnableForTemporaryPeriodSignal() {
		Signal sig = mock(SignalTemporary.class);
		when(sig.getSignalValue()).thenReturn(SignalValue.enable);
		service.processSignal(sig);
		verify(sig).getSignalValue();
		verify(router).enableSshAccess();
	}

	@Test
	public void processNoActionSignal() {
		Signal sig = new SignalNone();
		service.processSignal(sig);
		verify(router, never()).enableSshAccess();
		verify(router, never()).disableSshAccess();
	}

	@Test
	public void processEnableSshSignal() {
		Signal sig = new Signal(SignalValue.enable);
		service.processSignal(sig);
		verify(router).enableSshAccess();
		verify(router, never()).disableSshAccess();
	}

	@Test
	public void processDisableSshSignal() {
		Signal sig = new Signal(SignalValue.disable);
		service.processSignal(sig);
		verify(router, never()).enableSshAccess();
		verify(router).disableSshAccess();
	}

}
