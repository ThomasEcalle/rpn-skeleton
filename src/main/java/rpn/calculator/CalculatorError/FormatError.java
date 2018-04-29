package rpn.calculator.CalculatorError;

public final class FormatError extends CalculatorError
{

    public FormatError(String errorMessage)
    {
        super(errorMessage);
    }

    @Override
    public String getErrorMessage()
    {
        return errorMessage;
    }
}
