package type;

public class AnyType extends Type {
    public AnyType() {
        super("any");
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Type) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
