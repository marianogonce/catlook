package entidades;



import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Email  implements Comparable<Email> {

    private String subject;
    private String sender; 
    private String recipient; 
    private String sentDate;
    private String content;
    private String status;
    private String server;
    
    public Email (String subject, String sender, String recipient, String sentDate, String content, String status, String server) {
	this.subject = subject;
	this.sender = sender;
	this.recipient = recipient;
	this.sentDate = sentDate;
	this.content = content; 
	this.status = status;
	this.server = server;
    }
    
    public String getSubject() {
	return subject;
    }
    public void setSubject(String subject) {
	this.subject = subject;
    }
    public String getSender() {
	return sender;
    }
    public void setSender(String sender) {
	this.sender = sender;
    }
    public String getRecipient() {
	return recipient;
    }
    public void setRecipient(String recipient) {
	this.recipient = recipient;
    }
    public String getSentDate() {
	return sentDate;
    }
    public void setSentDate(String sentDate) {
	this.sentDate = sentDate;
    }
    public String getContent() {
	return content;
    }
    public void setContent(String content) {
	this.content = content;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getServer() {
	return server;
    }

    public void setServer(String server) {
	this.server = server;
    }
    
    @Override
    public int compareTo(Email email) {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE LLL dd kk:mm:ss z yyyy", Locale.ENGLISH);
	
	LocalDate dateTimeEmail = LocalDate.parse(email.getSentDate(), formatter);
	LocalDate dateTimeThis = LocalDate.parse(getSentDate(), formatter);

	    return -1*dateTimeThis.compareTo(dateTimeEmail);
	

    }
    
	@Override
	public String toString() {
	    return this.getSubject() + " ----- Sender: " + this.getSender() + " ---- Recipient: " + this.getRecipient() + " ---- Date: " + this.getSentDate();
	}

    
    
}
