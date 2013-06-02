package br.com.linuxgames.controller.filtros.conversores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.mentawai.converter.ConversionException;
import org.mentawai.converter.Converter;
import org.mentawai.converter.LocaleConverter;

public class MySQLDateConverter extends LocaleConverter implements Converter {

	private String MYSQL_MASK="yyyy-MM-dd";

	/**
	 * construtor sem nada (usa mascara padrao)
	 */
	MySQLDateConverter(){
	}

	/**
	 * construtor que usa mascara especificada
	 *
	 */
	MySQLDateConverter(String mask){
		this.MYSQL_MASK=mask;
	}

	/**
	 * conversor do objeto na mascara especificada
	 */
	public Object convert(Object value, Locale loc) throws ConversionException {

		if (value instanceof String) {
			String s = (String) value;
			if (s.length()==0) return null;
			SimpleDateFormat sdf = new SimpleDateFormat(MYSQL_MASK,loc);
			sdf.setLenient(false);
			try {
				return sdf.parse(s);
			} catch(ParseException e) {
				throw new ConversionException(e);
			}
		} else {
			throw new ConversionException("MySQLDateConverter can only parse strings: " + value.toString());
		}
	}

}
