# EX2 - Lexical Analyzer

The goal of this exercise is to implement a Lexical Analyzer for the Oberon-0 language. The analyzer is able to recognize the tokens in the program and report lexical errors.

## Structure

The structure of the project is as follows:

```text
.
├── bin/            # Compiled classes
├── src/            # Source codes
|   ├── exceptions/ # Exceptions
|   ├── lexer/      # Lexer and Token classes
|   |
|   ├── Main.java   # Main class
|   └── oberon.flex # JFlex specification
├── testcases/      # Test cases
|
├── generate.bat    # Batch file for generating lexer
├── build.bat       # Batch file for compiling classes
└── run.bat         # Batch file for running the program
```

## Usage

There are batch files in the folder for convenience. You can run them from any directory you like. Additionally for `build.bat` files, you can specify the very class you want to (re)-compile.

Here are some examples of using the batch files:

```bash
# Assume you are in the current folder

# Generate lexer
.\generate.bat

# Compile all the classes
.\build.bat

# Run with test case in testcases\pow.obr
.\run.bat .\testcases\pow.obr
```

## Design

The design of JFlex specification is rather straightforward. Addition to the keywords and symbols of the language, the rest of the terminals can be recognized by the following regular expressions:

```flex
whitespace    = [ \t\n\r] | \r\n
numberlike    = [0-9]\w*
identifier    = [a-zA-Z][a-zA-Z0-9]*
comment       = "(*" ~ "*)"
```

### Comment matching

The `comment` rule benifits from the syntax and greedy matching of JFlex, and matches the longest possible string that starts with `(*` and ends with `*)`.

### Number matching

Since a number in Oberon-0 can have either Octal and Decimal forms, and lexical error reporting is necessary when encountering a number with invalid format, the `numberlike` rule is designed to match the longest possible string that starts with a digit and ends with a non-space character (necessary because `123a` should be treated as one *numberlike* instead of a *number* and an *identifier*).

After matched such *numberlike* string, the program first checks whether the string is a valid number, then the program checks for the length of the string. Finally, the number is converted to the `Number` token with its value and returned.

### Error catching

In order to catch lexical errors, the specification needs several 'fallbacks' to catch the invalid tokens.

As required in the documentation, the program should report an open comment error when the program encounters a `(*` but never a `*)`.

One way to do this is to provide a complex rule for such string in the `<YYINITIAL>` state. However, such solution has horrible drawbacks because the lexer will read to the end of file every time it tries to parse the next token just to make sure such regex is not matched, which will significantly decrease the performance of the lexer.

In the implementation, the match of open comment is done in the fall back state.

When the lexer falls to such state, assumption can be made that the lexer can't find any `(*` `*)` pairs. Therefore, as far as the beginning of the rest of the input is a `(*`, it's definite that there is no `*)` in the rest of the input (otherwise the `<YYINITIAL>` state would return a valid *comment* token).

```flex
"(*"                {
                        /* Open comment error */
                        throw new LexicalException("Open comment error");
                    }
```

Other errors are also reported in the fall back state. The lexer will match one character in the input stream and report an error with the character.

```flex
[^]                 {
                        /* Unknown error, read one character */
                        throw new LexicalException("Unknown error");
                    }
```
