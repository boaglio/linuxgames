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
import br.com.linuxgames.model.vo.RoteiroInstalacaoJogoVO;
import br.com.linuxgames.model.vo.SolicitacaoVO;

public class RoteiroInstalacaoJogoDAO extends AbstractDAO {

	private static RoteiroInstalacaoJogoDAO instance = new RoteiroInstalacaoJogoDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado
	 */
	private RoteiroInstalacaoJogoDAO() {
		setInsertSQL("roteiroDeInstalacaoJogo.insere");
		setUpdateSQL("roteiroDeInstalacaoJogo.atualiza");
		setDeleteSQL("roteiroDeInstalacaoJogo.remove");
		setBuscaUmSQL("roteiroDeInstalacaoJogo.buscaUm");
		setBuscaTodosSQL("roteiroDeInstalacaoJogo.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static RoteiroInstalacaoJogoDAO getinstance() {
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
            pstmt = createPreparedStatement(conexao,"roteiroDeInstalacaoJogo.buscaPorJogo");
            pstmt.setInt(1,jogoId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
        	  RoteiroInstalacaoJogoVO vo = new RoteiroInstalacaoJogoVO();
        	  vo.setId(rset.getInt(1));
        	  vo.getJogo().setId(rset.getInt(2));
        	  vo.getJogo().setNome(rset.getString(3));
        	  vo.getDistro().setSiteOficial(rset.getString(4));
        	  vo.getDistro().setLinkLogo(rset.getString(5));
        	  vo.getDistro().setDescricao(rset.getString(6));
        	  vo.getDistro().setId(rset.getInt(7));
        	  vo.getDistro().setNome(rset.getString(8));
        	  vo.getUsuario().setId(rset.getInt(9));
        	  vo.getUsuario().setEmail(rset.getString(10));
        	  vo.setDescricao(rset.getString(11));
        	  vo.setAprovado(rset.getInt(12));
              retorno.add(vo);
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR JOGO 3]:" + e.getMessage());
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
	    RoteiroInstalacaoJogoVO vo = (RoteiroInstalacaoJogoVO) object;
	    pstmt.setInt(1,vo.getJogo().getId());
	    pstmt.setInt(2,vo.getDistro().getId());
	    pstmt.setInt(3,vo.getUsuario().getId());
	    pstmt.setString(4,vo.getDescricao());
	    pstmt.setInt(5,vo.getAprovado());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		RoteiroInstalacaoJogoVO vo = (RoteiroInstalacaoJogoVO) object;
	    pstmt.setInt(1,vo.getJogo().getId());
	    pstmt.setInt(2,vo.getDistro().getId());
	    pstmt.setInt(3,vo.getUsuario().getId());
	    pstmt.setString(4,vo.getDescricao());
	    pstmt.setInt(5,vo.getAprovado());
	    pstmt.setInt(6,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		RoteiroInstalacaoJogoVO vo = (RoteiroInstalacaoJogoVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		RoteiroInstalacaoJogoVO vo = new RoteiroInstalacaoJogoVO();
	    vo.setId(rset.getInt(1));
	    vo.getJogo().setId(rset.getInt(2));
		vo.getJogo().setNome(rset.getString(3));
		vo.getDistro().setId(rset.getInt(4));
	    vo.getDistro().setNome(rset.getString(5));
		vo.getUsuario().setId(rset.getInt(6));
	    vo.getUsuario().setEmail(rset.getString(7));
	    vo.setDescricao(rset.getString(8));
	    vo.setAprovado(rset.getInt(9));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}


	/**
	 * busca textos de jogos reprovados
	 */
	public Collection<Object> buscaTextosReprovados() throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista ...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"roteiroDeInstalacaoJogo.buscaTextosReprovados");
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	SolicitacaoVO vo = new SolicitacaoVO();
        		vo.setId(rset.getInt("id"));
        	    vo.setNome(rset.getString("jogo"));
        	    vo.getTexto().setId(rset.getInt("roteiro"));
        	    vo.getTexto().setTexto(rset.getString("descricao"));
        	    vo.getUsuario().setId(rset.getInt("user_id"));
        	    vo.getUsuario().setEmail(rset.getString("username"));
        	    vo.getUsuario().setSenha(rset.getString("user_email"));
                retorno.add(vo);
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR TEXTOS REPROVADOS]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return (List<Object>) retorno;
	}


	/**
	 * busca textos de jogos reprovados
	 */
	public SolicitacaoVO buscaTextoReprovado(int textoId) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		SolicitacaoVO vo = null;
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"roteiroDeInstalacaoJogo.buscaTextoReprovado");
            pstmt.setInt(1,textoId );
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	vo = new SolicitacaoVO();
        		vo.setId(rset.getInt("id"));
        	    vo.setNome(rset.getString("jogo"));
        	    vo.getTexto().setId(rset.getInt("roteiro"));
        	    vo.getTexto().setTexto(rset.getString("descricao"));
        	    vo.getUsuario().setId(rset.getInt("user_id"));
        	    vo.getUsuario().setEmail(rset.getString("username"));
        	    vo.getUsuario().setSenha(rset.getString("user_email"));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR TEXTOS REPROVADOS]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return vo;
	}

	/**
	 * busca maior ID
	 */
	public int buscaMaxId() throws DAOException {
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		int resultado=0 ;
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"roteiroDeInstalacaoJogo.buscaMaxId");
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	resultado = rset.getInt(1);
            }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR MAX]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return resultado;
	}

	/**
	 * altera um objeto qualquer uma vez definido o updateSQL
	 */
	public void atualizaStatus(Object object) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   logger.info("[UPDATE DE "+this.getClass()+"]:");
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"roteiroDeInstalacaoJogo.atualizaStatus");
 		 RoteiroInstalacaoJogoVO vo = (RoteiroInstalacaoJogoVO) object;
	     pstmt.setInt(1,vo.getAprovado());
	     pstmt.setInt(2,vo.getId());
         pstmt.execute();
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO UPDATE]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	}

}
