/**
 * 
 */
package org.reacher.common.pinyin;

/**
 * @author reacher
 *
 */
public final class PinyinUtil {

	public static String firstToUpperCase(String str) {
		if(!str.matches("[a-z]+")){
			return str; 
		}
		return str.replaceFirst("[a-z]", String.valueOf((char)(str.charAt(0) - 32)));
	}
	
	public static String firstToLowerCase(String str) {
		if(!str.matches("[A-Z]+")){
			return str; 
		}
		return str.replaceFirst("[A-Z]", String.valueOf((char)(str.charAt(0) + 32)));
	}
	
}
