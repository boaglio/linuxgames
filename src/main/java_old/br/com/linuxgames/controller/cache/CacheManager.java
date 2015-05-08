package br.com.linuxgames.controller.cache;

import java.util.HashMap;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.core.DAOException;

public class CacheManager
{

	Logger logger = Logger.getLogger(this.getClass());

	private static ArtigoCache cacheDeArtigos = ArtigoCache.getInstance();
	private static JogoCache cacheDeJogos= JogoCachePTBR.getInstance();
	private static JogoCache cacheDeJogosEN= JogoCacheEN.getInstance();
	private static HashMap<Integer,Integer> cacheIdJogosPtEn = new HashMap<Integer, Integer>();
	private static HashMap<Integer,Integer> cacheIdJogosEnPt = new HashMap<Integer, Integer>();
	private static EmuladorCache cacheDeEmuladores= EmuladorCache.getInstance();
	private static NovidadeCache cacheDeNovidades= NovidadeCache.getInstance();
	private static UpdatelogCache cacheDeUpdates= UpdatelogCache.getInstance();
	private static ArtigoExternoCache cacheDeArtigosExternos = ArtigoExternoCache.getInstance();

	static {
	 // criando cache
		try
		{
		 cacheDeArtigos.refreshNoCache();
		 cacheDeJogos.refreshNoCache();
		 cacheDeJogosEN.refreshNoCacheEN();
		 cacheDeEmuladores.refreshNoCache();
		 cacheDeNovidades.refreshNoCache();
		 cacheDeUpdates.refreshNoCache();
		 cacheDeArtigosExternos.refreshNoCache();
		} catch (DAOException e)
		{
		  e.printStackTrace();
		}
	}

	// getters e setters

	public static HashMap<Integer, Integer> getCacheIdJogosEnPt() {
		return cacheIdJogosEnPt;
	}

	public static void setCacheIdJogosEnPt(
			HashMap<Integer, Integer> cacheIdJogosEnPt) {
		CacheManager.cacheIdJogosEnPt = cacheIdJogosEnPt;
	}

	public static HashMap<Integer, Integer> getCacheIdJogosPtEn() {
		return cacheIdJogosPtEn;
	}

	public static void setCacheIdJogosPtEn(
			HashMap<Integer, Integer> cacheIdJogosPtEn) {
		CacheManager.cacheIdJogosPtEn = cacheIdJogosPtEn;
	}

	public static ArtigoCache getCacheDeArtigos()
	{
		return cacheDeArtigos;
	}

	public static void setCacheDeArtigos(ArtigoCache cacheDeArtigos)
	{
		CacheManager.cacheDeArtigos = cacheDeArtigos;
	}

	public static EmuladorCache getCacheDeEmuladores()
	{
		return cacheDeEmuladores;
	}

	public static void setCacheDeEmuladores(EmuladorCache cacheDeEmuladores)
	{
		CacheManager.cacheDeEmuladores = cacheDeEmuladores;
	}

	public static JogoCache  getCacheDeJogos()
	{
		return cacheDeJogos;
	}

	public static void setCacheDeJogos(JogoCache cacheDeJogos)
	{
		CacheManager.cacheDeJogos = cacheDeJogos;
	}

	public static NovidadeCache getCacheDeNovidades() {
		return cacheDeNovidades;
	}

	public static void setCacheDeNovidades(NovidadeCache cacheDeNovidades) {
		CacheManager.cacheDeNovidades = cacheDeNovidades;
	}

	public static UpdatelogCache getCacheDeUpdates() {
		return cacheDeUpdates;
	}

	public static void setCacheDeUpdates(UpdatelogCache cacheDeUpdates) {
		CacheManager.cacheDeUpdates = cacheDeUpdates;
	}

	public static ArtigoExternoCache getCacheDeArtigosExternos() {
		return cacheDeArtigosExternos;
	}

	public static void setCacheDeArtigosExternos(
			ArtigoExternoCache cacheDeArtigosExterno) {
		CacheManager.cacheDeArtigosExternos = cacheDeArtigosExterno;
	}

	public static JogoCache getCacheDeJogosEN() {
		return cacheDeJogosEN;
	}

	public static void setCacheDeJogosEN(JogoCache  cacheDeJogosEN) {
		CacheManager.cacheDeJogosEN = cacheDeJogosEN;
	}

}
