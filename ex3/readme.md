# EX3 - LALR Parser With JavaCUP

The goal of this exercise is to implement a parser for the Oberon-0 language with the help of JavaCUP. JavaCUP is a system for generating LALR parsers from simple specifications. It offers most of the features of YACC and is written in Java.

The implemented parser is able to parse mostly well-formed Oberon-0 program and plot a call graph for the program. It's also able to detect and report several lexical and syntactical errors in the program.

## Structure

The structure of the project is as follows:

```text
.
├── bin        # Compiled classes
├── src        # Source codes
└── testcases  # Test cases
```

## Usage

There are batch files in the folder for convenience. You can run them from any directory you like. Additionally for `build.bat` files, you can specify the very class you want to (re)-compile.

Here are some examples of using the batch files:

```bash
# Assume you are in the current folder

# Generate lexer and parser
.\generate.bat

# Compile all the classes
.\build.bat

# Run with test case in testcases\pow.obr
.\run.bat .\testcases\pow.obr

# Assume you suddenly found a bug in the .cup file and regenerated theh Parser class, now you need to recompile it
.\build.bat Parser
```

The compiled `Main` class have several arguments to control the behavior of the parser:

```text
Usage: java Main [arguments] <inputfile>
Options:
  -h, --help                Print this help message
  -e, --encoding <encoding> Specify the encoding of the input file
```

When running the program via `run.bat`, there are no worries about the classpath, the current path, or the path of the input file.

The batch script has been designed with care and can convert all paths to absolute paths and get the dependencies based on the path of the script itself. It also append the class path for you so there is no need for `-cp` argument.
