package br.com.linuxgames.controller.crud;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.converter.Convertable;
import org.mentawai.converter.Converter;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.filtros.conversores.DateConverterNullAllowed;
import br.com.linuxgames.model.dao.NovidadeDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;
import br.com.linuxgames.util.TwitterUtil;


public class NovidadeAction extends BaseAction implements RedirectAfterLogin , Convertable {

    NovidadeDAO dao = NovidadeDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private Novidade vo;
	private Collection<Novidade> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings("unchecked")
	private Collection<Novidade> listaDeNovidades()   {
		NovidadeDAO novidadeDAO = NovidadeDAO.getInstance();
    	Collection<Novidade> collection=null;
		try {
			collection = novidadeDAO.buscaTodos();
		} catch (DAOException e) {
		  logger.error("erro na busca!",e);
		}
	    return collection;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("novidades", listaDeNovidades());
    	output.setValue("dataPublic", DateHelper.getToday());
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
    		Novidade storedVO = (Novidade) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" texto="+storedVO.getTexto()+" link="+storedVO.getLink());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("texto", storedVO.getTexto());
    		output.setValue("link", storedVO.getLink());
    		output.setValue("dataPublic", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDataPublic().toString()));
    		output.setValue("user_id",  String.valueOf(storedVO.getUsuario().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("novidades", listaDeNovidades());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	// chama o dao e traz o resultado
    	logger.info(" Novidade: texto="+vo.getTexto());

    	vo.setId( input.getInt("id"));

     	String userID= input.getString("user_id");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

    	try {
    		dao.adiciona(vo);
    		CacheManager.getCacheDeNovidades().refreshNoCache();

    		int opt = input.getInt("postaTwitter");
    		if (opt == Constantes.APROVADO) {
    			TwitterUtil.postar(vo.getTexto(), vo.getLink());
    		}

		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}


		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

		output.setValue("novidades", listaDeNovidades());
		resetForm();
		return SUCCESS;
    }


    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String twittar() throws Exception {

    	int id = input.getInt("id");

    	try {

    		Novidade storedVO = (Novidade) dao.buscaUm(new Novidade(id));

    		logger.info("vo lido ID="+storedVO.getId()+" texto="+storedVO.getTexto()+" link="+storedVO.getLink());

    		TwitterUtil.postar(storedVO.getTexto(), storedVO.getLink());

    		logger.info(" Post twittado! [texto="+storedVO.getTexto()+" , link =" + storedVO.getLink() +"]" );

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }

		output.setValue("novidades", listaDeNovidades());
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
     	String userID= input.getString("user_id");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Novidade: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
    		CacheManager.getCacheDeNovidades().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Novidade: id="+vo.getId());
    	 try {
    		dao.remove(vo);
    		CacheManager.getCacheDeNovidades().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

		output.setValue("novidades", listaDeNovidades());
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
    	logger.info(" Remover Novidade: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("novidades", listaDeNovidades());
    	resetForm();

		// refresh
		CacheManager.getCacheDeNovidades().refreshNoCache();

        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id", "");
		output.setValue("texto","");
		output.setValue("link","");
		output.setValue("dataPublic","");
		output.setValue("user_id","");
		output.setValue("tipo","");
    }

    // getters e setters
	public Novidade getVo() {
		return vo;
	}

	public void setVo(Novidade vo) {
		this.vo = vo;
	}

	public Collection<Novidade> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<Novidade> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public void prepareConverters(Map<String, Converter> converters, String innerAction) {

		converters.put("dataPublic", new DateConverterNullAllowed());

	}

}