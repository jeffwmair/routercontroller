package com.jwm.routercontroller.service;

import com.jwm.routercontroller.task.*;
import com.jwm.routercontroller.concurrency.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ExecutorService {
	private static Logger log = LogManager.getLogger(ExecutorService.class);
	private Thread thread;
	private DelayedTaskExecutor executor;

	/**
	 * Schedule an execution
	 */
	public void scheduleExecution(DelayedTaskExecutor newExecutor) {

		log.info("scheduleExecution:" + newExecutor);

		// if there is already a task scheduled, cancel it and join the thread
		if (this.executor != null) {
			this.executor.cancel();
			try {
				log.debug("waiting to join thread...");
				thread.join();
				log.debug("Joined thread");
			} catch (InterruptedException ex) {
				log.error("Failed to join thread:" + ex.getMessage(), ex);	
			}
		}

		// should be no active thread now; so make a new one
		this.executor = newExecutor;
		thread = new Thread(newExecutor);
		thread.start();
		log.info("Started new thread for delayed execution");
	}
}
