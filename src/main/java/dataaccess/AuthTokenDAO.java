package dataaccess;

import model.AuthToken;
import model.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Connects AuthToken model class to the database.
 */
public class AuthTokenDAO {
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
        String sql = "INSERT INTO AuthToken (authToken, username) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authToken.getAuthtoken());
            stmt.setString(2, authToken.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error inserting authToken into the database");
        }
    }

    /**
     * Retrieves an AuthToken from the database.
     *
     * @param authToken the AuthToken sought for in the database
     * @return the AuthToken
     */
    public AuthToken find(AuthToken authToken) throws DataAccessException {
        AuthToken authTokenObj;
        ResultSet rs;
        String sql = "SELECT * FROM AuthToken WHERE authToken = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authToken.getAuthtoken());
            rs = stmt.executeQuery();
            if (rs.next()) {
                authTokenObj = new AuthToken(rs.getString("authToken"), rs.getString("username"));
                return authTokenObj;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding an authToken in the database");
        }
    }

    /**
     * Clears the table.
     */
    public void clearTable() {}
}
