package br.com.linuxgames.model.dao.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.linuxgames.util.Constantes;

public abstract class AbstractDAO {

	private Logger logger = Logger.getLogger(AbstractDAO.class);

	private String insertSQL;
	private String updateSQL;
	private String deleteSQL;
	private String buscaUmSQL;
	private String buscaTodosSQL;
	private String buscaTodosComFiltroSQL;

	private static QueryManager queryManager = QueryManager.getInstance();

	// OPERACOES CRUD

	/**
	 * Seta o PreparedStatement do INSERT
	 * @param object
	 * @param pstmt
	 */
    public abstract PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt )  throws SQLException;

	/**
	 * adiciona uma objeto qualquer uma vez definido o insertSQL
	 */
	public void adiciona(Object object) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   int linhasAfetadas = 0 ;
       try {
    	 conexao = dao.getConexao();
         pstmt = setPreparedStatementInsert(object,createPreparedStatement(conexao,this.getInsertSQL()));
         linhasAfetadas = pstmt.executeUpdate();
         logger.info("[INSERT DE "+this.getClass()+"]: "+linhasAfetadas+" linha(s) afetada(s)");
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO INSERT]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO adiciona "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	}

	/**
	 * adiciona uma objeto qualquer uma vez definido o insertSQL
	 */
	public int adicionaVoltandoID(Object object) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   int id = 0 ;
       try {
    	 conexao = dao.getConexao();
         pstmt = setPreparedStatementInsert(object,createPreparedStatement(conexao,this.getInsertSQL()));
         pstmt.executeUpdate();

         // pega o ultimo ID cadastrado

 		switch (Constantes.TIPO_DE_CONEXAO_USADA) {

 		 case Constantes.USANDO_JBDC_MYSQL:

 	  	    rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()");

 			break;

 		 case Constantes.USANDO_JBDC_PSQL:

 			rs = pstmt.executeQuery("SELECT currval()");

 			break;
 		}

         while (rs.next()) {
        	 id = rs.getInt(1);
         }

         logger.info("[INSERT DE "+this.getClass()+"]");
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO INSERT]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO adicionaVoltandoID "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	    return id;
	}
	/**
	 * Seta o PreparedStatement do UPDATE
	 * @param object
	 * @param pstmt
	 */
    public abstract PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt )  throws SQLException;


	/**
	 * altera um objeto qualquer uma vez definido o updateSQL
	 */
	public void atualiza(Object object) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   int linhasAfetadas = 0 ;
       try {
         conexao = dao.getConexao();
         pstmt = setPreparedStatementUpdate(object,createPreparedStatement(conexao,this.getUpdateSQL()));
         linhasAfetadas = pstmt.executeUpdate();
         logger.info("[UPDATE DE "+this.getClass()+"]: "+linhasAfetadas+" linha(s) afetada(s)");
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO UPDATE]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO atualiza "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	}

	/**
	 * remove um objeto qualquer uma vez definido o DELETE
	 */
	public void remove(Object object) throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conexao = null;
	   ResultSet rs = null;
	   int linhasAfetadas = 0 ;
       try {
         conexao = dao.getConexao();
         pstmt = setPreparedStatementBuscaUm(object,createPreparedStatement(conexao,this.getDeleteSQL()));
         linhasAfetadas = pstmt.executeUpdate();
         logger.info("[DELETE DE "+this.getClass()+"]: "+linhasAfetadas+" linha(s) afetada(s)");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO DELETE]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO remove "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	}

	/**
	 * Seta o PreparedStatement do DELETE
	 * @param object
	 * @param pstmt
	 */
    public abstract PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt )  throws SQLException;

    /**
     * Seta o VO com o result set do banco
     * @param rset
     * @return
     * @throws SQLException
     */
    protected abstract Object setVO(ResultSet rset) throws SQLException;

    /**
	 * busca um objeto
	 */
	public Object buscaUm(Object object) throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conexao = null;
	   ResultSet rs = null;
	   Object vo = new Object();
	   logger.info("[trazendo o objeto...]:");
       try {
         conexao = dao.getConexao();
         pstmt = setPreparedStatementBuscaUm(object,createPreparedStatement(conexao,this.getBuscaUmSQL()));
         rs = pstmt.executeQuery();
         while (rs.next()) {
            vo = setVO(rs);
         }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO BUSCA UM ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO buscaUm "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	    return vo;
	}


	/**
	 * busca tudo
	 */
	@SuppressWarnings("rawtypes")
	public Collection buscaTodos() throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		Statement stmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            stmt = createStatement(conexao);
            rs = stmt.executeQuery(getQuery(getBuscaTodosSQL()));
            while (rs.next()) {
              retorno.add(setVO(rs));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA TODOS]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO buscaTodos "+ this.getClass() +" ]:", e);
        } finally {
            close(rs);
            close(stmt);
            close(conexao);
        }
		return (List) retorno;
	}


	/**
	 * Seta o PreparedStatement do DELETE
	 * @param object
	 * @param pstmt
	 */
    public abstract PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt )  throws SQLException;


	/**
	 * busca tudo com filtro
	 */
	@SuppressWarnings("rawtypes")
	public Collection buscaTodos(Object object) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista filtrada...]:");
        try {
            conexao = dao.getConexao();
            pstmt = setPreparedStatementBuscaTodosComFiltro(object,createPreparedStatement(conexao,this.getQuery(this.getBuscaTodosComFiltroSQL())));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retorno.add(setVO(rs));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA TODOS COM FILTRO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO buscaTodos 2 "+ this.getClass() +" ]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
		return (List) retorno;
	}

	// manipula as conexoes
	public void close(Connection conexao) {
		try {
			if (conexao!=null)
				conexao.close();
		} catch (Exception e) {
		}
	}

	public void close(Statement stmt) {
		try {
			if (stmt!=null)
			 stmt.close();
		} catch (Exception e) {
		}
	}

	public void close(ResultSet rs) {
		try {
			if (rs!=null)
			 rs.close();
		} catch (Exception e) {
		}
	}

	/**
	 * cria a PreparedStatement para a query informada
	 * @param conexao
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public final PreparedStatement createPreparedStatement(Connection conexao,
			String queryName) throws SQLException {

		String query = queryManager.getQuery(queryName);
		logger.debug(queryName + " = " + query);

		return conexao.prepareStatement(query);
	}


	/**
	 * cria a PreparedStatement para a query informada
	 * @param conexao
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public final PreparedStatement createPreparedStatement(Connection conexao,
			String queryName,String adendo) throws SQLException {

		String query = queryManager.getQuery(queryName)+adendo;
		logger.debug(queryName + " = " + query);

		return conexao.prepareStatement(query);
	}

	/**
	 * cria o Statement
	 * @param conexao
	 * @return
	 * @throws SQLException
	 */
	public final Statement createStatement(Connection conexao) throws SQLException {
		return conexao.createStatement();
	}

	/**
	 * Retorna a Query requisitada
	 * @param queryName
	 * @return
	 */
	public final String getQuery(String queryName) {
		return queryManager.getQuery(queryName);
	}


	// getters and setters

	public String getDeleteSQL() {
		return deleteSQL;
	}

	public void setDeleteSQL(String deleteSQL) {
		this.deleteSQL = deleteSQL;
	}

	public String getInsertSQL() {
		return insertSQL;
	}

	public void setInsertSQL(String insertSQL) {
		this.insertSQL = insertSQL;
	}

	public String getUpdateSQL() {
		return updateSQL;
	}

	public void setUpdateSQL(String updateSQL) {
		this.updateSQL = updateSQL;
	}

	public String getBuscaTodosComFiltroSQL() {
		return buscaTodosComFiltroSQL;
	}

	public void setBuscaTodosComFiltroSQL(String buscaTodosComFiltroSQL) {
		this.buscaTodosComFiltroSQL = buscaTodosComFiltroSQL;
	}

	public String getBuscaTodosSQL() {
		return buscaTodosSQL;
	}

	public void setBuscaTodosSQL(String buscaTodosSQL) {
		this.buscaTodosSQL = buscaTodosSQL;
	}

	public String getBuscaUmSQL() {
		return buscaUmSQL;
	}

	public void setBuscaUmSQL(String buscaUmSQL) {
		this.buscaUmSQL = buscaUmSQL;
	}

}
