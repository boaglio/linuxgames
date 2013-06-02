package br.com.linuxgames.util;

import org.apache.log4j.Logger;

import br.com.linuxgames.model.dao.UsuariosLGDAO;
import br.com.linuxgames.model.dao.core.DAOException;
import br.com.linuxgames.model.vo.DistroVO;
import br.com.linuxgames.model.vo.Usuario;

public class UsuariosHelper {


	private static Logger logger = Logger.getLogger(UsuariosHelper.class);

	public static void verificaSeExisteUsuario(int idUsuario) {

		UsuariosLGDAO dao = UsuariosLGDAO.getInstance();
		Usuario usuario = new Usuario(idUsuario);

		boolean temUsuarioCadastrado = false;

		try {

			Object obj = dao.buscaUm(usuario);

			if (obj!=null && obj instanceof Usuario) {
				temUsuarioCadastrado = true;
				usuario = (Usuario) obj;
			}

		} catch (DAOException e) {
			logger.error("ERRO ao buscar",e);
		}

		if (!temUsuarioCadastrado) {
			usuario.setDistro(new DistroVO());
			usuario.setReceberNewsletter(Constantes.OPCAO_NAO);
			try {
				dao.adiciona(usuario);
			} catch (DAOException e) {
				logger.error("ERRO ao gravar usuario "+idUsuario,e);
			}
		}

		logger.info(" idUsuario="+idUsuario+" temUsuarioCadastrado="+temUsuarioCadastrado );

	}

}
