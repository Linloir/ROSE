package lexer;

public class Token extends java_cup.runtime.Symbol {
    private Lex type;       // The type of the token
    private Integer line;       // The line of the token
    private Integer column;     // The column of the token

    public Token(Lex type, int line, int column) {
        super(type.ordinal(), type);
        this.type = type;
        this.line = line;
        this.column = column;
    }

    public Token(Lex type) {
        super(type.ordinal(), type);
        this.type = type;
        this.line = null;
        this.column = null;
    }

    public Lex getType() {
        return type;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "<" + type.name() + ">";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Token)) {
            return false;
        }
        Token other = (Token) obj;
        return type == other.type;
    }
}