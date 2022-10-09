package enviando.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import io.github.cdimascio.dotenv.Dotenv;

public class ObjetoEnviaEmail {

	Dotenv dotenv = Dotenv.load();

	private String userMail = dotenv.get("USER_MAIL");
	private String passMail = dotenv.get("PASS_MAIL");
	
	private String listaDestinatarios;
	private String nomeRemetente;
	private String assuntoEmail;
	private String textoEmail;
	
	public ObjetoEnviaEmail(String listaDestinatarios, String nomeRemetente, String assuntoEmail, String textoEmail) {
		this.listaDestinatarios = listaDestinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.textoEmail = textoEmail;
	}

	public void enviarEmail(boolean envioHtml) throws Exception {
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
				.parse(listaDestinatarios);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(userMail, nomeRemetente)); 
		message.setRecipients(Message.RecipientType.TO, toUser); 
		message.setSubject(assuntoEmail); 
		
		if(envioHtml) {
			message.setContent(textoEmail, "text/html; charset=utf-8");
		} else {
			message.setText(textoEmail);
		}	 
																										
		Transport.send(message);

	}
	
	/**
	 * Esse método simula o PDF ou qualquer arquivo esse possa ser enviado por anexo no email.
	 * Você pode pegar o arquivo no seu banco de dados base64, byte[], Stream de Arquivos.
	 * Pode estar e um banco de dados, ou em uma pasta.
	 * */
	private FileInputStream simuladorDePDF() throws Exception {
		Document document = new Document();
		File file = new File("fileanexo.pdf");
		file.createNewFile();
		PdfWriter.getInstance(document, new FileOutputStream(file));
		document.open();
		document.add(new Paragraph("Conteudo do PDF anexo com Java Mail, esse texto é do PDF."));
		document.close();
		
		return new FileInputStream(file);
	}
}
