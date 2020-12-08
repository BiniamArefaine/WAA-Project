package edu.miu.cs.auctionproject.javaMailApi;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@SessionAttributes("pinCode")
public class SendEmailClass{
    public static void sendMailTo(String receipt) throws Exception{
        System.out.println("preparing to send.....");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        final String myAccountEmail = "venuswaa@gmail.com";
        final String password = "Test@1234";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, receipt);
        Transport.send(message);
        System.out.println("Message Sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String reciept){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(reciept));
            message.setSubject("New Account Created With this Email");
            int randomNumber = ( int )( Math. random() * 9999 );
            message.setText("This is one Time Verification code. Please enter this code!\n" + randomNumber);
            return message;
        } catch (Exception e) {
            Logger.getLogger(SendEmailClass.class.getName()).log(Level.SEVERE, null, e);
        }

         return null;
    }
}
