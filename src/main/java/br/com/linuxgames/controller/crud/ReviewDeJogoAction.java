package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.model.dao.ReviewDeJogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.ReviewDeJogoVO;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.UpdateLogHelper;


public class ReviewDeJogoAction extends BaseAction implements RedirectAfterLogin {

    ReviewDeJogoDAO dao = ReviewDeJogoDAO.getinstance();
	Logger logger = Logger.getLogger(this.getClass());

	private ReviewDeJogoVO vo;
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
    	output.setValue("reviewsDeJogos", listaDeReviews());
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
    		ReviewDeJogoVO storedVO = (ReviewDeJogoVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nota="+storedVO.getNota());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("comentario", storedVO.getComentario());
    		output.setValue("nota", storedVO.getNota());
    		output.setValue("jogo_id",  String.valueOf(storedVO.getJogo().getId()));
    		output.setValue("usuario_id",  String.valueOf(storedVO.getUsuario().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("reviewsDeJogos", listaDeReviews());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String jogoID= input.getString("jogo_id");
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogo(jogoVO);

     	String usuarioID= input.getString("usuario_id");
     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(Integer.parseInt(usuarioID));
     	vo.setUsuario(usuarioVO);

     	JogoCache jogoCache = CacheManager.getCacheDeJogos();
     	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(jogoID));
    	// chama o dao e traz o resultado
    	logger.info(" Review: nota="+vo.getNota());
    	try {
    		dao.adiciona(vo);
    		UpdateLogHelper.setUpdateLog("Adicionada nova review do jogo ["+storedVO.getNome()+"]");
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("reviewsDeJogos", listaDeReviews());
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

    	String jogoID= input.getString("jogo_id");
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogo(jogoVO);

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

		output.setValue("reviewsDeJogos", listaDeReviews());
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
    	output.setValue("reviewsDeJogos", listaDeReviews());
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
		output.setValue("jogo_id",new Integer(0));
		output.setValue("usuario_id",new Integer(0));
    }

    // getters e setters
	public ReviewDeJogoVO getVo() {
		return vo;
	}

	public void setVo(ReviewDeJogoVO vo) {
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