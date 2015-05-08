package br.com.linuxgames.controller.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.JogoHitComparator;


public class JogoCachePTBR extends JogoCache
{

	private static JogoCache instance = new JogoCachePTBR();
	private static final int TOP = 10;


	/**
	 * singleton
	 */
	public static JogoCache getInstance() {
		return instance;
	}

	public JogoCachePTBR() {
		super();
	}

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
		 Jogo vo = (Jogo) jogosOrdenadosPorHits.get(tamanho - a);
		 topGames.add(vo);
  	    }
		return topGames;
	}
}
