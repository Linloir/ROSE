package exceptions;

import lexer.*;

public class UnexpectedToken extends SyntaxException {
    private static String getTypes(Token[] tokens) {
        String types = "";
        for (int i = 0; i < tokens.length; i++) {
            types += tokens[i].getType().name().toLowerCase();
            if (i < tokens.length - 2) {
                types += ", ";
            } else if (i == tokens.length - 2) {
                types += ", or ";
            }
        }
        return types;
    }

    public UnexpectedToken(String expected, String found, Integer line, Integer column) {
        super("Unexpected token '" + found + "' at line " + line + ", column " + column + ". Expected '" + expected + "'.");
    }
    
    public UnexpectedToken(String expected, Token found) {
        super("Unexpected token '" + found.getType().name().toLowerCase() + "' at line " + found.getLine() + ", column " + found.getColumn() + ". Expected '" + expected + "'.");
    }

    public UnexpectedToken(Token expected, Token found) {
        super("Unexpected token '" + found.getType().name().toLowerCase() + "' at line " + found.getLine() + ", column " + found.getColumn() + ". Expected '" + expected.getType().name().toLowerCase() + "'.");
    }

    public UnexpectedToken(Token[] expected, Token found) {
        super("Unexpected token '" + found.getType().name().toLowerCase() + "' at line " + found.getLine() + ", column " + found.getColumn() + ". Expected '" + getTypes(expected) + "'.");
    }
}
