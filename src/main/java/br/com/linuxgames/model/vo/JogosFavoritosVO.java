package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class JogosFavoritosVO implements Serializable  {

	private static final long serialVersionUID = 25120667452782647L;
	private Usuario usuario;
	private Jogo jogo;
	private int oldUsuario;
	private int oldJogo;

	public JogosFavoritosVO() {
		usuario = new Usuario();
		jogo = new Jogo();
	}

	 
	public Jogo getJogo() {
		return jogo;
	}
	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public int getOldUsuario() {
		return oldUsuario;
	}

	public void setOldUsuario(int oldUsuario) {
		this.oldUsuario = oldUsuario;
	}

	public int getOldJogo() {
		return oldJogo;
	}

	public void setOldJogo(int oldJogo) {
		this.oldJogo = oldJogo;
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
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
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
		final JogosFavoritosVO other = (JogosFavoritosVO) obj;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		if (oldUsuario != other.oldUsuario)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return usuario+" jogo="+jogo;
	}

}
