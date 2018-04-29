package rpn.calculator.formatter;

import rpn.calculator.CalculatorError.FormatError;
import rpn.operators.Operator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Formatter
{
    private String arguments;

    public Formatter(String arguments)
    {
        this.arguments = arguments;
    }

    public Stack<String> format() throws FormatError
    {
        List<String> list = createListUsingSeparators(arguments);

        testElementsOfExpression(list);

        final Stack<String> stack = new Stack<>();
        Collections.reverse(list);
        stack.addAll(list);

        return stack;
    }

    private List<String> createListUsingSeparators(String arguments) throws FormatError
    {
        if (arguments == null || arguments.isEmpty())
        {
            throw new FormatError("You must pass a valid RPN expression");
        }

        List<String> list = null;

        final Separator[] separatorValues = Separator.values();

        for (Separator separator : separatorValues)
        {
            if (arguments.contains(separator.value))
            {
                list = Arrays.asList(arguments.split(separator.value));
            }
        }

        if (list == null)
        {
            throw new FormatError("There is an error with your separator.\n Please use one of them : " + Arrays.toString(separatorValues));
        }

        return list;
    }

    private void testElementsOfExpression(List<String> list) throws FormatError
    {
        for (String element : list)
        {
            try
            {
                Double.valueOf(element);
            }
            catch (NumberFormatException exception)
            {
                if (Operator.getFromString(element) == null)
                {
                    throw new FormatError("There is an error in your format, please use only numbers and operators");
                }
            }
        }
    }
}
