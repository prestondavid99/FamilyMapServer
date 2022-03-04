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

//            new EventDAO(db.getConnection()).insert();
//            new UserDAO(db.getConnection()).insert();
//            new PersonDAO(db.getConnection()).insert();
//            new AuthTokenDAO(db.getConnection()).insert();
        }
        catch(DataAccessException e) {

        }

        return null;
    }
}
