package br.com.linuxgames.util;

import java.util.ArrayList;
import java.util.Locale;

import org.mentawai.i18n.LocaleManager;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.JogoCache;

public class LocaleHelper {

	static ArrayList<String> validLocales = new ArrayList<String>();

	public static final String PT_BR1 = "pt";
	public static final String PT_BR2 = "pt_BR";

	public static final String EN = "en";

	public static final Locale defaultLocale =  new Locale("pt","BR");

	public static final Locale ptLocale =  new Locale("pt","BR");

	public static final Locale enLocale =  new Locale("en","US");

	static {
		validLocales.add(PT_BR1);
		validLocales.add(PT_BR2);
		validLocales.add(EN);
	}

    /**
     * retorna locale do usuario
     */
    public static Locale getUserLocale(String localeStr) {

    	Locale defaultLocale = LocaleManager.getDefaultLocale();

    	if (localeStr == null || localeStr.length()<2 ) {
    		return defaultLocale;
    	}

    	String localeInitial = localeStr.substring(0,2);

    	if (!validLocales.contains(localeInitial)) {
    		return defaultLocale;
    	}

    	// pt -> pt_BR
    	if (localeInitial.equals(PT_BR1))
    		return defaultLocale;

    	// pt -> pt_BR
    	if (localeInitial.equals(PT_BR1))
    		return defaultLocale;

    	// outros validos
    	return new Locale(localeInitial);

    }

    /**
     * retorna locale para DBListData
     */
    public static Locale getUserLocaleForDBListData(String localeStr) {

    	// por enquanto esta fixo, nao ha necessidade de alterar as tabelas de dominio
     	return defaultLocale;

    }

    public static JogoCache getJogoCacheByLocale(String localeStr) {

    	// pt -> pt_BR
    	if (localeStr == null || localeStr.equals(PT_BR2) || localeStr.equals(PT_BR1))
    		return CacheManager.getCacheDeJogos();

    	// EN
    	if (localeStr.equals(EN))
    		return CacheManager.getCacheDeJogosEN();

		return CacheManager.getCacheDeJogos();

    }
}
