package rpn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI
{
    public static final void main(String[] args)
    {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");

        double result = 0;
        try
        {
            result = evaluate(expression);
            System.out.println("> " + result);
        }
        catch (ArithmeticException exception)
        {
            System.out.printf("error while calculating : " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    static double evaluate(String expression) throws ArithmeticException
    {
        final Stack<Double> stack = new Stack<>();
        final List<Double> numbers = new ArrayList<>();

        final String[] calculatedValues = expression.split(" ");

        for (String operation : calculatedValues)
        {
            if (isOperator(operation))
            {
                while (!stack.isEmpty())
                {
                    numbers.add(stack.pop());
                }

                final Double result = operate(numbers, operation);
                stack.push(result);
            }
            else
            {
                stack.push(Double.valueOf(operation));
            }
        }

        return stack.pop();
    }

    private static Double operate(List<Double> numbers, String operator) throws ArithmeticException
    {
        return numbers.stream().reduce(0.0, (total, number) ->
        {
            switch (operator)
            {
                case "*":
                    total *= number;
                    break;
                case "+":
                    total += number;
                    break;
                case "-":
                    total -= number;
                    break;
                default:
                    if (number == 0)
                    {
                        throw new ArithmeticException("Impossible de diviser par 0");
                    }
                    total /= number;
                    break;
            }

            return total;
        });
    }

    private static boolean isOperator(String operation)
    {
        return operation.equals("*") || operation.equals("+") || operation.equals("-") || operation.equals("/");
    }
}
