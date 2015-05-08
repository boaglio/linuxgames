package br.com.linuxgames.model.vo;



public class VersaoDeBibliotecaVO extends VersaoVO {

	private static final long serialVersionUID = -1202414521013519438L;

    private BibliotecaVO biblioteca;

    // construtor padrao
    public VersaoDeBibliotecaVO () {
    	biblioteca=new BibliotecaVO();
    }

    // getters e setters
		public BibliotecaVO getBiblioteca() {
		return biblioteca;
	}

	public void setBiblioteca(BibliotecaVO biblioteca) {
		this.biblioteca = biblioteca;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((biblioteca == null) ? 0 : biblioteca.hashCode());
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
		final VersaoDeBibliotecaVO other = (VersaoDeBibliotecaVO) obj;
		if (biblioteca == null) {
			if (other.biblioteca != null)
				return false;
		} else if (!biblioteca.equals(other.biblioteca))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString()+" "+biblioteca;
	}
}