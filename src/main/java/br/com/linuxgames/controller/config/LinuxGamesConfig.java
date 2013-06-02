package br.com.linuxgames.controller.config;


import java.io.IOException;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.ApplicationManager;
import org.mentawai.core.Forward;
import org.mentawai.filter.AuthenticationFilter;
import org.mentawai.filter.FileUploadFilter;
import org.mentawai.filter.VOFilter;
import org.mentawai.list.BaseListData;
import org.mentawai.list.ListData;
import org.mentawai.list.ListManager;

import br.com.linuxgames.controller.crud.ArtigoAction;
import br.com.linuxgames.controller.crud.ArtigoExternoAction;
import br.com.linuxgames.controller.crud.BannerAction;
import br.com.linuxgames.controller.crud.BibliotecaAction;
import br.com.linuxgames.controller.crud.BibliotecaXemuladorAction;
import br.com.linuxgames.controller.crud.BibliotecaXjogoAction;
import br.com.linuxgames.controller.crud.ColaboradorAction;
import br.com.linuxgames.controller.crud.DistroAction;
import br.com.linuxgames.controller.crud.EmuladorAction;
import br.com.linuxgames.controller.crud.EmuladoresFavoritosAction;
import br.com.linuxgames.controller.crud.EnqueteCRUDAction;
import br.com.linuxgames.controller.crud.FabricanteAction;
import br.com.linuxgames.controller.crud.ImagensAction;
import br.com.linuxgames.controller.crud.InfoDoDiaAction;
import br.com.linuxgames.controller.crud.JogoAction;
import br.com.linuxgames.controller.crud.JogoENAction;
import br.com.linuxgames.controller.crud.JogoImagemAction;
import br.com.linuxgames.controller.crud.JogosFavoritosAction;
import br.com.linuxgames.controller.crud.NovidadeAction;
import br.com.linuxgames.controller.crud.NovidadeAgendadaAction;
import br.com.linuxgames.controller.crud.ReviewDeEmuladorAction;
import br.com.linuxgames.controller.crud.ReviewDeJogoAction;
import br.com.linuxgames.controller.crud.RoteiroInstalacaoBibliotecaAction;
import br.com.linuxgames.controller.crud.RoteiroInstalacaoEmuAction;
import br.com.linuxgames.controller.crud.RoteiroInstalacaoJogoAction;
import br.com.linuxgames.controller.crud.SitelogAction;
import br.com.linuxgames.controller.crud.TelaDeEmuladorAction;
import br.com.linuxgames.controller.crud.TelaDeJogoAction;
import br.com.linuxgames.controller.crud.TextoDeEmuladorAction;
import br.com.linuxgames.controller.crud.TextoDeJogoAction;
import br.com.linuxgames.controller.crud.UpdateLogAction;
import br.com.linuxgames.controller.crud.VersaoDeBibliotecaAction;
import br.com.linuxgames.controller.crud.VersaoDeDistroAction;
import br.com.linuxgames.controller.crud.VersaoDeEmuladorAction;
import br.com.linuxgames.controller.crud.VersaoDeJogoAction;
import br.com.linuxgames.controller.crud.VotoXemuladorAction;
import br.com.linuxgames.controller.crud.VotoXenqueteAction;
import br.com.linuxgames.controller.crud.VotoXjogoAction;
import br.com.linuxgames.controller.datalist.DBListDataExtended;
import br.com.linuxgames.controller.filtros.validacoes.ArtigoExternoValidation;
import br.com.linuxgames.controller.filtros.validacoes.ArtigoValidation;
import br.com.linuxgames.controller.filtros.validacoes.BannerValidation;
import br.com.linuxgames.controller.filtros.validacoes.BibliotecaValidation;
import br.com.linuxgames.controller.filtros.validacoes.BibliotecaXemuladorValidation;
import br.com.linuxgames.controller.filtros.validacoes.BibliotecaXjogoValidation;
import br.com.linuxgames.controller.filtros.validacoes.ColaboradorValidation;
import br.com.linuxgames.controller.filtros.validacoes.DistroValidation;
import br.com.linuxgames.controller.filtros.validacoes.EmuladorValidation;
import br.com.linuxgames.controller.filtros.validacoes.EmuladoresFavoritosValidation;
import br.com.linuxgames.controller.filtros.validacoes.EnqueteValidation;
import br.com.linuxgames.controller.filtros.validacoes.FabricanteValidation;
import br.com.linuxgames.controller.filtros.validacoes.ImagemValidation;
import br.com.linuxgames.controller.filtros.validacoes.InfoDoDiaValidation;
import br.com.linuxgames.controller.filtros.validacoes.JogoENValidation;
import br.com.linuxgames.controller.filtros.validacoes.JogoValidation;
import br.com.linuxgames.controller.filtros.validacoes.JogosFavoritosValidation;
import br.com.linuxgames.controller.filtros.validacoes.NovidadeAgendadaValidation;
import br.com.linuxgames.controller.filtros.validacoes.NovidadeValidation;
import br.com.linuxgames.controller.filtros.validacoes.ReviewDeEmuladorValidation;
import br.com.linuxgames.controller.filtros.validacoes.ReviewDeJogoValidation;
import br.com.linuxgames.controller.filtros.validacoes.RoteiroInstalacaoBibliotecaValidation;
import br.com.linuxgames.controller.filtros.validacoes.RoteiroInstalacaoEmuValidation;
import br.com.linuxgames.controller.filtros.validacoes.RoteiroInstalacaoJogoValidation;
import br.com.linuxgames.controller.filtros.validacoes.SitelogValidation;
import br.com.linuxgames.controller.filtros.validacoes.TelaDeJogoValidation;
import br.com.linuxgames.controller.filtros.validacoes.TextoDeEmuladorValidation;
import br.com.linuxgames.controller.filtros.validacoes.TextoDeJogoValidation;
import br.com.linuxgames.controller.filtros.validacoes.UpdateLogValidation;
import br.com.linuxgames.controller.filtros.validacoes.VersaoDeBibliotecaValidation;
import br.com.linuxgames.controller.filtros.validacoes.VersaoDeDistroValidation;
import br.com.linuxgames.controller.filtros.validacoes.VersaoDeEmuladorValidation;
import br.com.linuxgames.controller.filtros.validacoes.VersaoDeJogoValidation;
import br.com.linuxgames.controller.filtros.validacoes.VotoXemuladorValidation;
import br.com.linuxgames.controller.filtros.validacoes.VotoXenqueteValidation;
import br.com.linuxgames.controller.filtros.validacoes.VotoXjogoValidation;
import br.com.linuxgames.model.vo.ArtigoExternoVO;
import br.com.linuxgames.model.vo.ArtigoVO;
import br.com.linuxgames.model.vo.BannerVO;
import br.com.linuxgames.model.vo.BibliotecaVO;
import br.com.linuxgames.model.vo.BibliotecaXemuladorVO;
import br.com.linuxgames.model.vo.BibliotecaXjogoVO;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.DistroVO;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.EmuladoresFavoritosVO;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.model.vo.ImagemVO;
import br.com.linuxgames.model.vo.InfoDoDiaVO;
import br.com.linuxgames.model.vo.Jogo;
import br.com.linuxgames.model.vo.JogosFavoritosVO;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.model.vo.ReviewDeEmuladorVO;
import br.com.linuxgames.model.vo.ReviewDeJogoVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoBibliotecaVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoEmuVO;
import br.com.linuxgames.model.vo.RoteiroInstalacaoJogoVO;
import br.com.linuxgames.model.vo.SitelogVO;
import br.com.linuxgames.model.vo.TelaDeEmuladorVO;
import br.com.linuxgames.model.vo.TelaDeJogoVO;
import br.com.linuxgames.model.vo.TextoDeEmuladorVO;
import br.com.linuxgames.model.vo.TextoDeJogoVO;
import br.com.linuxgames.model.vo.UpdateLogVO;
import br.com.linuxgames.model.vo.VersaoDeBibliotecaVO;
import br.com.linuxgames.model.vo.VersaoDeDistroVO;
import br.com.linuxgames.model.vo.VersaoDeEmuladorVO;
import br.com.linuxgames.model.vo.VersaoDeJogoVO;
import br.com.linuxgames.model.vo.VotoXemuladorVO;
import br.com.linuxgames.model.vo.VotoXenqueteVO;
import br.com.linuxgames.model.vo.VotoXjogoVO;


 /**
  * Define configuracoes extras ao ApplicationManager do Mentawai
  * @author Fernando Boaglio
  */

