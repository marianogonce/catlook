package exeptions;

public class ServiceContactoException extends Exception{
    
	public ServiceContactoException() { 
	} 
	
	public ServiceContactoException(String message) { 
		super(message); 
	} 
	
	public ServiceContactoException(Throwable cause) { 
		super(cause); 
	} 
	
	public ServiceContactoException(String message, Throwable cause) { 
		super(message, cause); 
	} 

}
