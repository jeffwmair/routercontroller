package com.jwm.routercontroller.service;

import com.jwm.router.test.BaseTest;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import com.jwm.routercontroller.signal.*;

public class SignalServiceFileImplTest extends BaseTest {
	private SignalServiceFileImpl signal;

	@Test
	public void systemTest() {
		signal = new SignalServiceFileImpl(getSignalFile());
		signal.getSignal();
	}

}
