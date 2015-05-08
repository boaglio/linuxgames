package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.dao.core.QueryManager;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.TextoDeJogoVO;

public class JogoDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static JogoDAO instance = new JogoDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private JogoDAO() {
		setInsertSQL("jogo.insere");
		setUpdateSQL("jogo.atualiza");
		setDeleteSQL("jogo.remove");
		setBuscaUmSQL("jogo.buscaUm");
		setBuscaTodosSQL("jogo.buscaParaCombo");
	}

	/**
	 * singleton
	 */
	public static JogoDAO getInstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		Jogo vo = new Jogo();
	    vo.setId(rset.getInt("id"));
	    vo.setNome(rset.getString("nome"));
	    vo.getLicenca().setId(rset.getInt("licenca"));
	    vo.setTipo(rset.getInt("tipo"));
	    vo.setAberto(calculaBoolean("aberto",rset));
	    vo.setJogaEmRede(calculaBoolean("joga_em_rede",rset));
	    vo.setPrecisa3d(calculaBoolean("precisa_3d",rset));
	    vo.setTemSom(calculaBoolean("tem_som",rset));
	    vo.setConsoleOuX11(calculaBoolean("console_ou_x11",rset));
	    vo.setSiteOficial(rset.getString("site_oficial"));
	    vo.setSiteCompra(rset.getString("site_compra"));
	    vo.setDescricao(rset.getString("descricao"));
	    vo.setVotos(rset.getInt("votos"));
	    vo.setHits(rset.getLong("hits"));
	    vo.setNotaGeral(rset.getDouble("nota_geral"));
	    vo.getFabricante().setId(rset.getInt("fabricante_id"));
	    vo.setJogoId(rset.getInt("jogo_id"));
	    int destaque = rset.getInt("destaque");
	    if (destaque==1) vo.setDestaque(true);
	    else vo.setDestaque(false);
	    vo.setIdioma(rset.getString("idioma"));
        return vo;
    }

	private int calculaBoolean(String valor,ResultSet r) throws SQLException {
		boolean b = r.getBoolean(valor);
		if (b) return 1;
		return 0;
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		Jogo vo = (Jogo) object;
		pstmt.setString(1,vo.getNome());
        pstmt.setInt(2,vo.getLicenca().getId());
        pstmt.setInt(3,vo.getTipo());
        pstmt.setInt(4,vo.getAberto());
        pstmt.setInt(5,vo.getJogaEmRede());
        pstmt.setInt(6,vo.getPrecisa3d());
        pstmt.setInt(7,vo.getTemSom());
        pstmt.setInt(8,vo.getConsoleOuX11());
        pstmt.setString(9,vo.getSiteOficial());
        pstmt.setString(10,vo.getSiteCompra());
        pstmt.setString(11,vo.getDescricao());
        pstmt.setInt(12,vo.getVotos());
        pstmt.setLong(13,vo.getHits());
        pstmt.setDouble(14,vo.getNotaGeral());
        pstmt.setInt(15,vo.getFabricante().getId());

        if (vo.getJogoId()>0)
   	     pstmt.setInt(16,vo.getJogoId());
        else
         pstmt.setNull(16,Types.INTEGER);

	    boolean destaque = vo.isDestaque();
	    if (destaque)
	     pstmt.setInt(17,1);
	    else
	     pstmt.setInt(17,0);
	    pstmt.setString(18,vo.getIdioma());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		Jogo vo = (Jogo) object;
		pstmt.setString(1,vo.getNome());
        pstmt.setInt(2,vo.getLicenca().getId());
        pstmt.setInt(3,vo.getTipo());
        pstmt.setInt(4,vo.getAberto());
        pstmt.setInt(5,vo.getJogaEmRede());
        pstmt.setInt(6,vo.getPrecisa3d());
        pstmt.setInt(7,vo.getTemSom());
        pstmt.setInt(8,vo.getConsoleOuX11());
        pstmt.setString(9,vo.getSiteOficial());
        pstmt.setString(10,vo.getSiteCompra());
        pstmt.setString(11,vo.getDescricao());
        pstmt.setInt(12,vo.getVotos());
        pstmt.setLong(13,vo.getHits());
        pstmt.setDouble(14,vo.getNotaGeral());
        pstmt.setInt(15,vo.getFabricante().getId());

        if (vo.getJogoId()>0)
	     pstmt.setInt(16,vo.getJogoId());
        else
         pstmt.setNull(16,Types.INTEGER);

	    boolean destaque = vo.isDestaque();
	    if (destaque)
	     pstmt.setInt(17,1);
	    else
	     pstmt.setInt(17,0);
	    pstmt.setString(18,vo.getIdioma());
        pstmt.setInt(19,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		Jogo vo = (Jogo) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		Jogo vo = (Jogo) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	/**
	 * atualiza os votos do jogo
	 */
	public void atualizaVoto(Jogo vo) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   int linhasAfetadas = 0 ;
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"jogo.atualizaVoto");
         pstmt.setDouble(1,vo.getNotaGeral());
         pstmt.setInt(2,vo.getId());
         linhasAfetadas = pstmt.executeUpdate();
         logger.info("[UPDATE DE "+this.getClass()+"]: "+linhasAfetadas+" linha(s) afetada(s)");
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO UPDATE DO VOTO]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conexao);
	    }
	}

	/**
	 * atualiza os hits jogo
	 */
	public void atualizaHits(Jogo vo) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   int linhasAfetadas = 0 ;
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"jogo.atualizaHit");
         pstmt.setLong(1,vo.getHits());
         pstmt.setInt(2,vo.getId());
         linhasAfetadas = pstmt.executeUpdate();
         logger.info("[UPDATE DE "+this.getClass()+"]: "+linhasAfetadas+" linha(s) afetada(s)");
       } catch (SQLException e) {
			logger.error("[ERRO DO BANCO NO UPDATE DOS HITS]:" + e.getMessage());
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
   		 String query = (QueryManager.getInstance()).getQuery("jogo.buscaIPdeEnquete");
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
			logger.error("[ERRO DO BANCO NO BUSCA DO IP DO VOTO DO JOGO ]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
	    } finally {
	        close(rs);
	        close(pstmt);
	        close(conn);
	    }
	    return true;
	}

	protected Object setTextVO(ResultSet rset) throws SQLException{
		TextoDeJogoVO vo = new TextoDeJogoVO();
		vo.setId(rset.getInt("id"));
	    vo.setTexto(rset.getString("texto"));
	    vo.setDataPublic(rset.getDate("data_public"));
	    vo.setLink(rset.getString("link"));
	    vo.setTipo(rset.getInt("tipo"));
	    vo.setAprovado(rset.getInt("aprovado"));
	    vo.getJogo().setId(rset.getInt("tipo_id"));
	    vo.getJogo().setNome(rset.getString("jogo"));
	    vo.getUsuario().setId(rset.getInt("usuario_id"));
	    vo.getUsuario().setEmail(rset.getString("username"));
        return vo;
    }

	/**
	 * busca dicas
	 */
	public Collection<Object> buscaDicas(Object object) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista ...]:");
        try {
            conexao = dao.getConexao();
            pstmt = setPreparedStatementBuscaTodosComFiltro(object,createPreparedStatement(conexao,this.getQuery("jogo.dicas")));
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retorno.add(setTextVO(rs));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA DAS DICAS]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
		return (List<Object>) retorno;
	}

	/**
	 * busca jogos por idioma
	 */
	public Collection<Jogo> buscaPorIdioma(String idioma) throws DAOException {
		Collection<Jogo> retorno = new ArrayList<Jogo>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista de jogos em "+idioma+"...]:");
        try {
    		 String query = (QueryManager.getInstance()).getQuery("jogo.buscaPorIdioma");
    		 conexao = dao.getConexao();
     		 pstmt = conexao.prepareStatement(query);
     		 pstmt.setString(1,idioma);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                retorno.add( (Jogo) setVO(rs));
            }
			logger.debug(retorno.size() + " registros carregados! ");
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR IDIOMA]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rs);
            close(pstmt);
            close(conexao);
        }
		return (List<Jogo>) retorno;
	}

}
