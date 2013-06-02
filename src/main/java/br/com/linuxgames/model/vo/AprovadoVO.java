package br.com.linuxgames.model.vo;

import java.io.Serializable;

public interface AprovadoVO extends Serializable {

	public abstract boolean isAprovado();

	public abstract void setAprovado(boolean aprovado);

	public abstract void setAprovado(int aprovado);

	public abstract int getAprovado();

}