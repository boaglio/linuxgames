package br.com.linuxgames.model.vo;


public class VersaoDeJogoVO extends VersaoVO  {

	private static final long serialVersionUID = -1202414521013519438L;

    private Jogo jogo;

    // construtor padrao
    public VersaoDeJogoVO () {
     jogo=new Jogo();
    }

    // getters e setters
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
		final VersaoDeJogoVO other = (VersaoDeJogoVO) obj;
		if (jogo == null) {
			if (other.jogo != null)
				return false;
		} else if (!jogo.equals(other.jogo))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return jogo.toString();
	}
}