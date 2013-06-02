package br.com.linuxgames.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.BibliotecaVO;

public class BibliotecaDAO extends AbstractDAO {

	private static BibliotecaDAO instance = new BibliotecaDAO();

	/**
	 * construtor privado
	 */
	private BibliotecaDAO() {
		setInsertSQL("biblioteca.insere");
		setUpdateSQL("biblioteca.atualiza");
		setDeleteSQL("biblioteca.remove");
		setBuscaUmSQL("biblioteca.buscaUm");
		setBuscaTodosSQL("biblioteca.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static BibliotecaDAO getinstance() {
		return instance;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	    BibliotecaVO vo = (BibliotecaVO) object;
	    pstmt.setString(1,vo.getNome());
	    pstmt.setString(2,vo.getDescricao());
	    pstmt.setString(3,vo.getLink());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		BibliotecaVO vo = (BibliotecaVO) object;
        pstmt.setString(1,vo.getNome());
        pstmt.setString(2,vo.getDescricao());
        pstmt.setString(3,vo.getLink());
        pstmt.setInt(4,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		BibliotecaVO vo = (BibliotecaVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		BibliotecaVO vo = new BibliotecaVO();
	    vo.setId(rset.getInt("id"));
	    vo.setNome(rset.getString("nome"));
	    vo.setDescricao(rset.getString("descricao"));
	    vo.setLink(rset.getString("link"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
