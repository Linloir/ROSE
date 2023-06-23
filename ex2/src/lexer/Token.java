/*
 * @Author       : Linloir
 * @Date         : 2023-06-21 00:13:28
 * @LastEditTime : 2023-06-21 16:09:11
 * @Description  : 
 */
package lexer;

public class Token extends java_cup.runtime.Symbol {
    private Lex type;       // The type of the token
    private int line;       // The line of the token
    private int column;     // The column of the token

    public Token(Lex type, int line, int column) {
        super(type.ordinal(), type);
        this.type = type;
        this.line = line;
        this.column = column;
    }

    public Lex getType() {
        return type;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public String toString() {
        return "<" + type.name() + ">";
    }
}