package br.com.linuxgames.model.dao;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.Novidade;

/**
 * Novidades agendadas
 *
 * @author Fernando Boaglio
 */
public class NovidadeAgendadaDAO extends AbstractDAO {

	private static Logger logger = Logger.getLogger(NovidadeAgendadaDAO.class);

	private static NovidadeAgendadaDAO instance = new NovidadeAgendadaDAO();

    /**
     * Singleton
     */
	public static NovidadeAgendadaDAO getInstance() {
		return instance;
	}

	/**
	 * Construtor privado
	 */
	private NovidadeAgendadaDAO () {
		setInsertSQL("novidadeAgendada.insere");
		setUpdateSQL("novidadeAgendada.atualiza");
		setDeleteSQL("novidadeAgendada.remove");
		setBuscaUmSQL("novidadeAgendada.buscaUm");
		setBuscaTodosSQL("novidadeAgendada.buscaTudo");
	};

	/*
	 * busca ultima noticia
	 */
	public Novidade buscaUltimaNoticia() throws SQLException {

		Novidade vo = new Novidade();
		Statement stmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
		logger.info("trazendo ultimas noticia...");

        try {
            conexao = dao.getConexao();

            // busca o SQL correto
            stmt = super.createStatement(conexao);

            // executa a query
            rs = stmt.executeQuery(super.getQuery("novidadeAgendada.buscaUltima"));

			while (rs.next()) {
				vo.setId(rs.getInt("id"));
				vo.setLink(rs.getString("link"));
				vo.setTexto(rs.getString("texto"));
			}
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO 1]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO1]:", e);
        } finally {
            close(rs);
            close(stmt);
            close(conexao);
        }
		return vo;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		 Novidade vo = (Novidade) object;
		 pstmt.setString(1,vo.getLink());
		 pstmt.setString(2,vo.getTexto());
		 return pstmt;
	}
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		Novidade vo = (Novidade) object;
		pstmt.setString(1,vo.getLink());
		pstmt.setString(2,vo.getTexto());
        pstmt.setInt(3,vo.getId());
        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	   Novidade vo = (Novidade) object;
	   pstmt.setInt(1,vo.getId());
	   return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		Novidade vo = new Novidade();
	    vo.setId(rset.getInt("id"));
	    vo.setLink(rset.getString("link"));
	    vo.setTexto(rset.getString("texto"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}