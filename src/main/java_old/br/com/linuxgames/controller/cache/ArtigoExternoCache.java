package br.com.linuxgames.controller.cache;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.ArtigoExternoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ArtigoExternoVO;

public class ArtigoExternoCache extends AbstractCache implements RefreshableCache
{

	Logger logger = Logger.getLogger(this.getClass());
	private static ArtigoExternoCache instance = new ArtigoExternoCache();

	private ArtigoExternoCache()
	{
	 // criando cache de artigos
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
	public static ArtigoExternoCache getInstance() {
		return instance;
	}

	/**
	 * refresh no cache
	 */
	@SuppressWarnings("unchecked")
	public void refreshNoCache() throws DAOException
	{
		ArtigoExternoDAO artigoExternoDAO = ArtigoExternoDAO.getInstance();
		LinkedHashMap<Integer, ArtigoExternoVO>  novoCache = new LinkedHashMap<Integer, ArtigoExternoVO>();
		Collection<ArtigoExternoVO> todosArtigos = artigoExternoDAO.buscaTodos();
	    for (ArtigoExternoVO element : todosArtigos)
		{
		 novoCache.put(new Integer(element.getId()), element);
		}
	    setCache(novoCache);
	    setUltimoRefreshDoCache(new Date());
	}

}
