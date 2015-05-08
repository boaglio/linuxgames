package br.com.linuxgames.model.tipo;

/**
 * Seguindo a linha de locales suportados
 * http://java.sun.com/j2se/1.4.2/docs/guide/intl/locale.doc.html
 *
 * @author Fernando Boaglio
 *
 */
public enum Idioma {
	Portugues("português","Portuguese","pt_BR"),
	Arabe("árabe","Arabec","ar_SA"),
	Chines("chinês","Chinese (Simplified)","zh_CN"),
	Holandes("Holandês","Dutch","nl_NL"),
	Ingles("inglês","English","en_US"),
	Frances("francês","French","fr_FR"),
	Alemao("alemão","German","de_DE"),
	Hebreu("hebreu","Hebrew","iw_IL"),
	Hindi("hindi","Hindi","hi_IN"),
	Italiano("italiano","Italian","it_IT"),
	Japones("japonês","Japanese","ja_JP"),
	Coreano("coreano","Korean","ko_KR"),
	Espanhol("espanhol","Spanish","es_ES"),
	Sueco("sueco","Swedish","sv_SE"),
	Tailandes("tailandês","Thai","th_TH");

	private final String sigla;

	private final String nome;

	private final String nomeEmIngles;


	private Idioma(String nome, String nomeEmIngles, String sigla) {
		this.nome = nome;
		this.nomeEmIngles = nomeEmIngles;
		this.sigla = sigla;
	}


	public String getSigla() {
		return sigla;
	}

	public String getNome() {
		return nome;
	}

	public String getNomeEmIngles() {
		return nomeEmIngles;
	}

}
