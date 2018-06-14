package rpn.tokenizer;

public class Token
{
    private String token;

    public Token(String token)
    {
        this.token = token;
    }

    public String getValue()
    {
        return token;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Token)
        {
            Token other = (Token) obj;
            return other.token.equals(this.token);
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return token.hashCode();
    }

    public boolean isOperand()
    {
        try
        {
            Double.parseDouble(token);
            return true;
        }
        catch (Exception exception)
        {
            return false;
        }
    }
}
