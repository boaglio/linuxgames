package br.com.linuxgames.model.dao;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.InfoDoDiaVO;

/**
 * InfoDoDias
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class InfoDoDiaDAO extends AbstractDAO {

	Logger logger = Logger.getLogger(this.getClass());

	private static InfoDoDiaDAO instance = new InfoDoDiaDAO();

    /**
     * Singleton
     */
	public static InfoDoDiaDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private InfoDoDiaDAO () {
		setInsertSQL("infoDoDia.insere");
		setUpdateSQL("infoDoDia.atualiza");
		setDeleteSQL("infoDoDia.remove");
		setBuscaUmSQL("infoDoDia.buscaUm");
		setBuscaTodosSQL("infoDoDia.buscaTudo");
	};

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		InfoDoDiaVO vo = (InfoDoDiaVO) object;
        pstmt.setTimestamp(1,new Timestamp( vo.getData().getTime()));
		pstmt.setInt(2,vo.getJogoVO().getId());
		pstmt.setInt(3,vo.getEmuladorVO().getId());
		return pstmt;
	}
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		InfoDoDiaVO vo = (InfoDoDiaVO) object;
        pstmt.setTimestamp(1, new Timestamp(vo.getData().getTime()));
        pstmt.setInt(2,vo.getJogoVO().getId());
		pstmt.setInt(3,vo.getEmuladorVO().getId());
        pstmt.setInt(4,vo.getId());
        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	   InfoDoDiaVO vo = (InfoDoDiaVO) object;
	   pstmt.setInt(1,vo.getId());
	   return pstmt;
	}

	protected InfoDoDiaVO setVO(ResultSet rset) throws SQLException {
		InfoDoDiaVO vo = new InfoDoDiaVO();
	    vo.setId(rset.getInt("id"));
	    vo.setData(rset.getDate("data"));
	    vo.getJogoVO().setId(rset.getInt("jogo_id"));
	    vo.getJogoVO().setNome(rset.getString("jogo"));
	    vo.getJogoVO().setDescricao(rset.getString("jdesc"));
	    vo.getEmuladorVO().setId(rset.getInt("emu_id"));
	    vo.getEmuladorVO().setNome(rset.getString("emu"));
	    vo.getEmuladorVO().setDescricao(rset.getString("edesc"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

    /**
	 * busca o registro mais recente
	 */
	public InfoDoDiaVO buscaMaisRecente() throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conexao = null;
	   ResultSet rs = null;
	   InfoDoDiaVO vo = new InfoDoDiaVO();
	   logger.info("[trazendo o objeto...]:");
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"infoDoDia.buscaMaisRecente");
         rs = pstmt.executeQuery();
         while (rs.next()) {
            vo = setVO(rs);
         }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO buscaMaisRecente ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	    return vo;
	}

    /**
	 * busca a info do dia atual
	 */
	public Object buscaDoDia() throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conexao = null;
	   ResultSet rs = null;
	   Object vo = new Object();
	   logger.info("[trazendo o objeto...]:");
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"infoDoDia.buscaDoDia");
         rs = pstmt.executeQuery();
         while (rs.next()) {
            vo = setVO(rs);
         }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO buscaDoDia ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	    return vo;
	}

}