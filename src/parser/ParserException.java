package parser;

public class ParserException extends Exception {

	public ParserException(String errorMessage) {
		super(errorMessage);
	}
	
	public ParserException (String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}

