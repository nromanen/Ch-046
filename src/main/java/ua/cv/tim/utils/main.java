package ua.cv.tim.utils;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class main {

	
	
	public static void main(String[] args) {
		SendMail send = new SendMail();
		try {
			send.send("maximenkom@ukr.net", "Mail", "message");
		} catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}

	}

}
