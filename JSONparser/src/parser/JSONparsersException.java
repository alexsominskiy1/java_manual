package parser;

@SuppressWarnings("serial")
public class JSONparsersException extends Exception{

	public JSONparsersException(Exception e) {
		super("Caused by "+e.getClass().getName());
		this.setStackTrace(e.getStackTrace());
	}
}
