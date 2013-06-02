package br.com.linuxgames.controller.crud;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.ImagemDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ImageFile;
import br.com.linuxgames.model.vo.ImagemVO;
import br.com.linuxgames.util.Constantes;


public class ImagensAction extends BaseAction
 implements RedirectAfterLogin
{

    ImagemDAO dao = ImagemDAO.getInstance();
	Logger logger = Logger.getLogger(ImagensAction.class);

	private ImagemVO vo;
	private Collection<ImagemVO> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
    }

	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<ImagemVO> listaDeImagems() {
    	Collection<ImagemVO> collection = null;
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
    	output.setValue("imagens", listaDeImagems());
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.getEmu().setId(input.getInt("emu_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	try {
    		ImagemVO storedVO = (ImagemVO) dao.buscaUm(vo);

    		output.setValue("emu_id", storedVO.getEmu().getId());
    		output.setValue("jogo_id", storedVO.getJogo().getId());
    		output.setValue("fileName", storedVO.getFileName());
    		output.setValue("fileMimeType", storedVO.getFileMimeType());
    		output.setValue("emuOld", storedVO.getEmu().getId());
    		output.setValue("jogoOld", storedVO.getJogo().getId());

	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("imagem", listaDeImagems());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

    	ImagemVO vo = new ImagemVO();
    	vo.getEmu().setId(input.getInt("emu_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	FileItem item = (FileItem) input.getValue("fileContent");

    	vo.setFileMimeType(item.getContentType());
    	vo.setFileName(item.getName());

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

//    	// chama o dao e traz o resultado
//    	try {
//    		dao.adiciona(vo);
//		} catch (DAOException e) {
//			logger.error("erro no cadastro!",e);
//		}

        String imgPath = null;
        if (vo.getEmu().getId()==0)
         imgPath = "jogo";
        else
         imgPath = "emu";

        inserirImagemDiretorio(item,imgPath,vo);

		output.setValue("imagens", listaDeImagems());
		resetForm();
		return SUCCESS;
    }


    /**
    *
    * @param item FileItem, representa um arquivo que é enviado pelo formulario
    * MultiPart/Form-data
    * @throws IOException
    */

   private void inserirImagemDiretorio(FileItem item,String imgPath,ImageFile vo) throws  IOException {

           //Pega o diretório /logo dentro do diretório atual de onde a
           //aplicação está rodando
           String caminho =   org.mentawai.core.ApplicationManager.getRealPath() + "/img/cache/"+imgPath+"/";

            // Cria o diretório caso ele não exista
           File diretorio = new File(caminho);
           if (!diretorio.exists()){
               diretorio.mkdirs();
           }

           // Mandar o arquivo para o diretório informado
           String nome = vo.getFileName();
           String arq[] = nome.split("\\\\");
           for (int i = 0; i < arq.length; i++) {
               nome = arq[i];
           }

           File file = new File(diretorio, nome);
           FileOutputStream output = new FileOutputStream(file);
           InputStream is = item.getInputStream();
           byte[] buffer = new byte[2048];
           int nLidos;
           while ((nLidos = is.read(buffer)) >= 0) {
               output.write(buffer, 0, nLidos);
           }

           output.flush();
           output.close();

           vo.setFilePath(caminho+nome);

	       // chama o dao e traz o resultado
	       try {
	       		dao.adiciona(vo);
	   		} catch (DAOException e) {
	   			logger.error("erro no cadastro!",e);
	   		}

   }



	/**
	 *action que atualiza um registro
	 * @return
	 * @throws Exception
	 */
    public String atualiza() throws Exception {

    	// ajusta o VO
    	vo.getEmu().setId(input.getInt("emu_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Imagem: ");
    	 try {
    		dao.atualiza(vo);
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Imagem: id=");
    	 try {
    		dao.remove(vo);
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("imagens", listaDeImagems());
		resetForm();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {

    	vo.getEmu().setId(input.getInt("emu_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	logger.info(" Remover Imagem");
    	try {
    		dao.remove(vo);
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("imagens", listaDeImagems());
    	resetForm();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("emu_id", "");
		output.setValue("jogo_id","");
    }

    // getters e setters
	public ImageFile getVo() {
		return vo;
	}

	public void setVo(ImagemVO vo) {
		this.vo = vo;
	}

	public Collection<ImagemVO> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<ImagemVO> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}