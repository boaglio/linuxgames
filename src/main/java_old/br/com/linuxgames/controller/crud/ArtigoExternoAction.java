package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.ArtigoExternoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ArtigoExternoVO;
import br.com.linuxgames.util.Constantes;


public class ArtigoExternoAction extends BaseAction implements RedirectAfterLogin  {

    ArtigoExternoDAO dao = ArtigoExternoDAO.getInstance();
	Logger logger = Logger.getLogger(ArtigoExternoAction.class);

	private ArtigoExternoVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeArtigos() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("artigosExternos", listaDeArtigos());
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
    		ArtigoExternoVO storedVO = (ArtigoExternoVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" titulo="+storedVO.getTitulo());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("titulo", storedVO.getTitulo());
    		output.setValue("link", storedVO.getLink());
    		output.setValue("fonte",storedVO.getFonte());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("artigosExternos", listaDeArtigos());
        return SUCCESS;
    }

    /**
     * action que mostra um registro
     * @return
     * @throws ActionException
     */
    public String mostra() throws ActionException {
    	vo.setId( input.getInt("id"));
    	try {
    		ArtigoExternoVO storedVO = (ArtigoExternoVO) dao.buscaUm(vo);
    		output.setValue("titulo",storedVO.getTitulo());
    		output.setValue("link",storedVO.getLink());
    		output.setValue("fonte",storedVO.getFonte());
	    } catch (DAOException e) {
		  logger.error("erro em mostrar!",e);
	    }
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// chama o dao e traz o resultado
    	logger.info(" Artigo: texto="+vo.getTitulo());
    	try {
    		dao.adiciona(vo);
    		CacheManager.getCacheDeArtigosExternos().refreshNoCache();
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("artigosExternos", listaDeArtigos());
		resetForm();
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

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Artigo: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
    		CacheManager.getCacheDeArtigosExternos().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Artigo: id="+vo.getId());
    	 try {
    		dao.remove(vo);
    		CacheManager.getCacheDeArtigosExternos().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("artigosExternos", listaDeArtigos());
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
    	logger.info(" Remover Artigo: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("artigosExternos", listaDeArtigos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id","");
		output.setValue("link","");
		output.setValue("titulo","");
		output.setValue("fonte","");
    }

    // getters e setters
	public ArtigoExternoVO getVo() {
		return vo;
	}

	public void setVo(ArtigoExternoVO vo) {
		this.vo = vo;
	}

	public Collection<?> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<?> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}