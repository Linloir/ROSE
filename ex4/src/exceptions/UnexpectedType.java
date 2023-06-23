package exceptions;

import lexer.*;
import type.*;

public class UnexpectedType extends SyntaxException {
    private static String getTypes(Type[] t) {
        String types = "";
        for (int i = 0; i < t.length; i++) {
            types += t[i];
            if (i < t.length - 2) {
                types += ", ";
            } else if (i == t.length - 1) {
                types += ", or ";
            }
        }
        return types;
    }

    public UnexpectedType(Operator op, Type lhs, Type rhs, Type foundLhs, Type foundRhs) {
        super("Type mismatch for operator '" + op.getType().name().toLowerCase() + "'. Expected '" + lhs + "' and '" + rhs + "', got '" + foundLhs + "' and '" + foundRhs + "' at line " + op.getLine() + ", column " + op.getColumn() + ".");
    }

    public UnexpectedType(Operator op, Type t, Type foundT) {
        super("Type mismatch for operator '" + op.getType().name().toLowerCase() + "'. Expected '" + t + "', got '" + foundT + "' at line " + op.getLine() + ", column " + op.getColumn() + ".");
    }

    public UnexpectedType(Operator op, Type[] t, Type foundT) {
        super("Type mismatch for operator '" + op.getType().name().toLowerCase() + "'. Expected '" + getTypes(t) + "', got '" + foundT + "' at line " + op.getLine() + ", column " + op.getColumn() + ".");
    }

    public UnexpectedType(Type t, Type foundT, int line, int column) {
        super("Type mismatch. Expected '" + t + "', got '" + foundT + "' at line " + line + ", column " + column + ".");
    }

    public UnexpectedType(String expected, Type foundT, int line, int column) {
        super("Type mismatch. Expected '" + expected + "', got '" + foundT + "' at line " + line + ", column " + column + ".");
    }
}
