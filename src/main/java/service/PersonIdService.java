package service;

import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.PersonDAO;
import requestresult.ClearResult;
import requestresult.PersonIdResult;

/**
 * Returns the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
 */
public class PersonIdService {
    public PersonIdResult getPerson(String authtoken) throws DataAccessException {
        Database db = new Database();
        try {
            db.openConnection();

            new PersonDAO(db.getConnection()).find() // TODO : Where should I get this personID?

            db.closeConnection(true);

            PersonIdResult result = new PersonIdResult() // TODO : Where do I get the things to put in here?
            return result;
        }
        catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            PersonIdResult result = new PersonIdResult("Find Person Failed.", false);
            return result;
        }
    }
}
