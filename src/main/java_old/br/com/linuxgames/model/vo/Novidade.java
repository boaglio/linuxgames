package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Date;


public class Novidade implements Serializable  {

	public Novidade(int id) {
		this.id = id;
	}
	public Novidade() {}

	private static final long serialVersionUID = 3256720667452782647L;
	private int id;
    private Date dataPublic;
    private String link;
    private int tipo;
    private String texto;
    private Usuario usuario=new Usuario();

	private boolean aprovado;

    public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuarioVO) {
		this.usuario = usuarioVO;
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
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
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
		result = prime * result
				+ ((dataPublic == null) ? 0 : dataPublic.hashCode());
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
		final Novidade other = (Novidade) obj;
		if (dataPublic == null) {
			if (other.dataPublic != null)
				return false;
		} else if (!dataPublic.equals(other.dataPublic))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+dataPublic+" "+link+" "+tipo+" "+texto;
	}
}
