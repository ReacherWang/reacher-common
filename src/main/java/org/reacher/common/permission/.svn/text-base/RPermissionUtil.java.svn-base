/**
 * 
 */
package org.reacher.common.permission;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author reacher
 *
 */
public class RPermissionUtil {
	
	public static final int BIT = 16;
	
	public static int binaryToInt(String permissions) {
		return Integer.parseInt(permissions, 2);
	}
	
	public static String intToBinary(int value) {
		String binary = Integer.toBinaryString(value);
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < (RPermissionUtil.BIT - binary.length()); ++i) {
			buffer.append(0);
		}
		return buffer.append(binary).toString();
	}
	
	public static boolean regexBinary(String binary){
		String regex = "^[01]{" + binary.length() + "}$";
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(binary);
    	return matcher.matches();
    }
	
	public static String reverse(String source) {
		if(null == source) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		for(int i = source.length() - 1; i >= 0; --i) {
			buffer.append(source.charAt(i));
		}
		return buffer.toString();
	}
	
	public static String intercept(String source, int start, int end) {
		String temp = null;
		try {
			temp = source.substring(start, end);
		} catch(Exception e) {
			temp = null;
		}
		return temp;
	}
	
	public static String replace(String resource, String source, int start, int end) {
		if(null == resource) {
			return source;
		}
		int repeat = start / RPermissionUtil.BIT - (resource.length() - 1) / RPermissionUtil.BIT;
		StringBuffer buffer = new StringBuffer(resource);
		for(int i = 0; i < repeat; ++i) {
			buffer.append(RPermissionUtil.intToBinary(0));
		}
		buffer.replace(start, end, source);
		return buffer.toString();
	}
	
}
