package br.com.linuxgames.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.dao.core.DAOManager;
import br.com.linuxgames.model.dao.core.QueryManager;
import br.com.linuxgames.model.vo.JogoTemplate;
import br.com.linuxgames.model.vo.TextoDeJogoVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.util.Constantes;

public class JogoTemplateDAO extends AbstractDAO {

    //  SQLs do arquivo properties
	private static JogoTemplateDAO instance = new JogoTemplateDAO();
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * construtor privado que carrega os SQLs
	 */
	private JogoTemplateDAO() {
		setInsertSQL("jogoTemplate.insere");
		setUpdateSQL("jogoTemplate.atualiza");
		setDeleteSQL("jogoTemplate.remove");
		setBuscaUmSQL("jogoTemplate.buscaUm");
		setBuscaTodosSQL("jogoTemplate.buscaParaCombo");

	}

	/**
	 * singleton
	 */
	public static JogoTemplateDAO getInstance() {
		return instance;
	}

	/**
	 * seta o VO
	 */
	protected Object setVO(ResultSet rset) throws SQLException{
		JogoTemplate vo = new JogoTemplate();
	    vo.setId(rset.getInt("id"));
	    vo.setNome(rset.getString("nome"));
	    vo.getLicenca().setId(rset.getInt("licenca"));
	    vo.setTipo(rset.getInt("tipo"));
	    vo.setAberto(rset.getInt("aberto"));
	    vo.setJogaEmRede(rset.getInt("joga_em_rede"));
	    vo.setPrecisa3d(rset.getInt("precisa_3d"));
	    vo.setTemSom(rset.getInt("tem_som"));
	    vo.setConsoleOuX11(rset.getInt("console_ou_x11"));
	    vo.setSiteOficial(rset.getString("site_oficial"));
	    vo.setDescricao(rset.getString("descricao"));
	    vo.getFabricante().setId(rset.getInt("fabricante_id"));
	    vo.setJogoId(rset.getInt("jogo_id"));
	    vo.setIdioma(rset.getString("idioma"));
	    vo.setAprovado(rset.getInt("aprovado"));
	    vo.setObservacao(rset.getString("obs"));

	    int usuarioId =rset.getInt("usuario_id");
	    Usuario usuario = new Usuario(usuarioId);
	    if (usuarioId == 0) {
	    	usuario.setEmail("Ninguem!");
	    	usuario.setSenha("sem email!");
	    }
	    else
	    {
	    	UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
	    	try {
				usuario = (Usuario) usuariosDAO.buscaUm(usuario);
			} catch (DAOException e) {
				e.printStackTrace();
			}
	    }
	    vo.setUsuario(usuario);

        return vo;
    }

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		JogoTemplate vo = (JogoTemplate) object;
		pstmt.setString(1,vo.getNome());
        pstmt.setInt(2,vo.getLicenca().getId());
        pstmt.setInt(3,vo.getTipo());
        pstmt.setInt(4,vo.getAberto());
        pstmt.setInt(5,vo.getJogaEmRede());
        pstmt.setInt(6,vo.getPrecisa3d());
        pstmt.setInt(7,vo.getTemSom());
        pstmt.setInt(8,vo.getConsoleOuX11());
        pstmt.setString(9,vo.getSiteOficial());
        pstmt.setString(10,vo.getDescricao());
        pstmt.setInt(11,vo.getFabricante().getId());
        pstmt.setInt(12,vo.getUsuario().getId());
	    pstmt.setString(13,vo.getIdioma());
	    pstmt.setInt(14,vo.getAprovado());
	    pstmt.setInt(15,vo.getJogoId());
	    pstmt.setString(16,vo.getObservacao());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		JogoTemplate vo = (JogoTemplate) object;
		pstmt.setString(1,vo.getNome());
        pstmt.setInt(2,vo.getLicenca().getId());
        pstmt.setInt(3,vo.getTipo());
        pstmt.setInt(4,vo.getAberto());
        pstmt.setInt(5,vo.getJogaEmRede());
        pstmt.setInt(6,vo.getPrecisa3d());
        pstmt.setInt(7,vo.getTemSom());
        pstmt.setInt(8,vo.getConsoleOuX11());
        pstmt.setString(9,vo.getSiteOficial());
        pstmt.setString(10,vo.getDescricao());
        pstmt.setInt(11,vo.getFabricante().getId());
        pstmt.setInt(12,vo.getUsuario().getId());
	    pstmt.setString(13,vo.getIdioma());
	    pstmt.setInt(14,vo.getAprovado());
	    pstmt.setInt(15,vo.getJogoId());
        pstmt.setInt(16,vo.getId());
        return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		JogoTemplate vo = (JogoTemplate) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		JogoTemplate vo = (JogoTemplate) object;
		pstmt.setInt(1,vo.getId());
		return pstmt;
	}


	/**
	 * busca jogos por idioma
	 */
	public Collection<Object> buscaPorIdioma(String idioma) throws DAOException {
		Collection<Object> retorno = new ArrayList<Object>();
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
		logger.info("[trazendo a lista de jogos em "+idioma+"...]:");
        try {
    		 String query = (QueryManager.getInstance()).getQuery("jogoTemplate.buscaPorIdioma");
    		 conexao = dao.getConexao();
     		 pstmt = conexao.prepareStatement(query);
     		 pstmt.setString(1,idioma);
             rs = pstmt.executeQuery();
             while (rs.next()) {
                retorno.add(setVO(rs));
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
		return (List<Object>) retorno;
	}


	/**
	 * busca maior ID
	 */
	public int buscaMaxId() throws DAOException {
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		int resultado=0 ;
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"jogoTemplate.buscaMaxId");
            rset = pstmt.executeQuery();
            while (rset.next()) {
            	resultado = rset.getInt(1);
            }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR MAX]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return resultado;
	}


	/**
	 * busca ajudas reprovadas
	 */
	public Collection<JogoTemplate> buscaAjudasReprovadas() throws DAOException {
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		Collection<JogoTemplate> resultado= new ArrayList<JogoTemplate>();
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"jogoTemplate.buscaAjudasReprovadas");
            rset = pstmt.executeQuery();
            while (rset.next()) {
//            	 SELECT  jt.id,   j.id jogo_id, j.nome jogo ,u.user_id ,u.username,u.user_email , jt.aprovado
            	JogoTemplate jogoTemplate = new JogoTemplate();
        	    jogoTemplate.setId(rset.getInt("id"));
        	    jogoTemplate.setJogoId(rset.getInt("jogo_id"));
        	    jogoTemplate.setNome(rset.getString("jogo"));
        	    jogoTemplate.setUsuario(new Usuario(rset.getInt("user_id"),rset.getString("username"))) ;
        	    jogoTemplate.setIdioma(rset.getString("idioma"));
        	    if (rset.getInt("aprovado")==Constantes.APROVADO)
        	     jogoTemplate.setAprovado(true);
        	    else
        	     jogoTemplate.setAprovado(false);
            	resultado.add(jogoTemplate);
            }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR AJUDAS REPROVADAS]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return resultado;
	}



	/**
	 * busca ajudas de novos jogos  reprovadas
	 */
	public Collection<JogoTemplate> buscaAjudasNovasReprovadas() throws DAOException {
		PreparedStatement pstmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rset = null;
		Collection<JogoTemplate> resultado= new ArrayList<JogoTemplate>();
        try {
            conexao = dao.getConexao();
            pstmt = createPreparedStatement(conexao,"jogoTemplate.buscaAjudasNovasReprovadas");
            rset = pstmt.executeQuery();
            while (rset.next()) {
//            	 SELECT  jt.id,   j.id jogo_id, j.nome jogo ,u.user_id ,u.username,u.user_email , jt.aprovado
            	JogoTemplate jogoTemplate = new JogoTemplate();
        	    jogoTemplate.setId(rset.getInt("id"));
        	    jogoTemplate.setNome(rset.getString("jogo"));
        	    jogoTemplate.setUsuario(new Usuario(rset.getInt("user_id"),rset.getString("username"))) ;
        	    jogoTemplate.setIdioma(rset.getString("idioma"));
        	    if (rset.getInt("aprovado")==Constantes.APROVADO)
        	     jogoTemplate.setAprovado(true);
        	    else
        	     jogoTemplate.setAprovado(false);
            	resultado.add(jogoTemplate);
            }
		} catch (SQLException e) {
			logger.error("[ERRO DO BANCO BUSCA POR AJUDAS NOVAS REPROVADAS]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DAO "+ this.getClass() +" ]:", e);
        } finally {
            close(rset);
            close(pstmt);
            close(conexao);
        }
		return resultado;
	}

	/**
	 * altera um objeto qualquer uma vez definido o updateSQL
	 */
	public void atualizaStatus(Object object) throws DAOException {
	   PreparedStatement pstmt = null;
	   DAOManager dao = DAOManager.getInstance();
	   Connection conexao = null;
	   ResultSet rs = null;
	   logger.info("[UPDATE DE "+this.getClass()+"]:");
       try {
         conexao = dao.getConexao();
         pstmt = createPreparedStatement(conexao,"jogoTemplate.atualizaAprovacao");
         TextoDeJogoVO vo = (TextoDeJogoVO) object;
	     pstmt.setInt(1,vo.getAprovado());
	     pstmt.setInt(2,vo.getId());
         pstmt.execute();
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
