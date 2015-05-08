package br.com.linuxgames.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.ArtigoCache;
import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.ReviewDeArtigoDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.ArtigoVO;
import br.com.linuxgames.model.vo.InfoVO;
import br.com.linuxgames.util.DateHelper;
import br.com.linuxgames.util.InfoHelper;
import br.com.linuxgames.util.UserHelper;


public class InfoArtigoAction extends BaseAction implements AuthenticationFree {

	Logger logger = Logger.getLogger(this.getClass());
	private InfoVO vo;

	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	public String execute() throws Exception
	{
	 InfoHelper.setExtraInfo(this);
	 return SUCCESS;
	}

    /**
     * action que busca um artigo
     * @return
     * @throws ActionException
     */
    public String artigo() throws ActionException {
    	int idUsuario = InfoHelper.setExtraInfo(this);
		boolean esseUsuarioJaVotou = false ;
    	int idArtigo = input.getInt("id");
    	boolean estaLogado =  UserHelper.usuarioEstaLogado(this);

    	// busca artigo
    	ArtigoCache artigoCache = CacheManager.getCacheDeArtigos();
    	ArtigoVO storedVO = (ArtigoVO) artigoCache.getCacheComoHashMap().get(new Integer(idArtigo));
    	output.setValue("id", idArtigo);
    	output.setValue("texto", storedVO.getTexto());
		output.setValue("titulo", storedVO.getTitulo());
		output.setValue("dataPublic", DateHelper.mysqlFormat2EuropeanFormat(storedVO.getDataPublic().toString()));
		output.setValue("notaGeral",storedVO.getNotaGeral());

		// buscar reviews
		ReviewDeArtigoDAO reviewDeArtigoDAO =ReviewDeArtigoDAO.getinstance();
		Collection<?> reviews;
		try {
			reviews = reviewDeArtigoDAO.buscaPorArtigo(idArtigo);
			output.setValue("reviews", reviews);
			if (estaLogado)
			{
			 ReviewDeArtigoDAO daoReviewDeArtigo = ReviewDeArtigoDAO.getinstance();
			 esseUsuarioJaVotou = daoReviewDeArtigo.usuarioFezReviewDeArtigo(idUsuario,idArtigo);

			 if (esseUsuarioJaVotou)
			  output.setValue("jaVotou", "S");
			 else
			  output.setValue("jaVotou", "N");
			}
			else
			{
			 output.setValue("jaVotou", "N");
			}

		} catch (DAOException e) {
			logger.debug("Erro na busca de reviews...",e);
		}
        return SUCCESS;
    }

    // getters e setters
	public InfoVO getVo() {
		return vo;
	}

	public void setVo(InfoVO vo) {
		this.vo = vo;
	}

//	 metodos privados

}