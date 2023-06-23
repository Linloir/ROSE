package type;

public abstract class Type {
    private String name;

    public Type(String name) {
        this.name = name;
    }

    /**
     * Checks if this type is equal to another type.
     * 
     * @param obj The other type.
     * @return True if the types are equal, false otherwise.
     */
    @Override
    public abstract boolean equals(Object obj);

    /**
     * Hashes this type.
     * 
     * @return The hash code of this type.
     */
    @Override
    public abstract int hashCode();

    @Override
    public String toString() {
        return name;
    }
}
