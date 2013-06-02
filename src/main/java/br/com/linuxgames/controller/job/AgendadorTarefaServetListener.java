package br.com.linuxgames.controller.job;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class AgendadorTarefaServetListener implements ServletContextListener {

 	private static Logger logger = Logger.getLogger(AgendadorTarefaServetListener.class);

 	private AgendadorTarefaThread thread;

 	public void contextInitialized(ServletContextEvent event) {
 		logger.info("-------- contextInitialized -------- ");
 		thread = new AgendadorTarefaThread();
 		thread.start();
 	}

 	public void contextDestroyed(ServletContextEvent event) {
 		logger.info("-------- contextDestroyed -------- ");
 		thread.shutdownScheduler();
 	}


}
