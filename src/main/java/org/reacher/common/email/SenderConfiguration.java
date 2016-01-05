/**
 * 
 */
package org.reacher.common.email;

import java.util.Properties;

/**
 * @author reacher
 *
 */
public class SenderConfiguration {

	/**
	 * SMTP server key name
	 */
	private static final String MAIL_SMTP_HOST = "mail.smtp.host";

	/**
	 * SMTP server's port key name
	 */
	private static final String MAIL_SMTP_PORT = "mail.smtp.port";

	/**
	 * Authenticate user key name
	 */
	private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";

	private String host;
	private int port = 25;
	private boolean authentication = true;

	public SenderConfiguration(String host) {
		this.host = host;
	}

	public SenderConfiguration(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public SenderConfiguration(String host, int port, boolean authentication) {
		this.host = host;
		this.port = port;
		this.authentication = authentication;
	}

	/**
	 * Get properties
	 */
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.put(MAIL_SMTP_HOST, host);
		properties.put(MAIL_SMTP_PORT, port);
		properties.put(MAIL_SMTP_AUTH, authentication);
		return properties;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public boolean getAuthentication() {
		return authentication;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setAuthentication(boolean authentication) {
		this.authentication = authentication;
	}
}
