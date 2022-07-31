package exeptions;

public class DaoServerException extends DaoException {
    
	public DaoServerException() { 
	} 
	
	public DaoServerException(String message) { 
		super(message); 
	} 
	
	public DaoServerException(Throwable cause) { 
		super(cause); 
	} 
	
	public DaoServerException(String message, Throwable cause) { 
		super(message, cause); 
	} 
}
