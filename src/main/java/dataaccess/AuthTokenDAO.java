package dataaccess;

import model.AuthToken;

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
     * @param authtoken the AuthToken to be inserted
     */
    public void insert(AuthToken authtoken) throws DataAccessException {
        String sql = "INSERT INTO AuthToken (authtoken, username) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authtoken.getAuthtoken());
            stmt.setString(2, authtoken.getUsername());
            stmt.executeUpdate();
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
    public AuthToken find(String authToken) throws DataAccessException {
        AuthToken authTokenObj;
        ResultSet rs;
        String sql = "SELECT * FROM AuthToken WHERE authToken = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, authToken);
            rs = stmt.executeQuery();
            if (rs.next()) {
                authTokenObj = new AuthToken(rs.getString("authToken"), rs.getString("username"));
                return authTokenObj;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error: finding an authToken in the database failed");
        }
    }

    /**
     * Clears the table.
     */
    public void clear() throws DataAccessException {
        String sql = "DELETE FROM AuthToken";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error: clearing the AuthToken table failed");
        }
    }
}
