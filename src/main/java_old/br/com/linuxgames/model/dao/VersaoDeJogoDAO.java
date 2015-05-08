package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.VersaoDeJogoVO;

public class VersaoDeJogoDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static VersaoDeJogoDAO instance = new VersaoDeJogoDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private VersaoDeJogoDAO() {
	 setInsertSQL("versaoDeJogo.insere");
	 setUpdateSQL("versaoDeJogo.atualiza");
	 setDeleteSQL("versaoDeJogo.remove");
	 setBuscaUmSQL("versaoDeJogo.buscaUm");
	 setBuscaTodosSQL("versaoDeJogo.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static VersaoDeJogoDAO getinstance() {
		return instance;
	}

	@SuppressWarnings("rawtypes")
	public Collection buscaPorJogo(int jogoId) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"versaoDeJogo.buscaPorJogo");
            pstmt.setInt(1,jogoId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              retorno.add(setVO(rset));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR JOGO 8]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return (List) retorno;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		VersaoDeJogoVO vo = new VersaoDeJogoVO();
		vo.setId(rset.getInt("id"));
	    vo.setRelease(rset.getString("release"));
	    vo.setLink(rset.getString("link"));
	    vo.setObs(rset.getString("obs"));
	    vo.setDataLancamento(rset.getDate("data_lancamento"));
	    vo.getJogo().setId(rset.getInt("tipo_id"));
	    vo.getJogo().setNome(rset.getString("nome"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeJogoVO vo = (VersaoDeJogoVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getJogo().getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeJogoVO vo = (VersaoDeJogoVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getJogo().getId());
        pstmt.setInt(6,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeJogoVO vo = (VersaoDeJogoVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
