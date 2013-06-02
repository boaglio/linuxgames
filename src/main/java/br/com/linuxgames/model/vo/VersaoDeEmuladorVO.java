package br.com.linuxgames.model.vo;



public class VersaoDeEmuladorVO extends VersaoVO  {

	private static final long serialVersionUID = -1202414521013519438L;

    private EmuladorVO emulador;

    // construtor padrao
    public VersaoDeEmuladorVO () {
     emulador=new EmuladorVO();
    }

    // getters e setters
	public EmuladorVO getEmulador() {
		return emulador;
	}
	public void setEmulador(EmuladorVO jogo) {
		this.emulador = jogo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((emulador == null) ? 0 : emulador.hashCode());
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
		final VersaoDeEmuladorVO other = (VersaoDeEmuladorVO) obj;
		if (emulador == null) {
			if (other.emulador != null)
				return false;
		} else if (!emulador.equals(other.emulador))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString()+" "+emulador;
	}
}