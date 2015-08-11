package com.jwm.routercontroller;

import com.jwm.routercontroller.util.StringArgParserService;
import com.jwm.routercontroller.router.*;
import com.jwm.routercontroller.task.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class App {

	private static Logger log = LogManager.getLogger(App.class);

	public App(StringArgParserService parserService, Task task) throws Exception { 

		log.info("STARTING APP...");

		while(true) {
			task.execute();	
			Thread.sleep(2000);
		}

	}
}
