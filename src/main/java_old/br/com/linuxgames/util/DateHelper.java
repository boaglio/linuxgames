package br.com.linuxgames.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateHelper {

    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    public static final String DEFAULT_DATE_FORMAT_MASK = "dd-MM-yyyy";
    public static final String DEFAULT_HOUR_FORMAT = "HH:mm";
    public static final String DEFAULT_DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    public static final String DEFAULT_TIMESTAMP_FORMAT = "dd/MM/yyyy HH:mm:00.0";

	/**
	 * formata data: YYYY-MM-DD => DD-MM-YYYY
	 * @param mysqlDate
	 * @return
	 */
	public static String mysqlFormat2EuropeanFormat(String mysqlDate)
	{
	  String splitString="-";
	  String campos [] = mysqlDate.split(splitString);
	  return campos[2]+splitString+campos[1]+splitString+campos[0];
	}

    /**
     * Conforme o horario retorna Bom dia, tarde ou noite
     *
     * @return
     */
    public static String getGreeting() {
        Calendar cal = new GregorianCalendar();
        int hour = cal.get(Calendar.HOUR_OF_DAY); // 0..23
        if (hour >= 6 && hour <= 12)
            return "Bom dia";
        else if (hour > 12 && hour < 18)
            return "Boa tarde";
        return "Boa noite";
    }


    public static String getToday() {
    	Calendar calendar = GregorianCalendar.getInstance();
        SimpleDateFormat dataHoraCustomizado = new SimpleDateFormat(DEFAULT_DATE_FORMAT_MASK);
        String dateOut = dataHoraCustomizado.format(calendar.getTime());
        return dateOut;
    }

    public static Date formatStrDate(String str) {
    	 SimpleDateFormat dataHoraCustomizado = new SimpleDateFormat(DEFAULT_DATE_FORMAT_MASK);
         Date dateOut = null;
		try {
			dateOut = dataHoraCustomizado.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
         return dateOut;
    }
}
