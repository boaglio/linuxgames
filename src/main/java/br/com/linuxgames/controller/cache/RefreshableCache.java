package br.com.linuxgames.controller.cache;

import br.com.linuxgames.model.dao.core.DAOException;

public interface RefreshableCache
{
  public void refreshNoCache() throws DAOException;
}
