 
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
	 	  
	  str3=replace(text,"