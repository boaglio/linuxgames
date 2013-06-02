package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;
import org.mentawai.list.ListManager;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.datalist.DBListDataExtended;
import br.com.linuxgames.model.dao.JogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.LocaleHelper;
import br.com.linuxgames.util.UpdateLogHelper;


public class JogoENAction extends BaseAction implements RedirectAfterLogin {

    JogoDAO dao = JogoDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private Jogo vo;
	private Collection<Jogo> listaVO;
	private int action;
	private static String idioma = LocaleHelper.EN;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<Jogo> listaDeJogos() {
    	Collection<Jogo> collection = null;
    	try {
    		collection = dao.buscaPorIdioma(idioma);
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}


	private void atualistaCombo() {
    	try {
    		DBListDataExtended jogos = new DBListDataExtended("JOGOS_EN","jogo.buscaParaComboEN", "id", "nome");
            ListManager.addList(jogos);
	    } catch (Exception e) {
		  logger.error("erro no cadastro!",e);
	    }
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("jogos", listaDeJogos());
    	// define valores inicias para votos,hits e nota geral
		output.setValue("votos", new Integer(0));
		output.setValue("hits", new Integer(0));
		output.setValue("notaGeral", new Double(0));
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.setId( input.getInt("id"));

    	try {
    		Jogo storedVO = (Jogo) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getNome());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("nome", storedVO.getNome());
    		output.setValue("descricao", storedVO.getDescricao());
    		output.setValue("jogo_id",  String.valueOf(storedVO.getJogoId()));
    		output.setValue("idioma", storedVO.getIdioma());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("jogos", listaDeJogos());

        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	Jogo jogoPai = new Jogo();
    	jogoPai.setId(input.getInt("jogo_id"));
    	jogoPai = (Jogo) dao.buscaUm(jogoPai);

    	vo.setFabricante(jogoPai.getFabricante());
     	vo.setLicenca(jogoPai.getLicenca());
     	vo.setAberto(jogoPai.getAberto());
     	vo.setTipo(jogoPai.getTipo());
     	vo.setJogaEmRede(jogoPai.getJogaEmRede());
     	vo.setPrecisa3d(jogoPai.getPrecisa3d());
     	vo.setTemSom(jogoPai.getTemSom());
     	vo.setConsoleOuX11(jogoPai.getConsoleOuX11());
     	vo.setSiteOficial(jogoPai.getSiteOficial());
     	vo.setVotos(0);
     	vo.setHits(0);
     	vo.setNotaGeral(0);

     	vo.setJogoId(input.getInt("jogo_id"));
     	vo.setIdioma(idioma);

	    int destaque = input.getInt("destaque");
	    if (destaque==1) vo.setDestaque(true);
	    else vo.setDestaque(false);

    	// chama o dao e traz o resultado
    	logger.info(" Jogo: nome="+vo.getNome());

    	try {
    		dao.adiciona(vo);
    		CacheManager.getCacheDeJogosEN().refreshNoCacheEN();
    		UpdateLogHelper.setUpdateLog("Confira o novo jogo cadastrado:"+vo.getNome());
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("jogos", listaDeJogos());
		resetForm();
		atualistaCombo();
		return SUCCESS;
    }


	/**
	 *action que atualiza um registro
	 * @return
	 * @throws Exception
	 */
    public String atualiza() throws Exception {

    	Jogo jogoPai = new Jogo();
    	jogoPai.setId(input.getInt("jogo_id"));
    	jogoPai = (Jogo) dao.buscaUm(jogoPai);
    	// ajusta o VO
    	vo.setId( input.getInt("id"));

    	vo.setFabricante(jogoPai.getFabricante());
     	vo.setLicenca(jogoPai.getLicenca());
     	vo.setAberto(jogoPai.getAberto());
     	vo.setTipo(jogoPai.getTipo());
     	vo.setJogaEmRede(jogoPai.getJogaEmRede());
     	vo.setPrecisa3d(jogoPai.getPrecisa3d());
     	vo.setTemSom(jogoPai.getTemSom());
     	vo.setConsoleOuX11(jogoPai.getConsoleOuX11());
     	vo.setSiteOficial(jogoPai.getSiteOficial());
     	vo.setVotos(0);
     	vo.setHits(0);
     	vo.setNotaGeral(0);
	    vo.setJogoId(input.getInt("jogo_id"));
	    vo.setIdioma(idioma);

     	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Jogo: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
    		CacheManager.getCacheDeJogosEN().refreshNoCacheEN();
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Jogo: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("jogos", listaDeJogos());
		atualistaCombo();
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
        vo.setId( input.getInt("id"));
    	logger.info(" Remover Jogo: id="+vo.getId());
    	try {
    		dao.remove(vo);
    		CacheManager.getCacheDeJogos().refreshNoCache();
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("jogos", listaDeJogos());
    	resetForm();
    	atualistaCombo();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
    	output.setValue("id","");
		output.setValue("nome","");
		output.setValue("licenca_id","");
		output.setValue("aberto","");
		output.setValue("jogaEmRede","");
		output.setValue("precisa3d","");
		output.setValue("temSom","");
		output.setValue("consoleOuX11","");
		output.setValue("siteOficial","");
		output.setValue("siteCompra","");
		output.setValue("descricao","");
		output.setValue("fabricante_id","");
    	// define valores inicias para votos e nota geral
		output.setValue("votos", new Integer(1));
		output.setValue("hits", new Integer(0));
		output.setValue("notaGeral", new Double(5));
		output.setValue("destaque","");
		output.setValue("jogo_id","");
    }

    // getters e setters
	public Jogo getVo() {
		return vo;
	}

	public void setVo(Jogo vo) {
		this.vo = vo;
	}

	public Collection<Jogo> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<Jogo> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}