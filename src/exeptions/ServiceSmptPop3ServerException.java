package exeptions;

public class ServiceSmptPop3ServerException extends Exception {
	public ServiceSmptPop3ServerException() { 
	} 
	
	public ServiceSmptPop3ServerException(String message) { 
		super(message); 
	} 
	
	public ServiceSmptPop3ServerException(Throwable cause) { 
		super(cause); 
	} 
	
	public ServiceSmptPop3ServerException(String message, Throwable cause) { 
		super(message, cause); 
	} 
}
