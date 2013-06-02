package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class DistroVO implements Serializable  {

	private static final long serialVersionUID = 325672067452782647L;

	private int id;
	private String nome;
	private String abreviacao;
	private String siteOficial;
	private String linkLogo;
	private String descricao;
	private FabricanteVO fabricante = new FabricanteVO();

	public DistroVO(int id) {
		this.id = id;
	}

	public DistroVO() {
	}

	public String getAbreviacao() {
		return abreviacao;
	}
	public void setAbreviacao(String abreviacao) {
		this.abreviacao = abreviacao;
	}
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLinkLogo() {
		return linkLogo;
	}
	public void setLinkLogo(String linkLogo) {
		this.linkLogo = linkLogo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSiteOficial() {
		return siteOficial;
	}
	public void setSiteOficial(String siteOficial) {
		this.siteOficial = siteOficial;
	}
	public FabricanteVO getFabricante() {
		return fabricante;
	}
	public void setFabricanteVO(FabricanteVO fabricante) {
		this.fabricante = fabricante;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		final DistroVO other = (DistroVO) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+nome+" "+descricao;
	}
}
