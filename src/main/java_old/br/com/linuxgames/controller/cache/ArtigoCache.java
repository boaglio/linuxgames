package br.com.linuxgames.controller.cache;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.ArtigoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ArtigoVO;

public class ArtigoCache extends AbstractCache implements RefreshableCache
{

	Logger logger = Logger.getLogger(this.getClass());
	private static ArtigoCache instance = new ArtigoCache();

	private ArtigoCache()
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
	public static ArtigoCache getInstance() {
		return instance;
	}

	/**
	 * refresh no cache
	 */
	@SuppressWarnings("unchecked")
	public void refreshNoCache() throws DAOException
	{
		ArtigoDAO artigoDAO = ArtigoDAO.getInstance();
		LinkedHashMap<Integer, ArtigoVO>  novoCache = new LinkedHashMap<Integer, ArtigoVO>();
		Collection<ArtigoVO> todosArtigos = artigoDAO.buscaTodos();
	    for (ArtigoVO element : todosArtigos)
		{
		 novoCache.put(new Integer(element.getId()), element);
		}
	    setCache(novoCache);
	    setUltimoRefreshDoCache(new Date());
	}

	/**
	 * atualiza nota do emulador no cache
	 * @param artigo
	 */
	public void updateNotaArtigo(ArtigoVO artigo) {
		LinkedHashMap<?, ?> cache = getCacheAsLinkedHashMap();
		ArtigoVO artigoNoCache = (ArtigoVO) cache.get(artigo.getId());
		double notaGeral = artigoNoCache.getNotaGeral();
		int votos = artigoNoCache.getVotos()+1;
		notaGeral = (notaGeral*(votos-1) + artigo.getNotaGeral())/votos;
		artigoNoCache.setNotaGeral(notaGeral);
		artigoNoCache.setVotos(votos);
	}
}
