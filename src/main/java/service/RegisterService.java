package service;

import requestresult.RegisterRequest;
import requestresult.RegisterResult;

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
    public RegisterResult register(RegisterRequest r) {
        return null;
    }
}