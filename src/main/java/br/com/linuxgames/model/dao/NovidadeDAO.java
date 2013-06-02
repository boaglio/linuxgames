package br.com.linuxgames.model.dao;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.LinuxGamesVO;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.model.vo.TextoDeJogoVO;
import br.com.linuxgames.util.Constantes;

/**
 * Novidades
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class NovidadeDAO extends AbstractDAO {

	private static Logger logger = Logger.getLogger(NovidadeDAO.class);

	private String SQL_BUSCA_VERSAO_ATUAL = "versao.atual";
	private static String SQL_BUSCA_ULTIMAS_NOTICIAS = "novidade.busca5ultimas";
	private static NovidadeDAO instance = new NovidadeDAO();

    /**
     * Singleton
     */
	public static NovidadeDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private NovidadeDAO () {
		setInsertSQL("novidade.insere");
		setUpdateSQL("novidade.atualiza");
		setDeleteSQL("novidade.remove");
		setBuscaUmSQL("novidade.buscaUm");
		setBuscaTodosSQL("novidade.buscaTudo");
	};
	/**
	 * Busca de novidades
	 */
//	public Collection buscaNoticias() throws SQLException {
//
//		Collection retorno = new ArrayList();
//		NovidadeVO novidade = null;
//		Statement stmt = null;
//		DAOManager dao = DAOManager.getInstance();
//		Connection conexao = null;
//		ResultSet rs = null;
//		logger.info("trazendo a lista de novidades...:");
//
//        try {
//        	conexao = dao.getConexao();
//
//            // busca o SQL correto
//            stmt = super.createStatement(conexao);
//
//            // executa a query
//            rs = stmt.executeQuery(super.getQuery(SQL_BUSCA_NOVIDADES));
//
//			while (rs.next()) {
//				novidade = new NovidadeVO();
//				novidade.setId(rs.getInt(1));
//				novidade.setDataPublic(rs.getDate(2));
//				novidade.setLink(rs.getString(3));
//				novidade.setTipo(rs.getInt(4));
//				novidade.setTexto(rs.getString(5));
//				novidade.getUsuarioVO().setId(rs.getInt(6));
//				retorno.add(novidade);
//			}
//			logger.debug(retorno.size() + " Novidades carregadas! ");
//		} catch (SQLException e) {
//			logger.error("[ERRO DO BANCO 1]:" + e.getMessage());
//		} catch (Exception e) {
//			logger.error("[ERRO DO DAO1]:", e);
//        } finally {
//            close(rs);
//            close(stmt);
//            close(conexao);
//        }
//		return retorno;
//	}

	/*
	 * busca versao do sistema
	 */
	public LinuxGamesVO buscaUltimaVersao() throws SQLException {

		LinuxGamesVO versao = new LinuxGamesVO();
		Statement stmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
		logger.info("trazendo a ultima versao...");
        try {
            conexao = dao.getConexao();

            // busca o SQL correto
            stmt = super.createStatement(conexao);

            // executa a query
            rs = stmt.executeQuery(super.getQuery(SQL_BUSCA_VERSAO_ATUAL));

			while (rs.next()) {
				versao.setVersao(rs.getString("versao"));
				versao.setDescricao(rs.getString("descricao"));
				versao.setDataLancamento(rs.getDate("data"));
			}
			logger.debug(versao.getVersao()+" = "+versao.getDescricao());
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO 2]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO2]:", e);
        } finally {
            close(rs);
            close(stmt);
            close(conexao);
        }
		return versao;
	}

	/*
	 * busca ultimas noticias
	 */
	public Collection<Novidade> buscaUltimasNoticias() throws SQLException {

		Collection<Novidade> collection = new ArrayList<Novidade>();
		Statement stmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
		logger.info("trazendo 5 ultimas noticias...");

        try {
            conexao = dao.getConexao();

            // busca o SQL correto
            stmt = super.createStatement(conexao);

            // executa a query
            rs = stmt.executeQuery(super.getQuery(SQL_BUSCA_ULTIMAS_NOTICIAS));

			while (rs.next()) {
				Novidade vo = new Novidade ();
				vo.setId(rs.getInt("id"));
				vo.setLink(rs.getString("link"));
				vo.setTexto(rs.getString("texto"));
				vo.setDataPublic(rs.getDate("data_public"));
				collection.add(vo);
			}
			logger.debug(" noticias lidas = "+collection.size());
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO 3]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO3]:", e);
        } finally {
            close(rs);
            close(stmt);
            close(conexao);
        }
		return collection;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		Novidade vo = (Novidade) object;
        if (vo.getDataPublic()==null)
         pstmt.setTimestamp(1,new Timestamp((new Date()).getTime()));
        else
         pstmt.setTimestamp(1,new Timestamp( vo.getDataPublic().getTime()));

		 pstmt.setString(2,vo.getLink());
		 pstmt.setString(3,vo.getTexto());
		 pstmt.setInt(4,vo.getUsuario().getId());
		 pstmt.setInt(5,vo.getAprovado());
		 return pstmt;
	}
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		Novidade vo = (Novidade) object;
        pstmt.setTimestamp(1, new Timestamp(vo.getDataPublic().getTime()));
		pstmt.setString(2,vo.getLink());
		pstmt.setString(3,vo.getTexto());
		pstmt.setInt(4,vo.getUsuario().getId());
		pstmt.setInt(5,vo.getAprovado());
        pstmt.setInt(6,vo.getId());
        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	   Novidade vo = (Novidade) object;
	   pstmt.setInt(1,vo.getId());
	   return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		Novidade vo = new Novidade();
	    vo.setId(rset.getInt("id"));
	    vo.setDataPublic(rset.getDate("data_public"));
	    vo.setLink(rset.getString("link"));
	    vo.setTexto(rset.getString("texto"));
	    vo.getUsuario().setId(rset.getInt("usuario_id"));
	    vo.setAprovado(rset.getInt("aprovado"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

	/*
	 * busca ultimas noticias
	 */
	public Collection<String>  buscaUsuariosMaisAtivos() throws SQLException {

		Collection<Novidade> collection = new ArrayList<Novidade>();
		Statement stmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
		logger.info("trazendo 5 usuarios mais ativos...");
		Collection<String> usuariosMaisAtivos = new ArrayList<String>();

        try {
            conexao = dao.getConexao();

            // busca o SQL correto
            stmt = super.createStatement(conexao);

            // executa a query
            rs = stmt.executeQuery(super.getQuery("usuario.topAtivos"));

			while (rs.next()) {
				usuariosMaisAtivos.add(rs.getString(1));
			}
			logger.debug(" usuarios mais ativos = "+collection.size());
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO 3]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO3]:", e);
        } finally {
            close(rs);
            close(stmt);
            close(conexao);
        }
		return usuariosMaisAtivos ;
	}


	/**
	 * busca ajudas reprovadas
	 */
	public Collection<Novidade> buscaAjudasReprovadas() throws DAOException {
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		Collection<Novidade> resultado= new ArrayList<Novidade>();
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"novidade.buscaAjudasReprovadas");
            rset = pstmt.executeQuery();
            while (rset.next()) {
//            	 SELECT  jt.id,   j.id jogo_id, j.nome jogo ,u.user_id ,u.username,u.user_email , jt.aprovado
            	Novidade vo = new Novidade();
        	    vo.setId(rset.getInt("id"));
        	    vo.setDataPublic(rset.getDate("data_public"));
        	    vo.setLink(rset.getString("link"));
        	    vo.setTexto(rset.getString("texto"));
        	    vo.getUsuario().setId(rset.getInt("usuario_id"));
        	    vo.setAprovado(rset.getInt("aprovado"));
        	    if (rset.getInt("aprovado")==Constantes.APROVADO)
        	     vo.setAprovado(true);
        	    else
        	     vo.setAprovado(false);
            	resultado.add(vo);
            }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR AJUDAS REPROVADAS]:" + e.getMessage());
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
         pstmt = createPreparedStatement(conexao,"novidade.atualizaAprovacao");
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

}