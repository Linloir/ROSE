package exceptions;

public class MissingOperatorException extends SyntacticException
{
	public MissingOperatorException() {
		this("Missing operator.");
	}
	public MissingOperatorException(String msg) {
		super(msg);
	}	

}
