package br.com.linuxgames.controller;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.core.RequestInput;
import org.mentawai.filter.AuthenticationFree;
import org.mentawai.i18n.LocaleManager;
import org.mentawai.list.ListData;
import org.mentawai.list.ListManager;
import org.mentawai.mail.EmailException;
import org.mentawai.mail.HtmlEmail;

import br.com.linuxgames.model.vo.IndexVO;
import br.com.linuxgames.util.InfoHelper;


public class SugestaoAction extends BaseAction implements AuthenticationFree  {

	Logger logger = Logger.getLogger(this.getClass());


	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	private IndexVO indexVO = new IndexVO();

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {

		logger.debug("iniciando SugestaoAction...");
		InfoHelper.setExtraInfo(this);

		return SUCCESS;
    }

    /**
     * envio de mensagem
     * @return
     * @throws ActionException
     */
    public String enviar() throws ActionException {

		logger.debug("iniciando IndexAction...");
		InfoHelper.setExtraInfo(this);
	    int tipoId = input.getInt("tipo_id");
	    String mensagem = input.getString("msg");
	    String emailOrigem = input.getString("email");

	    ListData list = ListManager.getList("sugestoes");
		String tipo = list.getValue(tipoId, LocaleManager.getDefaultLocale());

	   	try {
	   		 logger.info("enviando email de sugestao...");

	   		 // busca informacoes do client
	    	 String ip = input.getProperty("remoteAddr");
	    	 String browser =  ( (RequestInput) input).getHeader("user-agent");
	    	 String extraInfo="<hr/><B>IP</B>:"+ip+"<br/><B>Browser</b>:"+browser+"<hr/>";
	    	 String emailOrigemFormatado = "<hr><B>E-mail:</B>&nbsp;"+emailOrigem+"<hr/>";

	    	 // setando propriedades de emails
	       	 HtmlEmail email = new HtmlEmail();
	         email.addTo("boaglio+linuxgames@gmail.com", "LinuxGames");
	         email.setSubject(tipo);
	         email.setMsg(extraInfo+emailOrigemFormatado+mensagem);
	         email.send();
//	   		 LocawebEmailHelper.mandaEmail("tux@linuxgames.com.br", "fb@linuxgames.com.br",
//	   				tipo, mensagem);
	         output.setValue("emailEnviado","ok");
//		    } catch (MessagingException e) {
			 } catch (EmailException e) {
			  logger.error("erro no envio de email!",e);
			  return ERROR;
		    }
	        return SUCCESS;
    }

	public IndexVO getIndexVO() {
		return indexVO;
	}

	public void setIndexVO(IndexVO indexVO) {
		this.indexVO = indexVO;
	}

}