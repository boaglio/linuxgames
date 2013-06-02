package br.com.linuxgames.controller.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.EmuladorDAO;
import br.com.linuxgames.model.dao.TelaDeEmuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladorHitComparator;
import br.com.linuxgames.model.vo.EmuladorVO;

public class EmuladorCache extends AbstractCache implements RefreshableCache
{

	Logger logger = Logger.getLogger(this.getClass());
	private static EmuladorCache instance = new EmuladorCache();
	private static final int TOP = 10;
	private EmuladorVO emuladorDoDia;

	private EmuladorCache()
	{
	 // criando cache de emuladores
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
	public static EmuladorCache getInstance() {
		return instance;
	}

	/**
	 * refresh no cache
	 */
	@SuppressWarnings("unchecked")
	public void refreshNoCache() throws DAOException
	{
		EmuladorDAO emuladorDAO = EmuladorDAO.getInstance();
		LinkedHashMap<Integer, EmuladorVO>  novoCache = new LinkedHashMap<Integer, EmuladorVO>();
		Collection<EmuladorVO> todosEmuladors = emuladorDAO.buscaTodos();
	    for (EmuladorVO element : todosEmuladors)
		{
		 novoCache.put(new Integer(element.getId()), element);
		 if (element.isDestaque())
				this.emuladorDoDia=element;
		}

		// atualiza emuladores com telas
		TelaDeEmuladorDAO telaDeEmuladorDao = TelaDeEmuladorDAO.getInstance();
		Collection<Integer> idsComTelas = telaDeEmuladorDao.buscaEmuladorIdComTelas();
		for(Integer emuladorId : idsComTelas) {
			if (novoCache.containsKey(emuladorId)) {
			 EmuladorVO emulador = novoCache.get(emuladorId);
			 emulador.setTemTela(true);
			 novoCache.put(emuladorId,emulador);
			}
		}

	    setCache(novoCache);
	    setUltimoRefreshDoCache(new Date());
	    super.refresh();
	}

	/**
	 * atualiza nota do emulador no cache
	 * @param emulador
	 */
	public void updateNotaEmulador(EmuladorVO emulador) {
		LinkedHashMap<?, ?> cache = getCacheAsLinkedHashMap();
		EmuladorVO emuladorNoCache = (EmuladorVO) cache.get(emulador.getId());
		double notaGeral = emuladorNoCache.getNotaGeral();
		int votos = emuladorNoCache.getVotos()+1;
		notaGeral = (notaGeral*(votos-1) + emulador.getNotaGeral())/votos;
		emuladorNoCache.setNotaGeral(notaGeral);
		emuladorNoCache.setVotos(votos);
	}

	/**
	 * traz os N emus mais acessados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<EmuladorVO> getTop() {
		Collection<EmuladorVO> topGames = new ArrayList<EmuladorVO>();
		// ordena emus pelos hits
		EmuladorCache emuCache = CacheManager.getCacheDeEmuladores();
		ArrayList<EmuladorVO> emusOrdenadosPorHits =  new ArrayList<EmuladorVO>(emuCache.getCache());
		Collections.sort(emusOrdenadosPorHits,new EmuladorHitComparator());

		for (int a=0;a<TOP;a++) {
		 int tamanho = emusOrdenadosPorHits.size()-1;
		 EmuladorVO vo = emusOrdenadosPorHits.get(tamanho - a);
		 topGames.add(vo);
  	    }
		return topGames;
	}


	/**
	 * atualiza hit do emu no cache
	 * @param emu
	 */
	public void updateHitEmulador(EmuladorVO emu) {
		LinkedHashMap<?, ?> cache = getCacheAsLinkedHashMap();
		EmuladorVO emuNoCache = (EmuladorVO) cache.get(emu.getId());
		emuNoCache.setHits(emu.getHits()+1);
		// atualiza hit do emu
		EmuladorDAO emuDAO = EmuladorDAO.getInstance();
		try {
			emuDAO.atualizaHits(emu);
		} catch (DAOException e) {
			logger.error("ERRO AO ATUALIZAR HIT",e);
		}
	}

	public EmuladorVO getEmuladorDoDia() {
		return emuladorDoDia;
	}


	@SuppressWarnings("unchecked")
	public int getEmuladorIdAleatorioComTela() {

		HashMap<Integer, EmuladorVO> emuladores = getCacheComoHashMap();
		int maxSize = emuladores.size();
		int emuladorId=1;
		Random randomGenerator = new Random();
		boolean achou = false;
		int desiste=100;
		while (!achou && desiste>0) {
		   int randomInt = randomGenerator.nextInt(maxSize);
		   EmuladorVO emulador = emuladores.get(randomInt);
		   if (emulador!=null && emulador.isTemTela()) {
			   achou=true;
			   emuladorId = randomInt;
		   }
		   desiste--;
		}

		return emuladorId;
	}

	@Override
	public String toString() {
		return "EmuladorCache [emuladorDoDia=" + emuladorDoDia + "]";
	}

}
