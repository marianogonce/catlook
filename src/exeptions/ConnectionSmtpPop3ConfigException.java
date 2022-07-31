package exeptions;

public class ConnectionSmtpPop3ConfigException extends Exception {

    public ConnectionSmtpPop3ConfigException() { 
	} 
	
	public ConnectionSmtpPop3ConfigException(String message) { 
		super(message); 
	} 
	
	public ConnectionSmtpPop3ConfigException(Throwable cause) { 
		super(cause); 
	} 
	
	public ConnectionSmtpPop3ConfigException(String message, Throwable cause) { 
		super(message, cause); 
	} 
}
