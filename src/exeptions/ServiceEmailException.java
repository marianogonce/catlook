package exeptions;

public class ServiceEmailException extends Exception {
    
	public ServiceEmailException() { 
	} 
	
	public ServiceEmailException(String message) { 
		super(message); 
	} 
	
	public ServiceEmailException(Throwable cause) { 
		super(cause); 
	} 
	
	public ServiceEmailException(String message, Throwable cause) { 
		super(message, cause); 
	} 

}
