package rpn.event;

public class ResultDisplayEvent implements Event
{
    private double result;


    public ResultDisplayEvent(double result)
    {
        this.result = result;
    }

    public double getResult()
    {
        return result;
    }
}
