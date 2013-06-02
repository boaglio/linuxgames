package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.JogosFavoritosDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.JogosFavoritosVO;
import br.com.linuxgames.util.Constantes;


public class JogosFavoritosAction extends BaseAction implements RedirectAfterLogin  {

    JogosFavoritosDAO dao = JogosFavoritosDAO.getinstance();
	Logger logger = Logger.getLogger(JogosFavoritosAction.class);

	private JogosFavoritosVO vo;
	private Collection<JogosFavoritosVO> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<JogosFavoritosVO> listaDeJogosFavoritos() {
    	Collection<JogosFavoritosVO> collection = null;
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
    	output.setValue("jogosFavoritos", listaDeJogosFavoritos());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	try {
    		JogosFavoritosVO storedVO = (JogosFavoritosVO) dao.buscaUm(vo);
    		// setando os valores lidos
    		output.setValue("usuario_id", storedVO.getUsuario().getId());
    		output.setValue("jogo_id", storedVO.getJogo().getId());
    		output.setValue("oldUsuario", storedVO.getUsuario().getId());
    		output.setValue("oldJogo", storedVO.getJogo().getId());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("jogosFavoritos", listaDeJogosFavoritos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	// chama o dao e traz o resultado
    	try {
    		dao.adiciona(vo);
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("jogosFavoritos", listaDeJogosFavoritos());
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
    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));
    	vo.setOldUsuario(input.getInt("oldUsuario"));
    	vo.setOldJogo(input.getInt("oldJogo"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar JogosFavoritos: ");
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover JogosFavoritos: id=");
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("jogosFavoritos", listaDeJogosFavoritos());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {

    	vo.getUsuario().setId(input.getInt("usuario_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	logger.info(" Remover JogosFavoritos");
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("jogosFavoritos", listaDeJogosFavoritos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("usuario_id", "");
		output.setValue("jogo_id","");
    }

    // getters e setters
	public JogosFavoritosVO getVo() {
		return vo;
	}

	public void setVo(JogosFavoritosVO vo) {
		this.vo = vo;
	}

	public Collection<JogosFavoritosVO> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<JogosFavoritosVO> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}