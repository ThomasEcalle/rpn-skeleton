package rpn.event;

public class InputEvent implements Event
{
    private String expression;

    public InputEvent(String expression)
    {
        this.expression = expression;
    }

    public String getExpression()
    {
        return expression;
    }
}
