package lexer;

public class Symbol extends Token {
    private Symbol(Lex type, int line, int column) {
        super(type, line, column);
    }

    private Symbol(Lex type) {
        super(type);
    }

    public static Symbol lpar(int line, int column) {
        return new Symbol(Lex.LPAR, line, column);
    }

    public static Symbol lpar() {
        return new Symbol(Lex.LPAR);
    }

    public static Symbol rpar(int line, int column) {
        return new Symbol(Lex.RPAR, line, column);
    }

    public static Symbol rpar() {
        return new Symbol(Lex.RPAR);
    }

    public static Symbol lbrack(int line, int column) {
        return new Symbol(Lex.LBRACK, line, column);
    }

    public static Symbol lbrack() {
        return new Symbol(Lex.LBRACK);
    }

    public static Symbol rbrack(int line, int column) {
        return new Symbol(Lex.RBRACK, line, column);
    }

    public static Symbol rbrack() {
        return new Symbol(Lex.RBRACK);
    }

    public static Symbol colon(int line, int column) {
        return new Symbol(Lex.COLON, line, column);
    }

    public static Symbol colon() {
        return new Symbol(Lex.COLON);
    }

    public static Symbol semicol(int line, int column) {
        return new Symbol(Lex.SEMICOLON, line, column);
    }

    public static Symbol semicol() {
        return new Symbol(Lex.SEMICOLON);
    }

    public static Symbol comma(int line, int column) {
        return new Symbol(Lex.COMMA, line, column);
    }

    public static Symbol comma() {
        return new Symbol(Lex.COMMA);
    }

    public static Symbol dot(int line, int column) {
        return new Symbol(Lex.DOT, line, column);
    }

    public static Symbol dot() {
        return new Symbol(Lex.DOT);
    }

    public static Symbol eof(int line, int column) {
        return new Symbol(Lex.EOF, line, column);
    }

    public static Symbol eof() {
        return new Symbol(Lex.EOF);
    }
}