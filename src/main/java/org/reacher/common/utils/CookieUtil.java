/**
 * 
 */
package org.reacher.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author reacher
 * 
 */
public final class CookieUtil {

	private static final String ROOT_URI = "/";

	private CookieUtil() {
	}

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		addCookie(response, name, value, maxAge, ROOT_URI);
	}

	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String uri) {
		if (response == null) {
			throw new IllegalArgumentException("response cannot be null");
		}
		Cookie cookie = new Cookie(name, value);
		cookie.setPath(uri);
		if (maxAge > 0) {
			cookie.setMaxAge(maxAge);
		}
		response.addCookie(cookie);
	}

	public static Cookie getCookieByName(HttpServletRequest request, String name) {
		return getCookieByName(request, name, false);
	}

	public static Cookie getCookieByName(HttpServletRequest request, String name, boolean ignoreCase) {
		if (request == null) {
			throw new IllegalArgumentException("request cannot be null");
		}
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ignoreCase ? cookie.getName().equalsIgnoreCase(name) : cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	public static void removeCookie(HttpServletResponse response, String name) {
		removeCookie(response, name, ROOT_URI);
	}

	public static void removeCookie(HttpServletResponse response, String name, String uri) {
		if (response == null) {
			throw new IllegalArgumentException("response cannot be null");
		}
		if (name == null) {
			throw new IllegalArgumentException("name cannot be null");
		}
		Cookie cookie = new Cookie(name, null);
		cookie.setPath(uri);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
