package exceptions;

public class LexicalException extends OberonException
{

    public LexicalException()
    {
        this("Lexical error.");
    }

    public LexicalException(String s)
    {
        super("LexicalException :\n" + s);
    }
}
