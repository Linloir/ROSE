package type;

public class ModuleType extends Type {
    public ModuleType() {
        super("module");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnyType) {
            return true;
        }
        return obj instanceof ModuleType;
    }

    @Override
    public int hashCode() {
        return super.toString().hashCode();
    }
}
