package br.com.linuxgames.model.vo;

import java.io.Serializable;


public class VotoXjogoVO implements Serializable  {

	private static final long serialVersionUID = 32567752782647L;
    private String IP;
    private int idUsuario;
    private int idJogo;
    private String nomeJogo;
    private int oldIdJogo;
    private int oldIdUsuario;
    
	public String getIP() {
		return IP;
	}
	public void setIP(String ip) {
		IP = ip;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdJogo() {
		return idJogo;
	}
	public void setIdJogo(int idJogo) {
		this.idJogo = idJogo;
	}
	public int getOldIdJogo() {
		return oldIdJogo;
	}
	public void setOldIdJogo(int oldIdJogo) {
		this.oldIdJogo = oldIdJogo;
	}
	public int getOldIdUsuario() {
		return oldIdUsuario;
	}
	public void setOldIdUsuario(int oldIdUsuario) {
		this.oldIdUsuario = oldIdUsuario;
	}
	public String getNomeJogo() {
		return nomeJogo;
	}
	public void setNomeJogo(String nomeJogo) {
		this.nomeJogo = nomeJogo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((IP == null) ? 0 : IP.hashCode());
		result = prime * result + idJogo;
		result = prime * result + idUsuario;
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
		final VotoXjogoVO other = (VotoXjogoVO) obj;
		if (IP == null) {
			if (other.IP != null)
				return false;
		} else if (!IP.equals(other.IP))
			return false;
		if (idJogo != other.idJogo)
			return false;
		if (idUsuario != other.idUsuario)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return nomeJogo+" "+IP+" "+idUsuario;
	}
	
}
