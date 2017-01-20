package ua.cv.tim.utils;

import com.sun.mail.smtp.SMTPTransport;

import ua.cv.tim.configuration.EmailConfiguration;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sendMail")
public class SendMail {
	
		
	@Autowired
	private EmailConfiguration emailConfig;

	 /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    public void send( String recipientEmail, String title, String message) throws AddressException, MessagingException {
    	send(recipientEmail, "", title, message);
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param username GMail username
     * @param password GMail password
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param message message to be sent
     * @throws AddressException if the email address parse failed
     * @throws MessagingException if the connection is dead or not in the connected state or if the message is not a MimeMessage
     */
    private void send(String recipientEmail, String ccEmail, String title, String message) throws AddressException, MessagingException {

    	Properties properties = emailConfig.getEmailProperties();

    	 String sender = properties.getProperty("senderEmail");
    	 String password = properties.getProperty("EmailPassword");
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

        Session session = Session.getInstance(properties, null);

        // -- Create a new message --
        final MimeMessage msg = new MimeMessage(session);

		/*
		 * If set to false, the QUIT command is sent and the connection is
		 * immediately closed. If set to true (the default), causes the
		 * transport to wait for the response to the QUIT command.
		 *
		 */

		msg.setFrom(new InternetAddress(sender));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));

		if (ccEmail.length() > 0) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccEmail, false));
		}

		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", sender, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}
}


