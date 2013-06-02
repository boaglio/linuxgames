package br.com.linuxgames.model.dao.core;

import java.util.Collection;



public interface DAO {

	/*
	 * busca todos os registros
	 */
	public abstract Collection<Object> buscaTodos() throws DAOException;

	/*
	 * busca todos os registros com filtro
	 */
	public abstract Collection<Object> buscaTodos(Object object) throws DAOException;

	/*
	 * busca um determinado registro 
	 */
	public abstract Object buscaUm(Object object) throws DAOException;

	/*
	 * adiciona um registro 
	 */
	public abstract void adiciona(Object object) throws DAOException;

	/*
	 * atualiza um registro 
	 */
	public abstract void atualiza(Object object) throws DAOException;

	/*
	 * remove um registro 
	 */
	public abstract void remove(Object object) throws DAOException;

}