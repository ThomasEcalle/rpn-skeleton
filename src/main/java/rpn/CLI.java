package rpn;


import rpn.event.*;
import rpn.orchestrator.Orchestrator;
import rpn.tokenizer.Tokenizer;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CLI
{
    public static final void main(String[] args)
    {
        String expression = Stream.of(args).collect(Collectors.joining(" "));
        evaluate(expression);
    }

    public static void evaluate(String expression)
    {
        new CLI().process(expression, null);
    }

    /**
     * This method is used fort Tests in order to inject a ResultDisplayer
     *
     * @param expression
     * @param resultDisplayerImpl
     */
    public static void evaluate(String expression, OnEventListener<ResultDisplayEvent> resultDisplayerImpl)
    {
        new CLI().process(expression, resultDisplayerImpl);
    }

    public void process(String expression, OnEventListener<ResultDisplayEvent> resultDisplayer)
    {

        EventDispatcher dispatcher = new EventDispatcher();

        Tokenizer tokenizer = new Tokenizer(dispatcher);
        Orchestrator orchestrator = new Orchestrator(dispatcher);

        if (resultDisplayer == null)
        {
            resultDisplayer = new ResultDisplayer();
        }

        dispatcher.registerChannel(InputEvent.class, tokenizer);

        dispatcher.registerChannel(TokenEvent.class, orchestrator);
        dispatcher.registerChannel(EndTokenEvent.class, orchestrator);

        dispatcher.registerChannel(ResultDisplayEvent.class, resultDisplayer);
        dispatcher.registerChannel(ErrorEvent.class, resultDisplayer);

        dispatcher.dispatch(new InputEvent(expression));
    }
}
