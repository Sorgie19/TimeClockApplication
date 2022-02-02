package employee;

public enum Position
{
    Administrator("Administrator"),

    Employee("Employee");

    private String value;

    /**
     * Constructs an instance of this enum.
     * @param value value of the part
     */
    Position(String value)
    {
        this.value = value;
    }

    /**
     * Retrieves the enum value as a string.
     * @return the value of this part. Will never be <code>null</code>, empty, or blank.
     */
    @Override
    public String toString()
    {
        return value;
    }
}
