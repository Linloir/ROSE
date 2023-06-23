package env;

import java.util.*;
import lexer.*;
import type.*;

public class Declaration {
    public static enum Modifiers {
        CONST,
        VAR,
    }

    private Identifier id;
    private String lexeme;
    private Integer line;
    private Integer column;
    private Type type;
    private Hashtable<String, Object> extAttrs;

    public Declaration(Identifier id, Type type, Hashtable<String, Object> extAttrs) {
        this.id = id;
        this.lexeme = id.getLexeme();
        this.line = id.getLine();
        this.column = id.getColumn();
        this.type = type;
        this.extAttrs = extAttrs == null ? new Hashtable<String, Object>() : extAttrs;
    }

    public Identifier getId() {
        return id;
    }

    public String getLexeme() {
        return lexeme;
    }

    public Integer getLine() {
        return line;
    }

    public Integer getColumn() {
        return column;
    }

    public Type getType() {
        return type;
    }

    public Object getExtAttr(String key) {
        return extAttrs.get(key);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Declaration)) {
            return false;
        }
        Declaration other = (Declaration) obj;
        return type.equals(other.type) && extAttrs.equals(other.extAttrs);
    }
    
}
