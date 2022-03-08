package service;

import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.PersonDAO;
import requestresult.PersonResult;

/**
 * Returns ALL family members of the current user. The current user is determined by the provided authtoken.
 */
public class PersonService {
    public PersonResult getPeople(String authtoken) throws DataAccessException {
        Database db = new Database();
        try {
            db.openConnection();

            new PersonDAO(db.getConnection()).findAll(authtoken);

            db.closeConnection(true);

            PersonResult result = new PersonResult("getPeople was a success.", true);
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            PersonResult result = new PersonResult("getPeople failed.", false);
            return result;
        }

    }
}
