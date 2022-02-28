package dataaccess;

import model.AuthToken;
import model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Connects AuthToken model class to the database.
 */
public class AuthTokenDAO {
    /**
     * Establishes a connection with the database
     */
    private Connection connection;

    public AuthTokenDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates an AuthToken to insert into the database
     *
     * @param authToken the AuthToken to be inserted
     */
    public void insert(AuthToken authToken) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO Authentication Token (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, authToken.getAuthtoken());
            stmt.setString(2, authToken.getUsername());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting an event into the database");
        }
    }

    /**
     * Retrieves an AuthToken from the database.
     *
     * @param authToken the AuthToken sought for in the database
     * @return the AuthToken
     */
    public AuthToken getAuthToken(AuthToken authToken) {
        return null;
    }

    /**
     * Clears the table.
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM Authorization Token";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while clearing the event table");
        }
    }
}
