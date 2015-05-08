package br.com.linuxgames.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.DistroVO;

public class DistroDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static DistroDAO instance = new DistroDAO();

	/**
	 * construtor privado que carrega os SQLs
	 */
	private DistroDAO() {
		setInsertSQL("distro.insere");
		setUpdateSQL("distro.atualiza");
		setDeleteSQL("distro.remove");
		setBuscaUmSQL("distro.buscaUm");
		setBuscaTodosSQL("distro.buscaTudo");
	}

	/**
	 * singleton
	 */
	public static DistroDAO getinstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		DistroVO vo = new DistroVO();
	    vo.setId(rset.getInt("id"));
	    vo.setNome(rset.getString("nome"));
	    vo.setAbreviacao(rset.getString("abreviacao"));
	    vo.setSiteOficial(rset.getString("site_oficial"));
	    vo.setLinkLogo(rset.getString("link_logo"));
	    vo.setDescricao(rset.getString("descricao"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		DistroVO vo = (DistroVO) object;
		pstmt.setString(1,vo.getNome());
        pstmt.setString(2,vo.getAbreviacao());
        pstmt.setString(3,vo.getSiteOficial());
        pstmt.setString(4,vo.getLinkLogo());
        pstmt.setString(5,vo.getDescricao());
        pstmt.setInt(6,vo.getFabricante().getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		DistroVO vo = (DistroVO) object;
        pstmt.setString(1,vo.getNome());
        pstmt.setString(2,vo.getAbreviacao());
        pstmt.setString(3,vo.getSiteOficial());
        pstmt.setString(4,vo.getLinkLogo());
        pstmt.setString(5,vo.getDescricao());
        pstmt.setInt(6,vo.getFabricante().getId());
        pstmt.setInt(7,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		DistroVO vo = (DistroVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}
