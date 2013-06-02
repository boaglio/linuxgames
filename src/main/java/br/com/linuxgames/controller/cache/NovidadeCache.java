package br.com.linuxgames.controller.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.EnqueteDAO;
import br.com.linuxgames.model.dao.InfoDoDiaDAO;
import br.com.linuxgames.model.dao.NovidadeDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.model.vo.InfoDoDiaVO;
import br.com.linuxgames.model.vo.LinuxGamesVO;
import br.com.linuxgames.model.vo.Novidade;

public class NovidadeCache extends AbstractCache implements RefreshableCache
{

	Logger logger = Logger.getLogger(this.getClass());
	private static NovidadeCache instance = new NovidadeCache();
	private LinuxGamesVO versaoVO=new LinuxGamesVO();
    private InfoDoDiaVO infoDoDiaVO=new InfoDoDiaVO();
    private EnqueteVO enqueteVO=new EnqueteVO();

	private Collection<String> usuariosMaisAtivos = new ArrayList<String>();

	private NovidadeCache()
	{
	 // criando cache de novidades
		try
		{
		 refreshNoCache();
		} catch (DAOException e)
		{
		  e.printStackTrace();
		}
	}

	/**
	 * singleton
	 */
	public static NovidadeCache getInstance() {
		return instance;
	}

	/**
	 * refresh no cache
	 */
	public void refreshNoCache() throws DAOException
	{
		NovidadeDAO daoNovidade = NovidadeDAO.getInstance();
		EnqueteDAO daoEnquete = EnqueteDAO.getInstance();
		InfoDoDiaDAO daoInfo=InfoDoDiaDAO.getInstance();

		LinkedHashMap<Integer, Novidade>  novoCache = new LinkedHashMap<Integer, Novidade>();
		Collection<Novidade> todasNovidades = null;
		try {
			todasNovidades = (Collection<Novidade>) daoNovidade.buscaUltimasNoticias();
			// busca a versao do sistema
		    this.versaoVO = daoNovidade.buscaUltimaVersao();
		    // busca os dados da info do dia
		    this.infoDoDiaVO = (InfoDoDiaVO) daoInfo.buscaMaisRecente();
		    // busca dados de uma enquete
		    this.enqueteVO = (EnqueteVO) daoEnquete.buscaEnqueteAtual();

		    refreshUsuariosMaisAtivos();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		int counter=1;
	    for (Iterator<Novidade> iter = todasNovidades.iterator(); iter.hasNext();)
		{
		 Novidade element = iter.next();
		 novoCache.put(new Integer(counter++), element);
		}
	    setCache(novoCache);
	    setUltimoRefreshDoCache(new Date());
	    super.refresh();
	}

	public void refreshUsuariosMaisAtivos() {
		try {
			NovidadeDAO daoNovidade = NovidadeDAO.getInstance();
			this.usuariosMaisAtivos = daoNovidade.buscaUsuariosMaisAtivos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LinuxGamesVO getVersaoVO() {
		return versaoVO;
	}

	public InfoDoDiaVO getInfoDoDiaVO() {
		return infoDoDiaVO;
	}

	public EnqueteVO getEnqueteVO() {
		return enqueteVO;
	}

    public Collection<String> getUsuariosMaisAtivos() {

		return usuariosMaisAtivos;
	}

}
