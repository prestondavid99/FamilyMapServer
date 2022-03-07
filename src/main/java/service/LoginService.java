package service;

import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.UserDAO;
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
            db.openConnection();
            user = new UserDAO(db.getConnection()).validate(l.getUsername(), l.getPassword());
            db.closeConnection(true);

            String authToken = UUID.randomUUID().toString();
            LoginResult result = new LoginResult(authToken, l.getUsername(), user.getPersonID(), true, "");
            return result;
        }
        catch (DataAccessException e) {
            e.printStackTrace();
            LoginResult result = new LoginResult("LoginService Error", false);
            return result;
        }

    }
}
