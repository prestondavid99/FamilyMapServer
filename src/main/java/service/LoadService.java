package service;

import dataaccess.*;
import model.Person;
import requestresult.LoadRequest;
import requestresult.LoadResult;

/**
 * Clears all data from the database (just like the /clear API)
 *
 * Loads the user, person, and event data from the request body into the database.
 */
public class LoadService {
    public LoadResult load(LoadRequest l) throws DataAccessException {
        Database db = new Database();
        try {
            db.openConnection();

            new EventDAO(db.getConnection()).clear();
            new UserDAO(db.getConnection()).clear();
            new PersonDAO(db.getConnection()).clear();
            new AuthTokenDAO(db.getConnection()).clear();

            db.closeConnection(true);

            LoadResult result = new LoadResult("Successfully added " + l.getUsers().length
                    + " users, " + l.getPersons().length + " persons, and " + l.getEvents().length
                    + " events to the database.", true);
            return result;
        }
        catch(DataAccessException e) {
            db.closeConnection(false);
            e.printStackTrace();
            LoadResult result = new LoadResult("Error: unable to load data", false);
        }

        return null;
    }
}
