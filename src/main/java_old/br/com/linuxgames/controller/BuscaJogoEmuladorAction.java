package br.com.linuxgames.controller;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.vo.Jogo;

public class BuscaJogoEmuladorAction extends BaseAction implements AuthenticationFree
{

	Logger logger = Logger.getLogger(this.getClass());


	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	@SuppressWarnings("unchecked")
	public String execute() throws Exception {

		String stringDeBusca= input.getString("q");
		if (stringDeBusca!=null)
			stringDeBusca=stringDeBusca.toLowerCase();
		logger.debug("buscando por "+stringDeBusca);

        Collection<Jogo> jogos = CacheManager.getCacheDeJogos().getCache();
        Map<Integer, String> novoMap = new LinkedHashMap<Integer, String>();

        for (Jogo element : jogos)
		{
			String nome = element.getNome().toLowerCase();
            // verifica se satisfaz o criterio de busca
    	    if (nome.indexOf(stringDeBusca) >= 0)
    	     novoMap.put(new Integer(element.getId()), element.getNome());
        }

        output.setValue("lgGames",novoMap);

        return SUCCESS;

	}

}
