package br.com.linuxgames.controller.job;

import java.util.Date;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.model.dao.NovidadeAgendadaDAO;
import br.com.linuxgames.model.dao.NovidadeDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.Colaborador;
import br.com.linuxgames.model.vo.Novidade;
import br.com.linuxgames.util.TwitterUtil;

public class PostaNoticiasJob implements Job {


	private static Logger logger = Logger.getLogger(PostaNoticiasJob.class);

	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		// busca primeiro registro da tabela de noticias agendadas
		NovidadeAgendadaDAO novidadeAgendadaDAO = NovidadeAgendadaDAO.getInstance();
		Novidade novidade = null;

		try {
			novidade = novidadeAgendadaDAO.buscaUltimaNoticia();
		} catch (Exception e) {
			logger.error("erro do SQL do job de noticias",e);
			return;
		}

		// se existir alguma, posta no site
		if (novidade==null || novidade.getTexto() == null || novidade.getLink() ==null ) return;

     	Colaborador usuarioVO = new Colaborador();
     	usuarioVO.setId(1);
     	novidade.setUsuario(usuarioVO);
        novidade.setDataPublic( new Date());
        novidade.setAprovado(true);

    	try {
    		// adiciona novidade
    		NovidadeDAO dao = NovidadeDAO.getInstance();
    		dao.adiciona(novidade);

    		// atualiza o cache
    		CacheManager.getCacheDeNovidades().refreshNoCache();

    		// twitta a noticia
    		if (novidade!=null && novidade.getTexto()!=null)
   			 TwitterUtil.postar(novidade.getTexto(), novidade.getLink());

    		// remove novidade agendada
    		novidadeAgendadaDAO.remove(novidade);

		} catch (DAOException e) {
			logger.error("erro no processo de cadastro!",e);
		}

	}

}
