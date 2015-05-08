package br.com.linuxgames.model.dao;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.VotoXjogoVO;

/**
 * VotoXjogos
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class VotoXjogoDAO extends AbstractDAO {

	private static VotoXjogoDAO instance = new VotoXjogoDAO();

    /**
     * Singleton
     */
	public static VotoXjogoDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private VotoXjogoDAO () {
	 setInsertSQL("votoXjogo.insere");
	 setUpdateSQL("votoXjogo.atualiza");
	 setDeleteSQL("votoXjogo.remove");
	 setBuscaUmSQL("votoXjogo.buscaUm");
	 setBuscaTodosSQL("votoXjogo.buscaTudo");
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	   VotoXjogoVO vo = (VotoXjogoVO) object;
       pstmt.setString(1, vo.getIP());
	   pstmt.setInt(2,vo.getIdJogo());
	   pstmt.setInt(3,vo.getIdUsuario());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
  	   VotoXjogoVO vo = (VotoXjogoVO) object;
	   pstmt.setString(1, vo.getIP());
	   pstmt.setInt(2,vo.getIdJogo());
	   pstmt.setInt(3, vo.getIdUsuario());
	   pstmt.setInt(4,vo.getOldIdUsuario());
	   pstmt.setInt(5,vo.getOldIdJogo());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	    VotoXjogoVO vo = (VotoXjogoVO) object;
	    pstmt.setInt(1,vo.getIdUsuario());
	    pstmt.setInt(2,vo.getIdJogo());
	    return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		VotoXjogoVO vo = new VotoXjogoVO();
	    vo.setIP(rset.getString("ip"));
	    vo.setIdJogo(rset.getInt("id_jogo"));
	    vo.setIdUsuario(rset.getInt("id_usuario"));
	    vo.setNomeJogo(rset.getString("nome"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}