package br.com.linuxgames.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.core.ResponseOutput;

import br.com.linuxgames.model.dao.ImagemDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ImagemVO;


public class BuscaImagemAction extends BaseAction
// implements AuthenticationFree
{

    ImagemDAO dao = ImagemDAO.getInstance();
	Logger logger = Logger.getLogger(BuscaImagemAction.class);

	public static final String JPG = "image/jpg";
	public static final String GIF = "image/gif";
	public static final String PNG = "image/png";
	public static final String TIFF = "image/tiff";

	public boolean shouldRedirect(String innerAction) {
	     return true;
	    }

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {

    	ImagemVO vo = new ImagemVO();
    	vo.getEmu().setId(input.getInt("emu_id"));
    	vo.getJogo().setId(input.getInt("jogo_id"));

    	ResponseOutput outputResp = (ResponseOutput) output;
    	try {
    		vo = (ImagemVO) dao.buscaUmaImagem(vo);
	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }

	    Blob photo = (Blob) vo.getFileContent();

        InputStream in;
		try {
			in = photo.getBinaryStream();
	        int length = (int) photo.length();
	        int bufferSize = 1024;
	        byte[] buffer = new byte[bufferSize];
	        while ((length = in.read(buffer)) != -1) {
	          outputResp.getResponse().getOutputStream().write(buffer, 0, length);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException eio) {
			eio.printStackTrace();
		}

    	// retorna o mime type correto
        if (vo.getFileMimeType().equals(TIFF))
        	return TIFF;

        if (vo.getFileMimeType().equals(PNG))
        	return PNG;

        if (vo.getFileMimeType().equals(GIF))
        	return GIF;

        return JPG;

    }


	public boolean bypassAuthentication(String arg0) {
		return true;
	}

}