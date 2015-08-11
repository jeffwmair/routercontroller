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
import com.jwm.routercontroller.concurrency.*;

public class RouterServiceTemporalTest {

	private RouterServiceTemporal service;
	@Mock
	private RouterService routerService;
	@Mock
	private ExecutorService executorService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void revertToDisabledThroughTemporalService() {

		SignalTemporary sig = mock(SignalTemporary.class);
		when(sig.getSecondsUntilRevert()).thenReturn(1L);
		when(sig.getPreviousSignalState()).thenReturn(SignalValue.disable);
		service = new RouterServiceTemporal(executorService, routerService);
		service.processSignal(sig);

		verify(sig).getSecondsUntilRevert();
		verify(sig).getPreviousSignalState();
		verify(executorService).scheduleExecution((DelayedTaskExecutor)anyObject());
	}
}
