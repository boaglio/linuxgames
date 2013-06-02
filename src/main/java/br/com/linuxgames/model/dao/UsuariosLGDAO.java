package br.com.linuxgames.model.dao;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.DistroVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.util.UsuariosHelper;

/**
 * Usuario USUARIOS_LG
 *
 * Dados do usuario
 *
 * @author Fernando Boaglio
 *
 * @version 1.0 - 14/02/2009
 */
public class UsuariosLGDAO extends AbstractDAO {

	private static UsuariosLGDAO instance = new UsuariosLGDAO();


	private static Logger logger = Logger.getLogger(UsuariosLGDAO.class);


    /**
     * Singleton
     */
	public static UsuariosLGDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private UsuariosLGDAO () {
		setInsertSQL("usuarioLG.insere");
		setUpdateSQL("usuarioLG.atualiza");
		setDeleteSQL("usuarioLG.remove");
		setBuscaUmSQL("usuarioLG.buscaUm");
		setBuscaTodosSQL("usuarioLG.buscaTudo");
	};

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		Usuario  vo = (Usuario ) object;
        pstmt.setString(1,vo.getEmail());
        pstmt.setString(2,vo.getSenha());
        if (vo.getDistro()==null)
        	pstmt.setNull(3,Types.INTEGER);
         else
 		    pstmt.setInt(3,vo.getDistro().getId());

