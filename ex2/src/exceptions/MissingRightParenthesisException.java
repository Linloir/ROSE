package exceptions;

public class MissingRightParenthesisException extends SyntacticException
{
	public MissingRightParenthesisException() {
		this("Missing right parenthesis.");
	}
	public MissingRightParenthesisException(String msg) {
		super(msg);
	}	

}