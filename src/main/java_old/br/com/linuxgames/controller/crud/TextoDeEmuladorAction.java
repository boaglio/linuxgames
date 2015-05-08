package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.model.dao.TextoDeEmuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.TextoDeEmuladorVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.DateHelper;
import br.com.linuxgames.util.UpdateLogHelper;


public class TextoDeEmuladorAction extends BaseAction implements RedirectAfterLogin {

    TextoDeEmuladorDAO dao = TextoDeEmuladorDAO.getinstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.TextoAction");

	private TextoDeEmuladorVO vo;
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
    	output.setValue("textosDeEmuladores", listaDeTextos());
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
    		TextoDeEmuladorVO storedVO = (TextoDeEmuladorVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("texto", storedVO.getTexto());
    		output.setValue("link", storedVO.getLink());
    		output.setValue("dataPublic", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDataPublic().toString()));
    		output.setValue("tipo_id",  String.valueOf(storedVO.getEmulador().getId()));
    		output.setValue("aprovado",  String.valueOf(storedVO.getAprovado()));
    		output.setValue("tipo",  String.valueOf(storedVO.getTipo()));
    		output.setValue("usuarioId",  String.valueOf(storedVO.getUsuario().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("textosDeEmuladores", listaDeTextos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String emuladorID= input.getString("tipo_id");
     	vo.setAprovado(input.getInt("aprovado"));
     	EmuladorVO emuladorVO = new EmuladorVO();
     	emuladorVO.setId(Integer.parseInt(emuladorID));
     	vo.setEmulador(emuladorVO);

     	String userID= input.getString("usuarioId");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

     	vo.setTipo(input.getInt("tipo"));

     	EmuladorCache emuCache = CacheManager.getCacheDeEmuladores();
     	EmuladorVO storedVO = (EmuladorVO) emuCache.getCacheComoHashMap().get(vo.getEmulador().getId());

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
      		UpdateLogHelper.setUpdateLog("Adicionada nova dica do emulador ["+storedVO.getNome()+"]");

		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("textosDeEmuladores", listaDeTextos());
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
     	String emuladorID= input.getString("tipo_id");
     	EmuladorVO emuladorVO = new EmuladorVO();
     	emuladorVO.setId(Integer.parseInt(emuladorID));
     	vo.setEmulador(emuladorVO);

     	String userID= input.getString("usuarioId");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(userID));
     	vo.setUsuario(usuarioVO);

     	vo.setTipo(input.getInt("tipo"));

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

		output.setValue("textosDeEmuladores", listaDeTextos());
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
    	output.setValue("textosDeEmuladores", listaDeTextos());
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
		output.setValue("aprovado","");
		output.setValue("usuarioId","");
		output.setValue("dataPublic","");
    }

    // getters e setters
	public TextoDeEmuladorVO getVo() {
		return vo;
	}

	public void setVo(TextoDeEmuladorVO vo) {
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