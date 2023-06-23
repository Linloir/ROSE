package exceptions;

public class IllegalIdentifierLengthException extends LexicalException
{
	private static final long serialVersionUID = 1L;
	public IllegalIdentifierLengthException() {
		this("IllegalIdentifierLength error.");
	}
	public IllegalIdentifierLengthException(String msg) {
		super("IllegalIdentifierLength error. (details:" + msg + ")");
	}
}
