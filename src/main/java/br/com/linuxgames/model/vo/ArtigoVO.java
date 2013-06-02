package br.com.linuxgames.model.vo;

import java.util.Date;



public class ArtigoVO implements AprovadoVO  {

	private static final long serialVersionUID = -3430723090562221410L;
	private int id;
	private Colaborador usuario;
    private Date dataPublic;
	private String titulo;
	private String texto;
	private int votos;
	private double notaGeral;
	private boolean aprovado;

	public ArtigoVO() {
		this.usuario = new Colaborador();
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
	public Date getDataPublic() {
        return dataPublic;
    }
    public void setDataPublic(Date data_public) {
        this.dataPublic = data_public;
    }
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Colaborador getUsuario() {
		return usuario;
	}

	public void setUsuario(Colaborador usuario) {
		this.usuario = usuario;
	}
	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}
	public double getNotaGeral() {
		return notaGeral;
	}

	public void setNotaGeral(double notaGeral) {
		this.notaGeral = notaGeral;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		final ArtigoVO other = (ArtigoVO) obj;
		if (id != other.id)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+titulo+" "+texto;
	}

}
