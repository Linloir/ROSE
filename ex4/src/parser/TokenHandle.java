package parser;

import exceptions.*;
import lexer.*;

public interface TokenHandle {
    public Token nextToken() throws java.io.IOException, OberonException;
}
