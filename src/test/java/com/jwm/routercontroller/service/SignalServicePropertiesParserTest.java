package com.jwm.routercontroller.service;

import com.jwm.routercontroller.signal.*;
import java.util.Properties;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class SignalServicePropertiesParserTest {

	SignalServicePropertiesParser service;
	@Mock
	Properties props;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testForNoPropertiesGivesSignalNone() {
		when(props.isEmpty()).thenReturn(true);
		service = new SignalServicePropertiesParser(props);
		Signal sig = service.getSignal();
	}

	@Test
	public void testForPropertiesWithDuration() {
		when(props.isEmpty()).thenReturn(false);
		when(props.containsKey("duration.seconds")).thenReturn(true);
		when(props.getProperty("duration.seconds")).thenReturn("1");
		when(props.getProperty("newstate", "none")).thenReturn("enable");
		service = new SignalServicePropertiesParser(props);
		Signal sig = service.getSignal();
		Assert.assertEquals(SignalValue.enable, sig.getSignalValue());
	}

}
