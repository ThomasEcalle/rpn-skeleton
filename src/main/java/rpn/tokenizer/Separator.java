package rpn.tokenizer;

public enum Separator
{
    SPACE(" "),
    COMMA(",");

    public final String value;

    Separator(String value)
    {
        this.value = value;
    }

    public boolean isValidSeparator(String value)
    {
        for (Separator sep : Separator.values())
        {
            if (sep.value.equals(value))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
