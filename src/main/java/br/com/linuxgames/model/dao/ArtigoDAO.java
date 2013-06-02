package br.com.linuxgames.model.dao;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.vo.ArtigoVO;
import br.com.linuxgames.util.JDBCHelper;

/**
 * Artigos
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class ArtigoDAO extends AbstractDAO {

	private static ArtigoDAO instance = new ArtigoDAO();

    /**
     * Singleton
     */
	public static ArtigoDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private ArtigoDAO () {
		setInsertSQL("artigo.insere");
		setUpdateSQL("artigo.atualiza");
		setDeleteSQL("artigo.remove");
		setBuscaUmSQL("artigo.buscaUm");
		setBuscaTodosSQL("artigo.buscaTudo");
	};


	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		ArtigoVO vo = (ArtigoVO) object;
		pstmt.setString(1,vo.getTitulo());
		pstmt.setString(2,vo.getTexto());
        pstmt.setTimestamp(3,new Timestamp( vo.getDataPublic().getTime()));
        pstmt.setBoolean(4,gravaBoolean(vo.getAprovado()));
        pstmt.setInt(5,vo.getUsuario().getId());
        pstmt.setInt(6,vo.getVotos());
        pstmt.setDouble(7,vo.getNotaGeral());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		ArtigoVO vo = (ArtigoVO) object;
		pstmt.setString(1,vo.getTitulo());
		pstmt.setString(2,vo.getTexto());
		pstmt.setTimestamp(3, new Timestamp(vo.getDataPublic().getTime()));
		pstmt.setBoolean(4,gravaBoolean(vo.getAprovado()));
		pstmt.setInt(5,vo.getUsuario().getId());
        pstmt.setInt(6,vo.getVotos());
        pstmt.setDouble(7,vo.getNotaGeral());
        pstmt.setInt(8,vo.getId());
        return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	   ArtigoVO vo = (ArtigoVO) object;
	   pstmt.setInt(1,vo.getId());
	   return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		ArtigoVO vo = new ArtigoVO();
	    vo.setId(rset.getInt("id"));
	    vo.setTitulo(rset.getString("titulo"));
	    vo.setTexto(rset.getString("texto"));
	    vo.setDataPublic(rset.getDate("data_public"));
	    vo.setAprovado(calculaBoolean("aprovado",rset));
	    vo.getUsuario().setId(rset.getInt("usuario_id"));
	    if (JDBCHelper.haveColumn("username", rset)) {
	     vo.getUsuario().setEmail(rset.getString("username"));
	    }
	    vo.setVotos(rset.getInt("votos"));
	    vo.setNotaGeral(rset.getDouble("nota_geral"));
        return vo;
	}

	private int calculaBoolean(String valor,ResultSet r) throws SQLException {
		boolean b = r.getBoolean(valor);
		if (b) return 1;
		return 0;
	}

	private boolean gravaBoolean(int valor) throws SQLException {
		if (valor==1) return true;
		return false;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

}