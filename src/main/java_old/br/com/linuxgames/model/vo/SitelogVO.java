package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Date;


public class SitelogVO implements Serializable  {

	/*
	  Tabela mapeada:

	mysql> describe SITELOG;

		+-----------+--------------+------+-----+------------+----------------+
		| Field     | Type         | Null | Key | Default    | Extra          |
		+-----------+--------------+------+-----+------------+----------------+
		| id        | int(11)      | NO   | PRI | NULL       | auto_increment |
		| versao    | varchar(10)  | NO   |     | NULL       |                |
		| descricao | varchar(255) | NO   |     | NULL       |                |
		| data      | date         | NO   |     | 0000-00-00 |                |
		+-----------+--------------+------+-----+------------+----------------+

	 */

	private static final long serialVersionUID = 32567667452782647L;
	private int id;
    private Date data;
    private String versao;
    private String descricao;

	public Date getData() {
        return data;
    }
    public void setData(Date data_public) {
        this.data = data_public;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getVersao() {
        return versao;
    }
    public void setVersao(String link) {
        this.versao = link;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String texto) {
        this.descricao = texto;
    }
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + id;
		result = prime * result + ((versao == null) ? 0 : versao.hashCode());
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
		final SitelogVO other = (SitelogVO) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (id != other.id)
			return false;
		if (versao == null) {
			if (other.versao != null)
				return false;
		} else if (!versao.equals(other.versao))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+data+" "+versao+ " "+descricao;
	}
}