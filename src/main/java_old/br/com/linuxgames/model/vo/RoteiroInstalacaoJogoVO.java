package br.com.linuxgames.model.vo;



public class RoteiroInstalacaoJogoVO extends RoteiroInstalacaoVO  {

	private static final long serialVersionUID = 25606674527847L;
	private Jogo jogo;

	public RoteiroInstalacaoJogoVO() {
		jogo = new Jogo();
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((jogo == null) ? 0 : jogo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final RoteiroInstalacaoJogoVO other = (RoteiroInstalacaoJogoVO) obj;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+" jogo="+jogo;
	}
}