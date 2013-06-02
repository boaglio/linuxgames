package br.com.linuxgames.model.dao;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.VotoXemuladorVO;

/**
 * VotoXemuladors
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class VotoXemuladorDAO extends AbstractDAO {

	private static VotoXemuladorDAO instance = new VotoXemuladorDAO();

    /**
     * Singleton
     */
	public static VotoXemuladorDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private VotoXemuladorDAO () {
	 setInsertSQL("votoXemulador.insere");
	 setUpdateSQL("votoXemulador.atualiza");
	 setDeleteSQL("votoXemulador.remove");
	 setBuscaUmSQL("votoXemulador.buscaUm");
	 setBuscaTodosSQL("votoXemulador.buscaTudo");
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	   VotoXemuladorVO vo = (VotoXemuladorVO) object;
       pstmt.setString(1, vo.getIP());
	   pstmt.setInt(2,vo.getEmulador().getId());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
  	   VotoXemuladorVO vo = (VotoXemuladorVO) object;
	   pstmt.setString(1, vo.getIP());
	   pstmt.setInt(2,vo.getEmulador().getId());
	   pstmt.setString(3, vo.getOldIP());
	   pstmt.setInt(4,vo.getOldIdEmulador());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	    VotoXemuladorVO vo = (VotoXemuladorVO) object;
	    pstmt.setString(1,vo.getIP());
	    pstmt.setInt(2,vo.getEmulador().getId());
	    return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		VotoXemuladorVO vo = new VotoXemuladorVO();
	    vo.setIP(rset.getString("ip"));
	    vo.getEmulador().setId(rset.getInt("id_emulador"));
	    vo.getEmulador().setNome(rset.getString("nome"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}