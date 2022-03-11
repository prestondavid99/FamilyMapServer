package service;

import dataaccess.AuthTokenDAO;
import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.PersonDAO;
import model.AuthToken;
import model.Person;
import requestresult.PersonResult;
import requestresult.PersonResult;

/**
 * Returns ALL family members of the current user. The current user is determined by the provided authtoken.
 */
public class PersonService {
    public PersonResult getPeople(String authtoken) throws DataAccessException {
        Database db = new Database();
        Person[] data;
        AuthToken authTokenObj = new AuthTokenDAO(db.getConnection()).find(authtoken);
        try {
            if (authTokenObj != null) {
                data = new PersonDAO(db.getConnection()).findAll(authTokenObj.getUsername()).toArray(Person[]::new);

                db.closeConnection(true);

                PersonResult result = new PersonResult(data, null, true);
                return result;
            } else {
                db.closeConnection(false);
                PersonResult result = new PersonResult("Error: Couldn't find authtoken", false);
                return result;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            PersonResult result = new PersonResult("Error: getPeople failed.", false);
            return result;
        }
    }
}
