package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class EmuladoresFavoritosVO implements Serializable  {

	private static final long serialVersionUID = 25120667452782647L;
	private Usuario usuario;
	private EmuladorVO emulador;
	private int oldUsuario;
	private int oldEmulador;

	public EmuladoresFavoritosVO() {
		usuario = new Usuario();
		emulador = new EmuladorVO();
	}

	 
	public EmuladorVO getEmulador() {
		return emulador;
	}
	public void setEmulador(EmuladorVO emulador) {
		this.emulador = emulador;
	}

	public int getOldUsuario() {
		return oldUsuario;
	}

	public void setOldUsuario(int oldUsuario) {
		this.oldUsuario = oldUsuario;
	}

	public int getOldEmulador() {
		return oldEmulador;
	}

	public void setOldEmulador(int oldEmulador) {
		this.oldEmulador = oldEmulador;
	}
 
	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emulador == null) ? 0 : emulador.hashCode());
		result = prime * result + oldUsuario;
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
		final EmuladoresFavoritosVO other = (EmuladoresFavoritosVO) obj;
		if (emulador == null) {
			if (other.emulador != null)
				return false;
		} else if (!emulador.equals(other.emulador))
			return false;
		if (oldUsuario != other.oldUsuario)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return usuario+" emulador="+emulador;
	}

}
