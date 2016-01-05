/**
 * 
 */
package org.reacher.common.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author reacher
 *
 */
public class EmailAuthenticator extends Authenticator {

	private String username;
	private String password;

	public EmailAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
