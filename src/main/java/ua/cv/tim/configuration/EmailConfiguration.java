package ua.cv.tim.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = { "classpath:sendMail.properties" })
public class EmailConfiguration {

	@Autowired
	private Environment environment;

	@Bean
	public Properties getEmailProperties() {

		Properties props = System.getProperties();
		props.setProperty("mail.smtps.host", environment.getRequiredProperty("mail.smtps.host"));
		props.setProperty("mail.smtp.socketFactory.class",
				environment.getRequiredProperty("mail.smtp.socketFactory.class"));
		props.setProperty("mail.smtp.socketFactory.fallback",
				environment.getRequiredProperty("mail.smtp.socketFactory.fallback"));
		props.setProperty("mail.smtp.port", environment.getRequiredProperty("mail.smtp.port"));
		props.setProperty("mail.smtp.socketFactory.port",
				environment.getRequiredProperty("mail.smtp.socketFactory.port"));
		props.setProperty("mail.smtps.auth", environment.getRequiredProperty("mail.smtps.auth"));

		props.setProperty("senderEmail", environment.getRequiredProperty("senderEmail"));
		props.setProperty("EmailPassword", environment.getRequiredProperty("EmailPassword"));

		props.put("mail.smtps.quitwait", environment.getRequiredProperty("mail.smtps.quitwait"));

		return props;
	}

}
