package br.com.linuxgames.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.VersaoDeBibliotecaVO;

public class VersaoDeBibliotecaDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static VersaoDeBibliotecaDAO instance = new VersaoDeBibliotecaDAO();

	/**
	 * construtor privado que carrega os SQLs
	 */
	private VersaoDeBibliotecaDAO() {
	 setInsertSQL("versaoDeBiblioteca.insere");
	 setUpdateSQL("versaoDeBiblioteca.atualiza");
	 setDeleteSQL("versaoDeBiblioteca.remove");
	 setBuscaUmSQL("versaoDeBiblioteca.buscaUm");
	 setBuscaTodosSQL("versaoDeBiblioteca.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static VersaoDeBibliotecaDAO getinstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		VersaoDeBibliotecaVO vo = new VersaoDeBibliotecaVO();
		vo.setId(rset.getInt("id"));
	    vo.setRelease(rset.getString("release"));
	    vo.setLink(rset.getString("link"));
	    vo.setObs(rset.getString("obs"));
	    vo.setDataLancamento(rset.getDate("data_lancamento"));
	    vo.getBiblioteca().setId(rset.getInt("tipo_id"));
	    vo.getBiblioteca().setNome(rset.getString("nome"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeBibliotecaVO vo = (VersaoDeBibliotecaVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getBiblioteca().getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeBibliotecaVO vo = (VersaoDeBibliotecaVO) object;
		pstmt.setString(1,vo.getRelease());
        pstmt.setString(2,vo.getLink());
        pstmt.setString(3,vo.getObs());
        pstmt.setTimestamp(4, new Timestamp(vo.getDataLancamento().getTime()));
        pstmt.setInt(5,vo.getBiblioteca().getId());
        pstmt.setInt(6,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		VersaoDeBibliotecaVO vo = (VersaoDeBibliotecaVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
