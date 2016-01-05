/**
 * 
 */
package org.reacher.common.email;

import java.io.File;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * @author reacher
 *
 */
public final class EmailSender {
	
	private SenderConfiguration config;

	public EmailSender(SenderConfiguration config) {
		this.config = config;
	}
	
	public void send(Email email, EmailAuthenticator authenticator) throws MessagingException {
		Session session = Session.getDefaultInstance(config.getProperties(), authenticator);
		Message message = new MimeMessage(session);
		message.setFrom(email.getFrom());
		message.setRecipients(RecipientType.TO, Utils.toArray(email.getTo()));
		message.setRecipients(RecipientType.CC, Utils.toArray(email.getCc()));
		message.setRecipients(RecipientType.BCC, Utils.toArray(email.getBcc()));
		message.setSubject(email.getSubject());
		Multipart multipart = new MimeMultipart();
		BodyPart bodyPart = new MimeBodyPart();
		bodyPart.setContent(email.getContent(), email.getFormat().getContentType());
		multipart.addBodyPart(bodyPart);
		message.setContent(multipart);
		if(null != email.getAttachments()) {
			for (File file : email.getAttachments()) {
				BodyPart attachment = new MimeBodyPart();
				DataSource source = new FileDataSource(file);
				attachment.setDataHandler(new DataHandler(source));
				attachment.setFileName(file.getName());
				attachment.setHeader("Content-ID", "image");
				multipart.addBodyPart(attachment);
			}
		}
		Transport.send(message);
	}
	
}
