package rpn.operators;

public class MinusOperator extends Operator
{

    @Override
    public double evaluate(double left, double right)
    {
        return left - right;
    }
}
