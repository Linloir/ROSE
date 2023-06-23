package type;

import java.util.*;
import env.*;
import lexer.Identifier;

public class ProcedureType extends Type {
    ArrayList<Declaration> params;

    public ProcedureType(ArrayList<Declaration> params) {
        super("procedure");
        this.params = new ArrayList<Declaration>();
        for (Declaration param : params) {
            this.params.add(param);
        }
    }

    public static ProcedureType rawSig(ArrayList<Type> types) {
        ArrayList<Declaration> params = new ArrayList<Declaration>();
        int i = 0;
        for (Type type : types) {
            params.add(new Declaration(new Identifier("__" + i + "__"), type, null));
            i += 1;
        }
        return new ProcedureType(params);
    }

    public ArrayList<Declaration> getParams() {
        return params;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        if (!(obj instanceof ProcedureType)) {
            return false;
        }
        ProcedureType other = (ProcedureType) obj;
        for (int i = 0; i < params.size(); i += 1) {
            if (!params.get(i).getType().equals(other.params.get(i).getType())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        String result = "procedure (";
        for (Declaration param : params) {
            result += param.getType() + ", ";
        }
        if (params.size() > 0) {
            result = result.substring(0, result.length() - 2);
        }
        return result + ")";
    }
}
