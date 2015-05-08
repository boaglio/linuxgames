package br.com.linuxgames.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;

import br.com.linuxgames.controller.cache.ArtigoCache;
import br.com.linuxgames.controller.cache.ArtigoExternoCache;
import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.cache.EmuladorCache;
import br.com.linuxgames.controller.cache.JogoCache;
import br.com.linuxgames.controller.cache.NovidadeCache;
import br.com.linuxgames.controller.cache.UpdatelogCache;
import br.com.linuxgames.model.dao.core.DAOException;


public class CacheAction extends BaseAction implements RedirectAfterLogin {

	Logger logger = Logger.getLogger(this.getClass());

	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {

    	// artigos
    	final float min = 60*1000F;
    	final float hora = 60*min;
    	final float dia = 24*hora;

    	ArtigoCache artigoCache = CacheManager.getCacheDeArtigos();
    	output.setValue("tamanhoCacheDeArtigos", artigoCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeArtigos", artigoCache.getUltimoRefreshDoCache());
    	output.setValue("hitsDeArtigos", artigoCache.getHits());
    	long diff = System.currentTimeMillis() - artigoCache.getUltimoRefreshDoCache().getTime();
        float elapsedTimeSec = (diff/1000F)%1000;
        float elapsedTimeMin = (diff/(min))%(min);
        float elapsedTimeHour = (diff/(hora))%(hora);
        float elapsedTimeDay = (diff/(dia))%(dia);
        NumberFormat formatter = new DecimalFormat("#.#");
        output.setValue("diffTempoDeArtigos", formatter.format(elapsedTimeDay)+"d "+formatter.format(elapsedTimeHour)+"h "+formatter.format(elapsedTimeMin)+"m "+formatter.format(elapsedTimeSec)+"s");

    	// artigos externos
    	ArtigoExternoCache artigoExternoCache = CacheManager.getCacheDeArtigosExternos();
    	output.setValue("tamanhoCacheDeArtigosExternos", artigoExternoCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeArtigosExternos", artigoExternoCache.getUltimoRefreshDoCache());
    	output.setValue("hitsDeArtigosExternos", artigoExternoCache.getHits());
    	diff = System.currentTimeMillis() - artigoExternoCache.getUltimoRefreshDoCache().getTime();
        elapsedTimeSec = (diff/1000F)%1000;
        elapsedTimeMin = (diff/(min))%(min);
        elapsedTimeHour = (diff/(hora))%(hora);
        elapsedTimeDay = (diff/(dia))%(dia);
        output.setValue("diffTempoDeArtigoExternos", formatter.format(elapsedTimeDay)+"d "+formatter.format(elapsedTimeHour)+"h "+formatter.format(elapsedTimeMin)+"m "+formatter.format(elapsedTimeSec)+"s");

        //jogos
    	JogoCache jogoCache = CacheManager.getCacheDeJogos();
    	output.setValue("tamanhoCacheDeJogos", jogoCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeJogos", jogoCache.getUltimoRefreshDoCache());
    	output.setValue("hitsDeJogos", jogoCache.getHits());
    	diff = System.currentTimeMillis() - jogoCache.getUltimoRefreshDoCache().getTime();
        elapsedTimeSec = (diff/1000F)%1000;
        elapsedTimeMin = (diff/(min))%(min);
        elapsedTimeHour = (diff/(hora))%(hora);
        elapsedTimeDay = (diff/(dia))%(dia);
        output.setValue("diffTempoDeJogos", formatter.format(elapsedTimeDay)+"d "+formatter.format(elapsedTimeHour)+"h "+formatter.format(elapsedTimeMin)+"m "+formatter.format(elapsedTimeSec)+"s");

        //jogos em EN
    	JogoCache jogoCacheEN = CacheManager.getCacheDeJogosEN();
    	output.setValue("tamanhoCacheDeJogosEN", jogoCacheEN.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeJogosEN", jogoCacheEN.getUltimoRefreshDoCache());
    	output.setValue("hitsDeJogosEN", jogoCacheEN.getHits());
    	diff = System.currentTimeMillis() - jogoCacheEN.getUltimoRefreshDoCache().getTime();
        elapsedTimeSec = (diff/1000F)%1000;
        elapsedTimeMin = (diff/(min))%(min);
        elapsedTimeHour = (diff/(hora))%(hora);
        elapsedTimeDay = (diff/(dia))%(dia);
        output.setValue("diffTempoDeJogosEN", formatter.format(elapsedTimeDay)+"d "+formatter.format(elapsedTimeHour)+"h "+formatter.format(elapsedTimeMin)+"m "+formatter.format(elapsedTimeSec)+"s");

        // emulador
    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	output.setValue("tamanhoCacheDeEmuladores", emuladorCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeEmuladores", emuladorCache.getUltimoRefreshDoCache());
    	output.setValue("hitsDeEmuladores", emuladorCache.getHits());
       	diff = System.currentTimeMillis() - emuladorCache.getUltimoRefreshDoCache().getTime();
        elapsedTimeSec = (diff/1000F)%1000;
        elapsedTimeMin = (diff/(min))%(min);
        elapsedTimeHour = (diff/(hora))%(hora);
        elapsedTimeDay = (diff/(dia))%(dia);
        output.setValue("diffTempoDeEmuladores", formatter.format(elapsedTimeDay)+"d "+formatter.format(elapsedTimeHour)+"h "+formatter.format(elapsedTimeMin)+"m "+formatter.format(elapsedTimeSec)+"s");

        // noticias
    	NovidadeCache novidadeCache = CacheManager.getCacheDeNovidades();
    	output.setValue("tamanhoCacheDeNovidades", novidadeCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeNovidades", novidadeCache.getUltimoRefreshDoCache());
    	output.setValue("hitsDeNovidades", novidadeCache.getHits());
       	diff = System.currentTimeMillis() - novidadeCache.getUltimoRefreshDoCache().getTime();
        elapsedTimeSec = (diff/1000F)%1000;
        elapsedTimeMin = (diff/(min))%(min);
        elapsedTimeHour = (diff/(hora))%(hora);
        elapsedTimeDay = (diff/(dia))%(dia);
        output.setValue("diffTempoDeNovidades", formatter.format(elapsedTimeDay)+"d "+formatter.format(elapsedTimeHour)+"h "+formatter.format(elapsedTimeMin)+"m "+formatter.format(elapsedTimeSec)+"s");

        // updates
    	UpdatelogCache updatelogCache = CacheManager.getCacheDeUpdates();
    	output.setValue("tamanhoCacheDeUpdates", updatelogCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeUpdates", updatelogCache.getUltimoRefreshDoCache());
    	output.setValue("hitsDeUpdates", updatelogCache.getHits());
       	diff = System.currentTimeMillis() - updatelogCache.getUltimoRefreshDoCache().getTime();
        elapsedTimeSec = (diff/1000F)%1000;
        elapsedTimeMin = (diff/(min))%(min);
        elapsedTimeHour = (diff/(hora))%(hora);
        elapsedTimeDay = (diff/(dia))%(dia);
        output.setValue("diffTempoDeUpdates", formatter.format(elapsedTimeDay)+"d "+formatter.format(elapsedTimeHour)+"h "+formatter.format(elapsedTimeMin)+"m "+formatter.format(elapsedTimeSec)+"s");

    	return SUCCESS;
    }

	/**
	 * action refresh artigos
	 */
    public String refreshArtigos() throws ActionException {

    	ArtigoCache artigoCache = CacheManager.getCacheDeArtigos();
    	// refresh de artigos
    	try
		{
    	 artigoCache.refreshNoCache();
		} catch (DAOException e)	{
		  logger.error("erro no refresh de artigos!",e);
		  return ERROR;
	    }
    	output.setValue("tamanhoCacheDeArtigos", artigoCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeArtigos", artigoCache.getUltimoRefreshDoCache());
    	return SUCCESS;
    }

	/**
	 * action refresh artigos
	 */
    public String refreshArtigosExternos() throws ActionException {

    	ArtigoExternoCache artigoExternoCache = CacheManager.getCacheDeArtigosExternos();
    	// refresh de artigos externos
    	try
		{
    	 artigoExternoCache.refreshNoCache();
		} catch (DAOException e)	{
		  logger.error("erro no refresh de artigos externos!",e);
		  return ERROR;
	    }
    	output.setValue("tamanhoCacheDeArtigosExternos", artigoExternoCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeArtigosExternos", artigoExternoCache.getUltimoRefreshDoCache());
    	return SUCCESS;
    }

	/**
	 * action refresh jogos
	 */
    public String refreshJogos() throws ActionException {

    	JogoCache jogoCache = CacheManager.getCacheDeJogos();
    	JogoCache jogoCacheEN = CacheManager.getCacheDeJogosEN();
    	// refresh de jogos
    	try
		{
    		jogoCache.refreshNoCache();
    		jogoCacheEN.refreshNoCacheEN();
		} catch (DAOException e)	{
		  logger.error("erro no refresh de jogos!",e);
		  return ERROR;
	    }
    	output.setValue("tamanhoCacheDeJogos", jogoCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeJogos", jogoCache.getUltimoRefreshDoCache());
    	return SUCCESS;
    }

	/**
	 * action refresh emuladores
	 */
    public String refreshEmuladores() throws ActionException {

    	EmuladorCache emuladorCache = CacheManager.getCacheDeEmuladores();
    	// refresh de emuladores
    	try
		{
    	 emuladorCache.refreshNoCache();
		}
    	catch (DAOException e)	{
		  logger.error("erro no refresh de emuladores!",e);
		  return ERROR;
	    }
    	output.setValue("tamanhoCacheDeEmuladores", emuladorCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeEmuladores", emuladorCache.getUltimoRefreshDoCache());
    	return SUCCESS;
    }

	/**
	 * action refresh novidades
	 */
    public String refreshNovidades() throws ActionException {

    	NovidadeCache novidadeCache = CacheManager.getCacheDeNovidades();
    	// refresh de novidades
    	try
		{
    		novidadeCache.refreshNoCache();
		}
    	catch (DAOException e)	{
		  logger.error("erro no refresh de novidades!",e);
		  return ERROR;
	    }
    	output.setValue("tamanhoCacheDeNovidades", novidadeCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeNovidades", novidadeCache.getUltimoRefreshDoCache());
    	return SUCCESS;
    }

	/**
	 * action refresh updates
	 */
    public String refreshUpdates() throws ActionException {

    	UpdatelogCache updatelogCache = CacheManager.getCacheDeUpdates();
    	// refresh de updates
    	try
		{
    		updatelogCache.refreshNoCache();
		}
    	catch (DAOException e)	{
		  logger.error("erro no refresh de updates!",e);
		  return ERROR;
	    }
    	output.setValue("tamanhoCacheDeUpdates", updatelogCache.getTamanhoCache());
    	output.setValue("ultimoRefreshDoCacheDeUpdates", updatelogCache.getUltimoRefreshDoCache());
    	return SUCCESS;
    }

    // getters e setters
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}

}