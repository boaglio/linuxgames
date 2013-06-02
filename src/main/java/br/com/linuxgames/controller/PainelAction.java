package br.com.linuxgames.controller;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.UsuariosDAO;
import br.com.linuxgames.model.dao.UsuariosLGDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.IndexVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.util.InfoHelper;


public class PainelAction extends BaseAction implements AuthenticationFree  {

	Logger logger = Logger.getLogger(this.getClass());

	private IndexVO indexVO = new IndexVO();

	public static final String NAO_LOGADO = "naoLogado";

	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	int idDoUsuarioLogado = InfoHelper.setExtraInfo(this);
		logger.debug("iniciando PainelAction...");

		// busca os dados da versao
		indexVO.setVersaoVO(CacheManager.getCacheDeNovidades().getVersaoVO());

		//Artigos
		output.setValue("artigos",CacheManager.getCacheDeArtigos().getCache());
		output.setValue("indexVO",indexVO);


		UsuariosLGDAO usuariosLGDAO = UsuariosLGDAO.getInstance();
		UsuariosDAO usuariosDAO = UsuariosDAO.getInstance();
		if (idDoUsuarioLogado>0) {
		 try {
			Usuario usuario = (Usuario) usuariosLGDAO.buscaUmCompleto(idDoUsuarioLogado);
			output.setValue("usuario",usuario);
			int posicao = usuariosDAO.buscaRank(idDoUsuarioLogado);
			output.setValue("usuarioPosicao",posicao);

		 } catch (DAOException e) {
			logger.error("erro ao buscar usuario",e);
		 }
		}
		else {
			return NAO_LOGADO;
		}

    	return SUCCESS;
    }

	public IndexVO getIndexVO() {
		return indexVO;
	}

	public void setIndexVO(IndexVO indexVO) {
		this.indexVO = indexVO;
	}

}