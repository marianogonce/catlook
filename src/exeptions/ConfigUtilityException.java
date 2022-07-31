package exeptions;

public class ConfigUtilityException extends Exception {
    
    public ConfigUtilityException() { 
	} 
	
	public ConfigUtilityException(String message) { 
		super(message); 
	} 
	
	public ConfigUtilityException(Throwable cause) { 
		super(cause); 
	} 
	
	public ConfigUtilityException(String message, Throwable cause) { 
		super(message, cause); 
	} 

}
