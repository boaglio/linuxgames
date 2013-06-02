package br.com.linuxgames.controller.crud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.model.dao.TelaDeJogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.TelaDeJogoVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.FileHelper;
import br.com.linuxgames.util.ImageHelper;
import br.com.linuxgames.util.UpdateLogHelper;


public class TelaDeJogoAction extends BaseAction implements RedirectAfterLogin {

    TelaDeJogoDAO dao = TelaDeJogoDAO.getInstance();
	Logger logger = Logger.getLogger(TelaDeJogoAction.class);

	private TelaDeJogoVO vo;
	private Collection<TelaDeJogoVO> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<TelaDeJogoVO> listaDeTelas() {
    	Collection<TelaDeJogoVO> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	/**
	 * Retorna o combo com todas as imagens
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "unused", "rawtypes" })
	private Collection listaDeImagensDeJogos() {
		String path = org.mentawai.core.ApplicationManager.getRealPath() + "//img//games//";
		ArrayList listaDeImagens = (ArrayList<?>) FileHelper.getAllFiles(path);
		listaDeImagens.remove(".svn");
		listaDeImagens.remove(".cvs");
	    Collections.sort(listaDeImagens);
	    return listaDeImagens;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("telasDeJogos", listaDeTelas());
//    	output.setValue("imagensDeJogos", listaDeImagensDeJogos());
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
    		TelaDeJogoVO storedVO = (TelaDeJogoVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getNome());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("nome", storedVO.getNome());
    		output.setValue("descricao", storedVO.getDescricao());
    		output.setValue("tipo_id",  String.valueOf(storedVO.getJogo().getId()));

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("telasDeJogos", listaDeTelas());
    	// output.setValue("imagensDeJogos", listaDeImagensDeJogos());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String jogoID= input.getString("tipo_id");
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogo(jogoVO);

     	JogoCache jogoCache = CacheManager.getCacheDeJogos();
     	Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(vo.getJogo().getId());

    	FileItem item = (FileItem) input.getValue("fileContent");

    	vo.setFileMimeType(item.getContentType());
    	vo.setFileName(item.getName());
    	vo.setNome(item.getName());

        if (item==null || item.getSize()==0)
        {
          addMessage("</br>Erro:<i> informe o arquivo para upload!</i>");
 		  return ERROR;
        }
        else
        {
         vo.setFileSize(item.getSize());
         vo.setFileContent(item.getInputStream());
        }

        String imgPath = "jogo";

        // chama o dao e traz o resultado
    	logger.info(" Tela: nome="+vo.getNome());

        // grava imagem no diretorio
        ImageHelper.inserirImagemDiretorio(item,imgPath,vo,dao);

		UpdateLogHelper.setUpdateLog("Adicionada nova tela do jogo ["+storedVO.getNome()+"]");

		output.setValue("telasDeJogos", listaDeTelas());
//		output.setValue("imagensDeJogos", listaDeImagensDeJogos());
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
     	String jogoID= input.getString("tipo_id");
     	Jogo jogoVO = new Jogo();
     	jogoVO.setId(Integer.parseInt(jogoID));
     	vo.setJogo(jogoVO);

     	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Tela: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Tela: id="+vo.getId());
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("telasDeJogos", listaDeTelas());
//		output.setValue("imagensDeJogos", listaDeImagensDeJogos());
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
    	logger.info(" Remover Tela: id="+vo.getId());
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("telasDeJogos", listaDeTelas());
//    	output.setValue("imagensDeJogos", listaDeImagensDeJogos());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id",new Integer(0));
		output.setValue("nome","");
		output.setValue("descricao","");
		output.setValue("tipo_id",new Integer(0));
    }

    // getters e setters
	public TelaDeJogoVO getVo() {
		return vo;
	}

	public void setVo(TelaDeJogoVO vo) {
		this.vo = vo;
	}

	public Collection<TelaDeJogoVO> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<TelaDeJogoVO> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}


}