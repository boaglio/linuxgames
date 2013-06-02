package br.com.linuxgames.controller.crud;

import java.io.File;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.model.dao.JogoDAO;


public class JogoImagemAction extends BaseAction implements RedirectAfterLogin {

    JogoDAO dao = JogoDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass());
	
	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {

       String destinoDaImagem = input.getString("destinoDaImagem");
       if (destinoDaImagem!= null)
    	try
 		{
 	        FileItem item = (FileItem) input.getValue("arquivo");
            //Get nome do Arquivo  / Endere�o
 	        String nomeDoArquivo = item.getName();
 	        
 	        if (item.getSize()==0)
 	        {
 	          addMessage("</br>Erro:<i> informe o arquivo para upload!</i>");
 	 		  return ERROR;
 	        }
 	        
            //Get posi��o das barras
 	        int pos = nomeDoArquivo.lastIndexOf("\\");
            //Recupera apenas o nome do arquivo  
 	        nomeDoArquivo = nomeDoArquivo.substring(pos+1);
            //Path de onde ser� salvo o arquivo
 	        String path = org.mentawai.core.ApplicationManager.getRealPath() + destinoDaImagem + nomeDoArquivo;
            //Escrevendo o arquivo
 	        item.write(new File(path));
 	        // adiciona mensagem
 	        addMessage("<b>Upload efetuado com sucesso! </b>");
 	        String urlImagem = destinoDaImagem.substring(1)+nomeDoArquivo;
 	        output.setValue("imagemNova",urlImagem);
 	        logger.info("UPLOAD: "+urlImagem);
 		}
 		catch (Exception e){
         	addMessage("N&atilde;o foi poss&iacute;vel salvar o arquivo! </br>Erro:<i>"+e.getMessage()+"</i>");
 			return ERROR;
 		}
 		
    	return SUCCESS;
    }

    

}