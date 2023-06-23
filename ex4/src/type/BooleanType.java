package type;

public class BooleanType extends Type {
    public BooleanType() {
        super("boolean");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        return obj instanceof BooleanType;
    }

    @Override
    public int hashCode() {
        return super.toString().hashCode();
    }
}
