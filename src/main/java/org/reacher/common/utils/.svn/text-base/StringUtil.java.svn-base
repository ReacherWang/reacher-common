/**
 * 
 */
package org.reacher.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author reacher
 * 
 */
public final class StringUtil {

	public static final String EMPTY = "";
	public static final String ELLIPSIS = "...";
	public static final String DOMAIN_NAME_PREFIX = "www";
	public static final String POINT = ".";

	private static final String IP_V4_ADDRESS_REGEX = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
	private static final Pattern IP_V4_ADDRESS_PATTERN = Pattern.compile(IP_V4_ADDRESS_REGEX);
	
	private StringUtil() {
	}

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public static boolean isNotEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public static boolean hasEmpty(String... strs) {
		if (strs == null || strs.length == 0) {
			return false;
		}
		for (String str : strs) {
			if (isEmpty(str)) {
				return true;
			}
		}
		return false;
	}

	public static String value(String... strs) {
		if (ArrayUtil.isEmpty(strs)) {
			return null;
		}
		for (String str : strs) {
			if (isNotEmpty(str)) {
				return str;
			}
		}
		return null;
	}

	public static boolean equals(String str1, String str2) {
		if (hasEmpty(str1, str2)) {
			return false;
		}
		return str1.equals(str2);
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {
		if (str1 == null && str2 == null) {
			return true;
		} else if (str1 == null || str2 == null) {
			return false;
		}
		return str1.equalsIgnoreCase(str2);
	}
	
	public static boolean isValidIpv4Address(String ipv4Address) {
		if (isEmpty(ipv4Address)) {
			return false;
		}
		Matcher matcher = IP_V4_ADDRESS_PATTERN.matcher(ipv4Address);
		return matcher.matches();
	}

	public static String abbreviate(String str, int maxLength) {
		if (str == null) {
			return null;
		}
		if (maxLength <= 0) {
			return str;
		}
		if (str.length() <= maxLength) {
			return str;
		}
		return str.substring(0, maxLength) + ELLIPSIS;
	}

	public static boolean startsWithIgnoreCase(String str, String start) {
		if (isEmpty(str) || isEmpty(start)) {
			return false;
		}
		if (str.length() < start.length()) {
			return false;
		}
		for (int i = 0; i < start.length(); i++) {
			char c1 = str.charAt(i);
			char c2 = start.charAt(i);
			if (c1 == c2) {
				continue;
			}
			c1 = Character.toUpperCase(c1);
			c2 = Character.toUpperCase(c2);
			if (c1 == c2) {
				continue;
			}
			return false;
		}
		return true;
	}

	public static boolean endsWithIgnoreCase(String str, String end) {
		if (isEmpty(str) || isEmpty(end)) {
			return false;
		}
		return str.toLowerCase().endsWith(end.toLowerCase());
	}

	public static String stripStart(String str, String start) {
		if (isEmpty(str) || isEmpty(start)) {
			return str;
		}
		boolean include = str.startsWith(start);
		if (include) {
			str = str.substring(start.length());
		}
		return str;
	}

	public static String stripStartIgnoreCase(String str, String start) {
		if (isEmpty(str) || isEmpty(start)) {
			return str;
		}
		boolean include = startsWithIgnoreCase(str, start);
		if (include) {
			str = str.substring(start.length());
		}
		return str;
	}

	public static String stripEnd(String str, String end) {
		if (isEmpty(str) || isEmpty(end)) {
			return str;
		}
		boolean include = str.endsWith(end);
		if (include) {
			str = str.substring(0, str.length() - end.length());
		}
		return str;
	}

	public static String stripEndsIgnoreCase(String str, String... ends) {
		if (isEmpty(str) || ArrayUtil.isEmpty(ends)) {
			return str;
		}
		for (String end : ends) {
			boolean include = endsWithIgnoreCase(str, end);
			if (include) {
				str = str.substring(0, str.length() - end.length());
				break;
			}
		}
		return str;
	}

	public static String getSubDomainName(String domainName) {
		if (isEmpty(domainName)) {
			return null;
		}
		if (isValidIpv4Address(domainName)) {
			return null;
		}
		// Strip the prefix www.
		domainName = stripStartIgnoreCase(domainName, DOMAIN_NAME_PREFIX + POINT);
		String[] levels = domainName.split("\\.");
		if (levels.length <= 2) {
			return null;
		}
		return levels[0];
	}
	
}
