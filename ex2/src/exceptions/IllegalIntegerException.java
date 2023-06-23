package exceptions;

public class IllegalIntegerException extends LexicalException
{
	private static final long serialVersionUID = 1L;
	public IllegalIntegerException() {
		this("IllegalInteger error.");
	}
	public IllegalIntegerException(String msg) {
		super("IllegalInteger error. (details:" + msg + ")");
	}
}
