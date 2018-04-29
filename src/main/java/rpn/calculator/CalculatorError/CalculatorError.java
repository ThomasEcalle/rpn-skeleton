package rpn.calculator.CalculatorError;

import rpn.CLI;

public abstract class CalculatorError extends Exception
{
    protected String errorMessage;

    public CalculatorError(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public abstract String getErrorMessage();
}
