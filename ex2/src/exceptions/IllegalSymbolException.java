package exceptions;

public class IllegalSymbolException extends LexicalException
{
	private static final long serialVersionUID = 1L;
	public IllegalSymbolException() {
		this("IllegalSymbol error.");
	}
	public IllegalSymbolException(String msg) {
		super("IllegalSymbol error. (details:" + msg + ")");
	}
}
