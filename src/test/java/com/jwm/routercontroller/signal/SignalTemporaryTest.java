package com.jwm.routercontroller.signal;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class SignalTemporaryTest {

	@Test
	public void signalFromDisabledToEnabled() {
		SignalTemporary sig = new SignalTemporary(SignalValue.disable, 1);
		Assert.assertEquals(SignalValue.enable, sig.getPreviousSignalState());
	}

	@Test
	public void signalFromEnabledToDisabled() {
		SignalTemporary sig = new SignalTemporary(SignalValue.enable, 1);
		Assert.assertEquals(SignalValue.disable, sig.getPreviousSignalState());
	}

}
