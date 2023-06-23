package exceptions;

public class MissingLeftParenthesisException extends SyntacticException
{

    public MissingLeftParenthesisException()
    {
        this("Missing LeftParenthesis Exception.");
    }

    public MissingLeftParenthesisException(String s)
    {
        super( s);
    }
}