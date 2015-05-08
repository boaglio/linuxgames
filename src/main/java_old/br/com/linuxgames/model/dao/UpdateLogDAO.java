package br.com.linuxgames.model.dao;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.UpdateLogVO;

/**
 * Updatelogs
 *
 * @author Fernando Boaglio
 */
public class UpdateLogDAO extends AbstractDAO {

	private static UpdateLogDAO instance = new UpdateLogDAO();
	private static Logger logger = Logger.getLogger(UpdateLogDAO.class);
    /**
     * Singleton
     */
	public static UpdateLogDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private UpdateLogDAO () {
		setInsertSQL("updatelog.insere");
		setUpdateSQL("updatelog.atualiza");
		setDeleteSQL("updatelog.remove");
		setBuscaUmSQL("updatelog.buscaUm");
		setBuscaTodosSQL("updatelog.buscaTudo");
	};

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		UpdateLogVO vo = (UpdateLogVO) object;
        pstmt.setTimestamp(1,new Timestamp( vo.getData().getTime()));
		pstmt.setString(2,vo.getDescricao());
		return pstmt;
	}
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		UpdateLogVO vo = (UpdateLogVO) object;
        pstmt.setTimestamp(1, new Timestamp(vo.getData().getTime()));
		pstmt.setString(2,vo.getDescricao());
		pstmt.setInt(3,vo.getId());
        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	    UpdateLogVO vo = (UpdateLogVO) object;
	    pstmt.setInt(1,vo.getId());
	    return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		UpdateLogVO vo = new UpdateLogVO();
	    vo.setId(rset.getInt("id"));
	    vo.setData(rset.getDate("data"));
	    vo.setDescricao(rset.getString("descricao"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

	/*
	 * busca ultimas atualizacoes
	 */
	public Collection<UpdateLogVO> buscaUltimasAtualizacoes() throws SQLException {

		Collection<UpdateLogVO> collection = new ArrayList<UpdateLogVO>();
		Statement stmt = null;
		Connection conexao = null;
		ResultSet rset = null;
		DAOManager dao = DAOManager.getInstance();

        try {
            conexao = dao.getConexao();

            // busca o SQL correto
            stmt = super.createStatement(conexao);

            // executa a query
            rset = stmt.executeQuery(super.getQuery("updatelog.busca5ultimas"));

			while (rset.next()) {
				UpdateLogVO vo = new UpdateLogVO();
			    vo.setId(rset.getInt("id"));
			    vo.setData(rset.getDate("data"));
			    vo.setDescricao(rset.getString("descricao"));
				collection.add(vo);
			}
		} catch (Exception e) {
			logger.error("[ERRO DO DAO UPDATE DE SITE]:", e);
        } finally {
            close(rset);
            close(stmt);
            close(conexao);
        }
		return collection;
	}

}