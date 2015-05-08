package br.com.linuxgames.model.dao;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.ArtigoExternoVO;

/**
 * Artigos externos
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class ArtigoExternoDAO extends AbstractDAO {

	private static ArtigoExternoDAO instance = new ArtigoExternoDAO();

    /**
     * Singleton
     */
	public static ArtigoExternoDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private ArtigoExternoDAO () {
		setInsertSQL("artigoExterno.insere");
		setUpdateSQL("artigoExterno.atualiza");
		setDeleteSQL("artigoExterno.remove");
		setBuscaUmSQL("artigoExterno.buscaUm");
		setBuscaTodosSQL("artigoExterno.buscaTudo");
	};

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		ArtigoExternoVO vo = (ArtigoExternoVO) object;
		pstmt.setString(1,vo.getTitulo());
		pstmt.setString(2,vo.getLink());
		pstmt.setString(3,vo.getFonte());
		return pstmt;
	}
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		ArtigoExternoVO vo = (ArtigoExternoVO) object;
		pstmt.setString(1,vo.getTitulo());
		pstmt.setString(2,vo.getLink());
		pstmt.setString(3,vo.getFonte());
		pstmt.setInt(4,vo.getId());
        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	   ArtigoExternoVO vo = (ArtigoExternoVO) object;
	   pstmt.setInt(1,vo.getId());
	   return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		ArtigoExternoVO vo = new ArtigoExternoVO();
	    vo.setId(rset.getInt("id"));
	    vo.setTitulo(rset.getString("titulo"));
	    vo.setLink(rset.getString("link"));
	    vo.setFonte(rset.getString("fonte"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}