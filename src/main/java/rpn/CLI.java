package rpn;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI
{

    public static final void main(String[] args)
    {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");

        if (expression.isEmpty())
        {
            System.out.println("Please enter a non empty expression");
            return;
        }

        double result;
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
        catch (IllegalArgumentException exception)
        {
            System.out.printf("error while calculating : " + exception.getMessage());
            exception.printStackTrace();

        }
    }

    public enum Operator
    {

        PLUS("+"),
        MINUS("-"),
        TIMES("*"),
        DIVIDE("/"),
        POWER("**");

        private static Map<String, Operator> lookup = new HashMap<>();

        static
        {
            for (Operator operator : Operator.values())
            {
                lookup.put(operator.operator, operator);
            }
        }

        private final String operator;

        Operator(String operator)
        {
            this.operator = operator;
        }

        public static Operator getFromString(String operator)
        {
            return lookup.get(operator);
        }

        public static boolean isOperator(String operator)
        {
            return getFromString(operator) != null;
        }
    }

    static double evaluate(String expression) throws ArithmeticException, IllegalArgumentException
    {
        final Stack<Double> stack = new Stack<>();
        final String[] calculatedValues = expression.split(" ");

        for (String operation : calculatedValues)
        {
            if (Operator.isOperator(operation))
            {
                final Operator operator = Operator.getFromString(operation);

                final Double second = stack.pop();
                final Double first = stack.pop();

                final Double result = operate(first, second, operator);
                stack.push(result);
            }
            else
            {
                try
                {
                    stack.push(Double.valueOf(operation));
                }
                catch (NumberFormatException exception)
                {
                    throw new IllegalArgumentException("'" + operation + "'" + " is not a valid argument");
                }

            }
        }

        return stack.pop();
    }

    private static Double operate(Double value1, Double value2, Operator operator) throws ArithmeticException
    {
        switch (operator)
        {
            case TIMES:
                return value1 * value2;
            case PLUS:
                return value1 + value2;

            case MINUS:
                return value1 - value2;

            default:
                if (value2 == 0)
                {
                    throw new ArithmeticException("Impossible de diviser par 0");
                }
                return value1 / value2;
        }
    }
}
