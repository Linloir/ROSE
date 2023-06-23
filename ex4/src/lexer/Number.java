package lexer;

public class Number extends Token {
    private Integer value;

    public Number(int line, int column, int value) {
        super(Lex.INTEGER, line, column);
        this.value = value;
    }

    public Number() {
        super(Lex.INTEGER);
        this.value = null;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<" + super.getType().name() + ", " + value + ">";
    }
}
