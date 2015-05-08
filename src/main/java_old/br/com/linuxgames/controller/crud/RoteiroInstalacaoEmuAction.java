package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.model.dao.RoteiroInstalacaoEmuDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoEmuVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.UpdateLogHelper;


public class RoteiroInstalacaoEmuAction extends BaseAction implements RedirectAfterLogin {

	RoteiroInstalacaoEmuDAO dao = RoteiroInstalacaoEmuDAO.getinstance();
	Logger logger = Logger.getLogger("br.com.linuxgames.controller.RoteiroInstalacaoEmuAction");

	private RoteiroInstalacaoEmuVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeRoteiros() {
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
    	output.setValue("roteirosDeInstalacaoEmu", listaDeRoteiros());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));
    	vo.setId(input.getInt("id"));
    	vo.getEmulador().setId(input.getInt("emulador_id"));
    	vo.getDistro().setId(input.getInt("distro_id"));
    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.setAprovado(input.getInt("aprovado"));

    	try {
    		RoteiroInstalacaoEmuVO storedVO = (RoteiroInstalacaoEmuVO) dao.buscaUm(vo);
    		// setando os valores lidos
    		output.setValue("id", storedVO.getId());
    		output.setValue("emulador_id", storedVO.getEmulador().getId());
    		output.setValue("distro_id", storedVO.getDistro().getId());
    		output.setValue("usuario_id", storedVO.getUsuario().getId());
    		output.setValue("aprovado", storedVO.getAprovado());
    		output.setValue("descricao", storedVO.getDescricao());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("roteirosDeInstalacaoEmu", listaDeRoteiros());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.getEmulador().setId(input.getInt("emulador_id"));
    	vo.getDistro().setId(input.getInt("distro_id"));
    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.setAprovado(input.getInt("aprovado"));

     	EmuladorCache emuCache = CacheManager.getCacheDeEmuladores();
     	EmuladorVO storedVO = (EmuladorVO) emuCache.getCacheComoHashMap().get(vo.getEmulador().getId());

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
      		UpdateLogHelper.setUpdateLog("Adicionado novo roteiro de instala&ccedil;&atilde;o do emulador ["+storedVO.getNome()+"]");
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}
		output.setValue("roteirosDeInstalacaoEmu", listaDeRoteiros());
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
    	vo.setId(input.getInt("id"));
    	vo.getEmulador().setId(input.getInt("emulador_id"));
    	vo.getDistro().setId(input.getInt("distro_id"));
    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.setAprovado(input.getInt("aprovado"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar roteiro: ");
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover roteiro: id=");
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("roteirosDeInstalacaoEmu", listaDeRoteiros());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {

    	vo.setId(input.getInt("id"));
    	vo.getEmulador().setId(input.getInt("emulador_id"));
    	vo.getDistro().setId(input.getInt("distro_id"));
    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.setAprovado(input.getInt("aprovado"));

    	logger.info(" Remover roteiro");
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("roteirosDeInstalacaoEmu", listaDeRoteiros());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("emulador_id","");
		output.setValue("distro_id","");
		output.setValue("usuario_id","");
		output.setValue("aprovado","");
		output.setValue("descricao","");
    }

    // getters e setters
	public RoteiroInstalacaoEmuVO getVo() {
		return vo;
	}

	public void setVo(RoteiroInstalacaoEmuVO vo) {
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