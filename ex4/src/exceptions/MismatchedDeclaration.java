package exceptions;

import lexer.*;

public class MismatchedDeclaration extends SyntaxException {
    private static String getText(String blockType, Identifier declId, Identifier id) {
        String text = "Mismatched declaration of " + blockType + ". ";
        text += "Declared identifier '" + declId.getLexeme() + "' at line " + declId.getLine() + ", column " + declId.getColumn() + ", ";
        text += "got identifier '" + id.getLexeme() + "' at line " + id.getLine() + ", column " + id.getColumn() + ".";
        return text;
    }

    public MismatchedDeclaration(String blockType, Identifier declId, Identifier id) {
        super(getText(blockType, declId, id));
    }
}
