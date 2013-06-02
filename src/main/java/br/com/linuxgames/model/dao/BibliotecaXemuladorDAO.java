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
import br.com.linuxgames.model.vo.BibliotecaXemuladorVO;

public class BibliotecaXemuladorDAO extends AbstractDAO {

	private static BibliotecaXemuladorDAO instance = new BibliotecaXemuladorDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado
	 */
	private BibliotecaXemuladorDAO() {
		setInsertSQL("bibliotecaXemulador.insere");
		setUpdateSQL("bibliotecaXemulador.atualiza");
		setDeleteSQL("bibliotecaXemulador.remove");
		setBuscaUmSQL("bibliotecaXemulador.buscaUm");
		setBuscaTodosSQL("bibliotecaXemulador.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static BibliotecaXemuladorDAO getinstance() {
		return instance;
	}

	public Collection<Object> buscaPorEmulador(int emuId) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"bibliotecaXemulador.buscaPorEmulador");
            pstmt.setInt(1,emuId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              retorno.add(setVO(rset));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR EMULADOR]:" + e.getMessage());
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
	    BibliotecaXemuladorVO vo = (BibliotecaXemuladorVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getEmulador().getId());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		BibliotecaXemuladorVO vo = (BibliotecaXemuladorVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getEmulador().getId());
	    pstmt.setInt(3,vo.getOldBiblioteca());
	    pstmt.setInt(4,vo.getOldEmulador());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		BibliotecaXemuladorVO vo = (BibliotecaXemuladorVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getEmulador().getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		BibliotecaXemuladorVO vo = new BibliotecaXemuladorVO();
	    vo.getBiblioteca().setNome(rset.getString(1));
	    vo.getBiblioteca().setId(rset.getInt(2));
	    vo.getEmulador().setNome(rset.getString(3));
	    vo.getEmulador().setId(rset.getInt(4));
	    vo.getBiblioteca().setLink(rset.getString(5));
	    vo.getBiblioteca().setDescricao(rset.getString(6));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
