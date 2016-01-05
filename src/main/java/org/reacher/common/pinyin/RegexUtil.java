/**
 * 
 */
package org.reacher.common.pinyin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author reacher
 *
 */
public final class RegexUtil {
	
	public static Boolean regexString(String str, String regex){
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(str);
    	Boolean result = matcher.matches();
    	return result;
    }
	
	public static boolean regexChineseCharacter(String src) {
		String regexChinese = "^[\u4e00-\u9fa5]$";
    	return RegexUtil.regexString(src, regexChinese);
	}

}
