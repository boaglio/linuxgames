package br.com.linuxgames.controller.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

public abstract class AbstractCache
{
  Logger logger = Logger.getLogger(this.getClass());

	private  LinkedHashMap<?, ?>  cache;
	private  Date ultimoRefreshDoCache;
	private  long hits;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  Collection getCache()
	{
	 addHit();
	 return new ArrayList(cache.values());
	}

	@SuppressWarnings("rawtypes")
	public  LinkedHashMap getCacheAsLinkedHashMap()
	{
	 addHit();
	 return cache;
	}

	@SuppressWarnings("rawtypes")
	public  HashMap getCacheComoHashMap()
	{
	 addHit();
	 return cache;
	}

	public  int getTamanhoCache()
	{
	 return cache.size();
	}

	public  Date getUltimoRefreshDoCache()
	{
	 return ultimoRefreshDoCache;
	}

	@SuppressWarnings("rawtypes")
	public void setCache(LinkedHashMap  cache)
	{
		this.cache = cache;
	}

	public void setUltimoRefreshDoCache(Date ultimoRefreshDoCache)
	{
		this.ultimoRefreshDoCache = ultimoRefreshDoCache;
	}

	/**
	 * le a quantidade de hits do cache
	 * @return
	 */
	public long getHits() {
		return hits;
	}

	/**
	 * refresh no cache
	 * @throws Exception
	 */
	public void refresh()
	{
	  // limpa o numero de hits
	  resetHits();
	}

	/**
	 * limpa o numero de hits do cache
	 */
	private void resetHits() {
		this.hits=0;
	}

	/**
	 * adiciona hit ao cache
	 */
	private void addHit() {
		this.hits++;
	}
}
