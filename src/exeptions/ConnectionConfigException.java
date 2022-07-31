package exeptions;

public class ConnectionConfigException extends Exception  {

    
    public ConnectionConfigException() { 
	} 
	
	public ConnectionConfigException(String message) { 
		super(message); 
	} 
	
	public ConnectionConfigException(Throwable cause) { 
		super(cause); 
	} 
	
	public ConnectionConfigException(String message, Throwable cause) { 
		super(message, cause); 
	} 
}
