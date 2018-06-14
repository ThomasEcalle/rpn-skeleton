package rpn.event;

public interface OnEventListener<T extends Event>
{
    void onEvent(T event);
}
