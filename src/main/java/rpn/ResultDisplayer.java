package rpn;

import rpn.event.ErrorEvent;
import rpn.event.Event;
import rpn.event.OnEventListener;
import rpn.event.ResultDisplayEvent;

public class ResultDisplayer implements OnEventListener
{

    @Override
    public void onEvent(Event event)
    {
        if (event instanceof ResultDisplayEvent)
        {
            ResultDisplayEvent resultDisplayEvent = (ResultDisplayEvent) event;
            System.out.println(resultDisplayEvent.getResult());
        }
        else if (event instanceof ErrorEvent)
        {
            ErrorEvent errorEvent = (ErrorEvent) event;
            System.out.println(errorEvent.getError());
        }
    }
}
