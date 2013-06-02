package br.com.linuxgames.model.dao.core;

import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.linuxgames.util.Constantes;

public class QueryManager {

	private static Logger logger = Logger.getLogger(QueryManager.class);

	protected static QueryManager queryManager = new QueryManager();

	protected Properties queries = new Properties();

	/*
	 * implementa o SingleTon
	 */
	private QueryManager() {
		// busca o valor da variavel de ambiente
		// le as sqls e guarda!
		logger.info(" Buscando os comandos SQL...");
		try {
			logger.info(" carregando os comandos SQL de LG...");
			queries.load(getClass().getResourceAsStream(Constantes.LG_SQL_PROPERTIES_FILE));
			logger.info(" carregando os comandos SQL de TOE...");
			queries.load(getClass().getResourceAsStream(Constantes.TOE_SQL_PROPERTIES_FILE));
			logger.info(queries.size() + " comandos SQL lidos!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static QueryManager getInstance() {
		return queryManager;
	}

	public String getQuery(String name) {
		String sql = queries.getProperty(name);
		logger.debug("SQL["+name+"] = ["+sql+"]");
		return sql;
	}
}
