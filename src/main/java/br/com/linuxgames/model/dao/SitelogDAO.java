package br.com.linuxgames.model.dao;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.SitelogVO;

/**
 * Sitelogs
 *
 * @author Fernando Boaglio
 *
 * @version 1.0 - 4/10/2005
 */
public class SitelogDAO extends AbstractDAO {

	private static SitelogDAO instance = new SitelogDAO();

    /**
     * Singleton
     */
	public static SitelogDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private SitelogDAO () {
		setInsertSQL("sitelog.insere");
		setUpdateSQL("sitelog.atualiza");
		setDeleteSQL("sitelog.remove");
		setBuscaUmSQL("sitelog.buscaUm");
		setBuscaTodosSQL("sitelog.buscaTudo");
	};

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		SitelogVO vo = (SitelogVO) object;
        pstmt.setTimestamp(1,new Timestamp( vo.getData().getTime()));
		pstmt.setString(2,vo.getVersao());
		pstmt.setString(3,vo.getDescricao());
		return pstmt;
	}
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		SitelogVO vo = (SitelogVO) object;
        pstmt.setTimestamp(1, new Timestamp(vo.getData().getTime()));
		pstmt.setString(2,vo.getVersao());
		pstmt.setString(3,vo.getDescricao());
		pstmt.setInt(4,vo.getId());
        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	    SitelogVO vo = (SitelogVO) object;
	    pstmt.setInt(1,vo.getId());
	    return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		SitelogVO vo = new SitelogVO();
	    vo.setId(rset.getInt("id"));
	    vo.setData(rset.getDate("data"));
	    vo.setVersao(rset.getString("versao"));
	    vo.setDescricao(rset.getString("descricao"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}