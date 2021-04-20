package scanner;

public class ScannerException extends Exception{
	public ScannerException(String errorMessage) {
		super(errorMessage);
	}
	
	public ScannerException (String errorMessage, Exception e) {
		super(errorMessage, e);
	}
}
