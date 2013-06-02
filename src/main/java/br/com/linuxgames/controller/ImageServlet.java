package br.com.linuxgames.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.ImagemDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ImageFile;
import br.com.linuxgames.model.vo.ImagemVO;
import br.com.linuxgames.util.Constantes;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 3465475686786786781L;

	Logger logger = Logger.getLogger(this.getClass().getName());

	public void doGet (HttpServletRequest request, HttpServletResponse response)
	    throws IOException, ServletException {

		String imagemId = request.getParameter("id");
		String nocache = request.getParameter("nocache");
		ImagemVO vo = new ImagemVO();

		if (nocache==null) nocache="n";

		if (imagemId==null || imagemId.length()==0) return;
		else
		{
		 vo.setId(Integer.parseInt(imagemId));
		}

   	    ImagemDAO dao = ImagemDAO.getInstance();
       	try {
       		Object obj = dao.buscaUm(vo);
       		if (obj instanceof ImagemVO)
       		 vo = (ImagemVO) obj;
       		else
       		 return;

   	    } catch (DAOException e) {
   		  logger.error("erro na busca!",e);
   	    }

   	    String imgPath = null;
        if (vo.getTipo() == Constantes.JOGO)
         imgPath = "jogo";
        else
         imgPath = "emu";
    	String caminho =   org.mentawai.core.ApplicationManager.getRealPath() + "/img/cache/"+imgPath+"/";


    	// imagem direto do banco...
    	if (nocache.equals("y")) {
    		buscaImagemDoBanco(vo,response);
            response.setContentType(vo.getFileMimeType());
            response.setHeader("Expires", "0");
            response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.getOutputStream().flush();
            return;
    	}

    	// arquivo nao existe no cache
    	if (!existsFile(caminho+vo.getFileName()))
    	{
	     vo = buscaImagemDoBanco(vo,response);
	     writeFileToCache(vo,caminho);
	     logger.info("imagem "+vo.getFileName()+" copiada para o cache ["+imgPath+"]");
    	}

    	String url = "/img/cache/"+imgPath+"/"+vo.getFileName();
    	// redireciona para imagem do cache
   	    logger.info("img => "+url);
   	    try {
	     response.sendRedirect(url);
		}catch (IllegalStateException ise) {
		  logger.info(" imagem no cache...");
		}

	  }


	private ImagemVO buscaImagemDoBanco(ImagemVO vo,HttpServletResponse response) {

	 ImagemDAO dao = ImagemDAO.getInstance();

    	try {
    		vo = (ImagemVO) dao.buscaUmaImagem(vo);
	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }

	    Blob photo = (Blob) vo.getFileContent();

	    if (photo==null) return null;

        InputStream in;
		try {
			in = photo.getBinaryStream();
	        int length = (int) photo.length();
	        int bufferSize = 1024;
	        byte[] buffer = new byte[bufferSize];
	        while ((length = in.read(buffer)) != -1) {
	          response.getOutputStream().write(buffer, 0, length);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException eio) {
			eio.printStackTrace();
		}

		return vo;
	 }

	private boolean existsFile(String filename) {
		File f = new File(filename);
		return f.exists();
	}

    private void writeFileToCache(ImageFile vo,String imgPath) {

     	try
  		{
             //Get nome do Arquivo  / Endere�o
  	        String nomeDoArquivo = vo.getFileName();
  	        int pos = nomeDoArquivo.lastIndexOf("\\");
             //Recupera apenas o nome do arquivo
  	        nomeDoArquivo = nomeDoArquivo.substring(pos+1);
             //Path de onde ser� salvo o arquivo
  	        String path = imgPath + nomeDoArquivo;
             //Escrevendo o arquivo
  	        Blob imgBin = (Blob) vo.getFileContent();
  	        InputStream streamIn = imgBin.getBinaryStream();
  	        FileOutputStream streamOut = new FileOutputStream(new File(path));

  	        int c;
  	        while ((c = streamIn.read()) != -1)
  	         {
  	            streamOut.write(c);
  	         }

  	         streamIn.close();
  	         streamOut.close();

  		}
  		catch (Exception e){
  			e.printStackTrace();
  		}

	}


}