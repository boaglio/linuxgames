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
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.LicencaVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.LocaleHelper;
import br.com.linuxgames.util.UpdateLogHelper;


public class JogoAction extends BaseAction implements RedirectAfterLogin {

    JogoDAO dao = JogoDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private Jogo vo;
	private Collection<Jogo> listaVO;
	private int action;
	private static String idioma = LocaleHelper.PT_BR2;

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
    		DBListDataExtended jogos = new DBListDataExtended("JOGOS","jogo.buscaParaCombo", "id", "nome");
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
		output.setValue("votos", new Integer(1));
		output.setValue("hits", new Integer(0));
		output.setValue("notaGeral", new Double(5));
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
    		output.setValue("tipo", storedVO.getTipo());
    		output.setValue("licenca_id",  String.valueOf(storedVO.getLicenca().getId()));
    		output.setValue("aberto", storedVO.getAberto());
    		output.setValue("jogaEmRede", storedVO.getJogaEmRede());
    		output.setValue("precisa3d", storedVO.getPrecisa3d());
    		output.setValue("temSom", storedVO.getTemSom());
    		output.setValue("consoleOuX11", storedVO.getConsoleOuX11());
    		output.setValue("siteOficial", storedVO.getSiteOficial());
    		output.setValue("siteCompra", storedVO.getSiteCompra());
    		output.setValue("descricao", storedVO.getDescricao());
    		output.setValue("votos", storedVO.getVotos());
    		output.setValue("hits", storedVO.getHits());
    		output.setValue("notaGeral", storedVO.getNotaGeral());
    		output.setValue("fabricante_id",  String.valueOf(storedVO.getFabricante().getId()));
    		output.setValue("idioma", storedVO.getIdioma());

    	    boolean destaque = storedVO.isDestaque();
    	    if (destaque)
    	     output.setValue("destaque", 1);
    	    else
    	     output.setValue("destaque", 0);

        	Collection<?> dicas = dao.buscaDicas(storedVO);
        	output.setValue("dicas",dicas);

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

     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricante(fabricanteVO);

     	String licencaID= input.getString("licenca_id");
     	LicencaVO licencaVO = new LicencaVO();
     	licencaVO.setId(Integer.parseInt(licencaID));
     	vo.setLicenca(licencaVO);
     	vo.setAberto(input.getInt("aberto"));
     	vo.setTipo(input.getInt("tipo"));
     	vo.setJogaEmRede(input.getInt("jogaEmRede"));
     	vo.setPrecisa3d(input.getInt("precisa3d"));
     	vo.setTemSom(input.getInt("temSom"));
     	vo.setConsoleOuX11(input.getInt("consoleOuX11"));
     	vo.setVotos(0);
     	vo.setHits(0);
     	vo.setNotaGeral(0);
     	vo.setIdioma(idioma);
	    int destaque = input.getInt("destaque");
	    if (destaque==1) vo.setDestaque(true);
	    else vo.setDestaque(false);

    	// chama o dao e traz o resultado
    	logger.info(" Jogo: nome="+vo.getNome());

    	try {
    		dao.adiciona(vo);
    		CacheManager.getCacheDeJogos().refreshNoCache();
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

    	// ajusta o VO
    	vo.setId( input.getInt("id"));
     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricante(fabricanteVO);

     	String licencaID= input.getString("licenca_id");
     	LicencaVO licencaVO = new LicencaVO();
     	licencaVO.setId(Integer.parseInt(licencaID));
     	vo.setLicenca(licencaVO);
     	vo.setAberto(input.getInt("aberto"));
     	vo.setTipo(input.getInt("tipo"));
     	vo.setJogaEmRede(input.getInt("jogaEmRede"));
     	vo.setPrecisa3d(input.getInt("precisa3d"));
     	vo.setTemSom(input.getInt("temSom"));
     	vo.setConsoleOuX11(input.getInt("consoleOuX11"));
     	vo.setVotos(0);
     	vo.setHits(0);
     	vo.setNotaGeral(0);
     	vo.setIdioma(idioma);
	    int destaque = input.getInt("destaque");
	    if (destaque==1) vo.setDestaque(true);
	     else vo.setDestaque(false);

     	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Jogo: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
    		CacheManager.getCacheDeJogos().refreshNoCache();
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