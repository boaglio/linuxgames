import java.io.IOException;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.mentawai.action.LogoutAction;
import org.mentawai.ajax.AjaxConsequence;
import org.mentawai.authorization.AuthorizationManager;
import org.mentawai.authorization.Group;
import org.mentawai.core.ActionConfig;
import org.mentawai.core.BaseAction;
import org.mentawai.core.Context;
import org.mentawai.core.Forward;
import org.mentawai.core.Redirect;
import org.mentawai.filter.AuthenticationFilter;
import org.mentawai.filter.ConverterFilter;
import org.mentawai.filter.InjectionFilter;
import org.mentawai.filter.PrettyURLParamFilter;
import org.mentawai.filter.RedirectAfterLoginFilter;
import org.mentawai.filter.VOFilter;
import org.mentawai.filter.ValidatorFilter;
import org.mentawai.formatter.DateFormatter;
import org.mentawai.formatter.FormatterManager;
import org.mentawai.i18n.LocaleManager;
import org.mentawai.mail.Email;

import br.com.linuxgames.controller.AdminEmuladorAction;
import br.com.linuxgames.controller.AdminJogoAction;
import br.com.linuxgames.controller.AdminNovidadeAction;
import br.com.linuxgames.controller.BuscaJogoEmuladorAction;
import br.com.linuxgames.controller.CacheAction;
import br.com.linuxgames.controller.EnqueteAction;
import br.com.linuxgames.controller.EntrevistaAction;
import br.com.linuxgames.controller.FavoritosAction;
import br.com.linuxgames.controller.HowtoAction;
import br.com.linuxgames.controller.IndexAction;
import br.com.linuxgames.controller.InfoArtigoAction;
import br.com.linuxgames.controller.InfoEmuladorAction;
import br.com.linuxgames.controller.InfoJogoAction;
import br.com.linuxgames.controller.LoginAction;
import br.com.linuxgames.controller.PainelAction;
import br.com.linuxgames.controller.PlanetaAction;
import br.com.linuxgames.controller.ReviewAction;
import br.com.linuxgames.controller.SobreAction;
import br.com.linuxgames.controller.SugestaoAction;
import br.com.linuxgames.controller.TodosAction;
import br.com.linuxgames.controller.UsuarioAction;
import br.com.linuxgames.controller.config.LinuxGamesConfig;
import br.com.linuxgames.controller.config.MapAjaxRenderer;
import br.com.linuxgames.controller.filtros.validacoes.JogoAjudaValidation;
import br.com.linuxgames.model.vo.EnqueteVO;
import br.com.linuxgames.model.vo.IndexVO;
import br.com.linuxgames.model.vo.InfoVO;


public class ApplicationManager extends org.mentawai.core.ApplicationManager {

	private static final boolean DEBUG_ATIVO = false;

	private static final String PAGINA_TODOS_EMULADORES = "/emuladores.jsp";
	private static final String PAGINA_TODOS_JOGOS = "/jogos.jsp";
	private static final String PAGINA_DE_LOGIN_JSP = "/login.jsp";
	private static final String PAGINA_DE_ERRO_JSP = "/erro.jsp";
	private static final String PAGINA_DE_ACESSO_NEGADO_JSP = "/semchance.jsp";
	private static final String PAGINA_DE_LOGOFF_JSP = "/index.jsp";
	private static final String ACTION_DE_LOGOFF_JSP = "/Sair";
	private static final String ACTION_DE_LOGIN_JSP = "/Login";
	private static final String ACTION_DE_LOGIN_FORUM = "/user/login.games";

	private static final String ACTION_DE_ENQUETE = "/enquete";
	private static final String PAGINA_DE_ENQUETE_OK_JSP="/enquetePopupVotoOK.jsp";
	private static final String PAGINA_DE_ENQUETE_JA_VOTOU_JSP="/enquetePopupJaVotou.jsp";
	private static final String PAGINA_DE_ENQUETE_RESULTADOS_JSP="/enquetePopupResultados.jsp";

