package type;

public class IntegerType extends Type {
    public IntegerType() {
        super("integer");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        return obj instanceof IntegerType;
    }

    @Override
    public int hashCode() {
        return super.toString().hashCode();
    }
}
