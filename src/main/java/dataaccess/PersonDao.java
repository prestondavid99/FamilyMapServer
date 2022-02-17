package dataaccess;

import model.Event;
import model.Person;

import java.sql.Connection;
import java.util.Set;

/**
 * Connects Person model class to the database
 */
public class PersonDao {
    private Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates a Person to be inserted into the database.
     *
     * @param person Person object to be inserted
     */
    public void createPerson(Person person) {

    }

    /**
     * Clears the table.
     */
    public void clearTable() {

    }

    /**
     * Retrieves a Person from the database using the Person's ID.
     *
     * @param personID retrieval tool
     * @return the Person object
     */
    public Person getPerson(String personID) {
        return null;
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
