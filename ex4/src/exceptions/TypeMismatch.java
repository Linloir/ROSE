package exceptions;

import type.*;
import lexer.*;

public class TypeMismatch extends SyntaxException {
    public TypeMismatch(Type lhs, Type rhs, Operator op) {
        super("Type '" + lhs + "' does not match type '" + rhs + "' on " + op.getType().name().toLowerCase() + " operator at line " + op.getLine() + ", column " + op.getColumn() + ".");
    }

    public TypeMismatch(Type expected, Type got, int line, int column) {
        super("Type mismatch. Expected '" + expected + "', got '" + got + "' at line " + line + ", column " + column + ".");
    }
}
