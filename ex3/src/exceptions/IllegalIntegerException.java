package exceptions;

public class IllegalIntegerException extends LexicalException
{

    public IllegalIntegerException()
    {
        this("Illegal Integer, no blank between interger and letters.");
    }

    public IllegalIntegerException(String s)
    {
        super("Illegal Integer, no blank between interger and letters.\n"+s);
    }
}