	private static final String PAGINA_HOME="/home.jsp";
	private static final String PAGINA_HOME_REDIR = "/games/index-home";
	private static final String PAGINA_INDEX="/index.jsp";
	private static final String PAGINA_DE_JOGO_JSP="/jogo.jsp";
	private static final String PAGINA_DE_AJUDA_DE_JOGO_JSP="/jogoAjuda.jsp";
	private static final String PAGINA_DE_AJUDA_DE_JOGO_CONCLUIDA = "/jogoAjudaConcluida.jsp";
	private static final String PAGINA_DE_AJUDA_NOVIDADE_JSP="/novidadeAjuda.jsp";
	private static final String PAGINA_DE_AJUDA_NOVIDADE_CONCLUIDA = "/novidadeAjudaConcluida.jsp";
	private static final String PAGINA_DE_ARTIGO_JSP="/artigo.jsp";
	private static final String PAGINA_DE_EMULADOR_JSP="/emulador.jsp";
	private static final String PAGINA_MSG="/msg.jsp";
	private static final String PAGINA_NOVO_USUARIO="/novoUsuario.jsp";

	private static final String URL_DO_CRUD="/cadastro/";

	Logger logger = Logger.getLogger(this.getClass().getName());

//
//	private String PAGINA_DE_VOTO_OK_JSP="/votoPopupOK.jsp";
//	private String PAGINA_DE_VOTO_JA_VOTOU_JSP="/votoPopupJaVotou.jsp";
//	private String PAGINA_DE_VOTO_RESULTADOS_JSP="/votoPopupResultados.jsp";

	/**
	 * - identifica servidor de email
	 * - configuracoes de autorizacao
	 */
    public void init(Context application) {
       //  email
//       Email.setDefaultHostName("localhost");
       Email.setDefaultHostName("smtp.mailgun.org");
       Email.setDefaultPort(587);
       Email.setDefaultAuthentication("postmaster@app5084825.mailgun.org","1raenfbwsw-7");
       Email.setDefaultFrom("tux@linuxgames.com.br", "LinuxGames.com.br");
       // seguranca - admins
       Group admins = new Group("admins");
       admins.addPermission("cadastro").addPermission("aprovador");
       AuthorizationManager.addGroup(admins);
       // seguranca - colaboradores
       Group colaboradores = new Group("colaboradores");
       colaboradores.addPermission("aprovador");
       AuthorizationManager.addGroup(colaboradores);
       // seguranca - editores
       Group editores = new Group("editores");
       AuthorizationManager.addGroup(editores);
    }

	/**
	 * carrega as informacoes dos COMBOS
	 */
    public void loadLists() throws IOException {
      LinuxGamesConfig.createLists(this);
    }

    /**
     * carrega as actions
     */
	public void loadActions() {

		// debug mode
	    setDebugMode(DEBUG_ATIVO);

		// suporte ao hotdeploy
		//setReloadMode(true);

	    setStatsMode(true);

		ActionConfig actionConfig = null;


		// adiciona o novo filtro de conversao
		addGlobalFilter(new ConverterFilter());

		// autenticacao global
//        addGlobalFilter(new AuthenticationFilter());
        addGlobalConsequence(AuthenticationFilter.LOGIN, new Redirect(PAGINA_DE_LOGIN_JSP));
        on(ACCESSDENIED, redir(PAGINA_DE_ACESSO_NEGADO_JSP));

        addGlobalConsequence(BaseAction.NULL, new Forward(PAGINA_INDEX));

        // autenticacao global - login
        ActionConfig ac = new ActionConfig(ACTION_DE_LOGIN_JSP, LoginAction.class)
         .addConsequence(LoginAction.SUCCESS, new Redirect(PAGINA_DE_LOGOFF_JSP))
         .addConsequence(LoginAction.ERROR,  new Forward(PAGINA_DE_LOGIN_JSP));
        addActionConfig(ac);

        ac.addFilter(new RedirectAfterLoginFilter());
        ac.addConsequence(RedirectAfterLoginFilter.REDIR, new Redirect());

        // autenticacao global - logoff
        ac = new ActionConfig(ACTION_DE_LOGOFF_JSP, LogoutAction.class);
        ac.addConsequence(LogoutAction.SUCCESS, new Redirect(PAGINA_DE_LOGOFF_JSP));
        addActionConfig(ac);

        // Todos os CRUDs da aplicacao
	    LinuxGamesConfig.createLinuxGamesCRUDs(this);

		// index
		actionConfig = new ActionConfig("index", IndexAction.class)
	     .addConsequence(IndexAction.SUCCESS, new Forward("/index2.jsp"))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
         .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);

