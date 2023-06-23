package exceptions;

public class MissingLeftParenthesisException extends SyntacticException
{
	public MissingLeftParenthesisException() {
		this("Missing left parenthesis.");
	}
	public MissingLeftParenthesisException(String msg) {
		super(msg);
	}	

}
