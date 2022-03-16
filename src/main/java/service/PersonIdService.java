package service;

import dataaccess.*;
import model.AuthToken;
import model.Person;
import requestresult.ClearResult;
import requestresult.PersonIdResult;

/**
 * Returns the single Person object with the specified ID (if the person is associated with the current user). The current user is determined by the provided authtoken.
 */
public class PersonIdService {
    public PersonIdResult getPerson(String authtoken, String personID) throws DataAccessException {
        Database db = new Database();
        Person person;
        try {
            db.openConnection();
            AuthToken authTokenObj = new AuthTokenDAO(db.getConnection()).find(authtoken);
            person = new PersonDAO(db.getConnection()).find(personID);
            if (authTokenObj != null && person.getAssociatedUsername().equals(authTokenObj.getUsername())) {
                db.closeConnection(true);

                PersonIdResult result = new PersonIdResult(person.getAssociatedUsername(), person.getPersonID(),
                        person.getFirstName(), person.getLastName(), person.getGender(), person.getFatherID(),
                        person.getMotherID(), person.getSpouseID(), null, true);
                return result;
            }
            else {
                db.closeConnection(false);
                PersonIdResult result = new PersonIdResult("Error: Invalid Authtoken", false);
                return result;
            }
        }
        catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            PersonIdResult result = new PersonIdResult("Error: Find Person Failed.", false);
            return result;
        }
    }
}
