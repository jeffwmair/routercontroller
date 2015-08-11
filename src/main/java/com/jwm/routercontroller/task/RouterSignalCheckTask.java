package com.jwm.routercontroller.task;

import com.jwm.routercontroller.router.*;
import com.jwm.routercontroller.signal.*;
import com.jwm.routercontroller.service.*;

import org.springframework.util.Assert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RouterSignalCheckTask implements Task {

	private static Logger log = LogManager.getLogger(RouterSignalCheckTask.class);
	private SignalService signaller;
	private RouterService routerService;

	public RouterSignalCheckTask(RouterService routerService, SignalService signaller) {
		this.signaller = signaller;
		this.routerService = routerService;
	}

	/**
	 * Check for a signal, update the router accordingly
	 */
	@Override
	public void execute() {

		log.info("Executing");
		Signal signal = signaller.getSignal();
		routerService.processSignal(signal);
	}

}
