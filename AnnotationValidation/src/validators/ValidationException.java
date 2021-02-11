package validators;

@SuppressWarnings("serial")
public class ValidationException extends RuntimeException {
	
	public ValidationException(String msg) {
		super(msg);
	}

}
