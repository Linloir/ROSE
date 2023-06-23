package exceptions;

import lexer.Identifier;

public class IdentifierAlreadyExists extends SyntaxException {
    public IdentifierAlreadyExists(Identifier id, Identifier pre) {
        super("Redeclaration of '" + id.getLexeme() + "' at line " + id.getLine() + ", column " + id.getColumn() + ". Previous declaration at line " + pre.getLine() + ", column " + pre.getColumn() + ".");
    }
}
