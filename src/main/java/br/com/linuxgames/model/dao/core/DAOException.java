package br.com.linuxgames.model.dao.core;

public class DAOException extends Exception {

	private static final long serialVersionUID = 3257570615794610738L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String message, Throwable t) {
		super(message, t);
	}

	public DAOException(Throwable t) {
		super(t);
	}
}