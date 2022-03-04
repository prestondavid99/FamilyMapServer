package requestresult;

import model.Event;

/**
 * Outcome of the Event Request
 */
public class EventResult extends Result {
    private Event[] data;

    public EventResult(String message, boolean success) {
        super(message, success);
    }
}
