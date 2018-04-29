package rpn;

import org.junit.Assert;
import org.junit.Test;
import rpn.calculator.CalculatorError.CalculationError;
import rpn.calculator.CalculatorError.FormatError;

import static rpn.CLI.evaluate;

public class CLITest
{

    // Addition's tests

    @Test
    public void should_evaluate_simple_addition() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("17 5 +"), 22.0, 0);
    }

    @Test
    public void should_evaluate_double_addition() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("17.3 3 +"), 20.3, 0);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_addition() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("17.3 3 6 4 + + +"), 30.3, 0);
    }

    @Test
    public void should_evaluate_negative_numbers_addition() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("-4 5 +"), 1.0, 0);
    }

    // Subtraction's tests

    @Test
    public void should_evaluate_simple_subtraction() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("3 2 -"), 1.0, 0);
    }

    @Test
    public void should_evaluate_double_subtraction() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("17.3 2 -"), 15.3, 0);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_subtraction() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("6 2 3 + -"), 1, 0);
    }

    @Test
    public void should_evaluate_negative_numbers_subtraction() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("-4 5 -"), -9, 0);
    }

    // Multiplication's tests

    @Test
    public void should_evaluate_simple_multiplication() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("3 2 *"), 6, 0);
    }

    @Test
    public void should_evaluate_double_multiplication() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("17.3 2 *"), 34.6, 0);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_multiplication() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("3 4 2 * *"), 24, 0);
    }

    @Test
    public void should_evaluate_negative_numbers_multiplication() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("-4 5 *"), -20, 0);
    }

    // Division's tests

    @Test
    public void should_evaluate_simple_division() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("3 2 /"), 1.5, 0);
    }

    @Test
    public void should_evaluate_double_division() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("6.4 2 /"), 3.2, 0);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_division() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("12 4 2 / /"), 6, 0);
    }

    @Test
    public void should_evaluate_negative_numbers_division() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("-4 2 /"), -2, 0);
    }

    @Test(expected = CalculationError.class)
    public void should_throw_exception_when_dividing_by_zero() throws CalculationError, FormatError
    {
        evaluate("3 0 /");
    }

    // Special cases

    @Test
    public void should_evaluate_multiple_operators() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("3 5 8 * 7 + *"), 141, 0);
    }


    @Test
    public void should_print_evaluate_other_separator() throws CalculationError, FormatError
    {
        Assert.assertEquals(evaluate("3,5,+"), 8.0, 0);
    }

}