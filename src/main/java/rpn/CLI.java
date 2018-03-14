package rpn;

import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI
{
    public static final void main(String[] args)
    {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");


        long result = 0;
        try
        {
            result = evaluate(expression);
        }
        catch (ArithmeticException exception)
        {
            System.out.printf("error while calculating : " + exception.getMessage());
            exception.printStackTrace();
        }
        System.out.println("> " + result);
    }

    static long evaluate(String expression) throws ArithmeticException
    {
        final Stack<Long> stack = new Stack<>();
        final String[] array = expression.split(" ");

        for (String operation : array)
        {
            if (isOperator(operation))
            {
                final Long second = stack.pop();
                final Long first = stack.pop();
                final Long result = operate(first, second, operation);
                stack.push(result);
            }
            else
            {
                stack.push(Long.valueOf(operation));
            }
        }

        return stack.pop();
    }

    private static Long operate(Long first, Long second, String operator) throws ArithmeticException
    {
        switch (operator)
        {
            case "*":
                return first * second;
            case "+":
                return first + second;
            case "-":
                return first - second;
            default:
                if (second == 0)
                {
                    throw new ArithmeticException("Impossible de diviser par 0");
                }
                return first / second;
        }
    }

    private static boolean isOperator(String operation)
    {
        return operation.equals("*") || operation.equals("+") || operation.equals("-") || operation.equals("/");
    }
}
