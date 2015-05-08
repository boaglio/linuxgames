package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class EnqueteVO implements Serializable  {

	private static final long serialVersionUID = 3257662782647L;
	private int id;
	private String titulo;
	private boolean ativo;
	private String opt1;
	private int qt1;
	private String opt2;
	private int qt2;
	private String opt3;
	private int qt3;
	private String opt4;
	private int qt4;
	private int qt;

	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOpt1() {
		return opt1;
	}
	public void setOpt1(String opt1) {
		this.opt1 = opt1;
	}
	public String getOpt2() {
		return opt2;
	}
	public void setOpt2(String opt2) {
		this.opt2 = opt2;
	}
	public String getOpt3() {
		return opt3;
	}
	public void setOpt3(String opt3) {
		this.opt3 = opt3;
	}
	public String getOpt4() {
		return opt4;
	}
	public void setOpt4(String opt4) {
		this.opt4 = opt4;
	}
	public int getQt() {
		return qt;
	}
	public void setQt(int qt) {
		this.qt = qt;
	}
	public int getQt1() {
		return qt1;
	}
	public void setQt1(int qt1) {
		this.qt1 = qt1;
	}
	public int getQt2() {
		return qt2;
	}
	public void setQt2(int qt2) {
		this.qt2 = qt2;
	}
	public int getQt3() {
		return qt3;
	}
	public void setQt3(int qt3) {
		this.qt3 = qt3;
	}
	public int getQt4() {
		return qt4;
	}
	public void setQt4(int qt4) {
		this.qt4 = qt4;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (ativo ? 1231 : 1237);
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
		final EnqueteVO other = (EnqueteVO) obj;
		if (ativo != other.ativo)
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return titulo+" ativo? "+ativo;
	}
	
}
