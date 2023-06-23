package lexer;

public enum Lex {
	/* Keywords */
    TYPE_BOOL,
    TYPE_INTEGER,
    TYPE_RECORD,
    TYPE_ARRAY,
    OF,

    DO,
    WHILE,
    IF,
    ELSE,
    ELIF,
    THEN,

    CONST,
	VAR,
	TYPE,

    MODULE,
    PROCEDURE,
    BEGIN,
    END,

    DIV,
    MOD,
    OR,

    READ,
    WRITE,
    WRITELN,

	/* Symbols */
    LPAR,
    RPAR,
    LBRACK,
    RBRACK,
    COLON,
    SEMICOLON,
    COMMA,
    DOT,

	/* Operators */
    ASSIGN,
    EQ,
    GT,
    LT,
    LTEQ,
    GTEQ,
    NEQ,
    AND,
    NOT,
    PLUS,
    MINUS,
    MULT,

	/* Literals & Identifiers */
    INTEGER,
    COMMENT,
    ID,

    EOF
}