package exceptions;

public class IllegalIntegerRangeException extends LexicalException
{
	private static final long serialVersionUID = 1L;
	public IllegalIntegerRangeException() {
		this("IllegalIntegerRange error.");
	}
	public IllegalIntegerRangeException(String msg) {
		super("IllegalIntegerRange error. (details:" + msg + ")");
	}
}
