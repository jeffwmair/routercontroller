package com.jwm.routercontroller.service;

import com.jwm.routercontroller.router.*;
import com.jwm.routercontroller.concurrency.*;
import com.jwm.routercontroller.signal.*;
import com.jwm.routercontroller.task.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RouterServiceTemporal implements RouterService {

	private static Logger log = LogManager.getLogger(RouterServiceTemporal.class);
	private ExecutorService executorService;
	private RouterService routerService;
	public RouterServiceTemporal(ExecutorService executorService, RouterService routerService) {
		this.executorService = executorService;
		this.routerService = routerService;
	}

	/**
	 * Process a signal temporal signal
	 */
	@Override
	public void processSignal(Signal signal) {
		// do we need to schedule switching back to the previous state?
		if (!(signal instanceof SignalTemporary)) {
			return;
		}

		log.info("Processing a signal that will revert later");
		// proceed with scheduling signal processing
		SignalTemporary tempSignal = (SignalTemporary)signal;
		long secondsDelay = tempSignal.getSecondsUntilRevert();
		Signal revertSignal = new Signal(tempSignal.getPreviousSignalState());

		Task task = new ProcessSignalTask(revertSignal, routerService);
		DelayedTaskExecutor executor = new DelayedTaskExecutor(task, secondsDelay);
		executorService.scheduleExecution(executor);
		log.info("Execution has been scheduled");
	}

}
