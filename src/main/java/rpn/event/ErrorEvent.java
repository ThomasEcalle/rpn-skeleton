package rpn.event;

public class ErrorEvent implements Event
{
    private String error;

    public ErrorEvent(String expression)
    {
        this.error = expression;
    }

    public String getError()
    {
        return error;
    }
}
