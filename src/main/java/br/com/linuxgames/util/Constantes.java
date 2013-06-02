
package br.com.linuxgames.util;

/**
 * @author Fernando Boaglio
 *
 * Constantes do Sistema
 */
public class Constantes {

	// versao
	public static final String VERSAO_DO_SISTEMA = "1.2";
	public static final String DATA_DA_VERSAO_DO_SISTEMA = "14/02/09";

	// arquivos properties
	public static final String LINUXGAMES_PROPERTIES_FILE = "linuxgames-config.properties";
	public static final String LG_SQL_PROPERTIES_FILE = "sql-lg.properties";
	public static final String TOE_SQL_PROPERTIES_FILE = "sql-toe.properties";

	// conexao ao banco de dados
	public static final int USANDO_DATA_SOURCE=1;
	public static final int USANDO_JBDC_MYSQL=2;
	public static final int USANDO_JBDC_PSQL=3;

	public static final boolean USANDO_HOST_HEROKU=true;

//	public static final int TIPO_DE_CONEXAO_USADA=Constantes.USANDO_DATA_SOURCE;
	public static final int TIPO_DE_CONEXAO_USADA=Constantes.USANDO_JBDC_PSQL;

	public static String JNDI_DATASOURCE="jdbc/linuxgames";

	// tipos de action
	public static final int ACTION_BUSCA = 1;
	public static final int ACTION_BUSCA_TODOS = 2;
	public static final int ACTION_ADICIONA = 3;
	public static final int ACTION_ADICIONADO = 4;
	public static final int ACTION_ALTERA = 5;
	public static final int ACTION_ALTERADO = 6;
	public static final int ACTION_ATUALIZADO = 7;
	public static final int ACTION_REMOVIDO = 8;

    // tipos nacionais/internacionais
	public static final int NACIONAL = 1;
	public static final int INTERNACIONAL = 2;

	// Sistemas Operacionais :
	public static final int SISTEMA_OPERACIONAL_WINDOWS = 1;
	public static final int SISTEMA_OPERACIONAL_LINUX = 2;
	public static final int SISTEMA_OPERACIONAL_LINUX_E_WINDOWS = 3;

	// tipos de versao
	public static final int VERSAO_DE_JOGO = 1;
	public static final int VERSAO_DE_EMULADOR = 2;
	public static final int VERSAO_DE_BIBLIOTECA = 3;
	public static final int VERSAO_DE_DISTRIBUICAO = 4;

	// tipos de status
	public static final int APROVADO  = 1;
	public static final int REPROVADO = 2;

	// tipos de solicitacao
	public static final int DICA = 1;
	public static final int VIDEO = 2;
	public static final int DOWNLOAD = 3;
	public static final int ROTEIRO_INSTALACAO= 4;
	public static final int NOVO_JOGO= 5;

	// categoria de solicitacao
	public static final int JOGO = 1;
	public static final int EMULADOR = 2;
	public static final int NOVIDADE = 3;

	// opcoes de Sim/ Nao
	public static final String OPCAO_SIM = "1";
	public static final String OPCAO_NAO = "2";

	// idiomas suportados
	public static final int IDIOMA_PT_BR = 1;
	public static final int IDIOMA_EN_US = 2;
	public static final String IDIOMA_STR_EN_US = LocaleHelper.EN;
	public static final String IDIOMA_STR_PT_BR = LocaleHelper.PT_BR2;

	// dados indefinidos
	public static final int DADO_INDEFINIDO = 0;

}
