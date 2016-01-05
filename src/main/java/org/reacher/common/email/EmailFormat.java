/**
 * 
 */
package org.reacher.common.email;

/**
 * @author reacher
 *
 */
public enum EmailFormat {
	TEXT("text/plain; charset=utf-8"),
	HTML("text/html; charset=utf-8");
	
	private String contentType;
	
	private EmailFormat(String contentType) {
		this.contentType = contentType;
	}
	
	public String getContentType() {
		return contentType;
	}
}
