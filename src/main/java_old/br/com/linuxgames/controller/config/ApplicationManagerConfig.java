package br.com.linuxgames.controller.config;


import org.mentawai.core.ActionConfig;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.Filter;
import org.mentawai.core.Forward;
import org.mentawai.filter.AuthenticationFilter;
import org.mentawai.filter.FileUploadFilter;
import org.mentawai.filter.InjectionFilter;
import org.mentawai.filter.PrettyURLParamFilter;
import org.mentawai.filter.VOFilter;

 /**
  * Define configuracoes extras ao ApplicationManager do Mentawai
  * @author Fernando Boaglio
  */

public class ApplicationManagerConfig
{
	/**
	 * cria as actions CRUDS para serem usadas com inneractions com conversor
	 * @param applicationManager
	 * @param nomeDaAction
	 * @param classeDaAction
	 * @param nomeDoVO
	 * @param sucesso
	 * @param erro
	 * @param paginaJSP
	 * @param classeDoVO
	 * @param filtroDeConversao
	 * @param filtroDeValidacao
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void createCRUDActions(ApplicationManager applicationManager,String nomeDaAction,Class classeDaAction, String nomeDoVO,String sucesso,String erro,
			String paginaJSP, Class classeDoVO, Filter filtroDeConversao,Filter filtroDeValidacao
			)
	{
		//  inicio
	    ActionConfig actionConfig = new ActionConfig(nomeDaAction, classeDaAction)
	      .addConsequence(erro, new Forward(paginaJSP))
	      .addConsequence(sucesso, new Forward(paginaJSP))
          .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true));
        applicationManager.addActionConfig(actionConfig);

        // nova
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"novo")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP))
	      .addFilter(filtroDeValidacao)
	      .addFilter(filtroDeConversao)
	      .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true));
        applicationManager.addActionConfig(actionConfig);

        // busca uma
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"buscaUm")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP))
	      .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true))
          .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

        // atualiza
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"atualiza")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP))
	      .addFilter(filtroDeValidacao)
	      .addFilter(filtroDeConversao)
	      .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true))
          .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

        // remove
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"remove")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP))
	      .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true))
          .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

	}


	/**
	 * cria as actions CRUDS para serem usadas com inneractions sem conversor
	 * @param applicationManager
	 * @param nomeDaAction
	 * @param classeDaAction
	 * @param nomeDoVO
	 * @param sucesso
	 * @param erro
	 * @param paginaJSP
	 * @param classeDoVO
	 * @param filtroDeValidacao
	 */
	@SuppressWarnings("rawtypes")
	public static void createCRUDActionsSemConversor(ApplicationManager applicationManager,String nomeDaAction,Class classeDaAction, String nomeDoVO,String sucesso,String erro,
			String paginaJSP, Class classeDoVO, Filter filtroDeValidacao
			) {

		createCRUDActionsSemConversorComUpload(applicationManager,nomeDaAction,classeDaAction, nomeDoVO,sucesso, erro,
				 paginaJSP, classeDoVO, filtroDeValidacao,false	);

    }

	/**
	 * cria as actions CRUDS para serem usadas com inneractions sem conversor
	 * @param applicationManager
	 * @param nomeDaAction
	 * @param classeDaAction
	 * @param nomeDoVO
	 * @param sucesso
	 * @param erro
	 * @param paginaJSP
	 * @param classeDoVO
	 * @param filtroDeValidacao
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void createCRUDActionsSemConversorComUpload(ApplicationManager applicationManager,String nomeDaAction,Class classeDaAction, String nomeDoVO,String sucesso,String erro,
			String paginaJSP, Class classeDoVO, Filter filtroDeValidacao,boolean uploadHabilitado
			)
	{
		//  inicio
		ActionConfig actionConfig = new ActionConfig(nomeDaAction, classeDaAction)
	     .addConsequence(sucesso, new Forward(paginaJSP))
	     .addConsequence(erro, new Forward(paginaJSP))
	     .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
         .addFilter(new InjectionFilter(true))
         .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

        // nova
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"novo")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP));

        if (uploadHabilitado)
        	actionConfig.addFilter(new FileUploadFilter());
        else
        	actionConfig.addFilter(filtroDeValidacao);

        actionConfig.addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true))
          .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

        // busca uma
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"buscaUm")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP))
	      .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true))
          .addFilter(new AuthenticationFilter())
          .addFilter(new PrettyURLParamFilter("id"));
        applicationManager.addActionConfig(actionConfig);

        // atualiza
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"atualiza")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP));

        if (uploadHabilitado)
        	actionConfig.addFilter(new FileUploadFilter());
        else
        	actionConfig.addFilter(filtroDeValidacao);

        actionConfig.addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true))
          .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

        // remove
        actionConfig = new ActionConfig(nomeDaAction, classeDaAction,"remove")
	      .addConsequence(sucesso, new Forward(paginaJSP))
	      .addConsequence(erro, new Forward(paginaJSP))
	      .addFilter(new VOFilter(classeDoVO, nomeDoVO,true))
          .addFilter(new InjectionFilter(true))
          .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);
	}

}
