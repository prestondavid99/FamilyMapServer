package model;

/**
 * A model for Events in the database
 *
 *
 */
public class Event {
    /**
     *  the Event's ID
     */
    private String eventID;
    /**
     * username associated with the event
     */
    private String associatedUsername;
    /**
     * the Person's ID
     */
    private String personID;
    /**
     * latitude of the Event on the earth
     */
    private float latitude;
    /**
     * longitude of the Event on the earth
     */
    private float longitude;
    /**
     * country where the Event takes place
     */
    private String country;
    /**
     * city where the Event takes place
     */
    private String city;
    /**
     * the type of event
     */
    private String eventType;
    /**
     * the year of the event
     */
    private int year;
    /**
     * Creates an Event object using the Event's details
     */
    public Event(String eventID, String associatedUsername, String personID, float latitude, float longitude, String country, String city, String eventType, int year) {
        this.eventID = eventID;
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
        this.city = city;
        this.eventType = eventType;
        this.year = year;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
