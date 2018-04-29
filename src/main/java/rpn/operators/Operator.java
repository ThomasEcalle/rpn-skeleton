package rpn.operators;

import rpn.calculator.CalculatorError.CalculationError;

import java.util.function.DoubleBinaryOperator;

public enum Operator
{
    PLUS("+", Double::sum),
    MINUS("-", (left, right) -> left - right),
    TIMES("*", (left, right) -> left * right),
    DIVIDE("/", (left, right) -> right != 0., (left, right) -> left / right),
    POWER("**", Math::pow);

    private final String operator;
    private final OperandsValidityChecker operandChecker;
    private final DoubleBinaryOperator operation;

    Operator(String operator, OperandsValidityChecker operandChecker, DoubleBinaryOperator operation)
    {
        this.operator = operator;
        this.operandChecker = operandChecker;
        this.operation = operation;
    }

    Operator(String operator, DoubleBinaryOperator operation)
    {
        this.operator = operator;
        this.operation = operation;
        this.operandChecker = null;
    }

    public double operate(Double left, Double right) throws CalculationError
    {
        if (operandChecker == null || operandChecker.isValid(left, right))
        {
            return this.operation.applyAsDouble(left, right);
        }
        else
        {
            throw new CalculationError("There is some mathematics error, did you try to divide by zero ?");
        }
    }

    public static Operator getFromString(String operator)
    {
        for (Operator op : Operator.values())
        {
            if (op.operator.equals(operator))
            {
                return op;
            }
        }
        return null;
    }
}
