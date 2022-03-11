package service;

import dataaccess.AuthTokenDAO;
import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.UserDAO;
import model.AuthToken;
import model.User;
import requestresult.LoginRequest;
import requestresult.LoginResult;

import java.util.UUID;

/**
 * Logs the user in
 */
public class LoginService {
    public LoginResult login(LoginRequest l) throws DataAccessException {
        Database db = new Database();
        try {
            User user;
            user = new UserDAO(db.getConnection()).find(l.getUsername());
            if (user != null && user.getPassword().equals(l.getPassword())) {
                String authToken = UUID.randomUUID().toString();
                AuthToken authTokenObj = new AuthToken(authToken, l.getUsername());
                new AuthTokenDAO(db.getConnection()).insert(authTokenObj);
                db.closeConnection(true);

                LoginResult result = new LoginResult(authToken, l.getUsername(), user.getPersonID(), true, null);
                return result;
            } else {
                db.closeConnection(false);
                LoginResult result = new LoginResult("Error: Username or password is incorrect", false);
                return result;
            }
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            db.closeConnection(false);
            LoginResult result = new LoginResult("Error: Login Failed", false);
            return result;
        }
    }
}
