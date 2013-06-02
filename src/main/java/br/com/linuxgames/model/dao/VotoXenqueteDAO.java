package br.com.linuxgames.model.dao;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.VotoXenqueteVO;

/**
 * VotoXenquetes
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class VotoXenqueteDAO extends AbstractDAO {

	private static VotoXenqueteDAO instance = new VotoXenqueteDAO();

    /**
     * Singleton
     */
	public static VotoXenqueteDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private VotoXenqueteDAO () {
	 setInsertSQL("votoXenquete.insere");
	 setUpdateSQL("votoXenquete.atualiza");
	 setDeleteSQL("votoXenquete.remove");
	 setBuscaUmSQL("votoXenquete.buscaUm");
	 setBuscaTodosSQL("votoXenquete.buscaTudo");
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	   VotoXenqueteVO vo = (VotoXenqueteVO) object;
       pstmt.setString(1, vo.getIP());
	   pstmt.setInt(2,vo.getEnquete().getId());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
  	   VotoXenqueteVO vo = (VotoXenqueteVO) object;
	   pstmt.setString(1, vo.getIP());
	   pstmt.setInt(2,vo.getEnquete().getId());
	   pstmt.setString(3, vo.getOldIP());
	   pstmt.setInt(4,vo.getOldIdEnquete());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	    VotoXenqueteVO vo = (VotoXenqueteVO) object;
	    pstmt.setString(1,vo.getIP());
	    pstmt.setInt(2,vo.getEnquete().getId());
	    return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		VotoXenqueteVO vo = new VotoXenqueteVO();
	    vo.setIP(rset.getString("ip"));
	    vo.getEnquete().setId(rset.getInt("id_enquete"));
	    vo.getEnquete().setTitulo(rset.getString("titulo"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}