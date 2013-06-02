package br.com.linuxgames.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.VersaoDeDistroVO;

public class VersaoDeDistroDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static VersaoDeDistroDAO instance = new VersaoDeDistroDAO();

	/**
	 * construtor privado que carrega os SQLs
	 */
	private VersaoDeDistroDAO() {
	 setInsertSQL("versaoDeDistro.insere");
	 setUpdateSQL("versaoDeDistro.atualiza");
	 setDeleteSQL("versaoDeDistro.remove");
	 setBuscaUmSQL("versaoDeDistro.buscaUm");
	 setBuscaTodosSQL("versaoDeDistro.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static VersaoDeDistroDAO getinstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		VersaoDeDistroVO vo = new VersaoDeDistroVO();
		vo.setId(rset.getInt("id"));
	    vo.setRelease(rset.getString("release"));
	    vo.setLink(rset.getString("link"));
	    vo.setObs(rset.getString("obs"));
	    vo.setDataLancamento(rset.getDate("data_lancamento"));
	    vo.getDistro().setId(rset.getInt("tipo_id"));
	    vo.getDistro().setNome(rset.getString("nome"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeDistroVO vo = (VersaoDeDistroVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getDistro().getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeDistroVO vo = (VersaoDeDistroVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getDistro().getId());
        pstmt.setInt(6,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeDistroVO vo = (VersaoDeDistroVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
