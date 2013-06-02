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
import br.com.linuxgames.model.vo.TextoDeEmuladorVO;

public class TextoDeEmuladorDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static TextoDeEmuladorDAO instance = new TextoDeEmuladorDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private TextoDeEmuladorDAO() {
	 setInsertSQL("textoDeEmulador.insere");
	 setUpdateSQL("textoDeEmulador.atualiza");
	 setDeleteSQL("textoDeEmulador.remove");
	 setBuscaUmSQL("textoDeEmulador.buscaUm");
	 setBuscaTodosSQL("textoDeEmulador.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static TextoDeEmuladorDAO getinstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		TextoDeEmuladorVO vo = new TextoDeEmuladorVO();
		vo.setId(rset.getInt("id"));
	    vo.setTexto(rset.getString("texto"));
	    vo.setDataPublic(rset.getDate("data_public"));
	    vo.setLink(rset.getString("link"));
	    vo.setTipo(rset.getInt("tipo"));
	    vo.setAprovado(rset.getInt("aprovado"));
	    vo.getEmulador().setId(rset.getInt("tipo_id"));
	    vo.getEmulador().setNome(rset.getString("emulador"));
	    vo.getUsuario().setId(rset.getInt("usuario_id"));
	    vo.getUsuario().setEmail(rset.getString("username"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		TextoDeEmuladorVO vo = (TextoDeEmuladorVO) object;
		pstmt.setString(1,vo.getTexto());
		pstmt.setTimestamp(2,new Timestamp( vo.getDataPublic().getTime()));
        pstmt.setString(3,vo.getLink());
        pstmt.setInt(4,vo.getEmulador().getId());
        pstmt.setInt(5,vo.getAprovado());
        pstmt.setInt(6,vo.getUsuario().getId());
        pstmt.setInt(7,vo.getTipo());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		TextoDeEmuladorVO vo = (TextoDeEmuladorVO) object;
		pstmt.setString(1,vo.getTexto());
		pstmt.setTimestamp(2,new Timestamp( vo.getDataPublic().getTime()));
        pstmt.setString(3,vo.getLink());
        pstmt.setInt(4,vo.getAprovado());
        pstmt.setInt(5,vo.getEmulador().getId());
        pstmt.setInt(6,vo.getUsuario().getId());
        pstmt.setInt(7,vo.getTipo());
        pstmt.setInt(8,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		TextoDeEmuladorVO vo = (TextoDeEmuladorVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
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
            pstmt = createPreparedStatement(conexao,"textoDeEmulador.buscaPorEmulador");
            pstmt.setInt(1,emuId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              TextoDeEmuladorVO vo = new TextoDeEmuladorVO();
      		  vo.setId(rset.getInt("id"));
    	      vo.setTexto(rset.getString("texto"));
    	      vo.setDataPublic(rset.getDate("data_public"));
    	      vo.setLink(rset.getString("link"));
    	      vo.setTipo(rset.getInt("tipo"));
    	      vo.setAprovado(rset.getInt("aprovado"));
    	      vo.getEmulador().setId(rset.getInt("tipo_id"));
    	      vo.getEmulador().setNome(rset.getString("emu"));
    	      vo.getUsuario().setId(rset.getInt("usuario_id"));
    	      vo.getUsuario().setEmail(rset.getString("username"));
              retorno.add(vo);
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

	/**
	 * busca textos de emus reprovados
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
            pstmt = createPreparedStatement(conexao,"textoDeEmulador.buscaTextosReprovados");
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	SolicitacaoVO vo = new SolicitacaoVO();
        		vo.setId(rset.getInt("id"));
        	    vo.setNome(rset.getString("emu"));
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
	 * busca textos de emus reprovados
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
            pstmt = createPreparedStatement(conexao,"textoDeEmulador.buscaTextoReprovado");
            pstmt.setInt(1,textoId );
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	vo = new SolicitacaoVO();
        		vo.setId(rset.getInt("id"));
        	    vo.setNome(rset.getString("emu"));
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
         pstmt = createPreparedStatement(conexao,"textoDeEmulador.atualizaAprovacao");
         TextoDeEmuladorVO vo = (TextoDeEmuladorVO) object;
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
            pstmt = createPreparedStatement(conexao,"textoDeEmulador.buscaMaxId");
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
