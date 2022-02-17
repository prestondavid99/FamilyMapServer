package dataaccess;

import model.User;

import java.sql.Connection;

/**
 * Connects User model class to the database
 */
public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    /**
     * Creates a User to be inserted into the database.
     *
     * @param user User object to be inserted
     */
    public void createUser(User user) {

    }

    /**
     * Validates the User's login information
     *
     * @param username the User's username
     * @param password the User's password
     * @return validation success
     */
    public boolean validate(String username, String password) {
        return false;
    }

    /**
     * Clears the table.
     */
    public void clearTable() {

    }

    /**
     * Retrieves a User from the database using the User's ID.
     *
     * @param userID retrieval tool, the User's ID
     * @return the User sought after
     */
    public User getUserByID(String userID) {
        return null;
    }
}
