package br.com.linuxgames.controller.datalist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mentawai.list.ListData;
import org.mentawai.list.ListDataItem;
import org.mentawai.list.ListItem;

import br.com.linuxgames.model.dao.core.AbstractDAO;
import br.com.linuxgames.model.dao.core.DAOManager;

public class DBListDataExtended  extends AbstractDAO implements ListData {

    private String query;
    private String name;
    private String idColumn;
    private String descriptionColumn;
    private int size = 0;
    private ArrayList<DBListDataItem> listLocales = new ArrayList<DBListDataItem>();
	private Map<Integer, DBListDataItem> map = new HashMap<Integer, DBListDataItem>();
	private Map<Locale, Map<Integer, DBListDataItem>> values = new HashMap<Locale, Map<Integer, DBListDataItem>>();

	private static Logger logger = Logger.getLogger(DBListDataExtended.class);

	/*
	 * Construtor que define pt_BR como locale padrao
	 */
	public DBListDataExtended(String name,String query, String idColumn, String descriptionColumn){
		DBListDataWithLocale(name,query,idColumn,descriptionColumn,new Locale("pt","BR"));
		DBListDataWithLocale(name,query,idColumn,descriptionColumn,new Locale("en","US"));
	}

	/*
	 * Rotina principal
	 */
	private void DBListDataWithLocale(String name,String query, String idColumn, String descriptionColumn,Locale locale) {
        this.query = query;
        this.name = name;
        this.idColumn = idColumn;
        this.descriptionColumn = descriptionColumn;

        logger.info("Carregando lista ["+ locale.getDisplayLanguage()+ "] = "+name);
		Statement stmt = null;
		DAOManager dao = DAOManager.getInstance();
		Connection conexao = null;
		ResultSet rs = null;
        try {
            conexao = dao.getConexao();
            stmt = super.createStatement(conexao);
            rs = stmt.executeQuery(super.getQuery(query));
            while (rs.next()) {
                DBListDataItem item = new DBListDataItem(rs.getInt(idColumn), rs.getString(descriptionColumn), locale);
                listLocales.add(item);
                map.put(new Integer(item.getId()), item);
                values.put(locale, map);
            }
            size = listLocales.size();
            logger.info("lista carregada ["+ locale.getDisplayLanguage()+ "]= "+size+" registros");
		} catch (SQLException e) {
			logger.error("[ERRO DE SQL DO DBListData]:" + e.getMessage());
		} catch (Exception e) {
			logger.error("[ERRO DO DBListData]:", e);
        } finally {
            close(rs);
            close(stmt);
            close(conexao);
        }
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public java.util.List getValues(java.util.Locale locale) {
        ArrayList<DBListDataItem> list = new ArrayList<DBListDataItem>();
        for (int i = 0; i < listLocales.size(); i++) {
//           if ( ((DBListDataItem)listLocales.get(i)).getLocale().equals(locale.toString()) ) {
        	 if ( ((DBListDataItem)listLocales.get(i)).getLocale().toString().equals(locale.toString()) ) {
                list.add(listLocales.get(i));
            }
        }
        return list;
    }

	public String getValue(int id, Locale loc) {

		Map<?, ?> m = (Map<?, ?>) values.get(loc);
		ListDataItem item = (ListDataItem) m.get(new Integer(id));
		return item.getValue();

//        ArrayList list = new ArrayList();
//        for (int i = 0; i < listLocales.size(); i++) {
//            if ( ((DBListDataItem)listLocales.get(i)).getLocale().equals(locale.toString()) ) {
//                list.add(listLocales.get(i));
//            }
//        }
//        return ((ListDataItem)list.get(param)).getValue();
    }

    public int size() {
        return size;
    }

	public String getName() {
		return name.toUpperCase();
	}

	public PreparedStatement setPreparedStatementInsert(Object object, PreparedStatement pstmt) throws SQLException {
		return null;
	}

	public PreparedStatement setPreparedStatementUpdate(Object object, PreparedStatement pstmt) throws SQLException {
		return null;
	}

	public PreparedStatement setPreparedStatementBuscaUm(Object object, PreparedStatement pstmt) throws SQLException {
		return null;
	}

	protected Object setVO(ResultSet rset) throws SQLException {
		return null;
	}

	public PreparedStatement setPreparedStatementBuscaTodosComFiltro(Object object, PreparedStatement pstmt) throws SQLException {
		return null;
	}

	public String getValue(String arg0, Locale arg1) {
		return null;
	}

	public String getValue(String arg0) {
		return null;
	}

	public List<ListItem> getValues() {
		return null;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getIdColumn() {
		return idColumn;
	}

	public void setIdColumn(String idColumn) {
		this.idColumn = idColumn;
	}

	public String getDescriptionColumn() {
		return descriptionColumn;
	}

	public void setDescriptionColumn(String descriptionColumn) {
		this.descriptionColumn = descriptionColumn;
	}

}
