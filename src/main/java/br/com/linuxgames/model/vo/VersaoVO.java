package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class VersaoVO  implements Serializable{

	private static final long serialVersionUID = -5571052075738406510L;
	private int id;
    private String release;
    private Date dataLancamento;
	private String link;
	private String obs;

    // getters e setters
	public Date getDataLancamento() {
		return dataLancamento;
	}
	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
		final VersaoVO other = (VersaoVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+release;
	}
}
