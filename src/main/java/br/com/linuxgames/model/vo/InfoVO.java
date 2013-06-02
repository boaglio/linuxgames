package br.com.linuxgames.model.vo;

import java.io.Serializable;
import java.util.Collection;


public class InfoVO implements Serializable  {

	private static final long serialVersionUID = 32567206745278247L;
	private ArtigoVO artigo;
	private Collection<ArtigoVO> artigos;
	private Jogo jogo;
	private Collection<Jogo> jogos;
	private EmuladorVO emulador;
	private Collection<EmuladorVO> emuladores;
	
	public ArtigoVO getArtigo()
	{
		if (this.artigo==null) return new ArtigoVO();
		return artigo;
	}
	public void setArtigo(ArtigoVO artigo)
	{
		this.artigo = artigo;
	}
	public Collection<ArtigoVO> getArtigos()
	{
		return artigos;
	}
	public void setArtigos(Collection<ArtigoVO> artigos)
	{
		this.artigos = artigos;
	}
	public EmuladorVO getEmulador()
	{
		if (this.emulador==null) return new EmuladorVO();
		return emulador;
	}
	public void setEmulador(EmuladorVO emulador)
	{
		this.emulador = emulador;
	}
	public Collection<EmuladorVO> getEmuladores()
	{
		return emuladores;
	}
	public void setEmuladores(Collection<EmuladorVO> emuladores)
	{
		this.emuladores = emuladores;
	}
	public Jogo getJogo()
	{
		if (this.jogo==null) return new Jogo();
		return jogo;
	}
	public void setJogo(Jogo jogo)
	{
		this.jogo = jogo;
	}
	public Collection<Jogo> getJogos()
	{
		return jogos;
	}
	public void setJogos(Collection<Jogo> jogos)
	{
		this.jogos = jogos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artigo == null) ? 0 : artigo.hashCode());
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
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
		final InfoVO other = (InfoVO) obj;
		if (artigo == null) {
			if (other.artigo != null)
				return false;
		} else if (!artigo.equals(other.artigo))
			return false;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return artigo+" "+jogo;
	}
	
}
