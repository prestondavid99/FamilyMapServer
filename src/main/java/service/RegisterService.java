package service;

import dataaccess.*;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import requestresult.FillResult;
import requestresult.RegisterRequest;
import requestresult.RegisterResult;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Creates a new user account (user row in the database)
 *
 * Generates 4 generations of ancestor data for the new user (just like the /fill endpoint if called with a generations value of 4 and this new user’s username as parameters)
 *
 * Logs the user in
 *
 * Returns an authtoken
 */
public class RegisterService {
    public RegisterResult register(RegisterRequest r) throws DataAccessException {
        Database db = new Database();
        ArrayList<Person> personList;
        try {
            User user = new User(r.getUsername(), r.getPassword(), r.getEmail(), r.getFirstName(), r.getLastName(),
                    r.getGender());
            GenerateFamilyTree familyTree = new GenerateFamilyTree(db.getConnection(), r.getUsername());
            familyTree.generatePerson(user.getGender(), 4, 2020, false);
            user.setPersonID(familyTree.getPersonID());
            new UserDAO(db.getConnection()).insert((user));
            personList = familyTree.getArrayList();
            for (Person p : personList) {
                if (p.getPersonID().equals(user.getPersonID())) {
                    p.setFirstName(user.getFirstName());
                    p.setLastName(user.getLastName());
                    p.setGender(user.getGender());
                    p.setAssociatedUsername(user.getUsername());
                }
                new PersonDAO(db.getConnection()).insert(p);
            }
            String authToken = UUID.randomUUID().toString();
            AuthToken authTokenObj = new AuthToken(authToken, user.getUsername());
            new AuthTokenDAO(db.getConnection()).insert(authTokenObj);
            AuthToken authToken1 = new AuthTokenDAO(db.getConnection()).find(authToken);



            db.closeConnection(true);

            RegisterResult result = new RegisterResult(authToken, user.getUsername(), user.getPersonID(), null, true);
            return result;

        } catch (DataAccessException | FileNotFoundException e) {
            e.printStackTrace();
            db.closeConnection(false);
            RegisterResult result = new RegisterResult("Error: Registration error", false);
            return result;
        }
    }
}
