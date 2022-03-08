package service;

import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.PersonDAO;
import model.Person;
import requestresult.PersonResult;

/**
 * Returns ALL family members of the current user. The current user is determined by the provided authtoken.
 */
public class PersonService {
    public PersonResult getPeople(String authtoken) throws DataAccessException {
        Database db = new Database();
        Person[] data;
        try {
            db.openConnection();

            data = new PersonDAO(db.getConnection()).findAll(authtoken);

            db.closeConnection(true);

            PersonResult result = new PersonResult(data,null, true);
            return result;
        } catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            PersonResult result = new PersonResult("getPeople failed.", false);
            return result;
        }

    }
}
