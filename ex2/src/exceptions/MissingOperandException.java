package exceptions;

public class MissingOperandException extends SyntacticException
{
	public MissingOperandException() {
		this("Missing operand.");
	}
	public MissingOperandException(String msg) {
		super(msg);
	}	

}