package env;

import lexer.*;
import type.*;
import java.util.*;

import exceptions.*;

public class ProcCall {
    private Identifier id;
    private ProcedureType signature;
    private Env env;

    public ProcCall(Identifier id, ArrayList<Type> params, Env env) {
        this.id = id;
        this.signature = ProcedureType.rawSig(params);
        this.env = env;
    }

    public Identifier getId() {
        return id;
    }

    public ProcedureType getSignature() {
        return signature;
    }

    public void verify() throws OberonException {
        Declaration decl = env.getDecl(id.getLexeme());
        if (decl == null) {
            throw new UnknownIdentifier(id);
        }
        if (!(decl.getType() instanceof ProcedureType)) {
            throw new UnexpectedType("procedure", decl.getType(), id.getLine(), id.getColumn());
        }
        ProcedureType procType = (ProcedureType) decl.getType();
        if (!procType.equals(signature)) {
            throw new UnexpectedType(decl.getType(), signature, id.getLine(), id.getColumn());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProcCall)) {
            return false;
        }
        ProcedureType other = (ProcedureType) obj;
        return signature.equals(other);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        String result = id + "(";
        for (Declaration param : signature.getParams()) {
            result += param.getType() + ", ";
        }
        if (signature.getParams().size() > 0) {
            result = result.substring(0, result.length() - 2);
        }
        result += ")";
        return result;
    }
}
