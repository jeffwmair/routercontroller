package com.jwm.routercontroller.service;

import com.jwm.routercontroller.router.*;
import com.jwm.routercontroller.signal.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class RouterServiceImpl implements RouterService {

	private static Logger log = LogManager.getLogger(RouterServiceImpl.class);
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
		if (log.isDebugEnabled()) log.debug("processSignal for value:" + sigval);
		
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
