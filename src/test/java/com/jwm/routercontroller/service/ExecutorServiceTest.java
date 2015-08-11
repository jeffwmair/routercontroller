package com.jwm.routercontroller.service;

import com.jwm.routercontroller.task.*;
import com.jwm.routercontroller.concurrency.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class ExecutorServiceTest {

	private ExecutorService service;

	@Before
	public void setup() {
		service = new ExecutorService();
	}

	/**
	 * Scheduling a second task should overwrite the first task if it hasn't completed
	 *
	 * Leaving this test disabled ordinarily because its slow.
	 */
	//@Test
	public void scheduleSecondTaskBeforeTheFirstOneRuns() {

		Task t1 = new MockTask("task1");
		Task t2 = new MockTask("task2");
		DelayedTaskExecutor executor1 = new DelayedTaskExecutor(t1, 5);
		DelayedTaskExecutor executor2 = new DelayedTaskExecutor(t2, 1);
		service.scheduleExecution(executor1);
		service.scheduleExecution(executor2);
		// need to keep the main thread alive till the test finishes

		int count = 0;
		while (true) {
			if (count++ > 10) break;
			try {
				System.out.print(".");
			Thread.sleep(1000);
			} catch(Exception ex) {}
		}
	}

	public class MockTask implements Task {
		private String msg;
		public MockTask(String msg) {
			this.msg = msg;
		}
		@Override
		public void execute() {
			System.out.println(msg);
		}
	}

}
