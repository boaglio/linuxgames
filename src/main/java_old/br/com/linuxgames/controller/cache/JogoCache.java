package br.com.linuxgames.controller.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Random;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.JogoDAO;
import br.com.linuxgames.model.dao.TelaDeJogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.JogoHitComparator;
import br.com.linuxgames.util.LocaleHelper;

public class JogoCache extends AbstractCache implements RefreshableCache
{

	Logger logger = Logger.getLogger(this.getClass());

	private static final int TOP = 10;
	private Jogo jogoDoDia;


	protected JogoCache()
	{
	 // criando cache de jogos
		try
		{
		 refreshNoCache();
		} catch (DAOException e)
		{
		  e.printStackTrace();
		}
	}


	public void refreshNoCache() throws DAOException
	{
		JogoDAO jogoDAO = JogoDAO.getInstance();

		LinkedHashMap<Integer, Jogo> jogosCache = new LinkedHashMap<Integer,Jogo>();

		// jogos em pt_BR
		Collection<Jogo> todosJogosBR = jogoDAO.buscaPorIdioma(LocaleHelper.PT_BR2);

		for (Jogo element :todosJogosBR) {

		    jogosCache.put(new Integer(element.getId()), element);

		    if (element.isDestaque())
			  this.jogoDoDia=element;
		}

		// atualiza jogos com telas
		TelaDeJogoDAO telaDeJogoDao = TelaDeJogoDAO.getInstance();
		Collection<Integer> idsComTelas = telaDeJogoDao.buscaJogosIdComTelas();
		for(Integer jogoId : idsComTelas) {
			if (jogosCache.containsKey(jogoId)) {
			 Jogo jogo = jogosCache.get(jogoId);
			 jogo.setTemTela(true);
			 jogosCache.put(jogoId,jogo);
			}
		}


	    setCache(jogosCache);
	    setUltimoRefreshDoCache(new Date());
	    super.refresh();
	}


	public void refreshNoCacheEN() throws DAOException
	{
		JogoDAO jogoDAO = JogoDAO.getInstance();

		LinkedHashMap<Integer, Jogo> jogosCache = new LinkedHashMap<Integer,Jogo>();

		HashMap<Integer,Integer> cacheIdJogosPtEn = CacheManager.getCacheIdJogosPtEn();

		HashMap<Integer,Integer> cacheIdJogosEnPt = CacheManager.getCacheIdJogosEnPt();

		// jogos em pt_BR
		Collection<Jogo> todosJogosBR = jogoDAO.buscaPorIdioma(LocaleHelper.EN);

		for (Iterator<Jogo> iterator = todosJogosBR.iterator(); iterator.hasNext();) {

			Jogo element  = iterator.next();

		    jogosCache.put(new Integer(element.getId()), element);

		    cacheIdJogosPtEn.put(new Integer(element.getJogoId()), new Integer(element.getId()));

		    cacheIdJogosEnPt.put(new Integer(element.getId()), new Integer(element.getJogoId()));

		    if (element.isDestaque())
			  this.jogoDoDia=element;
		}

	    setCache(jogosCache);
	    setUltimoRefreshDoCache(new Date());
	    super.refresh();
	}

	@SuppressWarnings("unchecked")
	public int getJogoIdAleatorioComTela() {

		HashMap<Integer, Jogo> jogos = getCacheComoHashMap();
		int maxSize = jogos.size();
		int jogoId=0;
		Random randomGenerator = new Random();
		boolean achou = false;
		while (!achou) {
		   int randomInt = randomGenerator.nextInt(maxSize);
		   Jogo jogo = jogos.get(randomInt);
		   if (jogo!=null && jogo.isTemTela()) {
			   achou=true;
			   jogoId = randomInt;
		   }
		}

		return jogoId;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.controller.cache.JogoCache#updateNotaJogo(br.com.linuxgames.model.vo.Jogo)
	 */
	public void updateNotaJogo(Jogo jogo) {
		LinkedHashMap<?, ?> cache = getCacheAsLinkedHashMap();

		int idParaAtualizar = getIdParaAtualizar(jogo);

		Jogo jogoNoCache = (Jogo) cache.get(idParaAtualizar);
		double notaGeral = jogoNoCache.getNotaGeral();
		int votos = jogoNoCache.getVotos()+1;
		notaGeral = (notaGeral*(votos-1) + jogo.getNotaGeral())/votos;
		jogoNoCache.setNotaGeral(notaGeral);
		jogoNoCache.setVotos(votos);
	}

	private int getIdParaAtualizar(Jogo jogo) {
		int idParaAtualizar = jogo.getJogoId();
		if (idParaAtualizar==0)
			idParaAtualizar = jogo.getId();
		return idParaAtualizar;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.controller.cache.JogoCache#updateHitJogo(br.com.linuxgames.model.vo.Jogo)
	 */
	public void updateHitJogo(Jogo jogo) {
		LinkedHashMap<?, ?> cache = getCacheAsLinkedHashMap();

		int idParaAtualizar = getIdParaAtualizar(jogo);

		Jogo jogoNoCache = (Jogo) cache.get(idParaAtualizar);
		jogoNoCache.setHits(jogo.getHits()+1);
		// atualiza hit do jogo
		JogoDAO jogoDAO = JogoDAO.getInstance();
		try {
			jogoDAO.atualizaHits(jogo);
		} catch (DAOException e) {
			logger.error("ERRO AO ATUALIZAR HIT",e);
		}
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.controller.cache.JogoCache#getTop()
	 */
	@SuppressWarnings("unchecked")
	public Collection<Jogo> getTop() {
		Collection<Jogo> topGames = new ArrayList<Jogo>();
		// ordena jogos pelos hits
		JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
		ArrayList<Jogo> jogosOrdenadosPorHits =  new ArrayList<Jogo>(jogoCache.getCache());
		Collections.sort(jogosOrdenadosPorHits,new JogoHitComparator());

		// busca os N primeiros
//		int counter=0;
//		for (Iterator iterator = jogosOrdenadosPorHits.iterator(); iterator.hasNext();) {
//			JogoVO vo = (JogoVO) iterator.next();
//			topGames.add(vo);
//			if (counter == TOP) break;
//			 else counter++;
//		}
		for (int a=0;a<TOP;a++) {
		 int tamanho = jogosOrdenadosPorHits.size()-1;
		 Jogo vo = jogosOrdenadosPorHits.get(tamanho - a);
		 topGames.add(vo);
  	    }
		return topGames;
	}

	/* (non-Javadoc)
	 * @see br.com.linuxgames.controller.cache.JogoCache#getJogoDoDia()
	 */
	public Jogo getJogoDoDia() {
		return jogoDoDia;
	}

}
