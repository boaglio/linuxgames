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
import br.com.linuxgames.model.vo.VersaoDeEmuladorVO;

public class VersaoDeEmuladorDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static VersaoDeEmuladorDAO instance = new VersaoDeEmuladorDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private VersaoDeEmuladorDAO() {
	 setInsertSQL("versaoDeEmulador.insere");
	 setUpdateSQL("versaoDeEmulador.atualiza");
	 setDeleteSQL("versaoDeEmulador.remove");
	 setBuscaUmSQL("versaoDeEmulador.buscaUm");
	 setBuscaTodosSQL("versaoDeEmulador.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static VersaoDeEmuladorDAO getinstance() {
		return instance;
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
            pstmt = createPreparedStatement(conexao,"versaoDeEmulador.buscaPorEmulador");
            pstmt.setInt(1,emuId);
            rset = pstmt.executeQuery();
            while (rset.next()) {
              retorno.add(setVO(rset));
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
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		VersaoDeEmuladorVO vo = new VersaoDeEmuladorVO();
		vo.setId(rset.getInt("id"));
	    vo.setRelease(rset.getString("release"));
	    vo.setLink(rset.getString("link"));
	    vo.setObs(rset.getString("obs"));
	    vo.setDataLancamento(rset.getDate("data_lancamento"));
	    vo.getEmulador().setId(rset.getInt("tipo_id"));
	    vo.getEmulador().setNome(rset.getString("nome"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeEmuladorVO vo = (VersaoDeEmuladorVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getEmulador().getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeEmuladorVO vo = (VersaoDeEmuladorVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getEmulador().getId());
        pstmt.setInt(6,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeEmuladorVO vo = (VersaoDeEmuladorVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
