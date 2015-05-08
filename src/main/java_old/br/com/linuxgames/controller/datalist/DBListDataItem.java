package br.com.linuxgames.controller.datalist;

import java.util.Locale;

import org.mentawai.list.ListDataItem;

public class DBListDataItem extends ListDataItem {

    private Locale locale;

    /** Creates a new instance of DBListDataItem */
    public DBListDataItem(int id, String value, Locale locale) {
        super(id, value);
        this.locale = locale;
    }

    public Locale getLocale() { return locale; }
}
