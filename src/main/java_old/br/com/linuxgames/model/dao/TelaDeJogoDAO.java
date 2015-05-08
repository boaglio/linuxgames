package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.TelaDeJogoVO;

public class TelaDeJogoDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static TelaDeJogoDAO instance = new TelaDeJogoDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private TelaDeJogoDAO() {
	 setInsertSQL("telaDeJogo.insere");
	 setUpdateSQL("telaDeJogo.atualiza");
	 setDeleteSQL("telaDeJogo.remove");
	 setBuscaUmSQL("telaDeJogo.buscaUm");
	 setBuscaTodosSQL("telaDeJogo.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static TelaDeJogoDAO getInstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		TelaDeJogoVO vo = new TelaDeJogoVO();
		vo.setId(rset.getInt("id"));
	    vo.setNome(rset.getString("nome"));
	    vo.setDescricao(rset.getString("descricao"));
	    vo.getJogo().setId(rset.getInt("tipo_id"));
	    vo.getJogo().setNome(rset.getString("jogo"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		TelaDeJogoVO vo = (TelaDeJogoVO) object;
		pstmt.setString(1,vo.getNome());
        pstmt.setString(2,vo.getDescricao());
        pstmt.setString(3,vo.getFileMimeType());
        pstmt.setString(4,vo.getFilePath());
        pstmt.setInt(5,vo.getJogo().getId());
        logger.info("fileContent="+vo.getFilePath());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		TelaDeJogoVO vo = (TelaDeJogoVO) object;
        pstmt.setString(1,vo.getDescricao());
        pstmt.setInt(2,vo.getJogo().getId());
        pstmt.setInt(3,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		TelaDeJogoVO vo = (TelaDeJogoVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

	/**
	 * busca telas por jogo
	 */
	public Collection<Object> buscaTelasPorJogo(Object object) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista ...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"telaDeJogo.buscaPorJogo");
    		TelaDeJogoVO vo = (TelaDeJogoVO) object;
            pstmt.setInt(1,vo.getJogo().getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retorno.add(setVO(rs));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR JOGO 4]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
		return (List<Object>) retorno;
	}

	/**
	 * busca primeira tela do jogo
	 */
	public String buscaPrimeiraTelaDoJogo(Object object) throws DAOException {
		String retorno="";
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista ...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"telaDeJogo.buscaPrimeiraTelaDoJogo");
    		TelaDeJogoVO vo = (TelaDeJogoVO) object;
            pstmt.setInt(1,vo.getJogo().getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getString(1);
            }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR JOGO 5]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
		return retorno;
	}

	public Collection<Integer> buscaJogosIdComTelas() throws DAOException {
		Collection<Integer>  retorno = new ArrayList<Integer> ();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista ...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"telaDeJogo.buscaJogosIdComTelas");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retorno.add(rs.getInt(1));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR JOGO 6]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
		return retorno;
	}
}
