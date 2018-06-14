package rpn;

import org.junit.Assert;
import org.junit.Test;

public class CLITest
{

    // Addition's tests


    @Test
    public void should_evaluate_simple_addition()
    {
        CLI.evaluate(("4 3 +"), event -> Assert.assertEquals(7.0, event.getResult(), 0));
    }


    @Test
    public void should_evaluate_double_addition()
    {
        CLI.evaluate(("17.3 3 +"), event -> Assert.assertEquals(20.3, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_more_than_two_numbers_addition()
    {
        CLI.evaluate(("17.3 3 6 4 + + +"), event -> Assert.assertEquals(30.3, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_negative_numbers_addition()
    {
        CLI.evaluate(("-4 5 +"), event -> Assert.assertEquals(1.0, event.getResult(), 0));
    }

    // Subtraction's tests

    @Test
    public void should_evaluate_simple_subtraction()
    {
        CLI.evaluate(("3 2 -"), event -> Assert.assertEquals(1.0, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_double_subtraction()
    {
        CLI.evaluate(("17.3 2 -"), event -> Assert.assertEquals(15.3, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_more_than_two_numbers_subtraction()
    {
        CLI.evaluate(("6 2 3 + -"), event -> Assert.assertEquals(1.0, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_negative_numbers_subtraction()
    {
        CLI.evaluate(("-4 5 -"), event -> Assert.assertEquals(-9.0, event.getResult(), 0));
    }

    // Multiplication's tests

    @Test
    public void should_evaluate_simple_multiplication()
    {
        CLI.evaluate(("3 2 *"), event -> Assert.assertEquals(6, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_double_multiplication()
    {
        CLI.evaluate(("17.3 2 *"), event -> Assert.assertEquals(34.6, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_more_than_two_numbers_multiplication()
    {
        CLI.evaluate(("3 4 2 * *"), event -> Assert.assertEquals(24, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_negative_numbers_multiplication()
    {
        CLI.evaluate(("-4 5 *"), event -> Assert.assertEquals(-20, event.getResult(), 0));
    }

    // Division's tests

    @Test
    public void should_evaluate_simple_division()
    {
        CLI.evaluate(("3 2 /"), event -> Assert.assertEquals(1.5, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_double_division()
    {
        CLI.evaluate(("6.4 2 /"), event -> Assert.assertEquals(3.2, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_more_than_two_numbers_division()
    {
        CLI.evaluate(("12 4 2 / /"), event -> Assert.assertEquals(6, event.getResult(), 0));
    }

    @Test
    public void should_evaluate_negative_numbers_division()
    {
        CLI.evaluate(("-4 2 /"), event -> Assert.assertEquals(-2, event.getResult(), 0));
    }

    // Special cases

    @Test
    public void should_evaluate_multiple_operators()
    {
        CLI.evaluate(("3 5 8 * 7 + *"), event -> Assert.assertEquals(141, event.getResult(), 0));
    }


    @Test
    public void should_print_evaluate_other_separator()
    {
        CLI.evaluate(("3,5,+"), event -> Assert.assertEquals(8.0, event.getResult(), 0));
    }

}