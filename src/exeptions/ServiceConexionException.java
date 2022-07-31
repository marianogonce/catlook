package exeptions;

public class ServiceConexionException extends Exception {
	public ServiceConexionException() { 
	} 
	
	public ServiceConexionException(String message) { 
		super(message); 
	} 
	
	public ServiceConexionException(Throwable cause) { 
		super(cause); 
	} 
	
	public ServiceConexionException(String message, Throwable cause) { 
		super(message, cause); 
	} 
}
