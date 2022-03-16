package service;

import dataaccess.*;
import model.AuthToken;
import model.Event;
import requestresult.EventResult;

/**
 * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
 */
public class EventService {
    public EventResult getEvents(String authtoken) throws DataAccessException {
        Database db = new Database();
        Event[] data;
        AuthToken authTokenObj;
        try {
            if (new AuthTokenDAO(db.getConnection()).find(authtoken) != null) {
                authTokenObj = new AuthTokenDAO(db.getConnection()).find(authtoken);

                data = new EventDAO(db.getConnection()).getAllEvents(authTokenObj.getUsername()).toArray(Event[]::new);

                db.closeConnection(true);

                EventResult result = new EventResult(data, null, true);
                return result;
            } else {
                db.closeConnection(false);
                EventResult result = new EventResult("Error: Couldn't find authtoken", false);
                return result;
            }

        } catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            EventResult result = new EventResult("getEvents failed.", false);
            return result;
        }
    }
}
