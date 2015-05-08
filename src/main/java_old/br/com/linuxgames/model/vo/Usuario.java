package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Date;


public class Usuario implements Serializable  {

	private static final long serialVersionUID = -1029007978764589334L;

	private int id;
	private int idOld;
	private String nome;
	private String email;
	private String senha;
	private Date dataDoCadastro;
	private DistroVO distro;
	private String receberNewsletter;
	private String minhaMaquina;
	private int nrJogosContribuidos;
	private int nrNoticiasContribuidas;
	private int nrEmuladoresContribuidos;
	private int totalContribuicoes;

	public Usuario() {}

	public Usuario(int idUsuario) {
		this.id = idUsuario;
	}

	public Usuario(int idUsuario, String nome) {
		this.id = idUsuario;
		this.email = nome;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String email) {
		this.senha = email;
	}

	public int getNrJogosContribuidos() {
		return nrJogosContribuidos;
	}

	public void setNrJogosContribuidos(int nrJogosContribuidos) {
		this.nrJogosContribuidos = nrJogosContribuidos;
	}

	public int getNrNoticiasContribuidas() {
		return nrNoticiasContribuidas;
	}

	public void setNrNoticiasContribuidas(int nrNoticiasContribuidas) {
		this.nrNoticiasContribuidas = nrNoticiasContribuidas;
	}

	public int getNrEmuladoresContribuidos() {
		return nrEmuladoresContribuidos;
	}

	public void setNrEmuladoresContribuidos(int nrEmuladoresContribuidos) {
		this.nrEmuladoresContribuidos = nrEmuladoresContribuidos;
	}

	public int getIdOld() {
		return idOld;
	}

	public void setIdOld(int idOld) {
		this.idOld = idOld;
	}

	public Date getDataDoCadastro() {
		return dataDoCadastro;
	}

	public void setDataDoCadastro(Date dataDoCadastro) {
		this.dataDoCadastro = dataDoCadastro;
	}

	public int getTotalContribuicoes() {
		return totalContribuicoes;
	}

	public void setTotalContribuicoes(int totalContribuicoes) {
		this.totalContribuicoes = totalContribuicoes;
	}

	public DistroVO getDistro() {
		return distro;
	}

	public void setDistro(DistroVO distro) {
		this.distro = distro;
	}

	public String getReceberNewsletter() {
		return receberNewsletter;
	}

	public void setReceberNewsletter(String receberNewsletter) {
		this.receberNewsletter = receberNewsletter;
	}

	public String getMinhaMaquina() {
		return minhaMaquina;
	}

	public void setMinhaMaquina(String minhaMaquina) {
		this.minhaMaquina = minhaMaquina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		final Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return id+" "+email+" "+senha;
	}
}
