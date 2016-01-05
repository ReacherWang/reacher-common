/**
 * 
 */
package org.reacher.common.filter;

/**
 * @author reacher
 *
 */
public enum RURIFilterRules {
	
	START, MIDDLE, END, NOMAL;
	
	public static String getRegex(RURIFilterUri filterUri) {
		String uri = filterUri.getUri();
		switch(filterUri.getRule()) {
		case START:
			uri = "^" + uri + ".+";
			break;
		case MIDDLE:
			uri = ".+" + uri + ".+";
		case END:
			uri = ".+" + uri + "$";
			break;
		case NOMAL:
			break;
		default:
			break;
		}
		return uri;
	}
	
}
