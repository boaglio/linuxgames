package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class VotoXemuladorVO implements Serializable  {

	private static final long serialVersionUID = 3256775282647L;
    private String IP;
    private EmuladorVO emulador;
    private String oldIP;
    private int oldIdEmulador;

    public VotoXemuladorVO() {
    	 emulador = new EmuladorVO();
    }

    public String getIP() {
        return IP;
    }
    public void setIP(String IP) {
        this.IP = IP;
    }
	public EmuladorVO getEmulador() {
		return emulador;
	}
	public void setEmulador(EmuladorVO emulador) {
		this.emulador = emulador;
	}

	public int getOldIdEmulador() {
		return oldIdEmulador;
	}

	public void setOldIdEmulador(int oldIdEmulador) {
		this.oldIdEmulador = oldIdEmulador;
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
		result = prime * result
				+ ((emulador == null) ? 0 : emulador.hashCode());
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
		final VotoXemuladorVO other = (VotoXemuladorVO) obj;
		if (IP == null) {
			if (other.IP != null)
				return false;
		} else if (!IP.equals(other.IP))
			return false;
		if (emulador == null) {
			if (other.emulador != null)
				return false;
		} else if (!emulador.equals(other.emulador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return emulador+" ip="+IP;
	}
}
