package exceptions;

public class SyntacticException extends OberonException
{
	private static final long serialVersionUID = 1L;
	public SyntacticException() {
		this("Syntactic error.");
	}
	public SyntacticException(String msg) {
		super("Syntactic error. (details:" + msg + ")");
	}
}
