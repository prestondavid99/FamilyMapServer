package dataaccess;

import model.Event;

import java.sql.Connection;

/**
 * Connects Event model class to the database
 */
public class EventDao {
    private Connection connection;

    public EventDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates an Event to insert into the database.
     *
     * @param event the Event to be inserted
     */
    public void createEvent(Event event) {}

    /**
     * Clears the table.
     */
    public void clearTable(){}

    /**
     * Retrieves an Event from the database using the eventID.
     *
     * @param eventID the Event's ID to locate
     * @return the Event
     */
    public Event getEvent(String eventID) {
        return null;
    }

    /**
     * Retrieves all Events from the database using the AuthToken.
     *
     * @param authtoken the AuthToken
     * @return an array of Events
     */
    public Event[] getAllEvents(String authtoken) {
        return null;
    }
}
