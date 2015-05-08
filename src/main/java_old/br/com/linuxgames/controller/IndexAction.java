package br.com.linuxgames.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.mentawai.action.BaseLoginAction;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;
import org.mentawai.rule.EmailRule;
import org.mentawai.rule.RequiredFieldRule;
import org.mentawai.rule.Rule;
import org.mentawai.validation.Validatable;
import org.mentawai.validation.Validator;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.ColaboradoresDAO;
import br.com.linuxgames.model.dao.NovidadeDAO;
import br.com.linuxgames.model.dao.TelaDeEmuladorDAO;
import br.com.linuxgames.model.dao.TelaDeJogoDAO;
import br.com.linuxgames.model.dao.UsuariosLGDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.IndexVO;
import br.com.linuxgames.model.vo.KeyValueVO;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.model.vo.TelaDeEmuladorVO;
import br.com.linuxgames.model.vo.TelaDeJogoVO;
import br.com.linuxgames.model.vo.Usuario;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.EmailHelper;
import br.com.linuxgames.util.InfoHelper;
import br.com.linuxgames.util.StringToHexHelper;
import br.com.linuxgames.util.TwitterUtil;
import br.com.linuxgames.util.UserHelper;

public class IndexAction extends BaseAction implements AuthenticationFree , Validatable {


	public static final String AJUDA = "ajuda";
	public static final String AJUDA_CONCLUIDA = "ajudaConcluida";


	Logger logger = Logger.getLogger(this.getClass());

	private IndexVO indexVO = new IndexVO();

	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	public IndexVO getIndexVO() {
		return indexVO;
	}

	public void setIndexVO(IndexVO indexVO) {
		this.indexVO = indexVO;
	}

	public String execute() throws Exception {

		InfoHelper.setExtraInfoForHome(this);
		return SUCCESS;
	}

	/**
	 * Home sweet home!
	 * @return
	 * @throws Exception
	 */
	public String home() throws Exception {

		logger.debug("iniciando IndexAction HOME...");
		InfoHelper.setExtraInfoForHome(this);

		String localeLink = input.getString("loc");
		if (localeLink!=null && localeLink.length()>0)
		 session.setAttribute(BaseLoginAction.LOCALE_KEY, localeLink);

   		indexVO = (IndexVO) output.getValue("indexVO");

		// adiciona informacao adicional: as imagens dos destaques
		int idJogo = indexVO.getJogoDoDia().getId();
		String imagemDoJogoDoDia ="";
        try {
       	    TelaDeJogoVO vo = new TelaDeJogoVO();
            vo.getJogo().setId(idJogo);
       	    TelaDeJogoDAO telaDeJogoDAO = TelaDeJogoDAO.getInstance();
       	    imagemDoJogoDoDia = telaDeJogoDAO.buscaPrimeiraTelaDoJogo(vo);
   		} catch (DAOException e) {
   			logger.error("Erro na busca de primeira tela...",e);
   		}
		indexVO.setImagemDoJogoDoDia(imagemDoJogoDoDia);

		int idEmu = indexVO.getEmuladorDoDia().getId();
		String imagemDoEmuladorDoDia = "";
        try {
       	    TelaDeEmuladorVO vo = new TelaDeEmuladorVO();
            vo.getEmulador().setId(idEmu);
       	    TelaDeEmuladorDAO telaDeEmuladorDAO = TelaDeEmuladorDAO.getInstance();
       	    imagemDoEmuladorDoDia = telaDeEmuladorDAO.buscaPrimeiraTelaDoEmulador(vo);
   		} catch (DAOException e) {
   			logger.error("Erro na busca de primeira tela...",e);
   		}
		indexVO.setImagemDoEmuladorDoDia(imagemDoEmuladorDoDia);

		output.setValue("indexVO",indexVO);

		Collection<?> top5updates = CacheManager.getCacheDeUpdates().getCache();
		output.setValue("top5updates",top5updates);

		Collection<String> topUsers = CacheManager.getCacheDeNovidades().getUsuariosMaisAtivos();
		output.setValue("topUsers",topUsers);

		return SUCCESS;

	}

	// iframe de novidades
	public String novidades() throws Exception {

		// busca os dados das noticias
		Collection<?> novidades =  CacheManager.getCacheDeNovidades().getCache();
		output.setValue("novidades",novidades);

		return SUCCESS;
	}

