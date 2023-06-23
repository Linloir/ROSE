# EX4 - Predictive Recursive Descent Parser

The goal of this exercise is to implement a predictive recursive descent parser for the Oberon-0 language. The definition of the Oberon-0 language in EBNF form can be found in the `requirements.pdf` document in the parent folder.

The implemented parser is able to parse any valid Oberon-0 program and plot a flow chart of the program. It's also able to detect and report any lexical and syntactical errors in the program.

## Structure

The structure of the project is as follows:

```text
.
├── bin/            # Compiled classes
├── src/            # Source codes
|   ├── env/        # Classes related to the compile time environment
|   ├── lexer/      # Classes related to the lexical analysis,
|   |               #   including lexer and token types
|   ├── logger/     # Classes related to logging
|   ├── parser/     # Classes related to the syntax analysis
|   ├── types/      # Classes related to the types in Oberon-0
|   |
|   ├── Main.java   # Main class
|   └── oberon.flex # Lexer definition file
├── testcases/      # Test cases
|
├── generate.bat    # Lexer generator
├── build.bat       # Class compiler
└── run.bat         # Program runner
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

# Run with test case in testcases\pow.obr with debug option on
.\run.bat .\testcases\pow.obr -d

# Assume you suddenly found a bug in the codes and modified Parser.java and Main.java, now you need to recompile them
.\build.bat Parser Main
```

The compiled `Main` class have several arguments to control the behavior of the parser:

```text
Usage: Main [options] <file>
Options:
  -d, --debug               Print debug information
      --delay               Delay the procedure signature checking
  -h, --help                Print this help message
  -e, --encoding <encoding> Specify the encoding of the input file
  -l, --log <file>          Specify the log file
      --parse-only          Only parse the input file, do not display
                            flow chart
```

When running the program via `run.bat`, there are no worries about the classpath, the current path, or the path of the input file.

The batch script has been designed with care and can convert all paths to absolute paths and get the dependencies based on the path of the script itself. It also append the class path for you so there is no need for `-cp` argument.

## Design

The design of the parser is based on the grammar of the Oberon-0 language, which is converted to LL(1) and is marked in BNF form in the `design.pdf` document in the current folder.

Although there are no ambiguities in the grammar, there are indeed left recursions and conflicts in the original EBNF. During the process of converting the grammar to LL(1), several techniques are used and are described in the following sections.

### Left Recursion Elimination

The left recursion elimination is done by the following rule:

$$
\begin{aligned}
A &\rightarrow A\alpha_1 | A\alpha_2 | \cdots | A\alpha_n | \beta_1 | \beta_2 | \cdots | \beta_m
\end{aligned}
\quad
\Rightarrow
\quad
\begin{aligned}
A &\rightarrow \beta_1A' | \beta_2A' | \cdots | \beta_mA' \\
A' &\rightarrow \alpha_1A' | \alpha_2A' | \cdots | \alpha_nA' | \epsilon
\end{aligned}
$$

### Conflict Resolution

The conflicts in the grammar comes from common prefixes in the right hand side of the productions. For example:

$$
\begin{aligned}
stat \rightarrow & \; assignment \; | \; proc\_call \; | \; \dots \\
assignment \rightarrow & \; \textbf{id} \; := \; expr \\
proc\_call \rightarrow & \; \textbf{id} \; ( \; expr\_list \; )
\end{aligned}
$$

Both `assignment` and `proc_call` contains $\textbf{id}$ in their $FIRST$ set, thus resulting in a conflict.

The conflict is resolved by the following rule:

$$
\begin{aligned}
A &\rightarrow \alpha \; | \; \beta \\
\alpha &\rightarrow \gamma \; \delta \\
\beta &\rightarrow \gamma \; \epsilon
\end{aligned}
\quad
\Rightarrow
\quad
\begin{aligned}
A &\rightarrow \gamma \; A' \\
A' &\rightarrow \delta \; | \; \epsilon
\end{aligned}
$$

## Extra Notice

As the given documentation doesn't specify the behavior when invoking a procedure that has not yet been declared, for example:

```oberon
MODULE CallLoop;
    VAR i, j: INTEGER;
        k: BOOLEAN;

    PROCEDURE func1 (
        unmodifiable: BOOLEAN;
        VAR modifiable: INTEGER
    );
        VAR innerVar: RECORD
                field1: INTEGER;
                field2: ARRAY 5 OF INTEGER;
            END;
    BEGIN
        func2 (innerVar, modifiable)
    END func1;

    PROCEDURE func2 (
        unmodifiable: RECORD
                field1: INTEGER;
                field2: ARRAY 5 OF INTEGER;
            END;
        modifiable: INTEGER
    );
        VAR something: BOOLEAN;
    BEGIN
        func1 (something, unmodifiable.field1)
    END func2;

BEGIN
    func1 (k, i)
END CallLoop.
```

By default, the parser implemented will treat the function call `func2 (innerVar, modifiable)` in `func1` as a syntax error, because it's referring to a procedure that is not in the environment when the statement is parsed.

However, as the behavior is not defined, a flag `--delay` is added to the program to trigger the behavior of the parser.

If the flag is set, the parser will not check the procedure signature when parsing the procedure call, but will check it after the whole program has been parsed.

This function can be test via the `testcases\CallLoop.obr` file:

```bash
# Assume you are in the current folder

# Reports an unknown identifier error
.\run.bat .\testcases\CallLoop.obr -d

# Does not report any error
.\run.bat .\testcases\CallLoop.obr -d --delay
```
