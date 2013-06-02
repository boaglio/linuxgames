package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class VotoXenqueteVO implements Serializable  {

	private static final long serialVersionUID = 325677452782647L;
    private String IP;
    private EnqueteVO enquete;
    private String oldIP;
    private int oldIdEnquete;

    public VotoXenqueteVO() {
    	 enquete = new EnqueteVO();
    }

    public String getIP() {
        return IP;
    }
    public void setIP(String IP) {
        this.IP = IP;
    }
	public EnqueteVO getEnquete() {
		return enquete;
	}
	public void setEnquete(EnqueteVO enquete) {
		this.enquete = enquete;
	}

	public int getOldIdEnquete() {
		return oldIdEnquete;
	}

	public void setOldIdEnquete(int oldIdEnquete) {
		this.oldIdEnquete = oldIdEnquete;
	}

	public String getOldIP() {
		return oldIP;
	}

	public void setOldIP(String oldIP) {
		this.oldIP = oldIP;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IP == null) ? 0 : IP.hashCode());
		result = prime * result + ((enquete == null) ? 0 : enquete.hashCode());
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
		final VotoXenqueteVO other = (VotoXenqueteVO) obj;
		if (IP == null) {
			if (other.IP != null)
				return false;
		} else if (!IP.equals(other.IP))
			return false;
		if (enquete == null) {
			if (other.enquete != null)
				return false;
		} else if (!enquete.equals(other.enquete))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return enquete+" ip="+IP;
	}
}
