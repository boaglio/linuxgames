package br.com.linuxgames.model.vo;

public class JogoTemplate extends Jogo {


	private static final long serialVersionUID = 450990826805242920L;

	private boolean aprovado;

	private String observacao;


	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public JogoTemplate() {}

	public JogoTemplate(int id) {
	  setId(id);
	}

	public JogoTemplate(Jogo j) {
		setId(j.getId());
		setNome(j.getNome());
		setLicenca(j.getLicenca());
		setTipo(j.getTipo());
		setAberto(j.getAberto());
		setJogaEmRede(j.getJogaEmRede());
		setPrecisa3d(j.getPrecisa3d());
		setTemSom(j.getTemSom());
		setConsoleOuX11(j.getConsoleOuX11());
		setDescricao(j.getDescricao());
		setFabricante(j.getFabricante());
		setUsuario(j.getUsuario());
		setIdioma(j.getIdioma());
		setJogoId(j.getJogoId());
	}

	public boolean isAprovado() {
		return aprovado;
	}

	public void setAprovado(boolean aprovado) {
		this.aprovado = aprovado;
	}

	public void setAprovado(int aprovado) {
		 if (aprovado==1)
		  this.aprovado = true;
		 else
		  this.aprovado = false;
	}

	public int getAprovado() {
		 if (this.aprovado)
		   return 1;
		 else
		   return 2;
	}
}
