package lexer;

public class Number extends Token {
    private int value;

    public Number(int line, int column, int value) {
        super(Lex.INTEGER, line, column);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "<" + super.getType().name() + ", " + value + ">";
    }
}
