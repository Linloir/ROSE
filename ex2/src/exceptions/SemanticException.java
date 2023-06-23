package exceptions;

public class SemanticException extends Exception
{
	private static final long serialVersionUID = 1L;
	public SemanticException() {
		this("Semantic error.");
	}
	public SemanticException(String msg) {
		super("Semantic error. (details:" + msg + ")");
	}
}