    /**
     * Ajuda a alterar a informacao do jogo
     * @return
     * @throws ActionException
     */
	public String ajuda() throws ActionException {

    	int idUsuario = InfoHelper.setExtraInfoForHome(this);
    	boolean usuarioQuePostouNovidadeEhAdmin = false;

    	int altera = input.getInt("upt");

    	// verifica se quem postou a novidade eh admin!
    	ColaboradoresDAO colaboradoresDAO = ColaboradoresDAO.getinstance();
    	ArrayList<KeyValueVO> colaboradores;
		try {

			usuarioQuePostouNovidadeEhAdmin = colaboradoresDAO.buscaSeUsuarioEhAdministradorAtivos(idUsuario);

		 } catch (DAOException e) {
			logger.error("DAO:buscando colaboradores...",e);
		 } catch (Exception e) {
			logger.error("buscando colaboradores...",e);
		}

    	if (altera < 0 ) {

    		//   joga na tela para informar a noticia

    		return AJUDA;

    	}

    	else

    	{

    	 // grava a solicitacao na tabela de template e envia o email avisando

    	 NovidadeDAO novidadeDAO = NovidadeDAO.getInstance();

    	 Novidade novidade = new Novidade();
      	 Colaborador usuario = new Colaborador();
      	 usuario.setId(idUsuario);
      	 usuario.setEmail(InfoHelper.getUsuarioLogado(this));

      	 novidade.setUsuario(usuario);

      	 novidade.setLink(input.getString("link"));
      	 novidade.setTexto(input.getString("texto"));
      	 novidade.setDataPublic(new Date());

    	if (usuarioQuePostouNovidadeEhAdmin) {
    		logger.info("usuario "+idUsuario+ " eh admin!");
    		novidade.setAprovado(Constantes.APROVADO);
    	} else {
    		logger.info("usuario "+idUsuario+ " NAO eh admin!");
      	    novidade.setAprovado(Constantes.REPROVADO);
    	}

     	 try {

        	 // grava o jogo na tabela de template (por padrao reprovado)
			int idNoticia = novidadeDAO.adicionaVoltandoID(novidade);

	    	// envia o email ao admin avisando
			novidade.setId(idNoticia);

			if (usuarioQuePostouNovidadeEhAdmin) {


				// atualiza cache de news
				CacheManager.getCacheDeNovidades().refreshNoCache();

				// atualiza o twitter
				TwitterUtil.postar(novidade.getTexto(),novidade.getLink());

			} else {
			   colaboradores = (ArrayList<KeyValueVO>) colaboradoresDAO.buscaColaboradores();
			   EmailHelper.mandaEmailAjudaNoticia(colaboradores, novidade);
			}

		 } catch (DAOException e) {
			logger.error("DAO:Cadastrando novidade template...",e);
		 } catch (Exception e) {
			logger.error("Cadastrando novidade template...",e);
		 }

       }

       // redireciona para a tela de agradecimentos
	   return AJUDA_CONCLUIDA;

    }


	// autentica usuario
	public String login() throws Exception {

		String usuario = input.getString("emaillg");
		String senha = input.getString("passwordlg");
		boolean error = false;


		if (usuario==null || senha==null || usuario.length()==0 || senha.length() ==0) {

			error = true;

		} else {

		 // valida usuario e senha
		 UsuariosLGDAO u = UsuariosLGDAO.getInstance();
		 Usuario usu = u.buscaPorEmail(usuario);

		 if (usu == null || usu.getEmail()==null || usu.getSenha()==null) {
			 error = true;
		 }
		 else {
		   String senhaGravada = StringToHexHelper.hexToString(usu.getSenha());
		   String usuarioGravado = usu.getEmail();

		   if (usuario.equalsIgnoreCase(usuarioGravado) &&  senha.equalsIgnoreCase(senhaGravada) ) {

			output.setValue("msg", "OK");

			UserHelper.setUserOnSession(usu,this);

			u.auditaUsuario(usu.getId(), usu.getEmail());

			error = false;
		 } else
			error = true;
		 }
		}

		if (error) {
		 output.setValue("msg", "<div style=\"color:red;\">Usuário ou senha inválidos!</div>");
		 return ERROR;
		}

		return SUCCESS;

	}

	public String logout() throws Exception {

		UserHelper.removeUserFromSession(this);

		return SUCCESS;
	}

	public String register() throws Exception {
		return SUCCESS;
	}

	// registra  usuario
	public String novo() throws Exception {

		String usuario = input.getString("emaillg");
		String senha = input.getString("passwordlg");
		String hoje = input.getString("hoje");

		// valida se eh um maldito webbot
		Calendar agora = new GregorianCalendar();
		int diaDeHoje = agora.get(Calendar.DAY_OF_MONTH);
		if (hoje == null || hoje.length()==0 || !String.valueOf(diaDeHoje).equals(hoje))
			return ERROR;


		if (usuario==null || senha==null || usuario.length()==0 || senha.length() ==0) {
			return ERROR;
		}

		UsuariosLGDAO u = UsuariosLGDAO.getInstance();

		// verificar se existe usuario
		Usuario usu = u.buscaPorEmail(usuario);
		if (usu!=null && usu.getId()>0 ) {
			addError("usuarioExistente");
			return ERROR;
		}

		// gravar usuario
		usu.setEmail(usuario);
		usu.setSenha(StringToHexHelper.stringToHex(senha));
		usu.setDataDoCadastro(new Date());
		usu.setReceberNewsletter(Constantes.OPCAO_NAO);
		u.adiciona(usu);
		addMessage("login.cadastrado");

		return SUCCESS;

	}

    public String esqueceu() throws Exception {

    	String usuario = input.getString("emaillg2");

    	UsuariosLGDAO u = UsuariosLGDAO.getInstance();

		Usuario usu = u.buscaPorEmail(usuario);
		if (usu!=null && usu.getId()>0 ) {
			EmailHelper.mandaEmailEsqueceuSenha(usu.getEmail(),usu.getSenha());
		}

		addMessage("login.esqueceuSenhaNotificado");
    	return SUCCESS;
    }


    /**
     * Validacao da action
     */
	public void prepareValidator(Validator val, String innerAction) {
        Rule required = RequiredFieldRule.getInstance();
        Rule email = EmailRule.getInstance();
		val.add("emaillg", required, "campoObrigatorio");
		val.add("emaillg", email, "emailValido");
		val.add("passwordlg", required, "campoObrigatorio");
		val.add("hoje", required, "campoObrigatorio");
	}


}
