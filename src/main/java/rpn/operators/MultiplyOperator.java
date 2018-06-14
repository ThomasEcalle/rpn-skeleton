package rpn.operators;

public class MultiplyOperator extends Operator
{
    @Override
    public double evaluate(double left, double right)
    {
        return left * right;
    }
}
