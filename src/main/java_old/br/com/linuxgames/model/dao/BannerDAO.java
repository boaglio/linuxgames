package br.com.linuxgames.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.BannerVO;

/**
 * Novidades
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class BannerDAO extends AbstractDAO {

	private static BannerDAO instance = new BannerDAO();

    /**
     * Singleton
     */
	public static BannerDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private BannerDAO () {
		setInsertSQL("banner.insere");
		setUpdateSQL("banner.atualiza");
		setDeleteSQL("banner.remove");
		setBuscaUmSQL("banner.buscaUm");
		setBuscaTodosSQL("banner.buscaTudo");
	};

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		BannerVO vo = new BannerVO();
	    vo.setId(rset.getInt("id"));
	    vo.setImagem(rset.getString("imagem"));
	    vo.setDtInicio(rset.getDate("dt_inicio"));
	    vo.setDtFim(rset.getDate("dt_fim"));
	    vo.getFabricanteVO().setId(rset.getInt("fabricante_id"));
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		BannerVO vo = (BannerVO) object;
		pstmt.setString(1,vo.getImagem());
        pstmt.setTimestamp(2,new Timestamp(vo.getDtInicio().getTime()));
        if (vo.getDtFim()!=null)
         pstmt.setTimestamp(3,new Timestamp(vo.getDtFim().getTime()));
        else
         pstmt.setNull(3,java.sql.Types.DATE);
        pstmt.setInt(4,vo.getFabricanteVO().getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		BannerVO vo = (BannerVO) object;
		pstmt.setString(1,vo.getImagem());
        pstmt.setTimestamp(2,new Timestamp(vo.getDtInicio().getTime()));
        if (vo.getDtFim()!=null)
            pstmt.setTimestamp(3,new Timestamp(vo.getDtFim().getTime()));
           else
            pstmt.setNull(3,java.sql.Types.DATE);
        pstmt.setInt(4,vo.getFabricanteVO().getId());
        pstmt.setInt(5,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		BannerVO vo = (BannerVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}