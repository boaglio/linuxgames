package br.com.linuxgames.model.dao;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.vo.ImagemVO;

/**
 * Imagens de jogos e emuladores
 *
 * @author Fernando Boaglio
 * @version 1.0 - 4/10/2005
 */
public class ImagemDAO extends AbstractDAO {

	private static ImagemDAO instance = new ImagemDAO();
	Logger logger = Logger.getLogger(this.getClass().getName());
	
	public static ImagemDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private ImagemDAO () {
	 setInsertSQL("imagens.insere");
	 setUpdateSQL("imagens.atualiza");
	 setDeleteSQL("imagens.remove");
	 setBuscaUmSQL("imagens.buscaUm");
	 setBuscaTodosSQL("imagens.buscaTudo");
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
	   ImagemVO vo = (ImagemVO) object;
       pstmt.setString(1, vo.getFileName());
	   pstmt.setString(2, vo.getFileMimeType());
	   pstmt.setString(3,vo.getFilePath());
	   pstmt.setInt(4,vo.getJogo().getId());
	   pstmt.setInt(5,vo.getEmu().getId());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
  	   ImagemVO vo = (ImagemVO) object;
	   pstmt.setInt(1,vo.getJogo().getId());
	   pstmt.setInt(2,vo.getEmu().getId());
	   pstmt.setInt(3,vo.getId());
	   return pstmt;
	}
	
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	    ImagemVO vo = (ImagemVO) object;
	    pstmt.setInt(1,vo.getId());
	    return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		ImagemVO vo = new ImagemVO();
	    vo.setId(rset.getInt("id"));
	    vo.setTipo(rset.getInt("tipo"));
	    vo.setFileName(rset.getString("nome"));
	    vo.setFileMimeType(rset.getString("file_mimetype"));
//	    try {
//	      String nome = rset.getString("nome");
//	      if (!"".equals(nome)) {
//	       vo.getJogo().setNome(nome);	
//	      }
//	     }
//	     catch (Exception e) {
//	     }
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

    /**
	 * busca um objeto
	 */
	public Object buscaUmaImagem(Object object) throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conexao = null;
	   ResultSet rs = null;
	   ImagemVO vo = new ImagemVO();
       try {
         conexao = dao.getConexao();    	   
         pstmt = setPreparedStatementBuscaUm(object,createPreparedStatement(conexao,"imagens.buscaUmaImagem"));
         rs = pstmt.executeQuery();
         while (rs.next()) {
        	vo.setFileContent(rs.getBlob("file_content"));
        	vo.setFileMimeType(rs.getString("file_mimetype"));
        	vo.setFileName(rs.getString("nome"));
    	    vo.setId(rs.getInt("id"));
         }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO BUSCA UM ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	    return vo;
	}
}