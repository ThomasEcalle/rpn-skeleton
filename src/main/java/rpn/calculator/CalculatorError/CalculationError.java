package rpn.calculator.CalculatorError;

public class CalculationError extends CalculatorError
{
    public CalculationError(String errorMessage)
    {
        super(errorMessage);
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }
}
