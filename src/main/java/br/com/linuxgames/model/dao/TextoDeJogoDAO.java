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
import br.com.linuxgames.model.vo.SolicitacaoVO;
import br.com.linuxgames.model.vo.TextoDeJogoVO;

public class TextoDeJogoDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static TextoDeJogoDAO instance = new TextoDeJogoDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private TextoDeJogoDAO() {
	 setInsertSQL("textoDeJogo.insere");
	 setUpdateSQL("textoDeJogo.atualiza");
	 setDeleteSQL("textoDeJogo.remove");
	 setBuscaUmSQL("textoDeJogo.buscaUm");
	 setBuscaTodosSQL("textoDeJogo.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static TextoDeJogoDAO getInstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		TextoDeJogoVO vo = new TextoDeJogoVO();
		vo.setId(rset.getInt("id"));
	    vo.setTexto(rset.getString("texto"));
	    vo.setDataPublic(rset.getDate("data_public"));
	    vo.setLink(rset.getString("link"));
	    vo.setTipo(rset.getInt("tipo"));
	    vo.setAprovado(rset.getInt("aprovado"));
	    vo.getJogo().setId(rset.getInt("tipo_id"));
	    vo.getJogo().setNome(rset.getString("jogo"));
	    vo.getUsuario().setId(rset.getInt("usuario_id"));
	    vo.getUsuario().setEmail(rset.getString("username"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		TextoDeJogoVO vo = (TextoDeJogoVO) object;
		pstmt.setString(1,vo.getTexto());
		pstmt.setTimestamp(2,new Timestamp( vo.getDataPublic().getTime()));
        pstmt.setString(3,vo.getLink());
        pstmt.setInt(4,vo.getJogo().getId());
        pstmt.setInt(5,vo.getAprovado());
        pstmt.setInt(6,vo.getUsuario().getId());
        pstmt.setInt(7,vo.getTipo());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		TextoDeJogoVO vo = (TextoDeJogoVO) object;
		pstmt.setString(1,vo.getTexto());
		pstmt.setTimestamp(2,new Timestamp( vo.getDataPublic().getTime()));
        pstmt.setString(3,vo.getLink());
        pstmt.setInt(4,vo.getAprovado());
        pstmt.setInt(5,vo.getJogo().getId());
        pstmt.setInt(6,vo.getUsuario().getId());
        pstmt.setInt(7,vo.getTipo());
        pstmt.setInt(8,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		TextoDeJogoVO vo = (TextoDeJogoVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
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
            pstmt = createPreparedStatement(conexao,"textoDeJogo.buscaPorJogo");
            pstmt.setInt(1,jogoId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              TextoDeJogoVO vo = new TextoDeJogoVO();
      		  vo.setId(rset.getInt("id"));
    	      vo.setTexto(rset.getString("texto"));
    	      vo.setDataPublic(rset.getDate("data_public"));
    	      vo.setLink(rset.getString("link"));
    	      vo.setTipo(rset.getInt("tipo"));
    	      vo.setAprovado(rset.getInt("aprovado"));
    	      vo.getJogo().setId(rset.getInt("tipo_id"));
    	      vo.getJogo().setNome(rset.getString("jogo"));
    	      vo.getUsuario().setId(rset.getInt("usuario_id"));
    	      vo.getUsuario().setEmail(rset.getString("username"));
              retorno.add(vo);
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR JOGO 7]:" + e.getMessage());
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
	public Collection<Object> buscaTextosReprovados() throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista ...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"textoDeJogo.buscaTextosReprovados");
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	SolicitacaoVO vo = new SolicitacaoVO();
        		vo.setId(rset.getInt("id"));
        	    vo.setNome(rset.getString("jogo"));
        	    vo.getTexto().setId(rset.getInt("roteiro"));
        	    vo.getTexto().setTexto(rset.getString("descricao"));
        	    vo.getTexto().setLink(rset.getString("link"));
        	    vo.getTexto().setTipo(rset.getInt("tipo"));
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
            pstmt = createPreparedStatement(conexao,"textoDeJogo.buscaTextoReprovado");
            pstmt.setInt(1,textoId );
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	vo = new SolicitacaoVO();
        		vo.setId(rset.getInt("id"));
        	    vo.setNome(rset.getString("jogo"));
        	    vo.getTexto().setId(rset.getInt("roteiro"));
        	    vo.getTexto().setTexto(rset.getString("descricao"));
        	    vo.getTexto().setLink(rset.getString("link"));
        	    vo.getTexto().setTipo(rset.getInt("tipo"));
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
         pstmt = createPreparedStatement(conexao,"textoDeJogo.atualizaAprovacao");
         TextoDeJogoVO vo = (TextoDeJogoVO) object;
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
            pstmt = createPreparedStatement(conexao,"textoDeJogo.buscaMaxId");
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
}
