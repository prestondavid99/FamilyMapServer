package service;

import dataaccess.DataAccessException;
import dataaccess.Database;
import dataaccess.PersonDAO;
import dataaccess.UserDAO;
import model.Event;
import model.Person;
import model.User;
import requestresult.FillResult;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Populates the server's database with generated data for the specified username. The required "username" parameter must be a user already registered with the server. If there is any data in the database already associated with the given username, it is deleted.
 *
 * The optional "generations" parameter lets the caller specify the number of generations of ancestors to be generated, and must be a non-negative integer (the default is 4, which results in 31 new persons each with associated events).
 */
public class FillService {
    public FillResult fill(String username, int generations) throws DataAccessException {
        Database db = new Database();
        ArrayList<Person> personList;
        ArrayList<Event> eventList;
        User user;
        try {
            db.openConnection();
            user = new UserDAO(db.getConnection()).find(username);
            GenerateFamilyTree familyTree = new GenerateFamilyTree(db.getConnection(), username); // TODO : What do I do here?
            familyTree.generatePerson(user.getGender(), generations, 2020, false);
            personList = familyTree.getArrayList();
            eventList = familyTree.getEventList();
            for (Person p : personList) {
                new PersonDAO(db.getConnection()).insert(p);
            }

            db.closeConnection(true);

            FillResult result = new FillResult("Successfully added " + personList.size() +
                    " persons and " + eventList.size() + " events to the database", true);
            return result;

        } catch (DataAccessException | FileNotFoundException e) {
            e.printStackTrace();
            db.closeConnection(false);
            FillResult result = new FillResult("Error: Fill function error", false);
            return result;
        }
    }
}
