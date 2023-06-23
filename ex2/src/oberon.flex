// Lexer description of Oberon-0 language
// Author: Linloir
// Reference: Dragon Book (UnderGraduate-ver) pp. 88

package lexer;

import exceptions.*;

%%

%public

%class Lexer
%type Token
%yylexthrow OberonException

%cup                                    // Switch to cup compatible mode
%line                                   // Switch line counting on (accessed via yyline)
%column                                 // Switch column counting on (accessed via yycolumn)
%ignorecase                             // Ignore case

%eofval{
    return Symbol.eof(yyline, yycolumn);
%eofval}

%{
    // Some class methods

%}

/* Regular definitions */

whitespace    = [ \t\n\r] | \r\n
numberlike    = [0-9]\w*
identifier    = [a-zA-Z][a-zA-Z0-9]*
comment       = "(*" ~ "*)"             // Reference: JFlex Manual

%%

/* Keywords */

<YYINITIAL> {
    "BOOLEAN"       { return Keyword.typeBool(yyline, yycolumn); }
    "INTEGER"       { return Keyword.typeInt(yyline, yycolumn); }
    "RECORD"        { return Keyword.typeRecord(yyline, yycolumn); }
    "ARRAY"         { return Keyword.typeArray(yyline, yycolumn); }
    "OF"            { return Keyword.symOf(yyline, yycolumn); }

    "DO"            { return Keyword.symDo(yyline, yycolumn); }
    "WHILE"         { return Keyword.symWhile(yyline, yycolumn); }
    "IF"            { return Keyword.symIf(yyline, yycolumn); }
    "ELSE"          { return Keyword.symElse(yyline, yycolumn); }
    "ELSIF"         { return Keyword.symElif(yyline, yycolumn); }
    "THEN"          { return Keyword.symThen(yyline, yycolumn); }

    "CONST"         { return Keyword.symConst(yyline, yycolumn); }
    "VAR"           { return Keyword.symVar(yyline, yycolumn); }
    "TYPE"          { return Keyword.symType(yyline, yycolumn); }

    "MODULE"        { return Keyword.symModule(yyline, yycolumn); }
    "PROCEDURE"     { return Keyword.symProcedure(yyline, yycolumn); }
    "BEGIN"         { return Keyword.symBegin(yyline, yycolumn); }
    "END"           { return Keyword.symEnd(yyline, yycolumn); }

    "READ"          { return Keyword.symRead(yyline, yycolumn); }
    "WRITE"         { return Keyword.symWrite(yyline, yycolumn); }
    "WRITELN"       { return Keyword.symWriteln(yyline, yycolumn); }

    "DIV"           { return Operator.div(yyline, yycolumn); }
    "MOD"           { return Operator.mod(yyline, yycolumn); }
    "OR"            { return Operator.or(yyline, yycolumn); }
}

/* Other lexes*/

<YYINITIAL> {
    {whitespace}    { /* Skip white spaces */ }

    {comment}       { /* Skip comments */ }

    {numberlike}    {
                        /* Parse number */

                        // Stage 1: Validate number, check whether every character is digit
                        if (!yytext().matches("[0-9]+")) {
                            throw new LexicalException("Invalid number");
                        }

                        // Stage 2: Check number length
                        if (yytext().length() > 24) {
                            throw new LexicalException("Number too long");
                        }

                        // Stage 3: Parse number value
                        if (yytext().startsWith("0")) {
                            // Parse as octal number
                            try {
                                return new Number(yyline, yycolumn, Integer.parseInt(yytext(), 8));
                            } catch (NumberFormatException e) {
                                throw new LexicalException("Invalid number");
                            }
                        } else {
                            // Parse as decimal number
                            try {
                                return new Number(yyline, yycolumn, Integer.parseInt(yytext()));
                            } catch (NumberFormatException e) {
                                throw new LexicalException("Invalid number");
                            }
                        }
                    }
    
    {identifier}    { return new Identifier(yyline, yycolumn, yytext()); }

    ":="            { return Operator.assign(yyline, yycolumn); }
    
    "+"             { return Operator.plus(yyline, yycolumn); }
    "-"             { return Operator.minus(yyline, yycolumn); }
    "*"             { return Operator.mult(yyline, yycolumn); }
    
    "&"             { return Operator.and(yyline, yycolumn); }
    "~"             { return Operator.not(yyline, yycolumn); }
    
    "="             { return Operator.eq(yyline, yycolumn); }
    "#"             { return Operator.neq(yyline, yycolumn); }

    "<"             { return Operator.lt(yyline, yycolumn); }
    "<="            { return Operator.lteq(yyline, yycolumn); }
    ">"             { return Operator.gt(yyline, yycolumn); }
    ">="            { return Operator.gteq(yyline, yycolumn); }

    "("             { return Symbol.lpar(yyline, yycolumn); }
    ")"             { return Symbol.rpar(yyline, yycolumn); }
    "["             { return Symbol.lbrack(yyline, yycolumn); }
    "]"             { return Symbol.rbrack(yyline, yycolumn); }
    ":"             { return Symbol.colon(yyline, yycolumn); }
    ";"             { return Symbol.semicol(yyline, yycolumn); }
    ","             { return Symbol.comma(yyline, yycolumn); }
    "."             { return Symbol.dot(yyline, yycolumn); }
}

/* Error fallback */

"(*"                {
                        /* Open comment error */
                        throw new LexicalException("Open comment error");
                    }

[^]                 {
                        /* Unknown error, read one character */
                        throw new LexicalException("Unknown error");
                    }
