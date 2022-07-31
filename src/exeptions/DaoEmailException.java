package exeptions;

public class DaoEmailException  extends Exception {
	public DaoEmailException() { 
	} 
	
	public DaoEmailException(String message) { 
		super(message); 
	} 
	
	public DaoEmailException(Throwable cause) { 
		super(cause); 
	} 
	
	public DaoEmailException(String message, Throwable cause) { 
		super(message, cause); 
	} 

}
