package rpn.operators;

public class DivideOperator extends Operator
{

    @Override
    public double evaluate(double left, double right) throws Exception
    {
        if (right == 0) throw new Exception();

        return left / right;
    }
}
