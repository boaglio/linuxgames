package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.Usuario;

/**
 * Usuarios
 *
 * @author Fernando Boaglio
 *
 */
public class UsuariosDAO extends AbstractDAO {

	private static UsuariosDAO instance = new UsuariosDAO();

	private static Logger logger = Logger.getLogger(UsuariosDAO.class);

	/**
	 * construtor privado
	 */
	private UsuariosDAO() {
		setBuscaTodosSQL("usuario.buscaTudo");
		setBuscaUmSQL("usuario.buscaUm");
	}

	/**
	 * singleton
	 */
	public static UsuariosDAO getInstance() {
		return instance;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		Usuario vo = new Usuario();
		vo.setId(rset.getInt("id"));
	    vo.setEmail(rset.getString("email"));
		vo.setDataDoCadastro(rset.getTimestamp("dt_cadastro"));
        return vo;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	    return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		Usuario vo = (Usuario) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}


	public String buscaNomePorId(int userId) throws SQLException {

		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
	    String nome = null;
        try {
             conexao = dao.getConexao();
             pstmt = createPreparedStatement(conexao,"usuario.buscaNomePorId");
             pstmt.setInt(1,userId);
             rs = pstmt.executeQuery();
             while (rs.next()) {
            	 nome = rs.getString(1);
             }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
        return nome;
	}


	public int buscaRank(int userId) throws DAOException {

		PreparedStatement pstmt = null;
		Connection conexao = null;
		ResultSet rs = null;
		DAOManager dao = DAOManager.getInstance();
	    int posicao =1;
        try {
             conexao = dao.getConexao();
             pstmt = createPreparedStatement(conexao,"usuario.buscaRank");
             rs = pstmt.executeQuery();
             while (rs.next()) {
            	 int storedUserId = rs.getInt(1);
            	 if (storedUserId == userId)
            		return posicao;
            	 posicao++;
             }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
        return posicao;
	}

}
