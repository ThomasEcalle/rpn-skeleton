package rpn;


import java.util.*;
import java.util.function.DoubleBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI
{
    public static class ArgumentsFormatter
    {
        private String arguments;

        public ArgumentsFormatter(String arguments)
        {

            this.arguments = arguments;
        }

        public Stack<String> format()
        {
            //TODO: Check arguments
            //TODO: Stream Java8

            final Stack<String> stack = new Stack<>();

            final List<String> list = Arrays.asList(arguments.split(" "));
            Collections.reverse(list);
            stack.addAll(list);

            return stack;
        }
    }

    public static class Calculator
    {

        private Stack<String> operables;

        public Calculator(Stack<String> operables)
        {
            this.operables = operables;
        }

        public double evaluate()
        {
            final Stack<Double> stack = new Stack<>();

            while (!operables.isEmpty())
            {
                final String operable = operables.pop();
                if (Operator.isOperator(operable))
                {
                    final Operator operator = Operator.getFromString(operable);

                    final Double second = stack.pop();
                    final Double first = stack.pop();

                    if (second == null || first == null)
                    {
                        throw new IllegalArgumentException("Il manque au moins une opérande pour que l'opération soit correcte");
                    }

                    final Double result = operator.operate(first, second);
                    stack.push(result);
                } else
                {
                    stack.push(Double.valueOf(operable));
                }

            }

            if (stack.size() == 1)
            {
                return stack.pop();
            } else
            { //TODO print error
                throw new IllegalStateException("" + Stream.of(stack.toArray()).map(Object::toString).collect(Collectors.joining(" ")));
            }

        }
    }

    public enum Operator
    {

        PLUS("+", Double::sum),
        MINUS("-", (left, right) -> left - right),
        TIMES("*", (left, right) -> left * right),
        DIVIDE("/", (left, right) -> right != 0., (left, right) -> left / right),
        POWER("**", (left, right) -> Math.pow(left, right));

        private interface OperandChecker
        {
            boolean isValid(Double left, Double right);
        }

        private static Map<String, Operator> lookup = new HashMap<>();

        static
        {
            for (Operator operator : Operator.values())
            {
                lookup.put(operator.operator, operator);
            }
        }


        private final String operator;
        private final OperandChecker operandChecker;
        private final DoubleBinaryOperator operation;

        Operator(String operator, OperandChecker operandChecker, DoubleBinaryOperator operation)
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

        public double operate(Double left, Double right)
        {
            if (left != null && right != null && (operandChecker == null || operandChecker.isValid(left, right)))
            {
                return this.operation.applyAsDouble(left, right);
            }

            throw new IllegalArgumentException("");
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

    public static final void main(String[] args)
    {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        System.out.println("About to evaluate '" + expression + "'");

        if (expression.isEmpty())
        {
            System.out.println("Please enter a non empty expression");
            return;
        }

        try
        {
            System.out.println("> " + evaluate(expression));
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

    static double evaluate(String expression) throws ArithmeticException
    {
        final ArgumentsFormatter argumentsFormatter = new ArgumentsFormatter(expression);

        final Calculator calculator = new Calculator(argumentsFormatter.format());

        return calculator.evaluate();

    }
}
