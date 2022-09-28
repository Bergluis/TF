package servidorcomthread;

import java.util.Properties;
 
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Email {
 
        private String mailSMTPserver;
        private String mailSMTPserverPort;
        private String mailSenha;
 
        public void sendMail(String from, String to, String subject,String message) {
 
                Properties props = new Properties();
 
                mailSMTPserver = "smtp.googlemail.com";
                mailSMTPserverPort = "465";
                mailSenha = "index12345";
 
                props.put("mail.transport.protocol", "smtp"); // define protocolo de envio como SMTP
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", mailSMTPserver); // server SMTP do GMAIL
                props.put("mail.smtp.auth", "true"); // ativa autenticacao
                props.put("mail.smtp.user", from); // usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
                //props.put("mail.smtp.ehlo", false);
                props.put("mail.debug", "true");
                props.put("mail.smtp.port", mailSMTPserverPort); // porta
                props.put("mail.smtp.socketFactory.port", mailSMTPserverPort); // mesma porta para o socket
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
                // Cria um autenticador que sera usado a seguir
                SimpleAuth auth = null;
                auth = new SimpleAuth(from, mailSenha);
 
                // Session - objeto que ira realizar a conexï¿½o com o servidor
                /*
                 * Como hï¿½ necessidade de autenticaï¿½ï¿½o ï¿½ criada uma autenticacao que ï¿½
                 * responsavel por solicitar e retornar o usuï¿½rio e senha para
                 * autenticaï¿½ï¿½o
                 */
                Session session = Session.getDefaultInstance(props, auth);
                session.setDebug(true); // Habilita o LOG das aï¿½ï¿½es executadas durante o envio do email
 
                // Objeto que contï¿½m a mensagem
                Message msg = new MimeMessage(session);
 
                try {
                        // Setando o destinatï¿½rio
                        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                        // Setando a origem do email
                        msg.setFrom(new InternetAddress(from));
                        // Setando o assunto
                        msg.setSubject(subject);
                        // Setando o conteudo/corpo do email
                        msg.setContent(message, "text/html;charset=UTF-8");
                        // Setando anexo
                        // FileDataSource fds = new
                        // FileDataSource("C:\\Users\\rbrasil\\Documents\\tre.pptx");
                        // msg.setDataHandler(new DataHandler(fds));
                        // msg.setFileName(fds.getName());
                       
 
                } catch (Exception e) {
                        System.out.println(">> Erro: Completar Mensagem");
                }
 
                // Objeto encarregado de enviar os dados para o email
                Transport tr;
                try {
                        tr = session.getTransport("smtp"); // define smtp para transporte
                        /*
                         * 1 - define o servidor smtp 2 - seu nome de usuario do gmail 3 -
                         * sua senha do gmail
                         */
                        tr.connect(mailSMTPserver, from, mailSenha);
                        msg.saveChanges(); // don't forget this
                        // envio da mensagem
                        tr.sendMessage(msg, msg.getAllRecipients());
                        tr.close();
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        System.out.println(">> Erro: Envio Mensagem");
                }
 
        }
}
 
// clase que retorna uma autenticacao para ser enviada e verificada pelo
// servidor smtp
class SimpleAuth extends Authenticator {
        public String username = null;
        public String password = null;
 
        public SimpleAuth(String user, String pwd) {
                username = user;
                password = pwd;
        }
 
        protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
        }
}