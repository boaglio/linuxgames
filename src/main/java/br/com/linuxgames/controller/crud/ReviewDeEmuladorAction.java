package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.model.dao.ReviewDeEmuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.ReviewDeEmuladorVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.UpdateLogHelper;


public class ReviewDeEmuladorAction extends BaseAction implements RedirectAfterLogin {

    ReviewDeEmuladorDAO dao = ReviewDeEmuladorDAO.getinstance();
	Logger logger = Logger.getLogger(this.getClass());

	private ReviewDeEmuladorVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeReviews() {
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
    	output.setValue("reviewsDeEmuladores", listaDeReviews());
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
    		ReviewDeEmuladorVO storedVO = (ReviewDeEmuladorVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nota="+storedVO.getNota());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("comentario", storedVO.getComentario());
    		output.setValue("nota", storedVO.getNota());
    		output.setValue("emu_id",  String.valueOf(storedVO.getEmulador().getId()));
    		output.setValue("usuario_id",  String.valueOf(storedVO.getUsuario().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("reviewsDeEmuladores", listaDeReviews());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String emuID= input.getString("emu_id");
     	EmuladorVO emuVO = new EmuladorVO();
     	emuVO.setId(Integer.parseInt(emuID));
     	vo.setEmulador(emuVO);

     	String usuarioID= input.getString("usuario_id");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(usuarioID));
     	vo.setUsuario(usuarioVO);

     	EmuladorCache emuCache = CacheManager.getCacheDeEmuladores();
     	EmuladorVO storedVO = (EmuladorVO) emuCache.getCacheComoHashMap().get(new Integer(emuID));

    	// chama o dao e traz o resultado
    	logger.info(" Review: nota="+vo.getNota());
    	try {
    		dao.adiciona(vo);
      		UpdateLogHelper.setUpdateLog("Adicionada nova review do emulador ["+storedVO.getNome()+"]");
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("reviewsDeEmuladores", listaDeReviews());
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
    	vo.setNota( input.getInt("nota"));

    	String emuID= input.getString("emu_id");
     	EmuladorVO emuVO = new EmuladorVO();
     	emuVO.setId(Integer.parseInt(emuID));
     	vo.setEmulador(emuVO);

     	String usuarioID= input.getString("usuario_id");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(usuarioID));
     	vo.setUsuario(usuarioVO);

     	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Review: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Review: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("reviewsDeEmuladores", listaDeReviews());
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
    	logger.info(" Remover Review: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("reviewsDeEmuladores", listaDeReviews());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id",new Integer(0));
		output.setValue("nota",new Integer(0));
		output.setValue("comentario","");
		output.setValue("emu_id",new Integer(0));
		output.setValue("usuario_id",new Integer(0));
    }

    // getters e setters
	public ReviewDeEmuladorVO getVo() {
		return vo;
	}

	public void setVo(ReviewDeEmuladorVO vo) {
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