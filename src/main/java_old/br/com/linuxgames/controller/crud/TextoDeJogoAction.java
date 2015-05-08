package br.com.linuxgames.controller.crud;

import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.converter.Converter;
import org.mentawai.converter.DateConverter;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.model.dao.TextoDeJogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.TextoDeJogoVO;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;
import br.com.linuxgames.util.UpdateLogHelper;


public class TextoDeJogoAction extends BaseAction implements RedirectAfterLogin {

    TextoDeJogoDAO dao = TextoDeJogoDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());

	private TextoDeJogoVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeTextos() {
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
    	output.setValue("textosDeJogos", listaDeTextos());
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
    		TextoDeJogoVO storedVO = (TextoDeJogoVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("texto", storedVO.getTexto());
    		output.setValue("link", storedVO.getLink());
    		output.setValue("dataPublic", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDataPublic().toString()));
    		output.setValue("tipo_id",  String.valueOf(storedVO.getJogo().getId()));
    		output.setValue("tipo", storedVO.getTipo());
    		output.setValue("aprovado",  String.valueOf(storedVO.getAprovado()));
    		output.setValue("usuarioId",  String.valueOf(storedVO.getUsuario().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("textosDeJogos", listaDeTextos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String jogoID= input.getString("tipo_id");
     	vo.setAprovado(input.getInt("aprovado"));
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogo(jogoVO);

     	String userID= input.getString("usuarioId");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

     	int tipoID= input.getInt("tipo");
     	vo.setTipo(tipoID);

     	JogoCache jogoCache = CacheManager.getCacheDeJogos();
     	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(vo.getJogo().getId());

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
      		UpdateLogHelper.setUpdateLog("Adicionada nova dica do jogo ["+storedVO.getNome()+"]");
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("textosDeJogos", listaDeTextos());
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
     	String jogoID= input.getString("tipo_id");
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogo(jogoVO);

     	String userID= input.getString("usuarioId");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

     	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Texto: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Texto: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("textosDeJogos", listaDeTextos());
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
    	logger.info(" Remover Texto: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("textosDeJogos", listaDeTextos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id",new Integer(0));
		output.setValue("texto","");
		output.setValue("link","");
		output.setValue("tipo_id",new Integer(0));
		output.setValue("tipo",new Integer(0));
		output.setValue("usuarioId","");
		output.setValue("aprovado","");
		output.setValue("dataPublic","");
    }

    // getters e setters
	public TextoDeJogoVO getVo() {
		return vo;
	}

	public void setVo(TextoDeJogoVO vo) {
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

	 converters.put("dataPublic", new DateConverter());

	}


}