package br.com.linuxgames.model.vo;

import java.io.Serializable;



public class JogoVO implements Serializable {

	private static final long serialVersionUID = 325672066745247L;
	private int id;
    private String nome;
    private LicencaVO licenca;
	private int tipo;
	private int aberto;
	private int jogaEmRede;
	private int precisa3d;
	private int temSom;
	private int consoleOuX11;
	private String siteOficial;
	private String siteCompra;
	private String descricao;
	private int votos;
	private double notaGeral;
	private long hits;
	private FabricanteVO fabricante;
	private boolean destaque;

	public JogoVO() {
		licenca= new LicencaVO();
		fabricante = new FabricanteVO();
	}

	public int getAberto() {
		return aberto;
	}

	public void setAberto(int aberto) {
		this.aberto = aberto;
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

	public int getPrecisa3d() {
		return precisa3d;
	}

	public void setPrecisa3d(int precisa3d) {
		this.precisa3d = precisa3d;
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public int getVotos() {
		return votos;
	}

	public void setVotos(int votos) {
		this.votos = votos;
	}

	public long getHits() {
		return hits;
	}

	public void setHits(long hits) {
		this.hits = hits;
	}

	public boolean isDestaque() {
		return destaque;
	}

	public boolean getDestaque() {
		return destaque;
	}

	public void setDestaque(boolean destaque) {
		this.destaque = destaque;
	}

	public String getSiteCompra() {
		return siteCompra;
	}

	public void setSiteCompra(String siteCompra) {
		this.siteCompra = siteCompra;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		final JogoVO other = (JogoVO) obj;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return id+" "+nome;
	}
}