public class LinuxGamesConfig
{

	private static Logger logger = Logger.getLogger("LinuxGamesCRUDs");
	private static String URL_DO_CRUD="/cadastro/";

	/**
	 * cria as actions CRUDS do LinuxGames no ApplicarionManager
	 * @param applicationManager
	 */
	public static void createLinuxGamesCRUDs(ApplicationManager applicationManager)
	{

	    // CRUD de NOVIDADE
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "novidade",NovidadeAction.class, "vo",NovidadeAction.SUCCESS,NovidadeAction.ERROR,
        		URL_DO_CRUD+"novidade.jsp", Novidade.class,new NovidadeValidation());

        ActionConfig actionConfig2 = new ActionConfig("novidade",NovidadeAction.class,"twittar")
	      .addConsequence(NovidadeAction.SUCCESS, new Forward(URL_DO_CRUD+"novidade.jsp"))
	      .addConsequence(NovidadeAction.ERROR, new Forward(URL_DO_CRUD+"novidade.jsp"));

        applicationManager.addActionConfig(actionConfig2);

        // CRUD de NOVIDADE AGENDADA
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "novidadeagendada",NovidadeAgendadaAction.class, "vo",NovidadeAgendadaAction.SUCCESS,NovidadeAgendadaAction.ERROR,
        		URL_DO_CRUD+"novidadeagendada.jsp", Novidade.class,new NovidadeAgendadaValidation());

		// CRUD de SITELOG
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "sitelog",SitelogAction.class, "vo",SitelogAction.SUCCESS,SitelogAction.ERROR,
        		URL_DO_CRUD+"sitelog.jsp", SitelogVO.class,new SitelogValidation());

		// CRUD de UPDATELOG
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "updatelog",UpdateLogAction.class, "vo",UpdateLogAction.SUCCESS,UpdateLogAction.ERROR,
        		URL_DO_CRUD+"updatelog.jsp", UpdateLogVO.class,new UpdateLogValidation());

        // CRUD de INFO DO DIA
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "infoDoDia",InfoDoDiaAction.class, "vo",InfoDoDiaAction.SUCCESS,InfoDoDiaAction.ERROR,
        		URL_DO_CRUD+"infoDoDia.jsp", InfoDoDiaVO.class,new InfoDoDiaValidation());

        // CRUD de ENQUETES
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager,"enqueteCRUD",EnqueteCRUDAction.class, "vo",EnqueteCRUDAction.SUCCESS,EnqueteCRUDAction.ERROR,
           URL_DO_CRUD+"enquete.jsp", EnqueteVO.class,new EnqueteValidation());

        // CRUD de VOTOS DE ENQUETES
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "votoXenquete",VotoXenqueteAction.class, "vo",VotoXenqueteAction.SUCCESS,VotoXenqueteAction.ERROR,
        		URL_DO_CRUD+"votoXenquete.jsp", VotoXenqueteVO.class,new VotoXenqueteValidation());

        // CRUD de VOTOS DE JOGO
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "votoXjogo",VotoXjogoAction.class, "vo",VotoXjogoAction.SUCCESS,VotoXjogoAction.ERROR,
        		URL_DO_CRUD+"votoXjogo.jsp", VotoXjogoVO.class,new VotoXjogoValidation());

        // CRUD de VOTOS DE EMULADOR
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "votoXemulador",VotoXemuladorAction.class, "vo",VotoXemuladorAction.SUCCESS,VotoXemuladorAction.ERROR,
        		URL_DO_CRUD+"votoXemulador.jsp", VotoXemuladorVO.class,new VotoXemuladorValidation());

        // CRUD de DISTRO
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "distro",DistroAction.class, "vo",DistroAction.SUCCESS,DistroAction.ERROR,
        		URL_DO_CRUD+"distro.jsp", DistroVO.class, new DistroValidation());

        // CRUD de FABRICANTE
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "fabricante",FabricanteAction.class, "vo",FabricanteAction.SUCCESS,FabricanteAction.ERROR,
        		URL_DO_CRUD+"fabricante.jsp", FabricanteVO.class, new FabricanteValidation());

        // CRUD de BANNER
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "banner",BannerAction.class, "vo",BannerAction.SUCCESS,BannerAction.ERROR,
        		URL_DO_CRUD+"banner.jsp", BannerVO.class, new BannerValidation());

        // CRUD de JOGO
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "jogo",JogoAction.class, "vo",JogoAction.SUCCESS,JogoAction.ERROR,
        		URL_DO_CRUD+"jogo.jsp", Jogo.class, new JogoValidation());

        ActionConfig actionConfig = new ActionConfig("jogoImagem", JogoImagemAction.class)
	     .addConsequence(JogoImagemAction.SUCCESS, new Forward(URL_DO_CRUD+"jogoImagem.jsp"))
	     .addConsequence(JogoImagemAction.ERROR, new Forward(URL_DO_CRUD+"jogoImagem.jsp"))
	     .addFilter(new FileUploadFilter())
         .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "jogoEN",JogoENAction.class, "vo",JogoENAction.SUCCESS,JogoENAction.ERROR,
        		URL_DO_CRUD+"jogoEN.jsp", Jogo.class, new JogoENValidation());

        // CRUD de EMULADOR
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "emulador",EmuladorAction.class, "vo",EmuladorAction.SUCCESS,EmuladorAction.ERROR,
        		URL_DO_CRUD+"emulador.jsp", EmuladorVO.class, new EmuladorValidation());

        // CRUD de BIBLIOTECA
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "biblioteca",BibliotecaAction.class, "vo",BibliotecaAction.SUCCESS,BibliotecaAction.ERROR,
        		URL_DO_CRUD+"biblioteca.jsp", BibliotecaVO.class, new BibliotecaValidation());

        // CRUD de BIBLIOTECAxJOGO
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "bibliotecaXjogo",BibliotecaXjogoAction.class, "vo",BibliotecaXjogoAction.SUCCESS,BibliotecaXjogoAction.ERROR,
        		URL_DO_CRUD+"bibliotecaXjogo.jsp", BibliotecaXjogoVO.class, new BibliotecaXjogoValidation());

        // CRUD de BIBLIOTECAxEMULADORES
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "bibliotecaXemulador",BibliotecaXemuladorAction.class, "vo",BibliotecaXemuladorAction.SUCCESS,BibliotecaXemuladorAction.ERROR,
        		URL_DO_CRUD+"bibliotecaXemulador.jsp", BibliotecaXemuladorVO.class, new BibliotecaXemuladorValidation());

        // CRUD de ROTEIRO DE INSTALACAO DE BIBLIOTECA
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "roteiroInstalacaoBiblioteca",RoteiroInstalacaoBibliotecaAction.class, "vo",RoteiroInstalacaoBibliotecaAction.SUCCESS,RoteiroInstalacaoBibliotecaAction.ERROR,
        		URL_DO_CRUD+"roteiroInstalacaoBiblioteca.jsp", RoteiroInstalacaoBibliotecaVO.class, new RoteiroInstalacaoBibliotecaValidation());

        // CRUD de ROTEIRO DE INSTALACAO DE JOGO
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "roteiroInstalacaoJogo",RoteiroInstalacaoJogoAction.class, "vo",RoteiroInstalacaoJogoAction.SUCCESS,RoteiroInstalacaoJogoAction.ERROR,
        		URL_DO_CRUD+"roteiroInstalacaoJogo.jsp", RoteiroInstalacaoJogoVO.class, new RoteiroInstalacaoJogoValidation());

        // CRUD de ROTEIRO DE INSTALACAO DE EMULADOR
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "roteiroInstalacaoEmu",RoteiroInstalacaoEmuAction.class, "vo",RoteiroInstalacaoEmuAction.SUCCESS,RoteiroInstalacaoEmuAction.ERROR,
        		URL_DO_CRUD+"roteiroInstalacaoEmu.jsp", RoteiroInstalacaoEmuVO.class, new RoteiroInstalacaoEmuValidation());

        // CRUD de VERSOES DE JOGOS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "versaoDeJogo",VersaoDeJogoAction.class, "vo",VersaoDeJogoAction.SUCCESS,VersaoDeJogoAction.ERROR,
        		URL_DO_CRUD+"versaoDeJogo.jsp", VersaoDeJogoVO.class, new VersaoDeJogoValidation());

        // CRUD de VERSOES DE EMULADORES
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "versaoDeEmulador",VersaoDeEmuladorAction.class, "vo",VersaoDeEmuladorAction.SUCCESS,VersaoDeEmuladorAction.ERROR,
        		URL_DO_CRUD+"versaoDeEmulador.jsp", VersaoDeEmuladorVO.class, new VersaoDeEmuladorValidation());

        // CRUD de VERSOES DE DISTROS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "versaoDeDistro",VersaoDeDistroAction.class, "vo",VersaoDeDistroAction.SUCCESS,VersaoDeDistroAction.ERROR,
        		URL_DO_CRUD+"versaoDeDistro.jsp", VersaoDeDistroVO.class, new VersaoDeDistroValidation());

        // CRUD de VERSOES DE BIBLIOTECAS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "versaoDeBiblioteca",VersaoDeBibliotecaAction.class, "vo",VersaoDeBibliotecaAction.SUCCESS,VersaoDeBibliotecaAction.ERROR,
        		URL_DO_CRUD+"versaoDeBiblioteca.jsp", VersaoDeBibliotecaVO.class ,new VersaoDeBibliotecaValidation());

        // CRUD de TELAS DE JOGOS (tem upload)
        ApplicationManagerConfig.createCRUDActionsSemConversorComUpload (applicationManager, "telaDeJogo",TelaDeJogoAction.class, "vo",TelaDeJogoAction.SUCCESS,TelaDeJogoAction.ERROR,
        		URL_DO_CRUD+"telaDeJogo.jsp", TelaDeJogoVO.class,new TelaDeJogoValidation(),true);

        // CRUD de TELAS DE EMULADORES (tem upload)
        ApplicationManagerConfig.createCRUDActionsSemConversorComUpload(applicationManager, "telaDeEmulador",TelaDeEmuladorAction.class, "vo",TelaDeEmuladorAction.SUCCESS,TelaDeEmuladorAction.ERROR,
        		URL_DO_CRUD+"telaDeEmulador.jsp", TelaDeEmuladorVO.class,new TelaDeJogoValidation(),true);

        // CRUD de COLABORADORES
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "colaborador",ColaboradorAction.class, "vo",ColaboradorAction.SUCCESS,ColaboradorAction.ERROR,
        		URL_DO_CRUD+"colaborador.jsp", Colaborador.class,new ColaboradorValidation());

		// CRUD de ARTIGO
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "artigo",ArtigoAction.class, "vo",ArtigoAction.SUCCESS,ArtigoAction.ERROR,
        		URL_DO_CRUD+"artigo.jsp", ArtigoVO.class, new ArtigoValidation());

		// CRUD de ARTIGOS EXTERNOS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "artigoExterno",ArtigoExternoAction.class, "vo",ArtigoExternoAction.SUCCESS,ArtigoExternoAction.ERROR,
        		URL_DO_CRUD+"artigoExterno.jsp", ArtigoExternoVO.class,new ArtigoExternoValidation());

		// CRUD de TEXTOS DE JOGOS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "textoDeJogo",TextoDeJogoAction.class, "vo",TextoDeJogoAction.SUCCESS,TextoDeJogoAction.ERROR,
        		URL_DO_CRUD+"textoDeJogo.jsp",TextoDeJogoVO.class, new TextoDeJogoValidation());

		// CRUD de TEXTOS DE EMULADORES
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "textoDeEmulador",TextoDeEmuladorAction.class, "vo",TextoDeEmuladorAction.SUCCESS,TextoDeEmuladorAction.ERROR,
        		URL_DO_CRUD+"textoDeEmulador.jsp",TextoDeEmuladorVO.class, new TextoDeEmuladorValidation());

        // CRUD de TELAS DE REVIEWS DE JOGOS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "reviewDeJogo",ReviewDeJogoAction.class, "vo",ReviewDeJogoAction.SUCCESS,ReviewDeJogoAction.ERROR,
        		URL_DO_CRUD+"reviewDeJogo.jsp", ReviewDeJogoVO.class,new ReviewDeJogoValidation());

        // CRUD de TELAS DE REVIEWS DE EMULADORES
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "reviewDeEmulador",ReviewDeEmuladorAction.class, "vo",ReviewDeEmuladorAction.SUCCESS,ReviewDeEmuladorAction.ERROR,
        		URL_DO_CRUD+"reviewDeEmulador.jsp", ReviewDeEmuladorVO.class,new ReviewDeEmuladorValidation());

        // CRUD de JOGOS FAVORITOS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "jogosFavoritos",JogosFavoritosAction.class, "vo",JogosFavoritosAction.SUCCESS,JogosFavoritosAction.ERROR,
        		URL_DO_CRUD+"jogosFavoritos.jsp", JogosFavoritosVO.class, new JogosFavoritosValidation());

        // CRUD de EMULADORES FAVORITOS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "emuladoresFavoritos",EmuladoresFavoritosAction.class, "vo",EmuladoresFavoritosAction.SUCCESS,EmuladoresFavoritosAction.ERROR,
        		URL_DO_CRUD+"emuladoresFavoritos.jsp", EmuladoresFavoritosVO.class, new EmuladoresFavoritosValidation());

        // CRUD DE IMAGENS
        ApplicationManagerConfig.createCRUDActionsSemConversor(applicationManager, "imagens",ImagensAction.class, "vo",ImagensAction.SUCCESS,ImagensAction.ERROR,
        		URL_DO_CRUD+"imagens.jsp", ImagemVO.class, new ImagemValidation());

        actionConfig = new ActionConfig("imagens", ImagensAction.class,"novo")
	     .addConsequence(ImagensAction.SUCCESS, new Forward(URL_DO_CRUD+"imagens.jsp"))
	     .addConsequence(ImagensAction.ERROR, new Forward(URL_DO_CRUD+"imagens.jsp"))
	     .addFilter(new FileUploadFilter())
	     .addFilter(new VOFilter(ImagemVO.class,"vo"))
         .addFilter(new AuthenticationFilter());
        applicationManager.addActionConfig(actionConfig);

	}

	/**
	 * cria as listas
	 * @param applicationManager
	 * @throws IOException
	 */
	public static void createLists(ApplicationManager applicationManager) throws IOException
	{
        ListManager.init();

        try
        {

                        //---------------------[ Listas Estaticas ]---------------------

        // SIM ou NAO
        ListData simOuNao = new BaseListData("simOuNao", BaseListData.ORDER_BY_ID);
		ListManager.addList(simOuNao);

        // Destaque ou NAO
        ListData destaqueOuNao = new BaseListData("destaqueOuNao", BaseListData.ORDER_BY_ID);
		ListManager.addList(destaqueOuNao);

        // Aprovado ou Reprovado
        ListData aprovadoOuReprovado = new BaseListData("aprovadoOuReprovado", BaseListData.ORDER_BY_ID);
		ListManager.addList(aprovadoOuReprovado);

        // Ativado ou Desativado
        ListData ativadoOuDesativado = new BaseListData("ativadoOuDesativado", BaseListData.ORDER_BY_VALUE);
		ListManager.addList(ativadoOuDesativado);

        // Console ou X11
        ListData consoleOuX11 = new BaseListData("consoleOuX11", BaseListData.ORDER_BY_ID);
		ListManager.addList(consoleOuX11);

        // origem nacional/internacional
        ListData origens = new BaseListData("origens", BaseListData.ORDER_BY_VALUE);
		ListManager.addList(origens);

        // tipo de jogo
        ListData tiposDeJogo = new BaseListData("tiposDeJogo", BaseListData.ORDER_BY_ID);
		ListManager.addList(tiposDeJogo);

        // tipo de emulador
        ListData tiposDeEmulador = new BaseListData("tiposDeEmulador", BaseListData.ORDER_BY_VALUE);
		ListManager.addList(tiposDeEmulador);

		// tipo de versao
        ListData tiposDeVersao = new BaseListData("tiposDeVersao", BaseListData.ORDER_BY_ID);
		ListManager.addList(tiposDeVersao);

		// tipo de texto
        ListData tiposDeTexto = new BaseListData("tiposDeTexto", BaseListData.ORDER_BY_ID);
		ListManager.addList(tiposDeTexto);

        // tipo de sugestoes
        ListData tiposDeSugestoes = new BaseListData("sugestoes", BaseListData.ORDER_BY_VALUE);
		ListManager.addList(tiposDeSugestoes);

        // notas dos votos
        ListData votos = new BaseListData("voto", BaseListData.ORDER_BY_ID);
		ListManager.addList(votos);

        // mime types
        ListData mimeTypes = new BaseListData("mimeTypes", BaseListData.ORDER_BY_ID);
		ListManager.addList(mimeTypes);

		// locales
        ListData locales = new BaseListData("locales", BaseListData.ORDER_BY_ID);
		ListManager.addList(locales);

		// locales
        ListData diasDoMes = new BaseListData("diasDoMes", BaseListData.ORDER_BY_ID);
		ListManager.addList(diasDoMes);

		//---------------------[ Listas Dinamicas ]---------------------

   		// usuarios do forum
        DBListDataExtended usuarios = new DBListDataExtended("USUARIOS","usuario.trazLista", "id", "email");
        ListManager.addList(usuarios);

		// fabricantes
        DBListDataExtended fabricantes = new DBListDataExtended("FABRICANTES","fabricante.buscaTudo", "id", "nome");
        ListManager.addList(fabricantes);

		// grupos
        DBListDataExtended grupos = new DBListDataExtended("GRUPOS","grupo.buscaTudo", "id", "name");
        ListManager.addList(grupos);

		// subgrupos
        DBListDataExtended subgrupos = new DBListDataExtended("SUBGRUPOS","subgrupo.buscaLista", "id", "name");
        ListManager.addList(subgrupos);

        // licencas
        DBListDataExtended licencas = new DBListDataExtended("LICENCAS","licenca.buscaTudo", "id", "name");
        ListManager.addList(licencas);

        // aplicacoes
        DBListDataExtended aplicacoes = new DBListDataExtended("APLICACOES","aplicacao.buscaTudo", "id", "name");
        ListManager.addList(aplicacoes);

        // bibliotecas
        DBListDataExtended bibliotecas = new DBListDataExtended("BIBLIOTECAS","biblioteca.buscaTudo", "id", "nome");
        ListManager.addList(bibliotecas);

        // jogos
        DBListDataExtended jogos = new DBListDataExtended("JOGOS","jogo.buscaParaCombo", "id", "nome");
        ListManager.addList(jogos);

        // jogos em ingles
        DBListDataExtended jogosEN = new DBListDataExtended("JOGOS_EN","jogo.buscaParaComboEN", "id", "nome");
        ListManager.addList(jogosEN);

        // emulador
        DBListDataExtended emuladores = new DBListDataExtended("EMULADORES","emulador.buscaTudo", "id", "nome");
        ListManager.addList(emuladores);

        // distros
        DBListDataExtended distros = new DBListDataExtended("DISTROS","distro.buscaTudo", "id", "nome");
        ListManager.addList(distros);

        // enquetes
        DBListDataExtended enquetes = new DBListDataExtended("ENQUETES","enquete.buscaTudo", "id", "titulo");
        ListManager.addList(enquetes);

        }
        catch (Exception e)
        {
         logger.error("erro na montagem dos combos!",e);
        }
	}

}
