package br.com.linuxgames.model.vo;

import java.util.Comparator;

public class JogoTipoComparator implements Comparator<Jogo>{

	public int compare(Jogo jogo1, Jogo jogo2) {
		Integer v1 = new Integer(jogo1.getTipo());
		Integer v2 = new Integer(jogo2.getTipo());
		return v1.compareTo(v2);
	}

}
