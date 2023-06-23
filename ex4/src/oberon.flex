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
    return Symbol.eof(yyline + 1, yycolumn + 1);
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
    "BOOLEAN"       { return Keyword.typeBool(yyline + 1, yycolumn + 1); }
    "INTEGER"       { return Keyword.typeInt(yyline + 1, yycolumn + 1); }
    "RECORD"        { return Keyword.typeRecord(yyline + 1, yycolumn + 1); }
    "ARRAY"         { return Keyword.typeArray(yyline + 1, yycolumn + 1); }
    "OF"            { return Keyword.symOf(yyline + 1, yycolumn + 1); }

    "DO"            { return Keyword.symDo(yyline + 1, yycolumn + 1); }
    "WHILE"         { return Keyword.symWhile(yyline + 1, yycolumn + 1); }
    "IF"            { return Keyword.symIf(yyline + 1, yycolumn + 1); }
    "ELSE"          { return Keyword.symElse(yyline + 1, yycolumn + 1); }
    "ELSIF"         { return Keyword.symElif(yyline + 1, yycolumn + 1); }
    "THEN"          { return Keyword.symThen(yyline + 1, yycolumn + 1); }

    "CONST"         { return Keyword.symConst(yyline + 1, yycolumn + 1); }
    "VAR"           { return Keyword.symVar(yyline + 1, yycolumn + 1); }
    "TYPE"          { return Keyword.symType(yyline + 1, yycolumn + 1); }

    "MODULE"        { return Keyword.symModule(yyline + 1, yycolumn + 1); }
    "PROCEDURE"     { return Keyword.symProcedure(yyline + 1, yycolumn + 1); }
    "BEGIN"         { return Keyword.symBegin(yyline + 1, yycolumn + 1); }
    "END"           { return Keyword.symEnd(yyline + 1, yycolumn + 1); }

    "DIV"           { return Operator.div(yyline + 1, yycolumn + 1); }
    "MOD"           { return Operator.mod(yyline + 1, yycolumn + 1); }
    "OR"            { return Operator.or(yyline + 1, yycolumn + 1); }
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
                                return new Number(yyline + 1, yycolumn + 1, Integer.parseInt(yytext(), 8));
                            } catch (NumberFormatException e) {
                                throw new LexicalException("Invalid number");
                            }
                        } else {
                            // Parse as decimal number
                            try {
                                return new Number(yyline + 1, yycolumn + 1, Integer.parseInt(yytext()));
                            } catch (NumberFormatException e) {
                                throw new LexicalException("Invalid number");
                            }
                        }
                    }
    
    {identifier}    { return new Identifier(yyline + 1, yycolumn + 1, yytext()); }

    ":="            { return Operator.assign(yyline + 1, yycolumn + 1); }
    
    "+"             { return Operator.plus(yyline + 1, yycolumn + 1); }
    "-"             { return Operator.minus(yyline + 1, yycolumn + 1); }
    "*"             { return Operator.mult(yyline + 1, yycolumn + 1); }
    
    "&"             { return Operator.and(yyline + 1, yycolumn + 1); }
    "~"             { return Operator.not(yyline + 1, yycolumn + 1); }
    
    "="             { return Operator.eq(yyline + 1, yycolumn + 1); }
    "#"             { return Operator.neq(yyline + 1, yycolumn + 1); }

    "<"             { return Operator.lt(yyline + 1, yycolumn + 1); }
    "<="            { return Operator.lteq(yyline + 1, yycolumn + 1); }
    ">"             { return Operator.gt(yyline + 1, yycolumn + 1); }
    ">="            { return Operator.gteq(yyline + 1, yycolumn + 1); }

    "("             { return Symbol.lpar(yyline + 1, yycolumn + 1); }
    ")"             { return Symbol.rpar(yyline + 1, yycolumn + 1); }
    "["             { return Symbol.lbrack(yyline + 1, yycolumn + 1); }
    "]"             { return Symbol.rbrack(yyline + 1, yycolumn + 1); }
    ":"             { return Symbol.colon(yyline + 1, yycolumn + 1); }
    ";"             { return Symbol.semicol(yyline + 1, yycolumn + 1); }
    ","             { return Symbol.comma(yyline + 1, yycolumn + 1); }
    "."             { return Symbol.dot(yyline + 1, yycolumn + 1); }
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
