package type;

public class TypedefType extends Type {
    private Type type;

    public TypedefType(Type type) {
        super("typedef");
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        if (!(obj instanceof TypedefType)) {
            return false;
        }
        TypedefType other = (TypedefType) obj;
        return type.equals(other.type);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "typedef " + type;
    }
}
