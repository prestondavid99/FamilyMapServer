package dataaccess;


import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Connects Person model class to the database
 */
public class PersonDAO {
    /**
     * Connection object for connecting to the database
     */
    private final Connection connection;

    public PersonDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates a Person to be inserted into the database.
     *
     * @param person Person object to be inserted
     * @throws DataAccessException exception to be thrown if insert fails
     */
    public void insert(Person person) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Person (personID, associatedUsername, firstName, lastName, gender, " +
                "fatherID, motherID, spouseID) VALUES(?,?,?,?,?,?,?,?)";
        try (PreparedStatement stml = connection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stml.setString(1, person.getPersonID());
            stml.setString(2, person.getAssociatedUsername());
            stml.setString(3, person.getFirstName());
            stml.setString(4, person.getLastName());
            stml.setString(5, person.getGender());
            stml.setString(6, person.getFatherID());
            stml.setString(7, person.getMotherID());
            stml.setString(8, person.getSpouseID());

            stml.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error: encountered while inserting a Person into the database");
        }
    }

    /**
     * Removes Person data from the table based on the username.
     *
     * @param associatedUsername persons to be removed
     * @throws DataAccessException exception to be thrown if clearOne fails
     */
    public void clearByUser(String associatedUsername) throws DataAccessException {
        String sql = "DELETE FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error: encountered while clearing the Person table by username");
        }
    }

    /**
     * Clears the table.
     *
     * @throws DataAccessException Exception to be thrown if clear fails
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Person;";
        try (PreparedStatement stml = connection.prepareStatement(sql)) {
            stml.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error: encountered while clearing the Person table");
        }
    }

    /**
     * Retrieves a Person from the database using the Person's ID.
     *
     * @param personID retrieval tool
     * @throws DataAccessException exception to be thrown if find fails
     * @return the Person object
     */
    public Person find(String personID) throws DataAccessException {
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE personID = ?;";
        try (PreparedStatement stml = connection.prepareStatement(sql)) {
            stml.setString(1, personID);
            rs = stml.executeQuery();
            if (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID")
                        );
                return person;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error: encountered while finding a person in the database");
        }

    }

    /**
     * Retrieves all Persons from the database using the AuthToken
     *
     * @param associatedUsername the AuthToken
     * @throws DataAccessException exception to be thrown if findAll fails
     * @return an array of Person objects
     */
    public ArrayList<Person> findAll(String associatedUsername) throws DataAccessException {
        ArrayList<Person> personArray = new ArrayList<>();
        Person person;
        ResultSet rs;
        String sql = "SELECT * FROM Person WHERE associatedUsername = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, associatedUsername);
            rs = stmt.executeQuery();
            while (rs.next()) {
                person = new Person(rs.getString("personID"), rs.getString("associatedUsername"),
                        rs.getString("firstName"), rs.getString("lastName"), rs.getString("gender"),
                        rs.getString("fatherID"), rs.getString("motherID"), rs.getString("spouseID")
                );
                personArray.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error: encountered while finding a person in the database");
        }
        return personArray;
    }


}
