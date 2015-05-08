package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class FabricanteVO implements Serializable  {


	private static final long serialVersionUID = 256720667452782647L;
	private int id;
    private String nome;
    private String descricao;

    public FabricanteVO() {}

	public FabricanteVO(int id) {
		super();
		this.id = id;
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
		final FabricanteVO other = (FabricanteVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+nome+" "+descricao;
	}
}
