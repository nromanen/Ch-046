package ua.cv.tim.utils;

import com.sun.mail.smtp.SMTPTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.cv.tim.configuration.EmailConfiguration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Service("sendMail")
public class SendMail {
	
		
	@Autowired
	private EmailConfiguration emailConfig;

    public void send(String recipientEmail, String title, String message) throws MessagingException {

    	Properties properties = emailConfig.getEmailProperties();

    	 String sender = properties.getProperty("senderEmail");
    	 String password = properties.getProperty("EmailPassword");

        Session session = Session.getInstance(properties, null);

        final MimeMessage msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(sender));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail, false));
		msg.setSubject(title);
		msg.setText(message, "utf-8");
		msg.setSentDate(new Date());

		SMTPTransport t = (SMTPTransport) session.getTransport("smtps");

		t.connect("smtp.gmail.com", sender, password);
		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}
}


