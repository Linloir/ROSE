package env;

import java.util.*;
import exceptions.*;
import type.*;

public class Env {

    private Hashtable<String, Declaration> decls;
    protected Env prev;

    /**
     * Creates a new embedded environment based on the current environment.
     * 
     * @param cur The current environment.
     * @return The new environment.
     */
    public Env(Env cur) {
        decls = new Hashtable<String, Declaration>();
        prev = cur;
    }

    /**
     * Puts an declaration in the environment.
     * 
     * @param s The lexeme of the declaration.
     * @param t The declaration.
     */
    public void putDecl(String s, Declaration t) throws OberonException {
        if (decls.containsKey(s.toLowerCase()))
            throw new OberonException();
        decls.put(s.toLowerCase(), t);
    }

    /**
     * Gets a declaration from the environment.
     * 
     * @param s The lexeme of the declaration.
     * @return The declaration.
     */
    public Declaration getDecl(String s) {
        String key = s.toLowerCase();
        for (Env e = this; e != null; e = e.prev) {
            Declaration found = e.decls.get(key);
            if (found != null)
                return found;
        }
        return null;
    }
}