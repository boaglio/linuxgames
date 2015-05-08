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
import br.com.linuxgames.model.vo.BibliotecaXjogoVO;

public class BibliotecaXjogoDAO extends AbstractDAO {

	private static BibliotecaXjogoDAO instance = new BibliotecaXjogoDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado
	 */
	private BibliotecaXjogoDAO() {
		setInsertSQL("bibliotecaXjogo.insere");
		setUpdateSQL("bibliotecaXjogo.atualiza");
		setDeleteSQL("bibliotecaXjogo.remove");
		setBuscaUmSQL("bibliotecaXjogo.buscaUm");
		setBuscaTodosSQL("bibliotecaXjogo.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static BibliotecaXjogoDAO getinstance() {
		return instance;
	}

	public Collection<Object> buscaPorJogo(int jogoId) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"bibliotecaXjogo.buscaPorJogo");
            pstmt.setInt(1,jogoId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              retorno.add(setVO(rset));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR JOGO 1]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return (List<Object>) retorno;
	}


	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	    BibliotecaXjogoVO vo = (BibliotecaXjogoVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getJogo().getId());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		BibliotecaXjogoVO vo = (BibliotecaXjogoVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getJogo().getId());
	    pstmt.setInt(3,vo.getOldBiblioteca());
	    pstmt.setInt(4,vo.getOldJogo());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		BibliotecaXjogoVO vo = (BibliotecaXjogoVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getJogo().getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		BibliotecaXjogoVO vo = new BibliotecaXjogoVO();
	    vo.getBiblioteca().setNome(rset.getString(1));
	    vo.getBiblioteca().setId(rset.getInt(2));
	    vo.getJogo().setNome(rset.getString(3));
	    vo.getJogo().setId(rset.getInt(4));
	    vo.getBiblioteca().setLink(rset.getString(5));
	    vo.getBiblioteca().setDescricao(rset.getString(6));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
