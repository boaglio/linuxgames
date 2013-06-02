package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class ReviewVO implements Serializable  {

	private static final long serialVersionUID = -1202414211351438L;

	private int id;
	private Colaborador usuario;
    private String comentario;
	private int nota;

	public ReviewVO() {
		usuario = new Colaborador();
	}

	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public Colaborador getUsuario() {
		return usuario;
	}
	public void setUsuario(Colaborador usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + nota;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		final ReviewVO other = (ReviewVO) obj;
		if (id != other.id)
			return false;
		if (nota != other.nota)
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+usuario+" "+comentario+" "+nota;
	}
	
}