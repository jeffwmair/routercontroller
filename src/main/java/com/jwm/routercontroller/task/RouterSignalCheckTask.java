package com.jwm.routercontroller.task;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.jwm.routercontroller.service.RouterService;
import com.jwm.routercontroller.service.SignalService;
import com.jwm.routercontroller.signal.Signal;

public class RouterSignalCheckTask implements Task {

	private static Logger log = LogManager.getLogger(RouterSignalCheckTask.class);
	private SignalService signaller;
	private RouterService routerService;
	private RouterService routerServiceTemporal;

	public RouterSignalCheckTask(RouterService routerService, RouterService routerServiceTemporal, SignalService signaller) {
		this.signaller = signaller;
		this.routerService = routerService;
		this.routerServiceTemporal = routerServiceTemporal;
	}

	/**
	 * Check for a signal, update the router accordingly
	 */
	@Override
	public void execute() {

		log.info("Executing");
		Signal signal = signaller.getSignal();
		routerService.processSignal(signal);
		routerServiceTemporal.processSignal(signal);
		signaller.clearSignal();
	}

}
