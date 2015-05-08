package br.com.linuxgames.model.dao.core;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.PropManager;

/**
 * Faz o mapeamento objeto relacional das tabelas do sistema.
 *
 * @author Fernando Boaglio
 * @version 1.0 - 18/11/2004
 */
public class DAOManager  {

	private static Logger logger = Logger.getLogger(DAOManager.class);
	private DataSource ds = null;
	private Connection conexao;
	private static DAOManager instance = new DAOManager();
	private static String jdbcURL;

	/**
	 *Construtor privado que busca o data source jdbc/gpt do servidor de aplicacao.
	 * @throws SQLException
	 */
	private DAOManager() {


		switch (Constantes.TIPO_DE_CONEXAO_USADA) {

		 case Constantes.USANDO_JBDC_MYSQL:

			 logger.info(" usando JDBC com MySQL!");
			 PropManager propriedades = PropManager.getInstance();

			 String bancoMySQL = propriedades.getProperty("bancoMySQL");
			 String host = propriedades.getProperty("mysql.host");
			 String nomeDoBanco = propriedades.getProperty("mysql.nomeDoBanco");
			 String usuario = propriedades.getProperty("mysql.usuario");
			 String senha = propriedades.getProperty("mysql.senha");
			 jdbcURL = "jdbc:mysql://" + host + "/" + nomeDoBanco + "?user="	+ usuario + "&password=" + senha;

			 logger.info(" Habilitado para MySQL ? " + bancoMySQL);
			 if (bancoMySQL.equals("sim")) {
				buscaConexaoMySQL();
			 }

			break;

		 case Constantes.USANDO_JBDC_PSQL:

			 logger.info(" usando JDBC com PostgreSQL!");
			 PropManager propriedades1 = PropManager.getInstance();
 			 String bancoPSQL = propriedades1.getProperty("bancoPSQL");
			 String host1 = propriedades1.getProperty("psql.host");
			 String nomeDoBanco1 = propriedades1.getProperty("psql.nomeDoBanco");
			 String usuario1 = propriedades1.getProperty("psql.usuario");
			 String senha1 = propriedades1.getProperty("psql.senha");
			 jdbcURL = "jdbc:mysql://" + host1 + "/" + nomeDoBanco1 + "?user="	+ usuario1 + "&password=" + senha1;

			 logger.info(" Habilitado para PostgreSQL ? " + bancoPSQL);
			 if (bancoPSQL.equals("sim")) {
				buscaConexaoPostgreSQL();
			 }

			break;
		case Constantes.USANDO_DATA_SOURCE:

			 logger.info("usando DataSource:"+Constantes.JNDI_DATASOURCE);

				try {
		            Context ctx = new InitialContext();
		            this.ds = (DataSource) ctx.lookup("java:comp/env/" + Constantes.JNDI_DATASOURCE);
		 		} catch(NamingException e) {
		 			logger.fatal("Nao abriu o contexto do DS do Tomcat, tentando pelo datasource local", e);
		 			// tentando pelo datasource local
		 			this.ds = LinuxGamesDataSource.getDataSource();
//					throw new RuntimeException(e);
		 		}

				break;
			}
		}


	private void buscaConexaoMySQL() {
		try {
			// MySQL
			Class.forName("com.mysql.jdbc.Driver");
			logger.debug(" classe MySQL JDBC encontrada.");
		} catch (ClassNotFoundException e) {
			logger.fatal("Nao achou a classe:", e);
			throw new RuntimeException(e);
		}

		try {
			// jdbc:mysql://host/BANCO_DE_DADOS?user=root&password=root
			conexao = DriverManager.getConnection(jdbcURL+"&useUnicode=true&characterEncoding=ISO-8859-1");
			 setConexao(conexao);
		} catch (SQLException e1) {
			logger.fatal("Nao abriu a conexao na url=" + jdbcURL + ":", e1);
			throw new RuntimeException(e1);
		}
	}


	private void buscaConexaoPostgreSQL() {


		try {
			 // PSQL
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			logger.fatal("Nao achou a classe:", e);
			throw new RuntimeException(e);
		}


		try {

			if (Constantes.USANDO_HOST_HEROKU) {

				URI dbUri;
				try {

					dbUri = new URI(System.getenv("DATABASE_URL"));
//					logger.info("dbUri="+dbUri.toString());
					String username = dbUri.getUserInfo().split(":")[0];
					String password = dbUri.getUserInfo().split(":")[1];
					String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath() + ":" + dbUri.getPort();
//					logger.info("username="+username);
//					logger.info("password="+password);
//					logger.info("host="+dbUri.getHost());
//					logger.info("port="+dbUri.getPort());
//					logger.info("path="+dbUri.getPath());
//					logger.info("dbUrl="+dbUrl);
					conexao =  DriverManager.getConnection(dbUrl, username, password);

				} catch (URISyntaxException e) {

					logger.error("erro ao buscar conexao no Heroku",e);

				}

			} else {

			  conexao = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/lg", "postgres","patterns73");

			}

			setConexao(conexao);
		} catch (SQLException e1) {
			logger.fatal("Nao abriu a conexao na url=" + jdbcURL + ":", e1);
			throw new RuntimeException(e1);
		}
	}


	/**
	 * Cria o PreparedStatement conforme a Query informada
	 */
	public PreparedStatement createPreparedStatement(Connection conn,
			String queryName) throws SQLException {
		QueryManager queryManager = QueryManager.getInstance();
		String query = queryManager.getQuery(queryName);
		logger.debug(queryName + " = " + query);
		return conn.prepareStatement(query);
	}

	/**
	 * Cria o Statement conforme a Query informada
	 */
	public Statement createStatement(Connection conn,String queryName) throws SQLException {
		QueryManager queryManager = QueryManager.getInstance();
		String query = queryManager.getQuery(queryName);
		logger.debug(queryName + " = " + query);
		return conn.createStatement();
	}

	/**
	 * Busca o SQL
	 */
	public String getQuery(String queryName) {
		QueryManager queryManager = QueryManager.getInstance();
		return queryManager.getQuery(queryName);
	}

	/**
	 * Buscando conexao do BANCO
	 */
	public Connection getConexao() {
		try {
			switch (Constantes.TIPO_DE_CONEXAO_USADA) {

			 case Constantes.USANDO_DATA_SOURCE:  return this.ds.getConnection();

			 case Constantes.USANDO_JBDC_MYSQL :
				 buscaConexaoMySQL();
				 return conexao;

			 case Constantes.USANDO_JBDC_PSQL :
				 buscaConexaoPostgreSQL();
				 return conexao;

		     default:  return conexao;
			}
		}
		catch (Exception e) {
			logger.error("Erro ao buscar Conexao:",e);
		}
		return conexao;
	}

	/**
	 * Configurando a conexao do BANCO
	 */
	public void setConexao(Connection conexao) {
		logger.info("setando conexao para:" + conexao);
		this.conexao = conexao;
	}

    /**
     * implementa o singleton
     */
	public static DAOManager getInstance()   {
    	return instance;
    }


}