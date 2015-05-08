package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class BibliotecaXjogoVO implements Serializable  {

	private static final long serialVersionUID = 2560667452782647L;
	private BibliotecaVO biblioteca;
	private Jogo jogo;
	private int oldBiblioteca;
	private int oldJogo;

	public BibliotecaXjogoVO() {
		biblioteca = new BibliotecaVO();
		jogo = new Jogo();
	}

	public BibliotecaVO getBiblioteca() {
		return biblioteca;
	}
	public void setBiblioteca(BibliotecaVO biblioteca) {
		this.biblioteca = biblioteca;
	}
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public int getOldBiblioteca() {
		return oldBiblioteca;
	}

	public void setOldBiblioteca(int oldBiblioteca) {
		this.oldBiblioteca = oldBiblioteca;
	}

	public int getOldJogo() {
		return oldJogo;
	}

	public void setOldJogo(int oldJogo) {
		this.oldJogo = oldJogo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((biblioteca == null) ? 0 : biblioteca.hashCode());
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BibliotecaXjogoVO other = (BibliotecaXjogoVO) obj;
		if (biblioteca == null) {
			if (other.biblioteca != null)
				return false;
		} else if (!biblioteca.equals(other.biblioteca))
			return false;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return biblioteca+" jogo="+jogo;
	}

}
