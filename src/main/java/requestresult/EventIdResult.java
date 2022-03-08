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
    public EventIdResult(String associatedUsername, String eventID, String personID, float latitude,
                         float longitude, String country, String city, String eventType, int year,
                         String message, boolean success) {
        super(message, success);
        this.associatedUsername = associatedUsername;
        this.eventID = eventID;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }
}
