/*
 * Criado por Fernando Boaglio 
 */
package br.com.linuxgames.util;

import java.util.Properties;

import org.apache.log4j.Logger;

public class PropManager {
	
	private static Logger logger = Logger.getLogger(PropManager.class);

	protected static PropManager propManager = new PropManager();

	protected Properties propriedades = new Properties();

	/*
	 * implementa o SingleTon
	 */
	private PropManager() {
		// busca o valor da variavel de ambiente
		// le as propriedades e guarda!
		logger.info("buscando propriedades...");
		try {
			propriedades.load(getClass().getResourceAsStream(Constantes.LINUXGAMES_PROPERTIES_FILE));
			logger.info(propriedades.size() + " propriedades lidas!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PropManager getInstance() {
		return propManager;
	}

	public String getProperty(String name) {
		return propriedades.getProperty(name);
	}
}
