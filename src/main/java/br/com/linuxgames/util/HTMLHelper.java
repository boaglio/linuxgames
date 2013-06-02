
package br.com.linuxgames.util;


public class HTMLHelper {


	/*
	 * replace dos codigos HTML
	 */
	static String replace(String str, String pattern, String replace)
	 {
	 int s = 0;
	 int e = 0;

	 StringBuffer resultBuf = new StringBuffer();
	 String result;
	 while ((e = str.indexOf(pattern, s)) >= 0) {
 		   resultBuf.append(str.substring(s, e));
 		   resultBuf.append(replace);
			s = e+pattern.length();
		 }
	 resultBuf.append(str.substring(s));
	 result=resultBuf.toString();

     return result;
    }


	/*
	 * Converter o texto para HTML standard
	 */

	public static String convertTextToHTMLStandard(String text)
	{

	 String str2 = null;
	 String str3 = null;

	  str3=replace(text,"é","&eacute;");
	  str2=replace(str3,"À","&Agrave;");
	  str3=replace(str2,"à","&agrave;");
	  //str2=replace(str3,"¡","&iexcl;");
	  str2=replace(str3,"À","&Agrave;");
      str3=replace(str2,"Á","&Aacute;");
	  str2=replace(str3,"á","&aacute;");
	  str3=replace(str2,"¢","&cent;");
	  str2=replace(str3,"Â","&Acirc;");
	  str3=replace(str2,"â","&acirc;");
	  //str2=replace(str3,"£","&pound;");
	  str2=replace(str3,"Â","&Acirc;");
	  str3=replace(str2,"Ã","&Atilde;");
	  str2=replace(str3,"ã","&atilde;");
	  str3=replace(str2,"€","&curren;");
	  str2=replace(str3,"Ä","&Auml;");
	  str3=replace(str2,"ä","&auml;");
	  str2=replace(str3,"¥","&yen;");
	  str3=replace(str2,"Å","&Aring;");
	  str2=replace(str3,"å","&aring;");
	  str3=replace(str2,"Š","&brvbar;");
	  str2=replace(str3,"Æ","&AElig;");
	  str3=replace(str2,"æ","&aelig;");
	  str2=replace(str3,"§","&sect;");
	  str3=replace(str2,"Ç","&Ccedil;");
	  str2=replace(str3,"ç","&ccedil;");
	  str3=replace(str2,"š","&uml;");
	  str2=replace(str3,"È","&Egrave;");
	  str3=replace(str2,"è","&egrave;");
	  //str2=replace(str3,"©","&copy;");
	  str2=replace(str3,"È","&Egrave;");
	  str3=replace(str2,"É","&Eacute;");
	  str2=replace(str3,"é","&eacute;");
	  str3=replace(str2,"ª","&ordf;");
	  str2=replace(str3,"Ê","&Ecirc;");
	  str3=replace(str2,"ê","&ecirc;");
	  str2=replace(str3,"«","&laquo;");
	  str3=replace(str2,"Ë","&Euml;");
	  str2=replace(str3,"ë","&euml;");
	  str3=replace(str2,"¬","&not;");
	  str2=replace(str3,"Ì","&Igrave;");
	  str3=replace(str2,"ì","&igrave;");
	  str2=replace(str3,"Í","&Iacute;");
	  str3=replace(str2,"í","&iacute;");
	  str2=replace(str3,"®","&reg;");
	  str3=replace(str2,"Î","&Icirc;");
	  str2=replace(str3,"î","&icirc;");
	  str3=replace(str2,"¯","&macr;");
	  str2=replace(str3,"Ï","&Iuml;");
	  str3=replace(str2,"ï","&iuml;");
	  str2=replace(str3,"°","&deg;");
	  str3=replace(str2,"±","&plusmn;");
	  str2=replace(str3,"Ñ","&Ntilde;");
	  str3=replace(str2,"ñ","&ntilde;");
	  str2=replace(str3,"²","&sup2;");
	  str3=replace(str2,"Ò","&Ograve;");
	  str2=replace(str3,"ò","&ograve;");
	  str3=replace(str2,"³","&sup3;");
	  str2=replace(str3,"Ó","&Oacute;");
	  str3=replace(str2,"ó","&oacute;");
	  str2=replace(str3,"Ž","&acute;");
	  str3=replace(str2,"Ô","&Ocirc;");
	  str2=replace(str3,"ô","&ocirc;");
	  str3=replace(str2,"µ","&micro;");
	  str2=replace(str3,"Õ","&Otilde;");
	  str3=replace(str2,"õ","&otilde;");
	  str2=replace(str3,"¶","&para;");
	  str3=replace(str2,"Ö","&Ouml;");
	  str2=replace(str3,"ö","&ouml;");
	  str3=replace(str2,"·","&middot;");
	  str2=replace(str3,"×","&times;");
	  str3=replace(str2,"÷","&divide;");
	  str2=replace(str3,"ž","&cedil;");
	  str3=replace(str2,"Ø","&Oslash;");
	  str2=replace(str3,"ø","&oslash;");
	  str3=replace(str2,"¹","&sup1;");
	  str2=replace(str3,"Ù","&Ugrave;");
	  str3=replace(str2,"ù","&ugrave;");
	  str2=replace(str3,"º","&ordm;");
	  str3=replace(str2,"Ú","&Uacute;");
	  str2=replace(str3,"ú","&uacute;");
	  str3=replace(str2,"»","&raquo;");
	  str2=replace(str3,"Û","&Ucirc;");
	  str3=replace(str2,"û","&ucirc;");
	  str2=replace(str3,"Œ","&frac14;");
	  str3=replace(str2,"Ü","&Uuml;");
	  str2=replace(str3,"ü","&uuml;");
	  str3=replace(str2,"œ","&frac12;");
	  str2=replace(str3,"Ÿ","&frac34;");
	  str3=replace(str2,"¿","&iquest;");
	  str2=replace(str3,"ß","&szlig;");
	  str3=replace(str2,"ÿ","&yuml;");
	  return str3;
	 }

}