        // index de home
		actionConfig = new ActionConfig("index", IndexAction.class,"home")
		 .filter(new PrettyURLParamFilter("loc"))
	     .addConsequence(IndexAction.SUCCESS, new Forward(PAGINA_HOME))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
         .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);

        actionConfig = new ActionConfig("index", IndexAction.class,"teste")
        .addConsequence(LoginAction.SUCCESS, new Redirect(PAGINA_DE_LOGOFF_JSP));
        addActionConfig(actionConfig);

        // login na home
		actionConfig = new ActionConfig("index", IndexAction.class,"login")
		 .filter(new PrettyURLParamFilter("login"))
	     .addConsequence(IndexAction.SUCCESS, new Forward(PAGINA_MSG))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_MSG))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
        .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);

        // logout na home
		actionConfig = new ActionConfig("index", IndexAction.class,"logout")
		 .filter(new PrettyURLParamFilter("logout"))
	     .addConsequence(IndexAction.SUCCESS, new Forward(PAGINA_HOME_REDIR))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_HOME_REDIR));
        addActionConfig(actionConfig);

        // pagina para cadastrar novo usuario na home
		actionConfig = new ActionConfig("index", IndexAction.class,"register")
		 .filter(new PrettyURLParamFilter("register"))
	     .addConsequence(IndexAction.SUCCESS, new Forward(PAGINA_NOVO_USUARIO))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_NOVO_USUARIO))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
        .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);

        // pagina para mandar email de quem esqueceu a senha
		actionConfig = new ActionConfig("index", IndexAction.class,"esqueceu")
		 .filter(new PrettyURLParamFilter("esqueceu"))
	     .addConsequence(IndexAction.SUCCESS, new Forward(PAGINA_NOVO_USUARIO))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_NOVO_USUARIO))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
        .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);


        // registrar novo usuario na home
		actionConfig = new ActionConfig("index", IndexAction.class,"novo")
		 .filter(new PrettyURLParamFilter("novo"))
	     .addConsequence(IndexAction.SUCCESS, new Forward(PAGINA_NOVO_USUARIO))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_NOVO_USUARIO))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
         .addFilter(new InjectionFilter(true))
	     .addFilter(new ValidatorFilter());
        addActionConfig(actionConfig);

        // novidades
		actionConfig = new ActionConfig("index", IndexAction.class,"novidades")
	     .addConsequence(IndexAction.SUCCESS, new Forward("/novidades.jsp"))
	     .addConsequence(IndexAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"));
        addActionConfig(actionConfig);

		// novidades - ajuda
        actionConfig = new ActionConfig("index", IndexAction.class,"ajuda")
         .addConsequence(IndexAction.SUCCESS, new Forward(PAGINA_HOME))
         .addConsequence(IndexAction.AJUDA, new Forward(PAGINA_DE_AJUDA_NOVIDADE_JSP))
         .addConsequence(IndexAction.AJUDA_CONCLUIDA, new Forward(PAGINA_DE_AJUDA_NOVIDADE_CONCLUIDA))
         .addConsequence(IndexAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
         .addFilter(new VOFilter(IndexVO.class,"vo"));
        addActionConfig(actionConfig);

		// todos - jogos
		actionConfig = new ActionConfig("todos", TodosAction.class,"jogos")
	     .addConsequence(TodosAction.SUCCESS, new Forward(PAGINA_TODOS_JOGOS))
	     .addConsequence(TodosAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// todos - emuladores
		actionConfig = new ActionConfig("todos", TodosAction.class,"emuladores")
	     .addConsequence(TodosAction.SUCCESS, new Forward(PAGINA_TODOS_EMULADORES))
	     .addConsequence(TodosAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// todos - artigos
		actionConfig = new ActionConfig("todos", TodosAction.class,"artigos")
	     .addConsequence(TodosAction.SUCCESS, new Forward("/artigos.jsp"))
	     .addConsequence(TodosAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

        // artigo
		actionConfig = new ActionConfig("infoArtigo", InfoArtigoAction.class,"artigo")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoArtigoAction.SUCCESS, new Forward(PAGINA_DE_ARTIGO_JSP))
	     .addConsequence(InfoArtigoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

        // artigo - VOTACAO
        actionConfig = new ActionConfig("review", ReviewAction.class,"artigo")
         .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(ReviewAction.SUCCESS,new Forward("info.artigo.action"))
	     .addConsequence(ReviewAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
	    addActionConfig(actionConfig);

		// artigo- artigoReview
		actionConfig = new ActionConfig("infoArtigo", InfoArtigoAction.class,"review")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoArtigoAction.SUCCESS, new Forward(PAGINA_DE_ARTIGO_JSP))
	     .addConsequence(InfoArtigoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - resumo
        ActionConfig actionConfigInfoJogo = new ActionConfig("infoJogo", InfoJogoAction.class,"jogo")
         .filter(new PrettyURLParamFilter("id","p"))
         .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
         .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
         .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfigInfoJogo);

		// jogo - jogoReview
		actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"review")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
	     .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

        // jogo - VOTACAO
        actionConfig = new ActionConfig("review", ReviewAction.class,"jogo")
	     .addConsequence(ReviewAction.SUCCESS,new Forward("infoJogo.review.action"))
	     .addConsequence(ReviewAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
	    addActionConfig(actionConfig);

		// jogo - jogoInst
		actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"inst")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
	     .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - atualiza Roteiro de Jogo
		actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"atualizaRoteiro")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoJogoAction.SUCCESS,new Forward("infoJogo.inst.action"))
	     .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - atualiza Dica de Jogo
		actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"atualizaDica")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoJogoAction.SUCCESS,new Forward("infoJogo.dica.action"))
	     .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - jogoDica
		actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"dica")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
	     .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - jogoTela
		actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"tela")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
	     .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - jogoDownload
		actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"download")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
	     .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - ajuda
        actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"ajuda")
         .filter(new PrettyURLParamFilter("id","loc"))
         .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
         .addConsequence(InfoJogoAction.AJUDA, new Forward(PAGINA_DE_AJUDA_DE_JOGO_JSP))
         .addConsequence(InfoJogoAction.AJUDA_CONCLUIDA, new Forward(PAGINA_DE_AJUDA_DE_JOGO_CONCLUIDA))
         .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
         .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - ajuda manda
        actionConfig = new ActionConfig("infoJogo", InfoJogoAction.class,"ajudaManda")
         .filter(new PrettyURLParamFilter("id"))
         .addConsequence(InfoJogoAction.SUCCESS, new Forward(PAGINA_DE_JOGO_JSP))
         .addConsequence(InfoJogoAction.AJUDA, new Forward(PAGINA_DE_AJUDA_DE_JOGO_JSP))
         .addConsequence(InfoJogoAction.AJUDA_CONCLUIDA, new Forward(PAGINA_DE_AJUDA_DE_JOGO_CONCLUIDA))
         .addConsequence(InfoJogoAction.ERROR, new Forward(PAGINA_DE_AJUDA_DE_JOGO_JSP))
         .addFilter(new JogoAjudaValidation())
         .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - add favorito
		actionConfig = new ActionConfig("favoritos", FavoritosAction.class,"jogoFavorito")
	     .addConsequence(FavoritosAction.SUCCESS, new Forward("infoJogo.jogo.action"))
	     .addConsequence(FavoritosAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// jogo - lista favorito
		actionConfig = new ActionConfig("favoritos", FavoritosAction.class,"lista")
	     .addConsequence(FavoritosAction.SUCCESS, new Forward("favoritos.jsp"))
	     .addConsequence(FavoritosAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - resumo
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"emulador")
		 .filter(new PrettyURLParamFilter("id","p"))
	     .addConsequence(InfoEmuladorAction.SUCCESS, new Forward(PAGINA_DE_EMULADOR_JSP))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

        // emulador - VOTACAO
        actionConfig = new ActionConfig("review", ReviewAction.class,"emulador")
	     .addConsequence(ReviewAction.SUCCESS,new Forward("infoEmulador.review.action"))
	     .addConsequence(ReviewAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
	    addActionConfig(actionConfig);

		// emulador - add favorito
		actionConfig = new ActionConfig("favoritos", FavoritosAction.class,"emuladorFavorito")
	     .addConsequence(FavoritosAction.SUCCESS, new Forward("infoEmulador.emulador.action"))
	     .addConsequence(FavoritosAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - review
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"review")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoEmuladorAction.SUCCESS, new Forward(PAGINA_DE_EMULADOR_JSP))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_EMULADOR_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - inst
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"inst")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoEmuladorAction.SUCCESS, new Forward(PAGINA_DE_EMULADOR_JSP))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - dica
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"dica")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoEmuladorAction.SUCCESS, new Forward(PAGINA_DE_EMULADOR_JSP))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - tela
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"tela")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoEmuladorAction.SUCCESS, new Forward(PAGINA_DE_EMULADOR_JSP))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - download
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"download")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoEmuladorAction.SUCCESS, new Forward(PAGINA_DE_EMULADOR_JSP))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - atualiza Roteiro de Emulador
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"atualizaRoteiro")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoEmuladorAction.SUCCESS,new Forward("infoEmulador.inst.action"))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

		// emulador - atualiza Dica de Emulador
		actionConfig = new ActionConfig("infoEmulador", InfoEmuladorAction.class,"atualizaDica")
		 .filter(new PrettyURLParamFilter("id"))
	     .addConsequence(InfoEmuladorAction.SUCCESS,new Forward("infoEmulador.dica.action"))
	     .addConsequence(InfoEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(InfoVO.class,"vo"));
        addActionConfig(actionConfig);

        // busca AJAX
        actionConfig = new ActionConfig("busca",BuscaJogoEmuladorAction.class)
         .filter(new PrettyURLParamFilter("q"))
         .addConsequence(BuscaJogoEmuladorAction.SUCCESS, new AjaxConsequence("lgGames", new MapAjaxRenderer()));
        addActionConfig(actionConfig);

		// sugestao
		actionConfig = new ActionConfig("sugestao", SugestaoAction.class,"enviar")
	     .addConsequence(SugestaoAction.SUCCESS, new Forward("/sugestao.jsp"))
	     .addConsequence(SugestaoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);
		actionConfig = new ActionConfig("sugestao", SugestaoAction.class)
	     .addConsequence(SugestaoAction.SUCCESS, new Forward("/sugestao.jsp"))
	     .addConsequence(SugestaoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);

		// sobre
		actionConfig = new ActionConfig("sobre", SobreAction.class)
	     .addConsequence(SobreAction.SUCCESS, new Forward("/sobre.jsp"))
	     .addConsequence(SobreAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);

	    // entrevista
		actionConfig = new ActionConfig("entrevista", EntrevistaAction.class)
	     .addConsequence(SobreAction.SUCCESS, new Forward("/entrevista.jsp"))
	     .addConsequence(SobreAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);


	    // painel
		actionConfig = new ActionConfig("painel", PainelAction.class)
	     .addConsequence(PainelAction.SUCCESS, new Forward("/painel.jsp"))
	     .addConsequence(PainelAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
         .addConsequence(PainelAction.NAO_LOGADO,  new Redirect(ACTION_DE_LOGIN_FORUM))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);

		// HowTo
		actionConfig = new ActionConfig("howto", HowtoAction.class)
	     .addConsequence(HowtoAction.SUCCESS, new Forward("/howto.jsp"))
	     .addConsequence(HowtoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);

		// HowTo
		actionConfig = new ActionConfig("howto", SobreAction.class)
	     .addConsequence(HowtoAction.SUCCESS, new Forward("/howto.jsp"))
	     .addConsequence(HowtoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);

		// Planeta Doom
		actionConfig = new ActionConfig("planeta", PlanetaAction.class , "doom")
	     .addConsequence(PlanetaAction.SUCCESS, new Forward("/doom.jsp"))
	     .addConsequence(PlanetaAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);

		// Planeta Quake
		actionConfig = new ActionConfig("planeta", PlanetaAction.class , "quake")
	     .addConsequence(PlanetaAction.SUCCESS, new Forward("/quake.jsp"))
	     .addConsequence(PlanetaAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);

		// Planeta Enemy Territory
		actionConfig = new ActionConfig("planeta", PlanetaAction.class , "et")
	     .addConsequence(PlanetaAction.SUCCESS, new Forward("/et.jsp"))
	     .addConsequence(PlanetaAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(IndexVO.class,"vo"))
	     .addFilter(new InjectionFilter(true));
	    addActionConfig(actionConfig);

        // TELA DE LISTA DE USUARIOS
        actionConfig = new ActionConfig("usuario", UsuarioAction.class)
	     .addConsequence(UsuarioAction.SUCCESS, new Forward(URL_DO_CRUD+"usuario.jsp"))
	     .addConsequence(UsuarioAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
        addActionConfig(actionConfig);

        // VOTACAO DE ENQUETES
        // opt1
        actionConfig = new ActionConfig(ACTION_DE_ENQUETE, EnqueteAction.class,"opt1")
	     .addConsequence(EnqueteAction.SUCCESS, new Forward(PAGINA_DE_ENQUETE_OK_JSP))
	     .addConsequence(EnqueteAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addConsequence(EnqueteAction.RESULTADOS, new Forward(PAGINA_DE_ENQUETE_RESULTADOS_JSP))
	     .addConsequence(EnqueteAction.JA_VOTOU, new Forward(PAGINA_DE_ENQUETE_JA_VOTOU_JSP));
        addActionConfig(actionConfig);
        // opt2
        actionConfig = new ActionConfig(ACTION_DE_ENQUETE, EnqueteAction.class,"opt2")
	     .addConsequence(EnqueteAction.SUCCESS, new Forward(PAGINA_DE_ENQUETE_OK_JSP))
	     .addConsequence(EnqueteAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addConsequence(EnqueteAction.RESULTADOS, new Forward(PAGINA_DE_ENQUETE_RESULTADOS_JSP))
	     .addConsequence(EnqueteAction.JA_VOTOU, new Forward(PAGINA_DE_ENQUETE_JA_VOTOU_JSP));
        addActionConfig(actionConfig);
        // opt3
        actionConfig = new ActionConfig(ACTION_DE_ENQUETE, EnqueteAction.class,"opt3")
	     .addConsequence(EnqueteAction.SUCCESS, new Forward(PAGINA_DE_ENQUETE_OK_JSP))
	     .addConsequence(EnqueteAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addConsequence(EnqueteAction.RESULTADOS, new Forward(PAGINA_DE_ENQUETE_RESULTADOS_JSP))
	     .addConsequence(EnqueteAction.JA_VOTOU, new Forward(PAGINA_DE_ENQUETE_JA_VOTOU_JSP));
        addActionConfig(actionConfig);
        // opt4
        actionConfig = new ActionConfig(ACTION_DE_ENQUETE, EnqueteAction.class,"opt4")
	     .addConsequence(EnqueteAction.SUCCESS, new Forward(PAGINA_DE_ENQUETE_OK_JSP))
	     .addConsequence(EnqueteAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addConsequence(EnqueteAction.RESULTADOS, new Forward(PAGINA_DE_ENQUETE_RESULTADOS_JSP))
	     .addConsequence(EnqueteAction.JA_VOTOU, new Forward(PAGINA_DE_ENQUETE_JA_VOTOU_JSP));
        addActionConfig(actionConfig);
        // resultados
        actionConfig = new ActionConfig(ACTION_DE_ENQUETE, EnqueteAction.class,"resultado")
	     .addConsequence(EnqueteAction.SUCCESS, new Forward(PAGINA_DE_ENQUETE_RESULTADOS_JSP))
	     .addConsequence(EnqueteAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
	     .addFilter(new VOFilter(EnqueteVO.class,"vo",true))
         .addFilter(new InjectionFilter(true));
        addActionConfig(actionConfig);

        // Controle de Cache
        actionConfig = new ActionConfig("cache", CacheAction.class)
	     .addConsequence(CacheAction.SUCCESS, new Forward(URL_DO_CRUD+"cache.jsp"))
	     .addConsequence(CacheAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
        addActionConfig(actionConfig);
        actionConfig = new ActionConfig("cache", CacheAction.class,"refreshNovidades")
	     .addConsequence(CacheAction.SUCCESS, new Forward(URL_DO_CRUD+"cache.jsp"))
	     .addConsequence(CacheAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
        actionConfig = new ActionConfig("cache", CacheAction.class,"refreshUpdates")
	     .addConsequence(CacheAction.SUCCESS, new Forward(URL_DO_CRUD+"cache.jsp"))
	     .addConsequence(CacheAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
        actionConfig = new ActionConfig("cache", CacheAction.class,"refreshArtigos")
	     .addConsequence(CacheAction.SUCCESS, new Forward(URL_DO_CRUD+"cache.jsp"))
	     .addConsequence(CacheAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
        actionConfig = new ActionConfig("cache", CacheAction.class,"refreshArtigosExternos")
	     .addConsequence(CacheAction.SUCCESS, new Forward(URL_DO_CRUD+"cache.jsp"))
	     .addConsequence(CacheAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
	    addActionConfig(actionConfig);
        actionConfig = new ActionConfig("cache", CacheAction.class,"refreshJogos")
	     .addConsequence(CacheAction.SUCCESS, new Forward(URL_DO_CRUD+"cache.jsp"))
	     .addConsequence(CacheAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
	    addActionConfig(actionConfig);
        actionConfig = new ActionConfig("cache", CacheAction.class,"refreshEmuladores")
	     .addConsequence(CacheAction.SUCCESS, new Forward(URL_DO_CRUD+"cache.jsp"))
	     .addConsequence(CacheAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
	    addActionConfig(actionConfig);

        // VOTACAO DE JOGO [DEPRECATED]
//        actionConfig = new ActionConfig("/voto", VotoAction.class,"jogo")
//	     .addConsequence(VotoAction.SUCCESS,new Redirect("infoJogo.jogo.action", true))
//	     .addConsequence(VotoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
//	     .addConsequence(VotoAction.RESULTADOS, new Forward(PAGINA_DE_VOTO_RESULTADOS_JSP));
//	     .addConsequence(VotoAction.JA_VOTOU, new Forward(PAGINA_DE_VOTO_JA_VOTOU_JSP));
//        addActionConfig(actionConfig);
        // VOTACAO DE EMULADOR [DEPRECATED]
//        actionConfig = new ActionConfig("/voto", VotoAction.class,"emulador")
//	     .addConsequence(VotoAction.SUCCESS, new Chain(actionConfigInfoJogo))
//	     .addConsequence(VotoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
//	     .addConsequence(VotoAction.RESULTADOS, new Forward(PAGINA_DE_VOTO_RESULTADOS_JSP));
//	     .addConsequence(VotoAction.JA_VOTOU, new Forward(PAGINA_DE_VOTO_JA_VOTOU_JSP));
//        addActionConfig(actionConfig);

        // ADMINISTRACAO PELOS COLABORADORES - solicitacao de roteiro de jogo
        actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoRoteiroJogo")
	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoRoteiroJogo.jsp"))
	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

        actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoRoteiroJogoDetalhe")
	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoRoteiroJogoDetalhe.jsp"))
	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

        actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"alteraSolicitacaoJogo")
	     .addConsequence(AdminJogoAction.ROTEIRO,new Forward(URL_DO_CRUD+"solicitacaoRoteiroJogo.jsp"))
	     .addConsequence(AdminJogoAction.TEXTO,new Forward(URL_DO_CRUD+"solicitacaoTextoJogo.jsp"))
	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

        //  ADMINISTRACAO PELOS COLABORADORES - solicitacao de texto de jogo (dica,video,download)
         actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoTextoJogo")
 	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoTextoJogo.jsp"))
 	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoTextoJogoDetalhe")
 	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoTextoJogoDetalhe.jsp"))
 	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

         // ADMINISTRACAO PELOS COLABORADORES - solicitacao de alteracao de jogo / ajuda
         actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoAlteracaoJogo")
   	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoAlteracaoJogo.jsp"))
   	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoAlteracaoJogoDetalhe")
   	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoAlteracaoJogoDetalhe.jsp"))
   	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"alteraSolicitacaoAjudaJogo")
   	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoAlteracaoJogo.jsp"))
   	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);


         // ADMINISTRACAO PELOS COLABORADORES - solicitacao de novo de jogo / ajuda
          actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoNovoJogo")
    	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoNovoJogo.jsp"))
    	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

          actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"solicitacaoNovoJogoDetalhe")
    	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoNovoJogoDetalhe.jsp"))
    	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

          actionConfig = new ActionConfig("/admin", AdminJogoAction.class,"alteraSolicitacaoAjudaNovoJogo")
    	     .addConsequence(AdminJogoAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoNovoJogo.jsp"))
    	     .addConsequence(AdminJogoAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

         // ADMINISTRACAO PELOS COLABORADORES - solicitacao de noticia
         actionConfig = new ActionConfig("/admin", AdminNovidadeAction.class,"solicitacaoNovidade")
    	  .addConsequence(AdminNovidadeAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoNovidade.jsp"))
    	  .addConsequence(AdminNovidadeAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminNovidadeAction.class,"solicitacaoNovidadeDetalhe")
    	  .addConsequence(AdminNovidadeAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoNovidadeDetalhe.jsp"))
    	  .addConsequence(AdminNovidadeAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminNovidadeAction.class,"alteraSolicitacaoNovidade")
    	  .addConsequence(AdminNovidadeAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoNovidade.jsp"))
    	  .addConsequence(AdminNovidadeAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
           addActionConfig(actionConfig);

         // ADMINISTRACAO PELOS COLABORADORES - solicitacao de roteiro de emulador
         actionConfig = new ActionConfig("/admin", AdminEmuladorAction.class,"solicitacaoRoteiroEmulador")
   	     .addConsequence(AdminEmuladorAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoRoteiroEmulador.jsp"))
   	     .addConsequence(AdminEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminEmuladorAction.class,"solicitacaoRoteiroEmuladorDetalhe")
   	     .addConsequence(AdminEmuladorAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoRoteiroEmuladorDetalhe.jsp"))
   	     .addConsequence(AdminEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminEmuladorAction.class,"alteraSolicitacaoEmulador")
   	     .addConsequence(AdminEmuladorAction.ROTEIRO,new Forward(URL_DO_CRUD+"solicitacaoRoteiroEmulador.jsp"))
   	     .addConsequence(AdminEmuladorAction.TEXTO,new Forward(URL_DO_CRUD+"solicitacaoTextoEmulador.jsp"))
   	     .addConsequence(AdminEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
          addActionConfig(actionConfig);

         // ADMINISTRACAO PELOS COLABORADORES - solicitacao de texto de emulador (dica,video,download)

         actionConfig = new ActionConfig("/admin", AdminEmuladorAction.class,"solicitacaoTextoEmulador")
  	     .addConsequence(AdminEmuladorAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoTextoEmulador.jsp"))
   	     .addConsequence(AdminEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
         addActionConfig(actionConfig);

         actionConfig = new ActionConfig("/admin", AdminEmuladorAction.class,"solicitacaoTextoEmuladorDetalhe")
   	     .addConsequence(AdminEmuladorAction.SUCCESS,new Forward(URL_DO_CRUD+"solicitacaoTextoEmuladorDetalhe.jsp"))
   	     .addConsequence(AdminEmuladorAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP));
         addActionConfig(actionConfig);

         // busca de imagens
// 		 actionConfig = new ActionConfig("buscaImagem", BuscaImagemAction.class)
//	     .addConsequence(BuscaImagemAction.ERROR, new Forward(PAGINA_DE_ERRO_JSP))
//	     .addConsequence(BuscaImagemAction.TIFF, new StreamConsequence(BuscaImagemAction.TIFF))
//	     .addConsequence(BuscaImagemAction.PNG, new StreamConsequence(BuscaImagemAction.PNG))
//	     .addConsequence(BuscaImagemAction.GIF, new StreamConsequence(BuscaImagemAction.GIF))
//	     .addConsequence(BuscaImagemAction.JPG, new StreamConsequence(BuscaImagemAction.JPG));
//         addActionConfig(actionConfig);

	}

	/**
	 * formatadores
	 */
    public void loadFormatters() {
        FormatterManager.addFormatter("dateFormatter", new DateFormatter("dd/MM/yyyy"));
        LocaleManager.setDefaultDateMask("dd-MM-yyyy");
    }


	/**
	 * define os idiomas da aplicacao
	 */
	public void loadLocales() {

		LocaleManager.setMaster("i18n/mensagensDoSistema");
		LocaleManager.useMasterI18N(true);

		LocaleManager.add(new Locale("pt", "BR"));
		LocaleManager.add(new Locale("en", "US"));
	}

}
