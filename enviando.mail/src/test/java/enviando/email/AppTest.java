package enviando.email;

import org.junit.Test;

import enviando.mail.ObjetoEnviaEmail;

public class AppTest {

	@Test
	public void testeEmail() {
		
		StringBuilder stringBuilderTextoEmail = new StringBuilder();
		
		stringBuilderTextoEmail.append("Olá, <br/> <br/>");
		stringBuilderTextoEmail.append("Você está recebendo o acesso ao curso de Java <br/><br/>");
		stringBuilderTextoEmail.append("Para ter acesso clique no botão abaixo. <br/><br/>");
		
		stringBuilderTextoEmail.append("<b>Login:</b> email.com.br<br/>");
		stringBuilderTextoEmail.append("<b>Senha:</> 123<br/><br/>");
		
		stringBuilderTextoEmail.append("<a target=\"_blank\" href=\"https://www.jdevtreinamento.com.br\" style=\"color:#2525a7; padding: 14px 25px; text-align: center; text-decoration:none; border-radius: 30px; font-size: 20px; font-family: courier; border: 3px solid green; display: inline-block; background-color: #99DA39\">Acessar Portal do Aluno</a><br/><br/>");
		
		stringBuilderTextoEmail.append("<span style=\"font-size: 8px\">Ass.: Leonardo Costa Santos</span>");
		try {
			ObjetoEnviaEmail obEmail = new ObjetoEnviaEmail(
					"leonardosistemas54@gmail.com, leonardo.costa.santos.si@gmail.com", "Leonardo Costa Santos",
					"Teste JavaMail", stringBuilderTextoEmail.toString());

			obEmail.enviarEmail(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}