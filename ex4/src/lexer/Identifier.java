package lexer;

public class Identifier extends Token {
    private String lexeme;

    public Identifier(int line, int column, String lexeme) {
        super(Lex.ID, line, column);
        this.lexeme = lexeme;
    }

    public Identifier(String lexeme) {
        super(Lex.ID);
        this.lexeme = lexeme;
    }

    public Identifier() {
        super(Lex.ID);
        this.lexeme = null;
    }

    public String getLexeme() {
        return lexeme;
    }

    @Override
    public String toString() {
        return "<" + super.getType().name() + ", " + lexeme + ">";
    }
}