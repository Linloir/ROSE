package type;

import java.util.*;

import env.*;

public class RecordType extends Type {
    private Hashtable<String, Declaration> fields;

    public RecordType(Hashtable<String, Declaration> fields) {
        super("record");
        this.fields = new Hashtable<String, Declaration>();
        Enumeration<String> keys = fields.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            this.fields.put(key.toLowerCase(), fields.get(key));
        }
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
        if (fields.size() != other.fields.size()) {
            return false;
        }
        Enumeration<String> keys = fields.keys();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            if (!other.fields.containsKey(key)) {
                return false;
            }
            if (!fields.get(key).getType().equals(other.fields.get(key).getType())) {
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
