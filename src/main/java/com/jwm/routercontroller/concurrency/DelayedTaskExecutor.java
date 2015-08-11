package com.jwm.routercontroller.concurrency;

import com.jwm.routercontroller.task.*;

import java.util.Date;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DelayedTaskExecutor implements Runnable {

	private static Logger log = LogManager.getLogger(DelayedTaskExecutor.class);
	private Task task;
	private long whenToRun;
	private boolean cancelling;
	public DelayedTaskExecutor(Task task, long secondsFromNow) {
		this.task = task;
		this.whenToRun = (new Date().getTime()) + secondsFromNow * 1000;
		Date dateToRun = new Date();
		dateToRun.setTime(whenToRun);
		log.info("Setting task to run "+secondsFromNow+" seconds from now ("+dateToRun+")");
	}

	/**
	 * Cancel the task
	 */
	public void cancel() {
		log.info("Cancelling task");
		this.cancelling = true;
	}

	@Override
	public void run() {
		while((new Date().getTime()) < whenToRun) {
			sleep();
			if (cancelling) {
				return;
			} 
		}

		log.info("Running task " + task);
		task.execute();
	}

	private void sleep() {
		log.trace("sleeping...");
		try {
			Thread.sleep(1000);
		}
		catch(Exception ex) {}
	}
	
}
