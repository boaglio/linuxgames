package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class BibliotecaXemuladorVO implements Serializable  {

	private static final long serialVersionUID = 2560667452782647L;
	private BibliotecaVO biblioteca;
	private EmuladorVO emulador;
	private int oldBiblioteca;
	private int oldEmulador;

	public BibliotecaXemuladorVO() {
		biblioteca = new BibliotecaVO();
		emulador = new EmuladorVO();
	}

	public BibliotecaVO getBiblioteca() {
		return biblioteca;
	}
	public void setBiblioteca(BibliotecaVO biblioteca) {
		this.biblioteca = biblioteca;
	}
	public EmuladorVO getEmulador() {
		return emulador;
	}
	public void setEmulador(EmuladorVO jogo) {
		this.emulador = jogo;
	}

	public int getOldBiblioteca() {
		return oldBiblioteca;
	}

	public void setOldBiblioteca(int oldBiblioteca) {
		this.oldBiblioteca = oldBiblioteca;
	}

	public int getOldEmulador() {
		return oldEmulador;
	}

	public void setOldEmulador(int oldJogo) {
		this.oldEmulador = oldJogo;
	}

	@Override
	public String toString() {
		return biblioteca+" emulador="+emulador;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((biblioteca == null) ? 0 : biblioteca.hashCode());
		result = prime * result
				+ ((emulador == null) ? 0 : emulador.hashCode());
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
		final BibliotecaXemuladorVO other = (BibliotecaXemuladorVO) obj;
		if (biblioteca == null) {
			if (other.biblioteca != null)
				return false;
		} else if (!biblioteca.equals(other.biblioteca))
			return false;
		if (emulador == null) {
			if (other.emulador != null)
				return false;
		} else if (!emulador.equals(other.emulador))
			return false;
		return true;
	}
}
