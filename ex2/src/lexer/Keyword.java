package lexer;

public class Keyword extends Token {
    private Keyword(Lex type, int line, int column) {
        super(type, line, column);
    }

    public static Keyword typeBool(int line, int column) {
        return new Keyword(Lex.TYPE_BOOL, line, column);
    }

    public static Keyword typeInt(int line, int column) {
        return new Keyword(Lex.TYPE_INTEGER, line, column);
    }

    public static Keyword typeRecord(int line, int column) {
        return new Keyword(Lex.TYPE_RECORD, line, column);
    }

    public static Keyword typeArray(int line, int column) {
        return new Keyword(Lex.TYPE_ARRAY, line, column);
    }

    public static Keyword symOf(int line, int column) {
        return new Keyword(Lex.OF, line, column);
    }

    public static Keyword symDo(int line, int column) {
        return new Keyword(Lex.DO, line, column);
    }

    public static Keyword symWhile(int line, int column) {
        return new Keyword(Lex.WHILE, line, column);
    }

    public static Keyword symIf(int line, int column) {
        return new Keyword(Lex.IF, line, column);
    }

    public static Keyword symElse(int line, int column) {
        return new Keyword(Lex.ELSE, line, column);
    }

    public static Keyword symElif(int line, int column) {
        return new Keyword(Lex.ELIF, line, column);
    }

    public static Keyword symThen(int line, int column) {
        return new Keyword(Lex.THEN, line, column);
    }

    public static Keyword symConst(int line, int column) {
        return new Keyword(Lex.CONST, line, column);
    }

    public static Keyword symVar(int line, int column) {
        return new Keyword(Lex.VAR, line, column);
    }

    public static Keyword symType(int line, int column) {
        return new Keyword(Lex.TYPE, line, column);
    }

    public static Keyword symModule(int line, int column) {
        return new Keyword(Lex.MODULE, line, column);
    }

    public static Keyword symProcedure(int line, int column) {
        return new Keyword(Lex.PROCEDURE, line, column);
    }

    public static Keyword symBegin(int line, int column) {
        return new Keyword(Lex.BEGIN, line, column);
    }

    public static Keyword symEnd(int line, int column) {
        return new Keyword(Lex.END, line, column);
    }

    public static Keyword symRead(int line, int column) {
        return new Keyword(Lex.READ, line, column);
    }

    public static Keyword symWrite(int line, int column) {
        return new Keyword(Lex.WRITE, line, column);
    }

    public static Keyword symWriteln(int line, int column) {
        return new Keyword(Lex.WRITELN, line, column);
    }
}
