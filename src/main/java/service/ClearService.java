package service;

import dataaccess.*;
import requestresult.ClearResult;

/**
 * Deletes ALL data from the database, including user, authtoken, person, and event data
 */
public class ClearService {
    public ClearResult clear() throws DataAccessException {
        Database db = new Database();
        try {
            db.openConnection();

            new EventDAO(db.getConnection()).clear();
            new AuthTokenDAO(db.getConnection()).clear();
            new PersonDAO(db.getConnection()).clear();
            new UserDAO(db.getConnection()).clear();

            db.closeConnection(true);

            ClearResult result = new ClearResult("Clear Succeeded.", true);
            return result;
        }
        catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            ClearResult result = new ClearResult("Clear Failed.", false);
            return result;
        }
    }
}
