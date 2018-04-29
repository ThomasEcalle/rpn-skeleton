package rpn;


import rpn.calculator.Calculator;
import rpn.calculator.CalculatorError.CalculationError;
import rpn.calculator.CalculatorError.FormatError;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI
{
    public static final void main(String[] args)
    {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");

        try
        {
            System.out.println("> " + evaluate(expression));
        }
        catch (CalculationError error)
        {
            System.out.println(error.getErrorMessage());
        }
        catch (FormatError error)
        {
            System.out.println(error.getErrorMessage());
        }

    }

    static double evaluate(String expression) throws CalculationError, FormatError
    {
        final Calculator calculator = new Calculator(expression);

        return calculator.evaluate();
    }
}
