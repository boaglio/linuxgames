package br.com.linuxgames.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.AuthenticationFree;

import br.com.linuxgames.model.dao.EmuladoresFavoritosDAO;
import br.com.linuxgames.model.dao.JogosFavoritosDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladoresFavoritosVO;
import br.com.linuxgames.model.vo.InfoVO;
import br.com.linuxgames.model.vo.JogosFavoritosVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.InfoHelper;
import br.com.linuxgames.util.UserHelper;


public class FavoritosAction extends BaseAction implements AuthenticationFree {

	Logger logger = Logger.getLogger(this.getClass());
	private InfoVO vo;
	private int jogoId;


	public boolean bypassAuthentication(String arg0) {
		return true;
	}

	public String execute() throws Exception
	{
	 InfoHelper.setExtraInfo(this);
	 return SUCCESS;
	}


    /**
     * action que grava o jogo como favorito
     * @return
     * @throws ActionException
     */
    public String jogoFavorito() throws ActionException {

    	// opcao selecionada
    	String opcaoFavoritos = input.getString("jogoFavorito");

    	int idDoUsuarioLogado = UserHelper.mantemUsuarioNaRequisicao(this);

        setJogoId(input.getInt("id"));

    	if (idDoUsuarioLogado > 0)
    	{
    	 JogosFavoritosDAO jogosFavoritosDAO = JogosFavoritosDAO.getinstance();
    	 JogosFavoritosVO jogosFavoritosVO = new JogosFavoritosVO();
    	 jogosFavoritosVO.getJogo().setId(getJogoId());
    	 jogosFavoritosVO.getUsuario().setId(idDoUsuarioLogado);
	 	 try {
    	      if (opcaoFavoritos.equals(Constantes.OPCAO_SIM))
				jogosFavoritosDAO.adiciona(jogosFavoritosVO);
    		  else
  		    	jogosFavoritosDAO.remove(jogosFavoritosVO);
			  }
	 	 catch (DAOException e) {
			   logger.error("Erro ao gravar o jogo favorito",e);
			}
    	}

    	return SUCCESS;
    }


    /**
     * action que grava o jogo como favorito
     * @return
     * @throws ActionException
     */
    public String emuladorFavorito() throws ActionException {

    	// opcao selecionada
    	String opcaoFavoritos = input.getString("emuladorFavorito");

        int idDoUsuarioLogado = UserHelper.mantemUsuarioNaRequisicao(this);

    	if (idDoUsuarioLogado > 0)
    	{
    	 EmuladoresFavoritosDAO emuladoresFavoritosDAO = EmuladoresFavoritosDAO.getinstance();
    	 EmuladoresFavoritosVO emuFavoritosVO = new EmuladoresFavoritosVO();
    	 emuFavoritosVO.getEmulador().setId(input.getInt("id"));
    	 emuFavoritosVO.getUsuario().setId(idDoUsuarioLogado);
	 	 try {
    	      if (opcaoFavoritos.equals(Constantes.OPCAO_SIM))
    	    	  emuladoresFavoritosDAO.adiciona(emuFavoritosVO);
    		  else
    			  emuladoresFavoritosDAO.remove(emuFavoritosVO);
			  }
	 	 catch (DAOException e) {
			   logger.error("Erro ao gravar o emulador favorito",e);
			}
    	}

    	return SUCCESS;
    }

    /**
     * action que lista todos os favoritos
     * @return
     * @throws ActionException
     */
    public String lista() throws ActionException {
    	InfoHelper.setExtraInfo(this);

        int idDoUsuarioLogado = UserHelper.mantemUsuarioNaRequisicao(this);

        if (UserHelper.usuarioEstaLogado(this))
         {
          Collection<JogosFavoritosVO> jogosFavoritos = new ArrayList<JogosFavoritosVO>();
          Collection<EmuladoresFavoritosVO> emuladoresFavoritos = new ArrayList<EmuladoresFavoritosVO>();
      	  JogosFavoritosDAO jogosFavoritosDAO = JogosFavoritosDAO.getinstance();
      	  EmuladoresFavoritosDAO emuladoresFavoritosDAO = EmuladoresFavoritosDAO.getinstance();
  	 	   try {
  	 		   jogosFavoritos = jogosFavoritosDAO.buscaPorUsuario(idDoUsuarioLogado);
  	 		   output.setValue("jogosFavoritos",jogosFavoritos);
  	 		   emuladoresFavoritos = emuladoresFavoritosDAO.buscaPorUsuario(idDoUsuarioLogado);
  	 		   output.setValue("emuladoresFavoritos",emuladoresFavoritos);
  			  }
   	   	   catch (DAOException e) {
  			   logger.error("Erro ao ler favoritos",e);
  			}
         }

    	return SUCCESS;
    }

    // getters e setters
	public InfoVO getVo() {
		return vo;
	}

	public void setVo(InfoVO vo) {
		this.vo = vo;
	}

    public int getJogoId() {
		return jogoId;
	}

	public void setJogoId(int jogoId) {
		this.jogoId = jogoId;
	}

}