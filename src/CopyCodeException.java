
public class CopyCodeException extends Exception {

	private String message = null;
	
	public CopyCodeException(){
		super();
	}
	
	public CopyCodeException(String message){
		super(message);
		this.message = message;
	}
	
	public CopyCodeException(Throwable cause){
		super(cause);
	}
	
	public CopyCodeException(Throwable cause, String message){
		super(message, cause);
		
	}
	
	@Override
	public String toString(){
		return message;
	}
	
	@Override 
	public String getMessage(){
		return message;
	}
	
	
}
