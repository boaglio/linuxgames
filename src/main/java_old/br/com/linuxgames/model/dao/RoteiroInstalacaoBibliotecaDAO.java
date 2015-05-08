package br.com.linuxgames.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoBibliotecaVO;

public class RoteiroInstalacaoBibliotecaDAO extends AbstractDAO {

	private static RoteiroInstalacaoBibliotecaDAO instance = new RoteiroInstalacaoBibliotecaDAO();

	/**
	 * construtor privado
	 */
	private RoteiroInstalacaoBibliotecaDAO() {
		setInsertSQL("roteiroDeInstalacaoBiblioteca.insere");
		setUpdateSQL("roteiroDeInstalacaoBiblioteca.atualiza");
		setDeleteSQL("roteiroDeInstalacaoBiblioteca.remove");
		setBuscaUmSQL("roteiroDeInstalacaoBiblioteca.buscaUm");
		setBuscaTodosSQL("roteiroDeInstalacaoBiblioteca.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static RoteiroInstalacaoBibliotecaDAO getinstance() {
		return instance;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	    RoteiroInstalacaoBibliotecaVO vo = (RoteiroInstalacaoBibliotecaVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getDistro().getId());
	    pstmt.setInt(3,vo.getUsuario().getId());
	    pstmt.setString(4,vo.getDescricao());
	    pstmt.setInt(5,vo.getAprovado());
	    return pstmt;
	 }

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		RoteiroInstalacaoBibliotecaVO vo = (RoteiroInstalacaoBibliotecaVO) object;
	    pstmt.setInt(1,vo.getBiblioteca().getId());
	    pstmt.setInt(2,vo.getDistro().getId());
	    pstmt.setInt(3,vo.getUsuario().getId());
	    pstmt.setString(4,vo.getDescricao());
	    pstmt.setInt(5,vo.getAprovado());  
	    pstmt.setInt(6,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		RoteiroInstalacaoBibliotecaVO vo = (RoteiroInstalacaoBibliotecaVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException {
		RoteiroInstalacaoBibliotecaVO vo = new RoteiroInstalacaoBibliotecaVO();
	    vo.setId(rset.getInt(1));
	    vo.getBiblioteca().setId(rset.getInt(2));
		vo.getBiblioteca().setNome(rset.getString(3));
		vo.getDistro().setId(rset.getInt(4));
	    vo.getDistro().setNome(rset.getString(5));
		vo.getUsuario().setId(rset.getInt(6));
	    vo.getUsuario().setEmail(rset.getString(7));
	    vo.setDescricao(rset.getString(8));
	    vo.setAprovado(rset.getInt(9));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
