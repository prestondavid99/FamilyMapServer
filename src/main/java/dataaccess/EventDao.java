package dataaccess;

import model.Event;

import java.sql.Connection;

public class EventDao {
    private Connection connection;

    public EventDao(Connection connection) {
        this.connection = connection;
    }

    public void createEvent(Event event) {

    }

    public Event getEvent(String eventID) {
        return null;
    }
}
