package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Date;


public class UpdateLogVO implements Serializable  {

	/*
	  Tabela mapeada:
	  
	mysql> describe UPDATES_LOG;
	
		+-----------+--------------+------+-----+------------+----------------+
		| Field     | Type         | Null | Key | Default    | Extra          |
		+-----------+--------------+------+-----+------------+----------------+
		| id        | int(11)      | NO   | PRI | NULL       | auto_increment |
		| descricao | varchar(255) | NO   |     |            |                |
		| data      | date         | NO   |     | 0000-00-00 |                |
		+-----------+--------------+------+-----+------------+----------------+

	 */

	private static final long serialVersionUID = 3256766741278247L;
	private int id;
    private Date data;
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
		final UpdateLogVO other = (UpdateLogVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+descricao+" "+data;
	}
}
