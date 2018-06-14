package rpn.operators;

public abstract class Operator
{
    public abstract double evaluate(double left, double right) throws Exception;
}
