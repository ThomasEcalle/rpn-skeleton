package rpn.operators;

import rpn.tokenizer.Token;

import java.util.HashMap;

public class OperatorRegistry
{
    private final Token PLUS;
    private final Token MINUS;
    private final Token MULTIPLY;
    private final Token DIVIDE;

    private HashMap<Token, Operator> operators;

    public OperatorRegistry()
    {
        PLUS = new Token("+");
        MINUS = new Token("-");
        MULTIPLY = new Token("*");
        DIVIDE = new Token("/");

        operators = new HashMap<>();
        operators.put(PLUS, new PlusOperator());
        operators.put(MINUS, new MinusOperator());
        operators.put(MULTIPLY, new MultiplyOperator());
        operators.put(DIVIDE, new DivideOperator());
    }

    public Operator getOperator(Token token)
    {
        return operators.get(token);
    }


}
