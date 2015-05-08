package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Date;


public class InfoDoDiaVO implements Serializable  {

	private static final long serialVersionUID = 325672066745278247L;
	private int id;
    private Date data;
    private String texto;
    private EmuladorVO emuladorVO=new EmuladorVO();
    private Jogo jogoVO=new Jogo();

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public EmuladorVO getEmuladorVO() {
		return emuladorVO;
	}
	public void setEmuladorVO(EmuladorVO emuladorVO) {
		this.emuladorVO = emuladorVO;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Jogo getJogoVO() {
		return jogoVO;
	}
	public void setJogoVO(Jogo jogoVO) {
		this.jogoVO = jogoVO;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
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
		final InfoDoDiaVO other = (InfoDoDiaVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return data+" "+texto;
	}
}
