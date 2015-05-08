package br.com.linuxgames.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ImageFile;

/**
 * Auxilia na manipulacao de imagens
 * @author Fernando Boaglio
 *
 */
public class ImageHelper {

	
	private static Logger logger = Logger.getLogger("ImageHelper");

    
    /**
    *
    * @param item FileItem, representa um arquivo que é enviado pelo formulario
    * MultiPart/Form-data
    * @throws IOException
    */

   public static void inserirImagemDiretorio(FileItem item,String imgPath,ImageFile vo,AbstractDAO dao) throws  IOException {

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
}
