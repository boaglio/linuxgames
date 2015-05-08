package br.com.linuxgames.controller.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;

public class AgendadorTarefaThread extends Thread{

 	private static Logger logger = Logger.getLogger(AgendadorTarefaThread.class);

 	private static Scheduler scheduler;

 	@Override
 	public void run() {
 		try {
 	        logger.info("-------------- Inicializando ------------------- (ThreadID = " + this.getId() + ")");

 	        // Inicializar o Agendador (Scheduler)
 	        SchedulerFactory sf = new StdSchedulerFactory();
 	        scheduler = sf.getScheduler();


 	        logger.info("------- Agendando Jobs ----------------");

 	        SimpleTrigger triggerDiaria = new SimpleTrigger("triggerDiariaA1h",
                   null,
                   new Date(),
                   null,
                   SimpleTrigger.REPEAT_INDEFINITELY,
                   24L * 60L *
                   60L * 1000L);

 	        JobDetail postaNoticiasJob = new JobDetail("PostaNoticiasJob", Scheduler.DEFAULT_GROUP, PostaNoticiasJob.class);

 	        // Agentar tarefa.
 	        scheduler.scheduleJob(postaNoticiasJob, triggerDiaria);

 	        logger.info("------- Starting Scheduler ----------------");

 	        scheduler.start();

 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}


 	/**
 	 * Finalizar o Agendador de Tarefas
 	 * @author Ricardo
 	 * @since 1.0
 	 */
 	public void shutdownScheduler() {
 		try {
 			scheduler.shutdown();
 		} catch (SchedulerException e) {
 			e.printStackTrace();
 		}
 	}



 	public static void main(String[] args) {
 		new AgendadorTarefaThread().start();
 	}

 }
