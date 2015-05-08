package br.com.linuxgames.controller.crud;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.mentawai.core.ActionException;
import org.mentawai.core.BaseAction;
import org.mentawai.filter.RedirectAfterLogin;
import org.mentawai.list.ListManager;


import br.com.linuxgames.controller.cache.CacheManager;
import br.com.linuxgames.controller.datalist.DBListDataExtended;
import br.com.linuxgames.model.dao.EmuladorDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.EmuladorVO;
import br.com.linuxgames.model.vo.FabricanteVO;
import br.com.linuxgames.model.vo.LicencaVO;
import br.com.linuxgames.util.Constantes;
import br.com.linuxgames.util.UpdateLogHelper;


public class EmuladorAction extends BaseAction implements RedirectAfterLogin  {

    EmuladorDAO dao = EmuladorDAO.getInstance();
	Logger logger = Logger.getLogger(this.getClass().getName());

	private EmuladorVO vo;
	private Collection<?> listaVO;
	private int action;

	public boolean shouldRedirect(String innerAction) {
	     return true;
	}


	/**
	 * Retorna o combo com todos os registros
	 * @return
	 */
	private Collection<?> listaDeEmuladores() {
    	Collection<?> collection = null;
    	try {
    		collection = dao.buscaTodos();
	    } catch (DAOException e) {
		  logger.error("erro no cadastro!",e);
	    }
	    return collection;
	}

	private void atualistaCombo() {
    	try {
            DBListDataExtended emuladores = new DBListDataExtended("EMULADORES","emulador.buscaTudo", "id", "nome");
            ListManager.addList(emuladores);
	    } catch (Exception e) {
		  logger.error("erro no cadastro!",e);
	    }
	}

	/**
	 * action inicial
	 */
    public String execute() throws ActionException {
    	output.setValue("emuladores", listaDeEmuladores());
		output.setValue("votos", new Integer(1));
		output.setValue("notaGeral", new Double(5));
    	return SUCCESS;
    }

    /**
     * action que busca um registro
     * @return
     * @throws ActionException
     */
    public String buscaUm() throws ActionException {

    	output.setValue("actionCRUD", String.valueOf(Constantes.ACTION_BUSCA));

    	vo.setId( input.getInt("id"));

    	try {
    		EmuladorVO storedVO = (EmuladorVO) dao.buscaUm(vo);
    		logger.info("vo lido ID="+storedVO.getId()+" Nome="+storedVO.getNome());
    		// setando os valores lidos
    		output.setValue("id",  String.valueOf(storedVO.getId()));
    		output.setValue("tipo",  String.valueOf(storedVO.getTipo()));
    		output.setValue("nome", storedVO.getNome());
    		output.setValue("licenca_id",  String.valueOf(storedVO.getLicenca().getId()));
    		output.setValue("jogaEmRede", storedVO.getJogaEmRede());
    		output.setValue("temSom", storedVO.getTemSom());
    		output.setValue("consoleOuX11", storedVO.getConsoleOuX11());
    		output.setValue("siteOficial", storedVO.getSiteOficial());
    		output.setValue("descricao", storedVO.getDescricao());
    		output.setValue("votos", storedVO.getVotos());
    		output.setValue("notaGeral", storedVO.getNotaGeral());
    		output.setValue("fabricante_id",  String.valueOf(storedVO.getFabricante().getId()));
    	    boolean destaque = storedVO.isDestaque();
    	    if (destaque)
    	     output.setValue("destaque", 1);
    	    else
    	     output.setValue("destaque", 0);
    	    output.setValue("hits", storedVO.getHits());
	    } catch (DAOException e) {
		  logger.error("erro na busca!",e);
	    }
    	output.setValue("emuladores", listaDeEmuladores());
        return SUCCESS;
    }

