package br.com.linuxgames.controller.cache;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.UpdateLogDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.UpdateLogVO;

public class UpdatelogCache extends AbstractCache implements RefreshableCache
{

	Logger logger = Logger.getLogger(this.getClass());
	private static UpdatelogCache instance = new UpdatelogCache();

	private UpdatelogCache()
	{
	 // criando cache de atualizacoes do site
		try
		{
		 refreshNoCache();
		} catch (DAOException e)
		{
		  e.printStackTrace();
		}
	}

	/**
	 * singleton
	 */
	public static UpdatelogCache getInstance() {
		return instance;
	}

	/**
	 * refresh no cache
	 */
	public void refreshNoCache() throws DAOException
	{
		UpdateLogDAO dao = UpdateLogDAO.getInstance();

		LinkedHashMap<Integer, UpdateLogVO>  novoCache = new LinkedHashMap<Integer, UpdateLogVO>();
		Collection<UpdateLogVO> top5updates = null;
		try {
			top5updates = (Collection<UpdateLogVO>) dao.buscaUltimasAtualizacoes();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		int counter=1;
	    for (UpdateLogVO element:  top5updates)
		{
		 novoCache.put(new Integer(counter++), element);
		}
	    setCache(novoCache);
	    setUltimoRefreshDoCache(new Date());
	    super.refresh();
	}

}
