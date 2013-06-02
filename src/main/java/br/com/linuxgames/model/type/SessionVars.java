package br.com.linuxgames.model.type;

public enum SessionVars {

	usuarioLG("usuarioLG");

	private String description;

	SessionVars(String desc) {
		this.description = desc;
	}

	public String description() {
		return description;
	}

}
