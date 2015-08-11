package com.jwm.routercontroller.task;

import com.jwm.routercontroller.service.*;
import com.jwm.routercontroller.signal.*;

/**
 * Processes a signal through a router service
 */
public class ProcessSignalTask implements Task {
	private RouterService service;
	private Signal signal;

	public ProcessSignalTask(Signal signal, RouterService service) {
		this.service = service;
		this.signal = signal;
	}

	@Override
	public void execute() {
		service.processSignal(signal);
	}
}
