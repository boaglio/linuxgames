package br.com.linuxgames.util;

/**
 * Convertsor de  String <<-->>  Hexa .
 */

public class StringToHexHelper {

	public static String stringToHex(String str) {
		char[] chars = str.toCharArray();
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(Integer.toHexString((int) chars[i]));
		}
		return strBuffer.toString();
	}

	public static String hexToString(String txtInHex)  {
		byte[] txtInByte = new byte[txtInHex.length() / 2];
		try {
		int j = 0;
		for (int i = 0; i < txtInHex.length(); i += 2) {
			txtInByte[j++] = Byte.parseByte(txtInHex.substring(i, i + 2), 16);
		}
		}
		catch (StringIndexOutOfBoundsException e) {
			return null;
		}
		return new String(txtInByte);
	}

	public static void main(String[] args) {
		System.out.println(stringToHex("123456"));
	}
}