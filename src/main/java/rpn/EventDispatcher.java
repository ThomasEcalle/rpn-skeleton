package rpn;

import javafx.util.Pair;
import rpn.event.Event;
import rpn.event.OnEventListener;

import java.util.ArrayList;

public class EventDispatcher
{
    private ArrayList<Pair<Class, OnEventListener<Event>>> couples;

    public EventDispatcher()
    {
        couples = new ArrayList<>();
    }

    public void registerChannel(Class eventType, OnEventListener onEventListener)
    {
        couples.add(new Pair<>(eventType, onEventListener));
    }

    public void dispatch(Event event)
    {
        for (Pair<Class, OnEventListener<Event>> couple : couples)
        {
            if (couple.getKey().equals(event.getClass()))
            {
                couple.getValue().onEvent(event);
            }
        }
    }
}
