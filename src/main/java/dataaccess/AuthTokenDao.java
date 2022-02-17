package dataaccess;

import model.AuthToken;

import java.sql.Connection;

/**
 * Connects AuthToken model class to the database.
 */
public class AuthTokenDao {
    private Connection connection;

    public AuthTokenDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates an AuthToken to insert into the database
     *
     * @param authToken the AuthToken to be inserted
     */
    public void createAuthToken(AuthToken authToken) {

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
    public void clearTable() {}
}
