package br.com.linuxgames.model.vo;



public class RoteiroInstalacaoEmuVO extends RoteiroInstalacaoVO {

	private static final long serialVersionUID = 25606677897847L;
	private EmuladorVO emulador;

	public RoteiroInstalacaoEmuVO() {
		emulador = new EmuladorVO();
	}

	public EmuladorVO getEmulador() {
		return emulador;
	}

	public void setEmulador(EmuladorVO emulador) {
		this.emulador = emulador;
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
		final RoteiroInstalacaoEmuVO other = (RoteiroInstalacaoEmuVO) obj;
		if (emulador == null) {
			if (other.emulador != null)
				return false;
		} else if (!emulador.equals(other.emulador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString()+" emulador="+emulador;
	}
}