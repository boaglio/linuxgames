package br.com.linuxgames.util;

import br.com.linuxgames.model.vo.RoteiroInstalacaoJogoVO;

/**
 * Responsavel por notificar via email 
 * @author Fernando Boaglio
 *
 */
public class NotificacaoManager {

	private static final String[] EMAILS_ADMIN = {"boaglio@gmail.com" };
	
	public void notificaNovoRoteiroDeJogo(RoteiroInstalacaoJogoVO vo){
	 // manda email para admin avisando
	 for (int i = 0; i < EMAILS_ADMIN.length; i++) {
		
	  }
	}
}
