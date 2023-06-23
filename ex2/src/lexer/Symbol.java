package lexer;

public class Symbol extends Token {
    private Symbol(Lex type, int line, int column) {
        super(type, line, column);
    }

    public static Symbol lpar(int line, int column) {
        return new Symbol(Lex.LPAR, line, column);
    }

    public static Symbol rpar(int line, int column) {
        return new Symbol(Lex.RPAR, line, column);
    }

    public static Symbol lbrack(int line, int column) {
        return new Symbol(Lex.LBRACK, line, column);
    }

    public static Symbol rbrack(int line, int column) {
        return new Symbol(Lex.RBRACK, line, column);
    }

    public static Symbol colon(int line, int column) {
        return new Symbol(Lex.COLON, line, column);
    }

    public static Symbol semicol(int line, int column) {
        return new Symbol(Lex.SEMICOLON, line, column);
    }

    public static Symbol comma(int line, int column) {
        return new Symbol(Lex.COMMA, line, column);
    }

    public static Symbol dot(int line, int column) {
        return new Symbol(Lex.DOT, line, column);
    }

    public static Symbol eof(int line, int column) {
        return new Symbol(Lex.EOF, line, column);
    }
}