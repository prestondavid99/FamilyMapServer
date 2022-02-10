package dataaccess;

import model.AuthToken;

import java.sql.Connection;

public class AuthTokenDao {
    private Connection connection;

    public AuthTokenDao(Connection connection) {
        this.connection = connection;
    }

    public AuthToken generateAuthToken(boolean validLogin) {
        return null;
    }

    public void createAuthToken(AuthToken authToken) {

    }

    public AuthToken getAuthToken(AuthToken authToken) {
        return null;
    }


}
