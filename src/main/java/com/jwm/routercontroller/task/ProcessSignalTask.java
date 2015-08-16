package com.jwm.routercontroller.task;

import com.jwm.routercontroller.service.*;
import com.jwm.routercontroller.signal.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Processes a signal through a router service
 */
public class ProcessSignalTask implements Task {
	private RouterService service;
	private Signal signal;
	private static Logger log = LogManager.getLogger(ProcessSignalTask.class);

	public ProcessSignalTask(Signal signal, RouterService service) {
		this.service = service;
		this.signal = signal;
	}

	@Override
	public void execute() {
		if (log.isDebugEnabled()) {
			log.debug("Processing signal:"+signal);
		}
		service.processSignal(signal);
	}
}
