package exceptions;

public class SyntaxException extends OberonException {
    public SyntaxException() {
        super("Syntax error found in the program.");
    }

    public SyntaxException(String s) {
        super(s);
    }
}
