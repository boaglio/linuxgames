package br.com.linuxgames.controller.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.JogoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.JogoHitComparator;
import br.com.linuxgames.util.LocaleHelper;

public class JogoCacheEN extends JogoCache
{

	Logger logger = Logger.getLogger(this.getClass());
	private static JogoCacheEN instance = new JogoCacheEN();
	private static final int TOP = 10;
	private Jogo jogoDoDia;


	private JogoCacheEN()
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

	/**
	 * singleton
	 */
	public static JogoCache getInstance() {
		return instance;
	}

	/**
	 * refresh no cache
	 */
	@Override
	public void refreshNoCache() throws DAOException
	{
		JogoDAO jogoDAO = JogoDAO.getInstance();

		LinkedHashMap<Integer, Jogo> jogosCache = new LinkedHashMap<Integer,Jogo>();

		// jogos em EN
		Collection<Jogo> todosJogosEN = jogoDAO.buscaPorIdioma(LocaleHelper.EN);

		for (Jogo element :  todosJogosEN) {

		    jogosCache.put(new Integer(element.getJogoId()), element);

		    if (element.isDestaque())
			  this.jogoDoDia=element;
		}

	    setCache(jogosCache);
	    setUltimoRefreshDoCache(new Date());
	    super.refresh();
	}

	/**
	 * traz os N jogos mais acessados
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Collection<Jogo> getTop() {
		Collection<Jogo> topGames = new ArrayList<Jogo>();
		// ordena jogos pelos hits
		JogoCacheEN jogoCache = (JogoCacheEN) CacheManager.getCacheDeJogos();
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

	public Jogo getJogoDoDia() {
		return jogoDoDia;
	}

}
