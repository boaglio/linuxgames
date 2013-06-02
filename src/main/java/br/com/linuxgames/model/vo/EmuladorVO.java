package br.com.linuxgames.model.vo;

import java.io.Serializable;



public class EmuladorVO implements Serializable {

	private static final long serialVersionUID = -2887054803933301978L;
	private int id;
    private String nome;
    private int tipo;
    private String tipoNome;
    private LicencaVO licenca;
	private int jogaEmRede;
	private int temSom;
	private int consoleOuX11;
	private String siteOficial;
	private String descricao;
	private int votos;
	private long hits;
	private double notaGeral;
	private FabricanteVO fabricante;
	private boolean destaque;
	private boolean temTela;

	public EmuladorVO() {
		licenca= new LicencaVO();
		fabricante = new FabricanteVO();
	}

	public boolean isTemTela() {
		return temTela;
	}

	public void setTemTela(boolean temTela) {
		this.temTela = temTela;
	}

	public int getConsoleOuX11() {
		return consoleOuX11;
	}

	public void setConsoleOuX11(int consoleOuX11) {
		this.consoleOuX11 = consoleOuX11;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public FabricanteVO getFabricante() {
		return fabricante;
	}

	public void setFabricante(FabricanteVO fabricante) {
		this.fabricante = fabricante;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJogaEmRede() {
		return jogaEmRede;
	}

	public void setJogaEmRede(int jogaEmRede) {
		this.jogaEmRede = jogaEmRede;
	}

	public LicencaVO getLicenca() {
		return licenca;
	}

	public void setLicenca(LicencaVO licenca) {
		this.licenca = licenca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getNotaGeral() {
		return notaGeral;
	}

	public void setNotaGeral(double notaGeral) {
		this.notaGeral = notaGeral;
	}

	public String getSiteOficial() {
		return siteOficial;
	}

	public void setSiteOficial(String siteOficial) {
		this.siteOficial = siteOficial;
	}

	public int getTemSom() {
		return temSom;
	}

	public void setTemSom(int temSom) {
		this.temSom = temSom;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getTipoNome() {
		return tipoNome;
	}

	public void setTipoNome(String tipoNome) {
		this.tipoNome = tipoNome;
	}

	public boolean isDestaque() {
		return destaque;
	}

	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
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
		final EmuladorVO other = (EmuladorVO) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+nome+" "+tipoNome;
	}
}
