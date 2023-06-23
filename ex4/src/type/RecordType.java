package type;

import java.util.*;

import env.*;

public class RecordType extends Type {
    private Hashtable<String, Declaration> fields;

    public RecordType(Hashtable<String, Declaration> fields) {
        super("record");
        this.fields = fields;
    }

    public Hashtable<String, Declaration> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RecordType)) {
            return false;
        }
        RecordType other = (RecordType) obj;
        return fields.equals(other.fields);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        String result = "{ ";
        Enumeration<String> keys = fields.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            result += key + ": " + fields.get(key).getType();
            if (keys.hasMoreElements()) {
                result += ", ";
            }
        }
        return result + " }";
    }
}
