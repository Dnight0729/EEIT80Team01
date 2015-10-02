package member.changedata;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
 public static boolean sendemail(String email, String url) {
  String host = "smtp.gmail.com";
  int port = 587;
  final String username = "eeit80team01@gmail.com";
  final String password = "EEit80123456";//email password

  Properties props = new Properties();
  props.put("mail.smtp.host", host);
  props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
  props.put("mail.smtp.auth", "true");
  props.put("mail.smtp.starttls.enable", "true");
  props.put("mail.smtp.port", port);
  Session session = Session.getInstance(props, new Authenticator() {
   protected PasswordAuthentication getPasswordAuthentication() {
    return new PasswordAuthentication(username, password);
   }
  });

  try {
	  MimeMessage  message = new MimeMessage(session); 
	  message.setFrom(new InternetAddress("eeit80team01@gmail.com"));
	  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));  
	  message.setSubject("密碼重設信件","UTF-8");   
	  Multipart mp = new MimeMultipart();
	  MimeBodyPart part1 = new MimeBodyPart();
	  StringBuilder sb = new StringBuilder();
	  sb.append("請點以下連結重新設定密碼<br><a href='");
	  sb.append(url);
	  sb.append("'>請點此連結</a>");
	  part1.setText(sb.toString());
	  part1.setContent(sb.toString(),"text/html; charset=utf-8");
	  mp.addBodyPart(part1);
	  message.setContent(mp);
	  Transport.send(message);

	  return true;
  	} catch (Exception e) {
  		return false;
  }
  
 }
}
