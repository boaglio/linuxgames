package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Date;

import br.com.linuxgames.util.Constantes;

public class AjudaJogo implements Serializable {


	private static final long serialVersionUID = 6610373517760713565L;

	private JogoTemplate jogo;

	private int idJogoTemplate;

	private String jogoTemplateObs;

	private Usuario usuario;

	private String localeDoUsuario;

	private boolean aprovado;

	private Date dataPublic;

	private boolean novoJogo;


	public boolean isNovoJogo() {
		return novoJogo;
	}

	public void setNovoJogo(boolean novoJogo) {
		this.novoJogo = novoJogo;
	}

	public String getLocaleDoUsuario() {
		return localeDoUsuario;
	}

	public void setLocaleDoUsuario(String localeDoUsuario) {
		this.localeDoUsuario = localeDoUsuario;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(JogoTemplate jogo) {
		this.jogo = jogo;
	}

	public String getJogoTemplateObs() {
		return jogoTemplateObs;
	}

	public void setJogoTemplateObs(String jogoTemplateObs) {
		this.jogoTemplateObs = jogoTemplateObs;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public int getIdJogoTemplate() {
		return idJogoTemplate;
	}

	public void setIdJogoTemplate(int idJogoTemplate) {
		this.idJogoTemplate = idJogoTemplate;
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

	public Date getDataPublic() {
		return dataPublic;
	}

	public void setDataPublic(Date dataPublic) {
		this.dataPublic = dataPublic;
	}
}
