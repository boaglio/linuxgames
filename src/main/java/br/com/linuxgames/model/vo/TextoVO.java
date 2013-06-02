package br.com.linuxgames.model.vo;

import java.util.Date;

import br.com.linuxgames.util.Constantes;

public class TextoVO implements AprovadoVO  {

	/*

    Tabela mapeada:

	+-------------+---------------------+------+-----+---------+----------------+
	| Field       | Type                | Null | Key | Default | Extra          |
	+-------------+---------------------+------+-----+---------+----------------+
	| id          | int(10)             | NO   | PRI | NULL    | auto_increment |
	| texto       | text                | NO   |     | NULL    |                |
	| data_public | date                | NO   |     | NULL    |                |
	| link        | varchar(200)        | YES  |     | NULL    |                |
	| tipo        | tinyint(1) unsigned | NO   |     | NULL    |                |
	| aprovado    | tinyint(1) unsigned | NO   |     | 0       |                |
	| tipo_id     | int(10)             | NO   |     | 0       |                |
	| usuario_id  | int(4)              | NO   |     | NULL    |                |
	+-------------+---------------------+------+-----+---------+----------------+

	 */

	private static final long serialVersionUID = -3703052648820941582L;
	private int id;
    private String texto;
	private String link;
	// 1=Dica; 2=Truque; 3=Filme; 4=Download
    private int tipo;
	private boolean aprovado;
    private Date dataPublic;
    private Colaborador usuario;

	public TextoVO() {
	  this.usuario= new Colaborador();
	}

	//  getters e setters
	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public void setAprovado(int aprovado) {
		 if (aprovado==Constantes.APROVADO)
		  this.aprovado = true;
		 else
		  this.aprovado = false;
	}

	public int getAprovado() {
		 if (this.aprovado)
		   return Constantes.APROVADO;
		return Constantes.REPROVADO;
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
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public Date getDataPublic() {
		return dataPublic;
	}
	public void setDataPublic(Date dataPublic) {
		this.dataPublic = dataPublic;
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
		result = prime * result + ((texto == null) ? 0 : texto.hashCode());
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
		final TextoVO other = (TextoVO) obj;
		if (id != other.id)
			return false;
		if (texto == null) {
			if (other.texto != null)
				return false;
		} else if (!texto.equals(other.texto))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+texto+" "+tipo;
	}
}