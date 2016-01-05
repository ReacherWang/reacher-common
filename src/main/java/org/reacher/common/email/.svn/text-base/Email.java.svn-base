/**
 * 
 */
package org.reacher.common.email;

import java.io.File;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author reacher
 *
 */
public class Email {

	private InternetAddress from;

	private List<InternetAddress> to;

	private List<InternetAddress> cc;

	private List<InternetAddress> bcc;

	private String subject;

	private String content;

	private EmailFormat format;

	private List<File> attachments;
	
	public Email() {
		this.format = EmailFormat.TEXT;
	}
	
	public Email(EmailFormat format) {
		this.format = format;
	}

	public void setFrom(String from) throws AddressException {
		this.from = new InternetAddress(from);
	}

	public void setTo(String to) throws AddressException {
		this.to = Utils.asList(InternetAddress.parse(to));
	}

	public void addTo(String to) throws AddressException {
		Utils.add(this.to, InternetAddress.parse(to));
	}

	public void setCc(String cc) throws AddressException {
		this.cc = Utils.asList(InternetAddress.parse(cc));
	}

	public void addCc(String cc) throws AddressException {
		Utils.add(this.cc, InternetAddress.parse(cc));
	}

	public void setBcc(String bcc) throws AddressException {
		this.bcc = Utils.asList(InternetAddress.parse(bcc));
	}

	public void addBcc(String bcc) throws AddressException {
		Utils.add(this.bcc, InternetAddress.parse(bcc));
	}

	public void addAttachment(File file) {
		attachments.add(file);
	}

	public InternetAddress getFrom() {
		return from;
	}

	public void setFrom(InternetAddress from) {
		this.from = from;
	}

	public List<InternetAddress> getTo() {
		return to;
	}

	public void setTo(List<InternetAddress> to) {
		this.to = to;
	}

	public List<InternetAddress> getCc() {
		return cc;
	}

	public void setCc(List<InternetAddress> cc) {
		this.cc = cc;
	}

	public List<InternetAddress> getBcc() {
		return bcc;
	}

	public void setBcc(List<InternetAddress> bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public EmailFormat getFormat() {
		return format;
	}

	public void setFormat(EmailFormat format) {
		this.format = format;
	}

	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}
}
