package br.com.linuxgames.util;

import org.mentawai.core.Output;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.Jogo;

public class TopHelper {

    /**
     * auxilia nas informacoes dos jogos
     */
    public static void updateJogoCache(int idJogo,Output output) {


    	if (idJogo<1) return;

    	// jogos mais acessados
		JogoCache jogoCache = (JogoCache) CacheManager.getCacheDeJogos();
		Jogo storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(idJogo));

		if (storedVO==null ) // se nulo, jogo é en EN
		{
			int idJogoPt = CacheManager.getCacheIdJogosEnPt().get(idJogo);
			storedVO = (Jogo) jogoCache.getCacheComoHashMap().get(new Integer(idJogoPt));
		}

		output.setValue("jogosTop", jogoCache.getTop());
		// informacao do jogo visualizado
		output.setValue("id", String.valueOf(idJogo));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		// soma um hit de acesso ao jogo
		jogoCache.updateHitJogo(storedVO);

		// coloca o top dos emuladores no output
    	// emuladores mais acessados
		output.setValue("emuladoresTop",(CacheManager.getCacheDeEmuladores()).getTop());
    }

    /**
     * auxilia nas informacoes dos emuladores
     */
    public static void updateEmuladorHelper(int idEmulador,Output output) {
    	// emuladores mais acessados
		EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
		EmuladorVO storedVO = (EmuladorVO) emuladorCache.getCacheComoHashMap().get(new Integer(idEmulador));
		output.setValue("emuladoresTop", emuladorCache.getTop());
		// informacao do emulador visualizado
		output.setValue("id", String.valueOf(idEmulador));
		output.setValue("nome", storedVO.getNome());
		output.setValue("notaGeral", storedVO.getNotaGeral());
		// soma um hit de acesso ao emulador
		emuladorCache.updateHitEmulador(storedVO);

		// coloca o top dos jogos no output
    	// jogos mais acessados
		output.setValue("jogosTop",(CacheManager.getCacheDeJogos()).getTop());
    }
}
