package enviando.email;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class AppTest {

	Dotenv dotenv = Dotenv.load();

	private String userMail = dotenv.get("USER_MAIL");
	private String passMail = dotenv.get("PASS_MAIL");

	@Test
	public void testeEmail() {

		try {
			Properties props = new Properties();
			props.put("mail.smtp.ssl.trust", "*");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp-mail.outlook.com");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.socketFactory.port", "587");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

			Session session = Session.getInstance(props, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(userMail, passMail);
				}
			});

			Address[] toUser = InternetAddress
					.parse("leonardo.costa.santos.si@gmail.com, leonardosistemas54@gmail.com, leocosta1992@live.com");

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(userMail, "Leonardo Costa Santos")); // Quem está enviando
			message.setRecipients(Message.RecipientType.TO, toUser); // Email de destino
			message.setSubject("Chegou o email enviado com Java"); // Assunto do email
			message.setText("Olá programador! Você acaba de receber um e-mail enviado através do Java"); // Corpo do
																											// email
			Transport.send(message);

			System.out.println("Emails enviados");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}