package br.com.linuxgames.model.vo;

import java.util.Comparator;

public class EmuladorNomeComparator implements Comparator<EmuladorVO>{

	public int compare(EmuladorVO emu1, EmuladorVO emu2) {
		return emu1.getNome().compareTo(emu2.getNome());
	}

}
