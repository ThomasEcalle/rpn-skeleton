package rpn.orchestrator;

import rpn.EventDispatcher;
import rpn.event.*;
import rpn.operators.Operand;
import rpn.operators.Operator;
import rpn.operators.OperatorRegistry;
import rpn.tokenizer.Token;

import java.util.Stack;

public class Orchestrator implements OnEventListener
{
    private EventDispatcher eventDispatcher;
    private Stack<Operand> operands;
    private OperatorRegistry operatorRegistry;

    public Orchestrator(EventDispatcher eventDispatcher)
    {
        this.eventDispatcher = eventDispatcher;
        this.operands = new Stack<>();
        operatorRegistry = new OperatorRegistry();
    }

    @Override
    public void onEvent(Event event)
    {
        if (event instanceof TokenEvent)
        {
            TokenEvent tokenEvent = (TokenEvent) event;
            Token token = tokenEvent.getToken();

            if (token.isOperand())
            {
                operands.add(new Operand(Double.parseDouble(token.getValue())));
                return;
            }

            if (operatorRegistry.getOperator(token) == null)
            {
                eventDispatcher.dispatch(new ErrorEvent("Unknown value : " + token.getValue()));
                return;
            }


            Operator operator = operatorRegistry.getOperator(token);
            if (operands.size() < 2)
            {
                eventDispatcher.dispatch(new ErrorEvent("One or more operands are missing for the expression to be correct"));
                return;
            }

            Operand right = operands.pop();
            Operand left = operands.pop();

            try
            {
                double result = operator.evaluate(left.getValue(), right.getValue());

                operands.push(new Operand(result));
            }
            catch (Exception exception)
            {
                eventDispatcher.dispatch(new ErrorEvent("Mathematical error, did you try to divide by zero ?"));
            }

        }
        else if (event instanceof EndTokenEvent)
        {
            if (operands.size() != 1)
            {
                eventDispatcher.dispatch(new ErrorEvent("Missing some operands or operators for the expression to be correct"));
                return;
            }
            eventDispatcher.dispatch(new ResultDisplayEvent(operands.pop().getValue()));
        }


    }

}
