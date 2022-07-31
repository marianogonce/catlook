package entidades;

public class SmtpPop3Server {

    private String userName;
    private String password;
    private String smptHost;
    private String smptPort;
    private String pop3Host;
    private String pop3Port;
        
    
    public SmtpPop3Server(String userName, String password, String smptHost, String smptPort,  String pop3Host, String pop3Port) {
	this.userName = userName;
	this.password = password;
	this.smptHost = smptHost;
	this.smptPort = smptPort;
	this.pop3Host = pop3Host;
	this.pop3Port = pop3Port;
    }
    
    public SmtpPop3Server() {}
    
    public String getUserName() {
	return userName;
    }
    public void setUserName(String userName) {
	this.userName = userName;
    }
    public String getPassword() {
	return password;
    }
    public void setPassword(String password) {
	this.password = password;
    }
    public String getSmptHost() {
	return smptHost;
    }
    public void setSmptHost(String smptHost) {
	this.smptHost = smptHost;
    }
    public String getSmptPort() {
	return smptPort;
    }
    public void setSmptPort(String smptPort) {
	this.smptPort = smptPort;
    }
    public String getPop3Host() {
	return pop3Host;
    }
    public void setPop3Host(String pop3Host) {
	this.pop3Host = pop3Host;
    }
    public String getPop3Port() {
	return pop3Port;
    }
    public void setPop3Port(String pop3Port) {
	this.pop3Port = pop3Port;
    }
    
    
    
    
}
