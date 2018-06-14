package rpn.tokenizer;

import rpn.EventDispatcher;
import rpn.event.*;

import java.util.Arrays;
import java.util.List;

public class Tokenizer implements OnEventListener<InputEvent>
{
    private EventDispatcher eventDispatcher;

    public Tokenizer(EventDispatcher eventDispatcher)
    {
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void onEvent(InputEvent event)
    {
        split(event.getExpression());
    }

    private void split(String expression)
    {
        List<String> tokenList = createListUsingSeparators(expression);
        if (tokenList != null)
        {
            for (String token : tokenList)
            {
                eventDispatcher.dispatch(new TokenEvent(new Token(token)));
            }

            eventDispatcher.dispatch(new EndTokenEvent());
        }
    }

    private List<String> createListUsingSeparators(String expression)
    {
        if (expression == null || expression.isEmpty())
        {
            eventDispatcher.dispatch(new ErrorEvent("You must pass a valid RPN expression"));
            return null;
        }

        List<String> list = null;

        final Separator[] separatorValues = Separator.values();

        for (Separator separator : separatorValues)
        {
            if (expression.contains(separator.value))
            {
                list = Arrays.asList(expression.split(separator.value));
            }
        }

        if (list == null)
        {
            eventDispatcher.dispatch(new ErrorEvent("There is an error with your separator.\n Please use one of them : " + Arrays.toString(separatorValues)));
        }

        return list;
    }


}
