package exceptions;

public class IllegalOctalException extends LexicalException
{
	private static final long serialVersionUID = 1L;
	public IllegalOctalException() {
		this("IllegalOctal error.");
	}
	public IllegalOctalException(String msg) {
		super("IllegalOctal error. (details:" + msg + ")");
	}
}
