package br.com.linuxgames.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.FabricanteVO;

public class FabricanteDAO extends AbstractDAO {

	private static FabricanteDAO instance = new FabricanteDAO();

	/**
	 * construtor privado
	 */
	private FabricanteDAO() {
		setInsertSQL("fabricante.insere");
		setUpdateSQL("fabricante.atualiza");
		setDeleteSQL("fabricante.remove");
		setBuscaUmSQL("fabricante.buscaUm");
		setBuscaTodosSQL("fabricante.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static FabricanteDAO getinstance() {
		return instance;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	    FabricanteVO vo = (FabricanteVO) object;
	    pstmt.setString(1,vo.getNome());
	    pstmt.setString(2,vo.getDescricao());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		FabricanteVO vo = (FabricanteVO) object;
        pstmt.setString(1,vo.getNome());
        pstmt.setString(2,vo.getDescricao());
        pstmt.setInt(3,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		FabricanteVO vo = (FabricanteVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		FabricanteVO vo = new FabricanteVO();
	    vo.setId(rset.getInt("id"));
	    vo.setNome(rset.getString("nome"));
	    vo.setDescricao(rset.getString("descricao"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
