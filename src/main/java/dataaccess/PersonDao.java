package dataaccess;

import model.Event;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Connects Person model class to the database
 */
public class PersonDao {
    private final Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates a Person to be inserted into the database.
     *
     * @param person Person object to be inserted
     */
    public void insert(Person person) throws DatabaseException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Events (EventID, AssociatedUsername, PersonID, Latitude, Longitude, " +
                "Country, City, EventType, Year) VALUES(?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, person.getPersonID());
            stmt.setString(2, person.getAssociatedUsername());
            stmt.setString(3, person.getFirstName());
            stmt.setString(4, person.getLastName());
            stmt.setString(5, person.getGender());
            stmt.setString(6, person.getFatherID());
            stmt.setString(7, person.getMotherID());
            stmt.setString(8, person.getSpouseID());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while inserting an event into the database");
        }
    }

    public void removePerson(Person person) {

    }

    /**
     * Clears the table.
     */
    public void clear() throws DatabaseException {
        String sql = "DELETE FROM Person";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while clearing the Person table");
        }
    }

    /**
     * Retrieves a Person from the database using the Person's ID.
     *
     * @param personID retrieval tool
     * @return the Person object
     */
    public Person find(String personID) throws DatabaseException {
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE PersonID = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, personID);
            rs = stmt.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("EventID"), rs.getString("AssociatedUsername"),
                        rs.getString("PersonID"), rs.getFloat("Latitude"), rs.getFloat("Longitude"),
                        rs.getString("Country"), rs.getString("City"), rs.getString("EventType"),
                        rs.getInt("Year"));
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error encountered while finding a person in the database");
        }

    }

    /**
     * Retrieves all Persons from the database using the AuthToken
     *
     * @param authtoken the AuthToken
     * @return an array of Person objects
     */
    public Person[] getPeople(String authtoken) {
        return null;
    }
}
