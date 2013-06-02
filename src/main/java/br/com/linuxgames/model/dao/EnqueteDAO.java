package br.com.linuxgames.model.dao;

 import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.dao.core.QueryManager;
import br.com.linuxgames.model.vo.EnqueteVO;

/**
 * Enquetes
 *
 * @author Fernando Boaglio
 * @version $version$
 */
public class EnqueteDAO extends AbstractDAO {

	private static EnqueteDAO instance = new EnqueteDAO();
	Logger logger = Logger.getLogger(this.getClass());

    /**
     * Singleton
     */
	public static EnqueteDAO getInstance() {
		return instance;
	}
	/**
	 * Construtor privado
	 */
	private EnqueteDAO () {
		setInsertSQL("enquete.insere");
		setUpdateSQL("enquete.atualiza");
		setDeleteSQL("enquete.remove");
		setBuscaUmSQL("enquete.buscaUm");
		setBuscaTodosSQL("enquete.buscaTudo");
	};

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		EnqueteVO vo = (EnqueteVO) object;

		// soma todos os votos (se mudar pra Integer, tah pronto)
		int qt1=0;
		int qt2=0;
		int qt3=0;
		int qt4=0;
		if (vo.getQt1()!=0) qt1=vo.getQt1();
		if (vo.getQt2()!=0) qt2=vo.getQt2();
		if (vo.getQt3()!=0) qt3=vo.getQt3();
		if (vo.getQt4()!=0) qt4=vo.getQt4();
		vo.setQt(qt1+qt2+qt3+qt4);

		pstmt.setString(1,vo.getTitulo());
		if (vo.isAtivo())
			pstmt.setInt(2,1);
		else
			pstmt.setInt(2,0);
		pstmt.setString(3,vo.getOpt1());
		pstmt.setInt(4,vo.getQt1());
		pstmt.setString(5,vo.getOpt2());
		pstmt.setInt(6,vo.getQt2());
		pstmt.setString(7,vo.getOpt3());
		pstmt.setInt(8,vo.getQt3());
		pstmt.setString(9,vo.getOpt4());
		pstmt.setInt(10,vo.getQt4());
		pstmt.setInt(11,vo.getQt());
		return pstmt;
	}
	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		EnqueteVO vo = (EnqueteVO) object;
		// soma todos os votos (se mudar pra Integer, tah pronto)
		int qt1=0;
		int qt2=0;
		int qt3=0;
		int qt4=0;
		if (vo.getQt1()!=0) qt1=vo.getQt1();
		if (vo.getQt2()!=0) qt2=vo.getQt2();
		if (vo.getQt3()!=0) qt3=vo.getQt3();
		if (vo.getQt4()!=0) qt4=vo.getQt4();
		vo.setQt(qt1+qt2+qt3+qt4);

		pstmt.setString(1,vo.getTitulo());
		if (vo.isAtivo())
			pstmt.setInt(2,1);
		else
			pstmt.setInt(2,0);
		pstmt.setString(3,vo.getOpt1());
		pstmt.setInt(4,vo.getQt1());
		pstmt.setString(5,vo.getOpt2());
		pstmt.setInt(6,vo.getQt2());
		pstmt.setString(7,vo.getOpt3());
		pstmt.setInt(8,vo.getQt3());
		pstmt.setString(9,vo.getOpt4());
		pstmt.setInt(10,vo.getQt4());
		pstmt.setInt(11,vo.getQt());
		pstmt.setInt(12,vo.getId());
		return pstmt;
	}
	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
	   EnqueteVO vo = (EnqueteVO) object;
	   pstmt.setInt(1,vo.getId());
	   return pstmt;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		EnqueteVO vo = new EnqueteVO();
	    vo.setId(rset.getInt("id"));
	    vo.setTitulo(rset.getString("titulo"));
	    int status = rset.getInt("status");
	    if (status==1)
	     vo.setAtivo(true);
	    vo.setOpt1(rset.getString("opt1"));
	    vo.setQt1(rset.getInt("qt1"));
	    vo.setOpt2(rset.getString("opt2"));
	    vo.setQt2(rset.getInt("qt2"));
	    vo.setOpt3(rset.getString("opt3"));
	    vo.setQt3(rset.getInt("qt3"));
	    vo.setOpt4(rset.getString("opt4"));
	    vo.setQt4(rset.getInt("qt4"));
	    vo.setQt(rset.getInt("qt"));
        return vo;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

	/**
	 * atualiza os votos
	 * @param opcao
	 * @throws DAOException
	 */
	public void opt(int opcao) throws DAOException {
		   DAOManager dao = DAOManager.getInstance();
		   PreparedStatement pstmt = null;
		   Connection conn = dao.getConexao();
		   ResultSet rs = null;
	       try {
	         Connection conexao = dao.getConexao();
	         pstmt = createPreparedStatement(conexao,"enquete.votaOpt"+opcao);
	         pstmt.execute();
			} catch (SQLException e) {
				logger.error("[ERRO DO BANCO NO VOTO"+opcao+"]:" + e.getMessage());
			} catch (Exception e) {
				logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
		    } finally {
		        close(rs);
		        close(pstmt);
		        close(conn);
		    }
	}
	
    /**
	 * busca a enquete atual
	 */ 
	public EnqueteVO buscaEnqueteAtual() throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conn = dao.getConexao();
	   ResultSet rs = null;
	   EnqueteVO vo = null;
	   logger.info("[trazendo a enquete atual...]:");
       try {
 		String query = (QueryManager.getInstance()).getQuery("enquete.buscaEnqueteAtual");
 		vo =new EnqueteVO();
 		pstmt = conn.prepareStatement(query);
         rs = pstmt.executeQuery();
         while (rs.next()) {
           Object o  = setVO(rs);
           vo = (EnqueteVO) o;
         }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO BUSCA DA ENQUETE ATUAL ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conn);
	    }
	    return vo;
	}

    /**
	 * busca o IP da base de dados
	 * Se achar, o usuario votou, retorna TRUE
	 * Se achar, eh novo voto,retorna FALSE
	 */ 
	public boolean esseIPjaVotou(String IP) throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conn = dao.getConexao();
	   ResultSet rs = null;
	   String storedIP=null;
	   logger.info("[verificando se "+IP+" ja votou...]:");
       try {
 		 String query = (QueryManager.getInstance()).getQuery("votoXenquete.buscaIPdeEnquete");
 		 pstmt = conn.prepareStatement(query);
 		 pstmt.setString(1,IP);
         rs = pstmt.executeQuery();
         while (rs.next()) {
           storedIP=rs.getString(1);
         }
         // faz a validacao
         if ( (storedIP==null) || (!storedIP.equals(IP)))
        	return false;
         
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO BUSCA DO IP DO VOTO DA ENQUETE ATUAL ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conn);
	    }
	    return true;
	}
}