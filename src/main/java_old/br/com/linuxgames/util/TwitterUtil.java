package br.com.linuxgames.util;

import org.apache.log4j.Logger;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TwitterUtil {

	private static Logger logger = Logger.getLogger(TwitterUtil.class);

	public static void postar(String mensagem,String url) {
	    Twitter twitter = new Twitter("linuxgamesbr","*****");
	    Status status;
		try {
			status = twitter.updateStatus(mensagem+" "+url);
		    logger.info("Atualizado status no twitter para [" + status.getText() + "].");
		} catch (TwitterException e) {
			logger.error("Erro do twitter",e);
		}
	}
}
