package rpn;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static rpn.CLI.evaluate;

public class CLITest
{

    @Test
    public void should_evaluate_single_digit_constant()
    {
        assertThat(evaluate("5")).isEqualTo(5);
    }

    @Test
    public void should_evaluate_multiple_digits_constant()
    {
        assertThat(evaluate("17")).isEqualTo(17);
    }

    // Addition's tests

    @Test
    public void should_evaluate_simple_addition()
    {
        assertThat(evaluate("17 5 +")).isEqualTo(22);
    }

    @Test
    public void should_evaluate_double_addition()
    {
        assertThat(evaluate("17.3 3 +")).isEqualTo(20.3);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_addition()
    {
        assertThat(evaluate("17.3 3 6 4 + + +")).isEqualTo(30.3);
    }

    @Test
    public void should_evaluate_negative_numbers_addition()
    {
        assertThat(evaluate("-4 5 +")).isEqualTo(1);
    }

    // Subtraction's tests

    @Test
    public void should_evaluate_simple_subtraction()
    {
        assertThat(evaluate("3 2 -")).isEqualTo(1);
    }

    @Test
    public void should_evaluate_double_subtraction()
    {
        assertThat(evaluate("17.3 2 -")).isEqualTo(15.3);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_subtraction()
    {
        assertThat(evaluate("6 2 3 + - ")).isEqualTo(1);
    }

    @Test
    public void should_evaluate_negative_numbers_subtraction()
    {
        assertThat(evaluate("-4 5 -")).isEqualTo(-9);
    }

    // Multiplication's tests

    @Test
    public void should_evaluate_simple_multiplication()
    {
        assertThat(evaluate("3 2 *")).isEqualTo(6);
    }

    @Test
    public void should_evaluate_double_multiplication()
    {
        assertThat(evaluate("17.3 2 *")).isEqualTo(34.6);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_multiplication()
    {
        assertThat(evaluate("3 4 2 * *")).isEqualTo(24);
    }

    @Test
    public void should_evaluate_negative_numbers_multiplication()
    {
        assertThat(evaluate("-4 5 *")).isEqualTo(-20);
    }

    // Division's tests

    @Test
    public void should_evaluate_simple_division()
    {
        assertThat(evaluate("3 2 /")).isEqualTo(1.5);
    }

    @Test
    public void should_evaluate_double_division()
    {
        assertThat(evaluate("6.4 2 /")).isEqualTo(3.2);
    }

    @Test
    public void should_evaluate_more_than_two_numbers_division()
    {
        assertThat(evaluate("12 4 2 / /")).isEqualTo(6);
    }

    @Test
    public void should_evaluate_negative_numbers_division()
    {
        assertThat(evaluate("-4 2 /")).isEqualTo(-2);
    }


    @Test(expected = ArithmeticException.class)
    public void should_throw_exception_when_dividing_by_zero()
    {
        evaluate("3 0 /");
    }


    // Special cases

    @Test
    public void should_evaluate_multiple_operators()
    {
        assertThat(evaluate("3 5 8 * 7 + *")).isEqualTo(141);
    }


    @Test(expected = IllegalArgumentException.class)
    public void should_throw_illegal_argument_excetion()
    {
        evaluate("7 2 i + +");
    }

}