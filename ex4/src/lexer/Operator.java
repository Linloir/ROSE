package lexer;

public class Operator extends Token {
    private Operator(Lex type, int line, int column) {
        super(type, line, column);
    }

    private Operator(Lex type) {
        super(type);
    }

    public static Operator plus(int line, int column) {
        return new Operator(Lex.PLUS, line, column);
    }

    public static Operator plus() {
        return new Operator(Lex.PLUS);
    }

    public static Operator minus(int line, int column) {
        return new Operator(Lex.MINUS, line, column);
    }

    public static Operator minus() {
        return new Operator(Lex.MINUS);
    }

    public static Operator mult(int line, int column) {
        return new Operator(Lex.MULT, line, column);
    }

    public static Operator mult() {
        return new Operator(Lex.MULT);
    }

    public static Operator div(int line, int column) {
        return new Operator(Lex.DIV, line, column);
    }

    public static Operator div() {
        return new Operator(Lex.DIV);
    }

    public static Operator mod(int line, int column) {
        return new Operator(Lex.MOD, line, column);
    }

    public static Operator mod() {
        return new Operator(Lex.MOD);
    }

    public static Operator and(int line, int column) {
        return new Operator(Lex.AND, line, column);
    }

    public static Operator and() {
        return new Operator(Lex.AND);
    }

    public static Operator or(int line, int column) {
        return new Operator(Lex.OR, line, column);
    }

    public static Operator or() {
        return new Operator(Lex.OR);
    }

    public static Operator not(int line, int column) {
        return new Operator(Lex.NOT, line, column);
    }

    public static Operator not() {
        return new Operator(Lex.NOT);
    }

    public static Operator eq(int line, int column) {
        return new Operator(Lex.EQ, line, column);
    }

    public static Operator eq() {
        return new Operator(Lex.EQ);
    }

    public static Operator neq(int line, int column) {
        return new Operator(Lex.NEQ, line, column);
    }

    public static Operator neq() {
        return new Operator(Lex.NEQ);
    }

    public static Operator lt(int line, int column) {
        return new Operator(Lex.LT, line, column);
    }

    public static Operator lt() {
        return new Operator(Lex.LT);
    }

    public static Operator lteq(int line, int column) {
        return new Operator(Lex.LTEQ, line, column);
    }

    public static Operator lteq() {
        return new Operator(Lex.LTEQ);
    }

    public static Operator gt(int line, int column) {
        return new Operator(Lex.GT, line, column);
    }

    public static Operator gt() {
        return new Operator(Lex.GT);
    }

    public static Operator gteq(int line, int column) {
        return new Operator(Lex.GTEQ, line, column);
    }

    public static Operator gteq() {
        return new Operator(Lex.GTEQ);
    }

    public static Operator assign(int line, int column) {
        return new Operator(Lex.ASSIGN, line, column);
    }

    public static Operator assign() {
        return new Operator(Lex.ASSIGN);
    }
}