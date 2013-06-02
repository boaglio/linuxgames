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
import br.com.linuxgames.model.dao.ArtigoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ArtigoVO;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;


public class ArtigoAction extends BaseAction implements RedirectAfterLogin  , Convertable {

    ArtigoDAO dao = ArtigoDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass().getName());

	private ArtigoVO vo;
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
    	output.setValue("artigos", listaDeArtigos());
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
    		ArtigoVO storedVO = (ArtigoVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" texto="+storedVO.getTexto());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("texto", storedVO.getTexto());
    		output.setValue("titulo", storedVO.getTitulo());
    		output.setValue("dataPublic", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDataPublic().toString()));
    		output.setValue("usuarioId",  String.valueOf(storedVO.getUsuario().getId()));
    		output.setValue("aprovado",  String.valueOf(storedVO.getAprovado()));
    		output.setValue("votos",String.valueOf(storedVO.getVotos()));
    		output.setValue("notaGeral",String.valueOf(storedVO.getNotaGeral()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("artigos", listaDeArtigos());
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
    		ArtigoVO storedVO = (ArtigoVO) dao.buscaUm(vo);
    		output.setValue("texto", storedVO.getTexto());
    		output.setValue("titulo", storedVO.getTitulo());
    		output.setValue("dataPublic", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDataPublic().toString()));
    		output.setValue("notaGeral",String.valueOf(storedVO.getNotaGeral()));
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
    	logger.info(" Artigo: texto="+vo.getTexto());

    	vo.setAprovado(input.getInt("aprovado"));
    	vo.setVotos(input.getInt("votos"));
    	vo.setNotaGeral(Double.parseDouble(input.getString("notaGeral")));

     	String userID= input.getString("usuarioId");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

    	try {
    		dao.adiciona(vo);
    		CacheManager.getCacheDeArtigos().refreshNoCache();
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("artigos", listaDeArtigos());
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
    	vo.setAprovado(input.getInt("aprovado"));
    	vo.setVotos(input.getInt("votos"));
    	vo.setNotaGeral(Double.parseDouble(input.getString("notaGeral")));

     	String userID= input.getString("usuarioId");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Artigo: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
    		CacheManager.getCacheDeArtigos().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Artigo: id="+vo.getId());
    	 try {
    		dao.remove(vo);
    		CacheManager.getCacheDeArtigos().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("artigos", listaDeArtigos());
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
    		CacheManager.getCacheDeArtigos().refreshNoCache();
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("artigos", listaDeArtigos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id","");
		output.setValue("texto","");
		output.setValue("titulo","");
		output.setValue("dataPublic","");
		output.setValue("usuarioId","");
		output.setValue("aprovado","");
		output.setValue("votos","");
		output.setValue("notaGeral","");
    }

    // getters e setters
	public ArtigoVO getVo() {
		return vo;
	}

	public void setVo(ArtigoVO vo) {
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


	public void prepareConverters(Map<String, Converter> converters, String innerAction) {

	 converters.put("dataPublic", new DateConverterNullAllowed());

	}

}