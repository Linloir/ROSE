package type;

public class ArrayType extends Type {
    private Type elementType;

    public ArrayType(Type elementType) {
        super("array");
        this.elementType = elementType;
    }

    public Type getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        if (!(obj instanceof ArrayType)) {
            return false;
        }
        ArrayType other = (ArrayType) obj;
        return elementType.equals(other.elementType);
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return elementType + "[ ]";
    }
}
