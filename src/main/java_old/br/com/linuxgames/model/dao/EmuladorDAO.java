package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.mentawai.i18n.LocaleManager;
import org.mentawai.list.ListData;
import org.mentawai.list.ListDataItem;
import org.mentawai.list.ListManager;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.dao.core.QueryManager;
import br.com.linuxgames.model.vo.EmuladorVO;

public class EmuladorDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static EmuladorDAO instance = new EmuladorDAO();
	private Logger logger = Logger.getLogger(this.getClass());
	private HashMap<Integer, String> tiposDeEmulador = new HashMap<Integer, String>();

	/**
	 * construtor privado que carrega os SQLs
	 */
	private EmuladorDAO() {
		setInsertSQL("emulador.insere");
		setUpdateSQL("emulador.atualiza");
		setDeleteSQL("emulador.remove");
		setBuscaUmSQL("emulador.buscaUm");
		setBuscaTodosSQL("emulador.buscaTudo");

		ListData tiposDeEmuladorData = ListManager.getList("tiposDeEmulador");
		List<?> tiposDeEmuladorList = tiposDeEmuladorData.getValues(LocaleManager.getDefaultLocale());
		Iterator<?> iter = tiposDeEmuladorList.iterator();
		while(iter.hasNext()) {
			ListDataItem item = (ListDataItem) iter.next();
			this.tiposDeEmulador.put(item.getId(),item.getValue());
		}
	}

	/**
	 * singleton
	 */
	public static EmuladorDAO getInstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		EmuladorVO vo = new EmuladorVO();
	    vo.setId(rset.getInt("id"));
	    vo.setNome(rset.getString("nome"));
	    vo.setTipo(rset.getInt("tipo"));
	    String tipoDeEmulador = (String) tiposDeEmulador.get(vo.getTipo());
	    vo.setTipoNome(tipoDeEmulador);
	    vo.getLicenca().setId(rset.getInt("licenca"));
	    vo.setJogaEmRede(rset.getInt("emula_rede"));
	    vo.setTemSom(rset.getInt("emula_som"));
	    vo.setConsoleOuX11(rset.getInt("console_ou_x11"));
	    vo.setSiteOficial(rset.getString("site_oficial"));
	    vo.setDescricao(rset.getString("descricao"));
	    vo.setVotos(rset.getInt("votos"));
	    vo.setNotaGeral(rset.getDouble("nota_geral"));
	    vo.getFabricante().setId(rset.getInt("fabricante_id"));
	    vo.setHits(rset.getLong("hits"));
	    int destaque = rset.getInt("destaque");
	    if (destaque==1) vo.setDestaque(true);
	    else vo.setDestaque(false);
        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		EmuladorVO vo = (EmuladorVO) object;
		pstmt.setString(1,vo.getNome());
		pstmt.setInt(2,vo.getTipo());
        pstmt.setInt(3,vo.getLicenca().getId());
        pstmt.setInt(4,vo.getJogaEmRede());
        pstmt.setInt(5,vo.getTemSom());
        pstmt.setString(6,vo.getSiteOficial());
        pstmt.setInt(7,vo.getConsoleOuX11());
        pstmt.setString(8,vo.getDescricao());
        pstmt.setInt(9,vo.getVotos());
        pstmt.setDouble(10,vo.getNotaGeral());
        pstmt.setInt(11,vo.getFabricante().getId());
	    boolean destaque = vo.isDestaque();
	    if (destaque)
	     pstmt.setInt(12,1);
	    else
	     pstmt.setInt(12,0);
	    pstmt.setLong(13,vo.getHits());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		EmuladorVO vo = (EmuladorVO) object;
		pstmt.setString(1,vo.getNome());
		pstmt.setInt(2,vo.getTipo());
        pstmt.setInt(3,vo.getLicenca().getId());
        pstmt.setInt(4,vo.getJogaEmRede());
        pstmt.setInt(5,vo.getTemSom());
        pstmt.setString(6,vo.getSiteOficial());
        pstmt.setInt(7,vo.getConsoleOuX11());
        pstmt.setString(8,vo.getDescricao());
        pstmt.setInt(9,vo.getVotos());
        pstmt.setDouble(10,vo.getNotaGeral());
        pstmt.setInt(11,vo.getFabricante().getId());
	    boolean destaque = vo.isDestaque();
	    if (destaque)
	     pstmt.setInt(12,1);
	    else
	     pstmt.setInt(12,0);
	    pstmt.setLong(13,vo.getHits());
        pstmt.setInt(14,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		EmuladorVO vo = (EmuladorVO) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return pstmt;
	}

	/**
	 * atualiza os votos do emulador
	 */
	public void atualizaVoto(EmuladorVO vo) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   int linhasAfetadas = 0 ;
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"emulador.atualizaVoto");
         pstmt.setDouble(1,vo.getNotaGeral());
         pstmt.setInt(2,vo.getId());
         linhasAfetadas = pstmt.executeUpdate();
         logger.info("[UPDATE DE "+this.getClass()+"]: "+linhasAfetadas+" linha(s) afetada(s)");
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO UPDATE]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	}

    /**
	 * busca o IP da base de dados
	 * Se achar, o usuario votou, retorna TRUE
	 * Se achar, eh novo voto,retorna FALSE
	 */
	public boolean esseIPjaVotou(String IP,int id) throws DAOException {
	   DAOManager dao = DAOManager.getInstance();
	   PreparedStatement pstmt = null;
	   Connection conn = dao.getConexao();
	   ResultSet rs = null;
	   String storedIP=null;
	   logger.info("[verificando se "+IP+" ja votou...]:");
       try {
 		 String query = (QueryManager.getInstance()).getQuery("emulador.buscaIPdeEnquete");
 		 pstmt = conn.prepareStatement(query);
 		 pstmt.setString(1,IP);
 		 pstmt.setInt(2,id);
         rs = pstmt.executeQuery();
         while (rs.next()) {
           storedIP=rs.getString(1);
         }
         // faz a validacao
         if ( (storedIP==null) || (!storedIP.equals(IP)))
        	return false;

		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO BUSCA DO IP DO VOTO DO EMULADOR ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conn);
	    }
	    return true;
	}

	/**
	 * atualiza os hits Emulador
	 */
	public void atualizaHits(EmuladorVO vo) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   int linhasAfetadas = 0 ;
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"emulador.atualizaHit");
         pstmt.setLong(1,vo.getHits());
         pstmt.setInt(2,vo.getId());
         linhasAfetadas = pstmt.executeUpdate();
         logger.info("[UPDATE DE "+this.getClass()+"]: "+linhasAfetadas+" linha(s) afetada(s)");
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO UPDATE]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	}

}
