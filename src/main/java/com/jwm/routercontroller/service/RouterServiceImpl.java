package com.jwm.routercontroller.service;

import com.jwm.routercontroller.router.*;
import com.jwm.routercontroller.signal.*;

public class RouterServiceImpl implements RouterService {

	private RouterAdapter router;
	private ExecutorService executorService;
	public RouterServiceImpl(RouterAdapter router) {
		this.router = router;
		this.executorService = executorService;
	}

	/**
	 * Process a signal
	 */
	@Override
	public void processSignal(Signal signal) {
		SignalValue sigval = signal.getSignalValue();
		switch(sigval) {
			case none:
				// do nothin'
				break;
			case enable:
				router.enableSshAccess();
				break;
			case disable:
				router.disableSshAccess();
				break;
			default:
				throw new RuntimeException("Unexpected signal value: " + sigval);
		}
	}
}
