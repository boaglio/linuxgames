package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class RoteiroInstalacaoVO implements Serializable,AprovadoVO  {

	private static final long serialVersionUID = 25606674527847L;
	private int id;
	private DistroVO distro;
	private Colaborador usuario;
	private String descricao;
	private boolean aprovado;

	public RoteiroInstalacaoVO() {
		distro = new DistroVO();
		usuario = new Colaborador();
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setDistro(DistroVO distro) {
		this.distro = distro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Colaborador getUsuario() {
		return usuario;
	}

	public void setUsuario(Colaborador usuario) {
		this.usuario = usuario;
	}

	public DistroVO getDistro() {
		return distro;
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public void setAprovado(int aprovado) {
		 if (aprovado==1)
		  this.aprovado = true;
		 else
		  this.aprovado = false;
	}

	public int getAprovado() {
		 if (this.aprovado)
		   return 1;
		 else
		   return 2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (aprovado ? 1231 : 1237);
		result = prime * result + ((distro == null) ? 0 : distro.hashCode());
		result = prime * result + id;
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
		final RoteiroInstalacaoVO other = (RoteiroInstalacaoVO) obj;
		if (aprovado != other.aprovado)
			return false;
		if (distro == null) {
			if (other.distro != null)
				return false;
		} else if (!distro.equals(other.distro))
			return false;
		if (id != other.id)
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
		return id+" "+distro+" "+usuario+" "+descricao;
	}
}