		pstmt.setString(4,vo.getReceberNewsletter());
		pstmt.setString(5,vo.getMinhaMaquina());
        pstmt.setInt(6,vo.getNrJogosContribuidos());
        pstmt.setInt(7,vo.getNrNoticiasContribuidas());
        pstmt.setInt(8,vo.getNrEmuladoresContribuidos());
        pstmt.setInt(9,vo.getTotalContribuicoes());
        pstmt.setTimestamp(10, new Timestamp(vo.getDataDoCadastro().getTime()));
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		Usuario  vo = (Usuario ) object;
        pstmt.setString(1,vo.getEmail());
        pstmt.setString(2,vo.getSenha());
		pstmt.setInt(3,vo.getDistro().getId());
		pstmt.setString(4,vo.getReceberNewsletter());
		pstmt.setString(5,vo.getMinhaMaquina());
        pstmt.setInt(6,vo.getNrJogosContribuidos());
        pstmt.setInt(7,vo.getNrNoticiasContribuidas());
        pstmt.setInt(8,vo.getNrEmuladoresContribuidos());
        pstmt.setInt(9,vo.getTotalContribuicoes());
        pstmt.setTimestamp(10, new Timestamp(vo.getDataDoCadastro().getTime()));
        pstmt.setInt(11,vo.getId());

        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	    Usuario  vo = (Usuario ) object;
	    pstmt.setInt(1,vo.getId());
	    return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		Usuario  vo = new Usuario();
	    vo.setId(rset.getInt("id"));
	    vo.setEmail(rset.getString("email"));
	    vo.setSenha(rset.getString("senha"));
	    vo.setDistro(new DistroVO( rset.getInt("distro_id")));
	    vo.setReceberNewsletter(rset.getString("receber_newsletter"));
	    vo.setMinhaMaquina(rset.getString("minha_maquina"));
	    vo.setNrJogosContribuidos(rset.getInt("nr_jogos_contribuidos"));
	    vo.setNrNoticiasContribuidas(rset.getInt("nr_noticias_contribuidas"));
	    vo.setNrEmuladoresContribuidos(rset.getInt("nr_emuladores_contribuidos"));
	    vo.setTotalContribuicoes(rset.getInt("total_contribuicoes"));

        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}


	public Usuario buscaUmCompleto(int userId) throws DAOException {

		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rset = null;
		DAOManager dao = DAOManager.getInstance();
 		Usuario  vo = new Usuario();
        try {
             conexao = dao.getConexao();
             pstmt = createPreparedStatement(conexao,"usuarioLG.buscaNomePorId");
             pstmt.setInt(1,userId);
             rset = pstmt.executeQuery();
             while (rset.next()) {
        	    vo.setId(rset.getInt("id"));
        	    vo.setEmail(rset.getString("email"));
        	    vo.setSenha(rset.getString("senha"));
        	    vo.setDistro(new DistroVO( rset.getInt("distro_id")));
        	    vo.setReceberNewsletter(rset.getString("receber_newsletter"));
        	    vo.setMinhaMaquina(rset.getString("minha_maquina"));
        	    vo.setNrJogosContribuidos(rset.getInt("nr_jogos_contribuidos"));
        	    vo.setNrNoticiasContribuidas(rset.getInt("nr_noticias_contribuidas"));
        	    vo.setNrEmuladoresContribuidos(rset.getInt("nr_emuladores_contribuidos"));
        	    vo.setTotalContribuicoes(rset.getInt("total_contribuicoes"));
             }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
        return vo;
	}

	public Usuario buscaPorEmail(String email) throws DAOException {

		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rset = null;
		DAOManager dao = DAOManager.getInstance();
 		Usuario  vo = new Usuario();
        try {
             conexao = dao.getConexao();
             pstmt = createPreparedStatement(conexao,"usuarioLG.buscaPorEmail");
             pstmt.setString(1,email);
             rset = pstmt.executeQuery();
             while (rset.next()) {
        	    vo.setId(rset.getInt("id"));
        	    vo.setEmail(rset.getString("email"));
        	    vo.setSenha(rset.getString("senha"));
        	    vo.setDistro(new DistroVO( rset.getInt("distro_id")));
        	    vo.setReceberNewsletter(rset.getString("receber_newsletter"));
        	    vo.setMinhaMaquina(rset.getString("minha_maquina"));
        	    vo.setNrJogosContribuidos(rset.getInt("nr_jogos_contribuidos"));
        	    vo.setNrNoticiasContribuidas(rset.getInt("nr_noticias_contribuidas"));
        	    vo.setNrEmuladoresContribuidos(rset.getInt("nr_emuladores_contribuidos"));
        	    vo.setTotalContribuicoes(rset.getInt("total_contribuicoes"));
             }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO 2]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO 2]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
        return vo;
	}

	public void atualizaJogosContribuidos(int userId) throws SQLException {

		UsuariosHelper.verificaSeExisteUsuario(userId);

		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
	    int linhasAfetadas = 0 ;
        try {
             conexao = dao.getConexao();
             pstmt = createPreparedStatement(conexao,"usuarioLG.atualizaJogosContribuidos");
             pstmt.setInt(1,userId);
             linhasAfetadas = pstmt.executeUpdate();
             logger.info("user="+userId+" rows="+linhasAfetadas);
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
	}

	public void atualizaNoticiasContribuidas(int userId) throws SQLException {

		UsuariosHelper.verificaSeExisteUsuario(userId);

		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
		int linhasAfetadas = 0 ;
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"usuarioLG.atualizaNoticiasContribuidas");
            pstmt.setInt(1,userId);
            linhasAfetadas = pstmt.executeUpdate();
            logger.info("user="+userId+" rows="+linhasAfetadas);
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO]:", e);
       } finally {
           close(rs);
           close(pstmt);
           close(conexao);
        }
	}

	public void atualizaEmuladoresContribuidos(int userId) throws SQLException {

		UsuariosHelper.verificaSeExisteUsuario(userId);

		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
		int linhasAfetadas = 0 ;
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"usuarioLG.atualizaEmuladoresContribuidos");
            pstmt.setInt(1,userId);
            linhasAfetadas = pstmt.executeUpdate();
            logger.info("user="+userId+" rows="+linhasAfetadas);
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO]:", e);
       } finally {
           close(rs);
           close(pstmt);
           close(conexao);
        }
	}

	public void auditaUsuario(int userId,String login) throws SQLException {


		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
	    int linhasAfetadas = 0 ;
        try {
             conexao = dao.getConexao();
             pstmt = createPreparedStatement(conexao,"usuarioLG.audita");
             pstmt.setInt(1,userId);
             pstmt.setString(2,login);
             pstmt.setTimestamp(3, new Timestamp((new Date()).getTime()));
             linhasAfetadas = pstmt.executeUpdate();
             logger.info("user="+userId+" rows="+linhasAfetadas);
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
	}

}