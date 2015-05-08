package br.com.linuxgames.model.vo;



public class Colaborador extends Usuario {

	private static final long serialVersionUID = -120241452101359438L;

	private boolean admin;
	private boolean ativo;

	public Colaborador(int id) {
		setId(id);
	}

	public Colaborador() {
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public void setAdmin(int admin) {
		 if (admin==1)
		  this.admin = true;
		 else
		  this.admin = false;
	}

	public int getAdmin() {
		 if (this.admin)
		   return 1;
		 else
		   return 2;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setAtivo(int ativo) {
		 if (ativo==1)
		  this.ativo = true;
		 else
		  this.ativo = false;
	}

	public int getAtivo() {
		 if (this.ativo)
		   return 1;
		 else
		   return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + (ativo ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Colaborador other = (Colaborador) obj;
		if (admin != other.admin)
			return false;
		if (ativo != other.ativo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+" admin? "+admin+" ativo? "+ativo;
	}
}
