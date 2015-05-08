package br.com.linuxgames.util;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.Action;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.controller.cache.NovidadeCache;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.IndexVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.UsuarioSession;

public class InfoHelper {

	static Logger logger = Logger.getLogger(InfoHelper.class);

	private static IndexVO indexVO = new IndexVO();

	/**
	 * Coloca na action as informacoes minimas do site
	 * @param action
	 */
	public static int setExtraInfo(Action action)
	    {
           int idDoUsuarioLogado = 0;

	        UsuarioSession  usuarioSession = UserHelper.getUserFromSession(action);
	        if (usuarioSession!=null && usuarioSession.getId()>0)
	         {
	          idDoUsuarioLogado = usuarioSession.getId();
	          action.getOutput().setValue("logado","S");
	          action.getOutput().setValue("nomeLogado",usuarioSession.getEmail());
	          action.getOutput().setValue("idUsuario",idDoUsuarioLogado);
			  logger.info("usuario ["+usuarioSession.getEmail()+"] logado!");
	         }
	        else
	         {
	          action.getOutput().setValue("logado","N");
	          logger.info("usuario anonimo!");
	         }

	        return idDoUsuarioLogado;
	    }


	/**
	 * Coloca na action as informacoes minimas do site
	 * @param action
	 */
	public static int setExtraInfoForHome(Action action)
	    {
           int idDoUsuarioLogado = 0;

		    NovidadeCache novidadeCache = CacheManager.getCacheDeNovidades();

			// busca os dados da versao
			indexVO.setVersaoVO(novidadeCache.getVersaoVO());

			// busca os dados das noticias
			Collection<?> novidades =  CacheManager.getCacheDeNovidades().getCache();

			// busca dados de uma enquete
			indexVO.setEnqueteVO(novidadeCache.getEnqueteVO());

			// busca jogo do dia
			JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
//			indexVO.setJogoDoDia(jogoCache.getJogoDoDia());
			int jogoDoDia = jogoCache.getJogoIdAleatorioComTela();
			indexVO.setJogoDoDia((Jogo) CacheManager.getCacheDeJogos().getCacheComoHashMap().get(jogoDoDia));

			// busca emu do dia
			EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
//			indexVO.setEmuladorDoDia(emuladorCache.getEmuladorDoDia());
			int emuDoDia = emuladorCache.getEmuladorIdAleatorioComTela();
			indexVO.setEmuladorDoDia((EmuladorVO) CacheManager.getCacheDeEmuladores().getCacheComoHashMap().get(emuDoDia));

			// quantidade de jogos
			indexVO.setTotalDeJogos(jogoCache.getTamanhoCache());

			// quantidade de emuladores
			indexVO.setTotalDeEmuladores(emuladorCache.getTamanhoCache());

			action.getOutput().setValue("novidades",novidades);
			action.getOutput().setValue("indexVO",indexVO);

			//Artigos
			action.getOutput().setValue("artigos",CacheManager.getCacheDeArtigos().getCache());

	        UsuarioSession  usuarioSession = UserHelper.getUserFromSession(action);

	        if (usuarioSession!=null && usuarioSession.getId()>0)
	         {
	          idDoUsuarioLogado = usuarioSession.getId();
	          action.getOutput().setValue("logado","S");
	          action.getOutput().setValue("nomeLogado",usuarioSession.getEmail());
	          action.getOutput().setValue("idUsuario",idDoUsuarioLogado);
			  logger.info("usuario ["+usuarioSession.getEmail()+"] logado!");
	         }
	        else
	         {
	          action.getOutput().setValue("logado","N");
	          logger.info("usuario anonimo!");
	         }
	        return idDoUsuarioLogado;
	    }


	public static String getUsuarioLogado(Action action) {

		UsuarioSession  usuarioSession = UserHelper.getUserFromSession(action);

        return usuarioSession.getEmail();
	}

	public static Jogo buscarJogoDoCache(int idDoJogo,String locale) {

		JogoCache jogoCache = null;

    	// EN
    	if (locale!=null && locale.equals(LocaleHelper.EN)) {
    		jogoCache = CacheManager.getCacheDeJogosEN();
    		int idJogoEN = 0;
    		try {
       		 idJogoEN = CacheManager.getCacheIdJogosPtEn().get(idDoJogo);
    		} catch (Exception e) {
    		 idJogoEN =  idDoJogo;
			}

    		return (Jogo) jogoCache.getCacheComoHashMap().get(idJogoEN);
    	}

    	// pt -> pt_BR
//    	if (locale == null || locale.equals(LocaleHelper.PT_BR2) || locale.equals(LocaleHelper.PT_BR1))
    	return (Jogo) CacheManager.getCacheDeJogos().getCacheComoHashMap().get(idDoJogo);

	}

}
