package rpn.operators;

public class PlusOperator extends Operator
{
    @Override
    public double evaluate(double left, double right)
    {
        return left + right;
    }
}
