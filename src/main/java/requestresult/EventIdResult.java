package requestresult;

/**
 * Outcome of the EventId Request
 */
public class EventIdResult extends Result{
    private String associatedUsername;
    private String eventID;
    private String personID;
    private float latitude;
    private float longitude;
    private String country;
    private String city;
    private String eventType;
    private int year;

    public EventIdResult(String message, boolean success) {
        super(message, success);
    }
}
