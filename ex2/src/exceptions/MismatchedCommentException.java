package exceptions;

public class MismatchedCommentException extends LexicalException
{
	private static final long serialVersionUID = 1L;
	public MismatchedCommentException() {
		this("MismatchedComment error.");
	}
	public MismatchedCommentException(String msg) {
		super("MismatchedComment error. (details:" + msg + ")");
	}
}
