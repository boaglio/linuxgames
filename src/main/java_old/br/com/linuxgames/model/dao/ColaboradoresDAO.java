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
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.KeyValueVO;

public class ColaboradoresDAO extends AbstractDAO {

	private static ColaboradoresDAO instance = new ColaboradoresDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado
	 */
	private ColaboradoresDAO() {
		setInsertSQL("colaborador.insere");
		setUpdateSQL("colaborador.atualiza");
		setDeleteSQL("colaborador.remove");
		setBuscaUmSQL("colaborador.buscaUm");
		setBuscaTodosSQL("colaborador.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static ColaboradoresDAO getinstance() {
		return instance;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		Colaborador vo = new Colaborador();
		vo.setId(rset.getInt("id"));
	    vo.setSenha(rset.getString("email"));
		vo.setAtivo(rset.getInt("ativo"));
		vo.setAdmin(rset.getInt("admin"));
        return vo;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		Colaborador vo = (Colaborador) object;
		pstmt.setString(1,vo.getSenha());
		pstmt.setInt(2,vo.getAdmin());
	    pstmt.setInt(3,vo.getId());
	    pstmt.setInt(4,vo.getAtivo());
	    return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		Colaborador vo = (Colaborador) object;
		pstmt.setString(1,vo.getSenha());
		pstmt.setInt(2,vo.getAdmin());
		pstmt.setInt(3,vo.getAtivo());
		pstmt.setInt(4,vo.getId());
		pstmt.setInt(5,vo.getIdOld());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		Colaborador vo = (Colaborador) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}


	public Collection<KeyValueVO> buscaColaboradores() throws DAOException {
		Collection<KeyValueVO> retorno = new ArrayList<KeyValueVO>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"colaborador.buscaColaboradores");
            rset = pstmt.executeQuery();
            while (rset.next()) {
        	  KeyValueVO vo = new KeyValueVO();
        	  vo.setKey(rset.getString("nome"));
        	  vo.setValue(rset.getString("email"));
              retorno.add(vo);
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR COLABORADORES]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return (List<KeyValueVO>) retorno;
	}

	public Collection<KeyValueVO> buscaAdmins() throws DAOException {
		Collection<KeyValueVO> retorno = new ArrayList<KeyValueVO>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		logger.info("[trazendo a lista...]:");
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"colaborador.buscaAdmins");
            rset = pstmt.executeQuery();
            while (rset.next()) {
        	  KeyValueVO vo = new KeyValueVO();
        	  vo.setKey(rset.getString("nome"));
        	  vo.setValue(rset.getString("email"));
              retorno.add(vo);
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR COLABORADORES]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return (List<KeyValueVO>) retorno;
	}

	public boolean buscaSeUsuarioEhAdministradorAtivos(int idUser) throws DAOException {
		boolean retorno = false;
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"colaborador.buscaSeUsuarioEhAdministradorAtivos");
            pstmt.setInt(1,idUser);

            rset = pstmt.executeQuery();
            int contador =0;
            while (rset.next()) {
            	contador = rset.getInt(1);
            }

            if (contador>0)
             retorno=true;

		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR COLABORADORES 2 ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO 2 "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return retorno;
	}

}
