package rpn;


import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI {

    public static final void main(String[] args) {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");

        if (expression.isEmpty())
        {
            System.out.println("Please enter a non empty expression");
            return;
        }

        double result;
        try {
            result = evaluate(expression);
            System.out.println("> " + result);
        } catch (ArithmeticException exception) {
            System.out.printf("error while calculating : " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    static double evaluate(String expression) throws ArithmeticException {
        final Stack<Double> stack = new Stack<>();
        final String[] calculatedValues = expression.split(" ");

        for (String operation : calculatedValues) {
            if (isOperator(operation)) {
                final Double second = stack.pop();
                final Double first = stack.pop();

                final Double result = operate(first, second, operation);
                stack.push(result);
            } else {
                stack.push(Double.valueOf(operation));
            }
        }

        return stack.pop();
    }

    private static Double operate(Double value1, Double value2, String operator) throws ArithmeticException {
        switch (operator) {
            case "*":
                return value1 * value2;
            case "+":
                return value1 + value2;

            case "-":
                return value1 - value2;

            default:
                if (value2 == 0) {
                    throw new ArithmeticException("Impossible de diviser par 0");
                }
                return value1 / value2;
        }
    }

    private static boolean isOperator(String operation) {
        return operation.equals("*") || operation.equals("+") || operation.equals("-") || operation.equals("/");
    }
}
