package dataaccess;

import model.Event;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Connects Event model class to the database
 */
public class EventDAO {
    private final Connection connection;

    public EventDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates an Event to insert into the database.
     *
     * @param event the Event to be inserted
     */
    public void insert(Event event) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Event (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, event.getEventID());
            stmt.setString(2, event.getAssociatedUsername());
            stmt.setString(3, event.getPersonID());
            stmt.setFloat(4, event.getLatitude());
            stmt.setFloat(5, event.getLongitude());
            stmt.setString(6, event.getCountry());
            stmt.setString(7, event.getCity());
            stmt.setString(8, event.getEventType());
            stmt.setInt(9, event.getYear());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }


    /**
     * Clears the table.
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Event";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }


    /**
     * Retrieves an Event from the database using the eventID.
     *
     * @param eventID the Event's ID to locate
     * @return the Event
     */
    public Event find(String eventID) throws DataAccessException {
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Event WHERE eventID = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                return event;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an event in the database");
        }

    }


    /**
     * Retrieves all Events from the database using the AuthToken.
     *
     * @param authtoken the AuthToken
     * @return an array of Events
     */
    public Event[] getAllEvents(String authtoken) throws DataAccessException {
        Event[] eventArray = new Event[0];
        Event event;
        ResultSet rs;
        String sql = "SELECT * FROM Event WHERE authtoken = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authtoken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event(rs.getString("eventID"), rs.getString("associatedUsername"),
                        rs.getString("personID"), rs.getFloat("latitude"), rs.getFloat("longitude"),
                        rs.getString("country"), rs.getString("city"), rs.getString("eventType"),
                        rs.getInt("year"));
                event.addX(eventArray.length, eventArray, event);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding a person in the database");
        }
        return eventArray;
    }
}
