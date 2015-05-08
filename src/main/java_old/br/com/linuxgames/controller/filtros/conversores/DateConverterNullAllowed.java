
package br.com.linuxgames.controller.filtros.conversores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.mentawai.converter.ConversionException;
import org.mentawai.converter.LocaleConverter;
import org.mentawai.i18n.LocaleManager;

/**
 * Converts a <i>java.lang.String</i> to a <i>java.util.Date</i> using the user locale.
 *
 * @author Sergio Oliveira
 */
public class DateConverterNullAllowed extends LocaleConverter {

	private static final int STYLE = DateFormat.SHORT;

	private int style = STYLE;

	private boolean useStyle = false;

	private SimpleDateFormat sdf = null;

	private String  dateFormat="dd-MM-yyyy";

    /**
     * Creates an converter using the default DateFormat style (SHORT).
     */
	public DateConverterNullAllowed() { }

    /**
     * Creates an converter using the given DateFormat style.
     *
     * @param style The DateFormat style. (Ex: DateFormat.SHORT, DateFormat.FULL, etc.)
     */
	public DateConverterNullAllowed(int style) {
		this.style = style;
		useStyle = true;
	}

	public DateConverterNullAllowed(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}

	public DateConverterNullAllowed(String pattern) {
		this(new SimpleDateFormat(pattern));
	}

	public Object convert(Object value, Locale loc) throws ConversionException {

	  if (value==null) return "";

	  if (value instanceof String) {

		  String s = ((String) value).trim();

		  if (s.equals("")) return null;

		  if (this.sdf != null) {

			  try {

				  return this.sdf.parse(s);

			  } catch(ParseException e) {

				  throw new ConversionException(e);
			  }


		  } else if (!useStyle && LocaleManager.getDateMask(loc) != null) {

			  SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

			  try {

				  return sdf.parse(s);

			  } catch(ParseException e) {

				  return new ConversionException(e);
			  }

		  } else {

			DateFormat df = DateFormat.getDateInstance(style, loc);
			df.setLenient(false);
			try {
				return df.parse(s);
			} catch(ParseException e) {
				throw new ConversionException(e);
			}

		  }

		} else {
			throw new ConversionException("DateConverter can only parse strings: " + value.toString());
		}
	}

	@Override
	protected boolean allowNull() {
		return true;
	}
}

