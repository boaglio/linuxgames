package br.com.linuxgames.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class JDBCHelper {

	public static final boolean haveColumn(String name,ResultSet rset) {
		ResultSetMetaData meta;
		String columnName = name.toLowerCase();
		try {
			meta = rset.getMetaData();
			int numCol = meta.getColumnCount();

			for (int i = 1; i < numCol+1; i++)
			{
				if(meta.getColumnName(i).toLowerCase().equals(columnName))
				{return true;}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
}
