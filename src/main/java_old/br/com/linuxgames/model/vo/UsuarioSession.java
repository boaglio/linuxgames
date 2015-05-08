package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class UsuarioSession implements Serializable  {


	private static final long serialVersionUID = -8729364347614493177L;

	private int id;
	private String email;

	public UsuarioSession() {}

	public UsuarioSession(int idUsuario) {
		this.id = idUsuario;
	}

	public UsuarioSession(int idUsuario, String email) {
		this.id = idUsuario;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String nome) {
		this.email = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		UsuarioSession other = (UsuarioSession) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UsuarioSession [id=" + id + ", email=" + email + "]";
	}

}
