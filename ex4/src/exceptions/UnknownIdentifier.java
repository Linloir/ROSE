package exceptions;

import lexer.Identifier;

public class UnknownIdentifier extends SyntaxException {
    public UnknownIdentifier(String id, Integer line, Integer column) {
        super("Unkown identifier '" + id + "' at line " + line + ", column " + column + ".");
    }

    public UnknownIdentifier(Identifier id) {
        super("Unkown identifier '" + id.getLexeme() + "' at line " + id.getLine() + ", column " + id.getColumn() + ".");
    }
}
