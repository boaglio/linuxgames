package br.com.linuxgames.model.dao.core;

import java.io.Serializable;

public class BancoDeDados implements Serializable {

	private static final long serialVersionUID = 1L;
	private String bancoOracle;
	private String IPdoServidor;
	private int portaDoListener;
	private String oracleSID;
	private String usuario;
	private String senha;


	public String getBancoOracle() {
		return bancoOracle;
	}
	public void setBancoOracle(String bancoOracle) {
		this.bancoOracle = bancoOracle;
	}
	public String getIPdoServidor() {
		return IPdoServidor;
	}
	public void setIPdoServidor(String pdoServidor) {
		IPdoServidor = pdoServidor;
	}
	public String getOracleSID() {
		return oracleSID;
	}
	public void setOracleSID(String oracleSID) {
		this.oracleSID = oracleSID;
	}
	public int getPortaDoListener() {
		return portaDoListener;
	}
	public void setPortaDoListener(int portaDoListener) {
		this.portaDoListener = portaDoListener;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
