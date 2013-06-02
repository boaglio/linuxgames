package br.com.linuxgames.model.vo;



public class VersaoDeDistroVO extends VersaoVO {

	private static final long serialVersionUID = -1202414521013519438L;

    private DistroVO distro;

    // construtor padrao
    public VersaoDeDistroVO () {
     distro=new DistroVO();
    }

    // getters e setters
	public DistroVO getDistro() {
		return distro;
	}

	public void setDistro(DistroVO distro) {
		this.distro = distro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((distro == null) ? 0 : distro.hashCode());
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
		final VersaoDeDistroVO other = (VersaoDeDistroVO) obj;
		if (distro == null) {
			if (other.distro != null)
				return false;
		} else if (!distro.equals(other.distro))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return super.toString()+" "+distro;
	}
}