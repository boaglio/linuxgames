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
import br.com.linuxgames.model.vo.JogosFavoritosVO;

public class JogosFavoritosDAO extends AbstractDAO {

	private static JogosFavoritosDAO instance = new JogosFavoritosDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado
	 */
	private JogosFavoritosDAO() {
		setInsertSQL("jogosFavoritos.insere");
		setUpdateSQL("jogosFavoritos.atualiza");
		setDeleteSQL("jogosFavoritos.remove");
		setBuscaUmSQL("jogosFavoritos.buscaUm");
		setBuscaTodosSQL("jogosFavoritos.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static JogosFavoritosDAO getinstance() {
		return instance;
	}

	public Collection<JogosFavoritosVO> buscaPorUsuario(int usuarioId) throws DAOException {
		Collection<JogosFavoritosVO> retorno = new ArrayList<JogosFavoritosVO>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"jogosFavoritos.buscaPorUsuario");
            pstmt.setInt(1,usuarioId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              retorno.add( (JogosFavoritosVO) setVO(rset));
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
		return (List<JogosFavoritosVO>) retorno;
	}

    /**
	 * busca um objeto
	 */
	public boolean existeFavoritoParaEsseJogo(JogosFavoritosVO jogosFavoritos) throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conexao = null;
	   ResultSet rs = null;
	   int contador=0;
       try {
         conexao = dao.getConexao();
         pstmt = setPreparedStatementBuscaUm(jogosFavoritos,createPreparedStatement(conexao,"jogosFavoritos.contaFavoritos"));
         rs = pstmt.executeQuery();
         while (rs.next()) {
          contador = rs.getInt(1);
         }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO existeFavoritoParaEsseJogo ]:" + e.getMessage());
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
	    JogosFavoritosVO vo = (JogosFavoritosVO) object;
	    pstmt.setInt(1,vo.getUsuario().getId());
	    pstmt.setInt(2,vo.getJogo().getId());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		JogosFavoritosVO vo = (JogosFavoritosVO) object;
	    pstmt.setInt(1,vo.getUsuario().getId());
	    pstmt.setInt(2,vo.getJogo().getId());
	    pstmt.setInt(3,vo.getOldUsuario());
	    pstmt.setInt(4,vo.getOldJogo());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		JogosFavoritosVO vo = (JogosFavoritosVO) object;
	    pstmt.setInt(1,vo.getUsuario().getId());
	    pstmt.setInt(2,vo.getJogo().getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		JogosFavoritosVO vo = new JogosFavoritosVO();
		vo.getUsuario().setEmail(rset.getString(1));
	    vo.getUsuario().setId(rset.getInt(2));
	    vo.getJogo().setNome(rset.getString(3));
	    vo.getJogo().setId(rset.getInt(4));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
