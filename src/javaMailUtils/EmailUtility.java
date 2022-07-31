package javaMailUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import entidades.Email;

/**
 * A utility class that sends an e-mail message with attachments.
 *
 */
public class EmailUtility {
    
    
    public static void sendEmail(Properties smtpProperties, String toAddress,
            String subject, String message, File[] attachFiles)
            throws AddressException, MessagingException, IOException {
	
 
        final String userName = smtpProperties.getProperty("mail.user");
        final String password = smtpProperties.getProperty("mail.password");
        
         
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(smtpProperties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) {
            for (File aFile : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(aFile);
                } catch (IOException ex) {
                    throw ex;
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
        
        
    }
    
    
    public static List<Email>  receiveEmail(Properties pop3Properties) {
	
		
		Properties props = pop3Properties;
		
		List<Email> listaDeMails = null;
		
		String host = props.getProperty("mail.pop3.host");
		String user = props.getProperty("mail.user");
		String password = props.getProperty("mail.password");
	 
	       Session session = Session.getInstance(props);
	 
	    try {
	        //Create the POP3 store object and connect to the pop store.
		Store store = session.getStore("pop3");
		
		listaDeMails = new ArrayList<Email>();
		
		store.connect(host, user, password);
		
		
		
		//Create the folder object and open it in your mailbox.
		Folder emailFolder = store.getFolder("INBOX");
		emailFolder.open(Folder.READ_ONLY);
	 
		//Retrieve the messages from the folder object.
		Message[] messages = emailFolder.getMessages();
		System.out.println("Total Message " +  messages.length);
	 
		//Iterate the messages
		      for (int i = 0, n = messages.length; i < n; i++) {
			         Message indvidualmsg = messages[i];
			         
			         String status= null;
			         
			         
			         String sender;
			         if (indvidualmsg.getFrom()[0].toString().indexOf("<")==-1) {
			             sender= indvidualmsg.getFrom()[0].toString();
			         } else {
			             sender = indvidualmsg.getFrom()[0].toString().substring(indvidualmsg.getFrom()[0].toString().indexOf("<")+1, indvidualmsg.getFrom()[0].toString().indexOf(">"));
			         }
			         String recipient;
			         if (indvidualmsg.getAllRecipients()[0].toString().indexOf("<")==-1) {
			             recipient = indvidualmsg.getAllRecipients()[0].toString();
			         } else {
			             recipient = indvidualmsg.getAllRecipients()[0].toString().substring(indvidualmsg.getAllRecipients()[0].toString().indexOf("<")+1, indvidualmsg.getAllRecipients()[0].toString().indexOf(">"));
			         }
			         
			         if( recipient.equals(user.replace("recent:", ""))) {
				            status="recibido";
				         } else {
				             status="enviado";
				         }
			         
			         listaDeMails.add(new Email(indvidualmsg.getSubject(), sender, recipient, indvidualmsg.getSentDate().toString(), getTextFromMessage(indvidualmsg), status, user.replace("recent:", "")));
			         
			        
			      }
		      
	 
		   //close the folder and store objects
		   emailFolder.close(false);
		   store.close();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e){
			e.printStackTrace();
		} catch (Exception e) {
		       e.printStackTrace();
		}
	    return listaDeMails;
	 
	    }
    
    
    private static String getTextFromMessage(Message message) throws MessagingException, IOException {
	    String result = "";
	    if (message.isMimeType("text/plain")) {
	        result = message.getContent().toString();
	    } else if (message.isMimeType("multipart/*")) {
	        MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
	        result = getTextFromMimeMultipart(mimeMultipart);
	    }
	    return result;
	}

	private static String getTextFromMimeMultipart(
	        MimeMultipart mimeMultipart)  throws MessagingException, IOException{
	    String result = "";
	    int count = mimeMultipart.getCount();
	    for (int i = 0; i < count; i++) {
	        BodyPart bodyPart = mimeMultipart.getBodyPart(i);
	        if (bodyPart.isMimeType("text/plain")) {
	            result = result + "\n" + bodyPart.getContent();
	            break; // without break same text appears twice in my tests
	        } else if (bodyPart.isMimeType("text/html")) {
	            String html = (String) bodyPart.getContent();
	            result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
	        } else if (bodyPart.getContent() instanceof MimeMultipart){
	            result = result + getTextFromMimeMultipart((MimeMultipart)bodyPart.getContent());
	        }
	    }
	    return result;
	}
	     
}