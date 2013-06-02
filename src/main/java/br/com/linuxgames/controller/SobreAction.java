package br.com.linuxgames.controller;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.EnqueteDAO;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.model.vo.IndexVO;
import br.com.linuxgames.util.InfoHelper;


public class SobreAction extends BaseAction implements AuthenticationFree  {

	Logger logger = Logger.getLogger(this.getClass());


	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	private IndexVO indexVO = new IndexVO();

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	InfoHelper.setExtraInfo(this);
		logger.debug("iniciando SobreAction...");
		Collection<?> novidades = null;

		// busca os dados da versao
		indexVO.setVersaoVO(CacheManager.getCacheDeNovidades().getVersaoVO());

		// busca os dados das noticias
		novidades =  CacheManager.getCacheDeNovidades().getCache();

		// busca dados de uma enquete
		EnqueteDAO daoEnquete = null;
		try {
			daoEnquete = EnqueteDAO.getInstance();
			indexVO.setEnqueteVO( (EnqueteVO) daoEnquete.buscaEnqueteAtual());
		} catch (Exception e) {
			logger.error("erro na leitura de enquetes:", e);
			return ERROR;
		}

		//Artigos
		output.setValue("artigos",CacheManager.getCacheDeArtigos().getCache());
		output.setValue("novidades",novidades);
		output.setValue("indexVO",indexVO);

    	return SUCCESS;
    }

	public IndexVO getIndexVO() {
		return indexVO;
	}

	public void setIndexVO(IndexVO indexVO) {
		this.indexVO = indexVO;
	}

}