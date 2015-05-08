package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.EmuladoresFavoritosVO;

public class EmuladoresFavoritosDAO extends AbstractDAO {

	private static EmuladoresFavoritosDAO instance = new EmuladoresFavoritosDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado
	 */
	private EmuladoresFavoritosDAO() {
		setInsertSQL("emuladoresFavoritos.insere");
		setUpdateSQL("emuladoresFavoritos.atualiza");
		setDeleteSQL("emuladoresFavoritos.remove");
		setBuscaUmSQL("emuladoresFavoritos.buscaUm");
		setBuscaTodosSQL("emuladoresFavoritos.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static EmuladoresFavoritosDAO getinstance() {
		return instance;
	}

	public Collection<EmuladoresFavoritosVO> buscaPorUsuario(int usuarioId) throws DAOException {
		Collection<EmuladoresFavoritosVO> retorno = new ArrayList<EmuladoresFavoritosVO>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"emuladoresFavoritos.buscaPorUsuario");
            pstmt.setInt(1,usuarioId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              retorno.add((EmuladoresFavoritosVO) setVO(rset));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR USUARIO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return (Collection<EmuladoresFavoritosVO>) retorno;
	}

    /**
	 * busca um objeto
	 */
	public boolean existeFavoritoParaEsseEmulador(EmuladoresFavoritosVO emuladoresFavoritos) throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conexao = null;
	   ResultSet rs = null;
	   int contador=0;
       try {
         conexao = dao.getConexao();
         pstmt = setPreparedStatementBuscaUm(emuladoresFavoritos,createPreparedStatement(conexao,"emuladoresFavoritos.contaFavoritos"));
         rs = pstmt.executeQuery();
         while (rs.next()) {
          contador = rs.getInt(1);
         }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO existeFavoritoParaEsseEmulador ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	    if (contador>0)
	     return true;
	   return false;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	    EmuladoresFavoritosVO vo = (EmuladoresFavoritosVO) object;
	    pstmt.setInt(1,vo.getUsuario().getId());
	    pstmt.setInt(2,vo.getEmulador().getId());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		EmuladoresFavoritosVO vo = (EmuladoresFavoritosVO) object;
	    pstmt.setInt(1,vo.getUsuario().getId());
	    pstmt.setInt(2,vo.getEmulador().getId());
	    pstmt.setInt(3,vo.getOldUsuario());
	    pstmt.setInt(4,vo.getOldEmulador());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		EmuladoresFavoritosVO vo = (EmuladoresFavoritosVO) object;
	    pstmt.setInt(1,vo.getUsuario().getId());
	    pstmt.setInt(2,vo.getEmulador().getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		EmuladoresFavoritosVO vo = new EmuladoresFavoritosVO();
		vo.getUsuario().setEmail(rset.getString(1));
	    vo.getUsuario().setId(rset.getInt(2));
	    vo.getEmulador().setNome(rset.getString(3));
	    vo.getEmulador().setId(rset.getInt(4));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
