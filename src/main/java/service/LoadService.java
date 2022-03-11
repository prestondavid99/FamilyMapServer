package service;

import dataaccess.*;
import model.Event;
import model.Person;
import model.User;
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
        User[] userArray = l.getUsers();
        Person[] personArray = l.getPersons();
        Event[] eventArray = l.getEvents();
        try {
            db.openConnection();

            new EventDAO(db.getConnection()).clear();
            new UserDAO(db.getConnection()).clear();
            new PersonDAO(db.getConnection()).clear();
            new AuthTokenDAO(db.getConnection()).clear();

            for (User u : userArray) {
                new UserDAO(db.getConnection()).insert(u);
            }
            for (Person p : personArray) {
                new PersonDAO(db.getConnection()).insert(p);
            }
            for (Event e : eventArray) {
                new EventDAO(db.getConnection()).insert(e);
            }

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
            return result;
        }
    }
}
