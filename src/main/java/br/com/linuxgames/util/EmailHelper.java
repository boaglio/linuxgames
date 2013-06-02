package br.com.linuxgames.util;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.mentawai.i18n.LocaleManager;
import org.mentawai.mail.HtmlEmail;
import org.mentawai.mail.Letter;
import org.mentawai.mail.VelocityLetter;

import br.com.linuxgames.model.vo.AjudaJogo;
import br.com.linuxgames.model.vo.KeyValueVO;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.model.vo.SolicitacaoVO;

public class EmailHelper {

	static Logger logger = Logger.getLogger(EmailHelper.class);

	public static void mandaEmail(String from, String to,String assunto, String mensagem)
	 throws MessagingException
	{
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        Session sessao = Session.getInstance(props, null);
        MimeMessage message = new MimeMessage(sessao);
        message.setFrom(new InternetAddress(from));
        Address toAddress = new InternetAddress(to);
        message.addRecipient(Message.RecipientType.TO, toAddress);
        message.setSubject(assunto);
        message.setText(mensagem);
        Transport.send(message);
	}


	/**
	 * Manda mensagem de solicitacao
	 * @return
	 */

	public static void mandaEmailSolicitacao(ArrayList<KeyValueVO> enderecosDeEmails,SolicitacaoVO solicitacao) throws Exception {

		  try {
			    logger.info("enviando email de solicitacao...");
	            Letter letter = new VelocityLetter("email-solicitacao.html"); // default dir is /letters
	            letter.setAttribute("greeting",DateHelper.getGreeting());
	            letter.setAttribute("nome",solicitacao.getNome());
	            letter.setAttribute("id",solicitacao.getId());
	            letter.setAttribute("tipo",solicitacao.getTipoDeSolicitacao());
	            letter.setAttribute("categoria",solicitacao.getCategoria());

	            String msg = letter.getText(LocaleManager.getDefaultLocale());

	            HtmlEmail email = new HtmlEmail();
	            // destinatarios
	            for(KeyValueVO vo:enderecosDeEmails)
	             {
	              logger.info("email de solicitacao para: "+vo.getValue()+"<"+vo.getKey()+">");
	              email.addTo(vo.getValue(),vo.getKey());
	             }
	            email.setSubject("LinuxGames.com.br - SOLICITACAO");
	            email.setHtmlMsg(msg);
	            email.send();
	            logger.info("email de solicitacao enviado!");
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	}

	/**
	 * Manda mensagem de solicitacao aprovada/reprovada
	 * @return
	 */

	public static void mandaEmailAlteracaoSolicitacao
	    (ArrayList<KeyValueVO> enderecosDeEmailsSolicitantes,
	     ArrayList<KeyValueVO> enderecosDeEmailsColaboradores,
	     SolicitacaoVO solicitacao) throws Exception {

		  try {
			    logger.info("enviando email de alteracao de solicitacao...");

			    Letter letter;

			    if (solicitacao.getTexto().isAprovado())
			     letter= new VelocityLetter("email-solicitacao-aprovada.html");
			    else
			     letter= new VelocityLetter("email-solicitacao-reprovada.html");

	            letter.setAttribute("greeting",DateHelper.getGreeting());
	            letter.setAttribute("nome",solicitacao.getNome());
	            letter.setAttribute("id",solicitacao.getId());
	            letter.setAttribute("categoria",solicitacao.getCategoria());
	            // adiciona o motivo se ele existir
	            String motivo = solicitacao.getTexto().getTexto();
	            if (motivo!=null && motivo.length()>0)
	             letter.setAttribute("motivo",HTMLHelper.convertTextToHTMLStandard(motivo));
	            String msg = letter.getText(LocaleManager.getDefaultLocale());

	            HtmlEmail email = new HtmlEmail();
	            // solicitantes
	            for(KeyValueVO vo:enderecosDeEmailsSolicitantes)
	             {
	              logger.info("email de solicitacao para: "+vo.getValue()+"<"+vo.getKey()+">");
	              email.addTo(vo.getValue(),vo.getKey());
	             }
	            // colaboradores (BCC)
	            for(KeyValueVO vo:enderecosDeEmailsColaboradores)
	             {
	              logger.info("email de colaborador para: "+vo.getValue()+"<"+vo.getKey()+">");
	              email.addBcc(vo.getValue(),vo.getKey());
	             }
	            email.setSubject("LinuxGames.com.br - SOLICITACAO");
	            email.setHtmlMsg(msg);
	            email.send();
	            logger.info("email de alteracao de solicitacao enviado!");
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	}


	/**
	 * Manda mensagem de solicitacao de alteracao/traducao de jogo!
	 * @return
	 */

	public static void mandaEmailAjudaJogo(ArrayList<KeyValueVO> enderecosDeEmails,AjudaJogo ajudaJogo) throws Exception {

		  try {
			    int insert = 0;
			    if (ajudaJogo.isNovoJogo())
			    	insert=1;
			    else
			    	insert=0;

			    logger.info("enviando email de solicitacao...");
	            Letter letter = new VelocityLetter("email-ajuda-jogo.html"); // default dir is /letters
	            letter.setAttribute("greeting",DateHelper.getGreeting());
	            letter.setAttribute("nomeDoUsuario",ajudaJogo.getUsuario().getEmail());
	            letter.setAttribute("idDoTemplate",ajudaJogo.getIdJogoTemplate());
	            letter.setAttribute("idDoJogo",ajudaJogo.getJogo().getId());
	            letter.setAttribute("nomeDoJogo",ajudaJogo.getJogo().getNome());
	            letter.setAttribute("localeDoUsuario",ajudaJogo.getLocaleDoUsuario());
	            letter.setAttribute("insert",insert);

	            String msg = letter.getText(LocaleManager.getDefaultLocale());

	            HtmlEmail email = new HtmlEmail();

	            // destinatarios
	            for(KeyValueVO vo:enderecosDeEmails)
	             {
	              logger.info("email de ajuda de jogo para: "+vo.getValue()+"<"+vo.getKey()+">");
	              email.addTo(vo.getValue(),vo.getKey());
	             }
	            email.setSubject("LinuxGames.com.br - AJUDA DE JOGO");
	            email.setHtmlMsg(msg);
	            email.send();
	            logger.info("email de ajuda de jogo enviado!");
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	}



	/**
	 * Manda mensagem de solicitacao de nova noticia!
	 * @return
	 */

	public static void mandaEmailAjudaNoticia(ArrayList<KeyValueVO> enderecosDeEmails,Novidade novidade) throws Exception {

		  try {
			    logger.info("enviando email de solicitacao de noticia ...");
	            Letter letter = new VelocityLetter("email-ajuda-noticia.html"); // default dir is /letters
	            letter.setAttribute("greeting",DateHelper.getGreeting());
	            letter.setAttribute("nomeDoUsuario",novidade.getUsuario().getEmail());
	            letter.setAttribute("texto",novidade.getTexto());
	            letter.setAttribute("link",novidade.getLink());
	            letter.setAttribute("id",novidade.getId());

	            String msg = letter.getText(LocaleManager.getDefaultLocale());

	            HtmlEmail email = new HtmlEmail();

	            // destinatarios
	            for(KeyValueVO vo:enderecosDeEmails)
	             {
	              logger.info("email de ajuda de noticia para: "+vo.getValue()+"<"+vo.getKey()+">");
	              email.addTo(vo.getValue(),vo.getKey());
	             }
	            email.setSubject("LinuxGames.com.br - AJUDA DE NOTICIA");
	            email.setHtmlMsg(msg);
	            email.send();
	            logger.info("email de ajuda de noticia enviado!");
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	}

	/**
	 * Manda mensagem de solicitacao
	 * @return
	 */

	public static void mandaEmailEsqueceuSenha(String emailUsuario,String senha) throws Exception {

		 if (emailUsuario==null) return;

		  try {
			    logger.info("enviando email de solicitacao...");
	            Letter letter = new VelocityLetter("esqueceu-senha.html");
	            letter.setAttribute("greeting",DateHelper.getGreeting());
	            letter.setAttribute("senha",StringToHexHelper.hexToString(senha));

	            String msg = letter.getText(LocaleManager.getDefaultLocale());

	            HtmlEmail email = new HtmlEmail();
	            logger.info("email de esqueceu a senha para: "+emailUsuario);
	            email.addTo(emailUsuario,emailUsuario);
	            email.setSubject("LinuxGames.com.br - Lembrete de senha");
	            email.setHtmlMsg(msg);
	            email.send();
	            logger.info("email de lembrete enviado!");
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	}


}