    /**
     * action que adiciona um registro
     * @return
     * @throws Exception
     */
    public String novo() throws Exception {

     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricante(fabricanteVO);

     	String licencaID= input.getString("licenca_id");
     	LicencaVO licencaVO = new LicencaVO();
     	licencaVO.setId(Integer.parseInt(licencaID));
     	vo.setLicenca(licencaVO);
     	vo.setJogaEmRede(input.getInt("jogaEmRede"));
     	vo.setTemSom(input.getInt("temSom"));
     	vo.setTipo(input.getInt("tipo"));
     	vo.setConsoleOuX11(input.getInt("consoleOuX11"));
     	vo.setVotos(input.getInt("votos"));
     	vo.setNotaGeral(Double.parseDouble(input.getString("notaGeral")));
	    int destaque = input.getInt("destaque");
	    if (destaque==1) vo.setDestaque(true);
	    else vo.setDestaque(false);
	    vo.setHits(input.getLong("hits"));

    	// chama o dao e traz o resultado
    	logger.info(" Emulador: nome="+vo.getNome());
    	try {
    		dao.adiciona(vo);
    		CacheManager.getCacheDeEmuladores().refreshNoCache();
    		UpdateLogHelper.setUpdateLog("Confira o novo emulador cadastrado:"+vo.getNome());
		} catch (DAOException e) {
			logger.error("erro no cadastro!",e);
		}

		output.setValue("emuladores", listaDeEmuladores());
		resetForm();
		atualistaCombo();
		return SUCCESS;
    }


	/**
	 *action que atualiza um registro
	 * @return
	 * @throws Exception
	 */
    public String atualiza() throws Exception {

    	// ajusta o VO
    	vo.setId( input.getInt("id"));
     	String fabricanteID= input.getString("fabricante_id");
     	FabricanteVO fabricanteVO = new FabricanteVO();
     	fabricanteVO.setId(Integer.parseInt(fabricanteID));
     	vo.setFabricante(fabricanteVO);

     	String licencaID= input.getString("licenca_id");
     	LicencaVO licencaVO = new LicencaVO();
     	licencaVO.setId(Integer.parseInt(licencaID));
     	vo.setLicenca(licencaVO);
     	vo.setJogaEmRede(input.getInt("jogaEmRede"));
     	vo.setTemSom(input.getInt("temSom"));
     	vo.setTipo(input.getInt("tipo"));
     	vo.setConsoleOuX11(input.getInt("consoleOuX11"));
     	vo.setVotos(input.getInt("votos"));
     	vo.setNotaGeral(Double.parseDouble(input.getString("notaGeral")));
	    int destaque = input.getInt("destaque");
	    if (destaque==1) vo.setDestaque(true);
	     else vo.setDestaque(false);
	    vo.setHits(input.getLong("hits"));

     	String tipoCRUD = input.getString("tipoCRUD");

    	if (tipoCRUD.equals("Alterar"))
    	{
    	 logger.info(" Atualizar Emulador: id="+vo.getId());
    	 try {
    		dao.atualiza(vo);
    		CacheManager.getCacheDeEmuladores().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro na alteracao do registro!",e);
		 }
    	}
    	else if (tipoCRUD.equals("Remover"))
    	{
    	 logger.info(" Remover Emulador: id="+vo.getId());
    	 try {
    		dao.remove(vo);
    		CacheManager.getCacheDeEmuladores().refreshNoCache();
		 } catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		 }
    	}

		output.setValue("emuladores", listaDeEmuladores());
		resetForm();
		atualistaCombo();
        return SUCCESS;
    }


    /**
     * action que remove um registro
     * @return
     * @throws Exception
     */
    public String remove() throws Exception {
        vo.setId( input.getInt("id"));
    	logger.info(" Remover Emulador: id="+vo.getId());
    	try {
    		dao.remove(vo);
    		CacheManager.getCacheDeEmuladores().refreshNoCache();
		} catch (DAOException e) {
			logger.error("erro ao remover registro!",e);
		}
    	output.setValue("emuladores", listaDeEmuladores());
    	resetForm();
    	atualistaCombo();
        return SUCCESS;
    }

    /**
     * limpa o formulario depois de um cadastro
     */
    private void resetForm(){
		output.setValue("id","");
		output.setValue("nome","");
		output.setValue("licenca_id","");
		output.setValue("tipo","");
		output.setValue("jogaEmRede","");
		output.setValue("temSom","");
		output.setValue("consoleOuX11","");
		output.setValue("siteOficial","");
		output.setValue("descricao","");
		output.setValue("fabricante_id","");
		output.setValue("destaque","");
    	// define valores inicias para votos e nota geral
		output.setValue("votos", new Integer(1));
		output.setValue("hits", new Integer(1));
		output.setValue("notaGeral", new Double(5));
    }

    // getters e setters
	public EmuladorVO getVo() {
		return vo;
	}

	public void setVo(EmuladorVO vo) {
		this.vo = vo;
	}

	public Collection<?> getListaVO() {
		return listaVO;
	}

	public void setListaVO(Collection<?> listaVO) {
		this.listaVO = listaVO;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}