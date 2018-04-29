package rpn.calculator;

import rpn.calculator.CalculatorError.CalculationError;
import rpn.calculator.CalculatorError.FormatError;
import rpn.calculator.formatter.Formatter;
import rpn.operators.Operator;

import java.util.Stack;

public final class Calculator
{
    private Stack<String> operables;
    private Formatter formatter;

    public Calculator(String arguments)
    {
        formatter = new Formatter(arguments);
    }

    public double evaluate() throws FormatError, CalculationError
    {
        this.operables = formatter.format();

        final Stack<Double> stack = new Stack<>();

        while (!operables.isEmpty())
        {
            final String operable = operables.pop();
            final Operator operator = Operator.getFromString(operable);

            if (operator != null)
            {
                final Double second = stack.pop();

                if (!stack.isEmpty())
                {
                    final Double first = stack.pop();
                    final Double result = operator.operate(first, second);

                    stack.push(result);
                }
                else
                {
                    throw new FormatError("One or more operands are missing for the expression to be correct");
                }
            }
            else
            {
                stack.push(Double.valueOf(operable));
            }

        }

        if (stack.size() == 1)
        {
            return stack.pop();
        }
        else
        {
            throw new CalculationError("Missing some operands or operators for the expression to be correct");
        }

    }
}
