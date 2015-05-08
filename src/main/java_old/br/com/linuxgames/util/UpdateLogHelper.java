package br.com.linuxgames.util;

import java.util.Date;

import org.apache.log4j.Logger;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.UpdateLogDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.UpdateLogVO;

public class UpdateLogHelper {

	static Logger logger = Logger.getLogger(UpdateLogHelper.class);

	/**
	 * Grava a alteracao no log do site
	 * @param mensagem
	 */
	public static void setUpdateLog(String mensagem)
    {
	  UpdateLogVO updateLogVO = new UpdateLogVO();
	  UpdateLogDAO updateLogDAO = UpdateLogDAO.getInstance();
	  // data de hoje
	  updateLogVO.setData(new Date());
	  // mensagem de update
	  updateLogVO.setDescricao(mensagem);
	  // grava update
	  try {
	       updateLogDAO.adiciona(updateLogVO);
	       CacheManager.getCacheDeUpdates().refreshNoCache();
		  } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	   }
	  logger.info("Atualizacao do site gravada!"); 
    }
}
