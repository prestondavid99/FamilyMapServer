package service;

import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.UserDAO;
import requestresult.LoginRequest;
import requestresult.LoginResult;

/**
 * Logs the user in
 */
public class LoginService {
    public LoginResult login(LoginRequest l) throws DataAccessException {
        Database db = new Database();
        try {
            db.openConnection();

            new UserDAO(db.getConnection());
            return null;
        }
        catch (DataAccessException e) {

        }
        return null;
    }
}
