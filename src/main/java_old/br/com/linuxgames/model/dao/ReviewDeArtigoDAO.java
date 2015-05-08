package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.ReviewDeArtigoVO;

public class ReviewDeArtigoDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static ReviewDeArtigoDAO instance = new ReviewDeArtigoDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private ReviewDeArtigoDAO() {
	     setInsertSQL("reviewDeArtigo.insere");
	     setUpdateSQL("reviewDeArtigo.atualiza");
	     setDeleteSQL("reviewDeArtigo.remove");
	    setBuscaUmSQL("reviewDeArtigo.buscaUm");
	 setBuscaTodosSQL("reviewDeArtigo.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static ReviewDeArtigoDAO getinstance() {
		return instance;
	}

	public Collection<Object> buscaTodos() throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		Statement stmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            stmt = createStatement(conexao);
            rset = stmt.executeQuery(getQuery(getBuscaTodosSQL()));
            while (rset.next()) {
        	  ReviewDeArtigoVO vo = new ReviewDeArtigoVO();
        	  vo.setId(rset.getInt("id"));
        	  vo.getArtigo().setId(rset.getInt("artigo_id"));
        	  vo.getArtigo().setTitulo(rset.getString("titulo"));
        	  vo.getUsuario().setEmail(rset.getString("username"));
        	  vo.getUsuario().setId(rset.getInt("usuario_id"));
        	  vo.setComentario(rset.getString("comentario"));
        	  vo.setNota(rset.getInt("nota"));
              retorno.add(vo);
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA TODOS]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(stmt);
            close(conexao);
        }
		return (List<Object>) retorno;
	}


	public Collection<Object> buscaPorArtigo(int artigoId) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"reviewDeArtigo.buscaPorArtigo");
            pstmt.setInt(1,artigoId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
        	  ReviewDeArtigoVO vo = new ReviewDeArtigoVO();
      		  vo.setId(rset.getInt("id"));
    		  vo.getArtigo().setId(rset.getInt("artigo_id"));
    		  vo.getUsuario().setId(rset.getInt("usuario_id"));
    		  vo.getUsuario().setEmail(rset.getString("username"));
    	      vo.setComentario(rset.getString("comentario"));
    	      vo.setNota(rset.getInt("nota"));
              retorno.add(vo);
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR ARTIGO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return (List<Object>) retorno;
	}

	public boolean usuarioFezReviewDeArtigo(int usuarioId,int artigoId) throws DAOException {
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		int reviews = 0;
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"reviewDeArtigo.buscaPorUsuario");
            pstmt.setInt(1,usuarioId);
            pstmt.setInt(2,artigoId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              reviews = rset.getInt(1);
            }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR REVIEW DE ARTIGO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		if (reviews>0) return true;
		return false;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		ReviewDeArtigoVO vo = new ReviewDeArtigoVO();
		vo.setId(rset.getInt("id"));
		vo.getArtigo().setId(rset.getInt("artigo_id"));
		vo.getUsuario().setId(rset.getInt("usuario_id"));
	    vo.setComentario(rset.getString("comentario"));
	    vo.setNota(rset.getInt("nota"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		ReviewDeArtigoVO vo = (ReviewDeArtigoVO) object;
     	pstmt.setInt(1,vo.getArtigo().getId());
        pstmt.setInt(2,vo.getUsuario().getId());
        pstmt.setString(3,vo.getComentario());
        pstmt.setInt(4,vo.getNota());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		ReviewDeArtigoVO vo = (ReviewDeArtigoVO) object;
    	pstmt.setInt(1,vo.getArtigo().getId());
        pstmt.setInt(2,vo.getUsuario().getId());
        pstmt.setString(3,vo.getComentario());
        pstmt.setInt(4,vo.getNota());
        pstmt.setInt(5,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		ReviewDeArtigoVO vo = (ReviewDeArtigoVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
