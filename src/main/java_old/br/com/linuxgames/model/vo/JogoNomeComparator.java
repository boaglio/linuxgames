package br.com.linuxgames.model.vo;

import java.util.Comparator;

public class JogoNomeComparator implements Comparator<Jogo>{

	public int compare(Jogo jogo1, Jogo jogo2) {
		return jogo1.getNome().compareTo(jogo2.getNome());
	}

}
