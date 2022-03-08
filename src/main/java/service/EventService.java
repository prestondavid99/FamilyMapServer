package service;

import dataaccess.*;
import model.Event;
import requestresult.EventResult;
import requestresult.EventResult;
import requestresult.RegisterRequest;
import requestresult.RegisterResult;

/**
 * Returns ALL events for ALL family members of the current user. The current user is determined from the provided auth token.
 */
public class EventService {
    public EventResult getEvents(String authtoken) throws DataAccessException {
        Database db = new Database();
        Event[] data;
        try {
            if (new AuthTokenDAO(db.getConnection()).find(authtoken) != null) {
                db.openConnection();

                data = new EventDAO(db.getConnection()).getAllEvents(authtoken);

                db.closeConnection(true);

                EventResult result = new EventResult(data,null, true);
                return result;
            }

        } catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            EventResult result = new EventResult("getEvents failed.", false);
            return result;
        }
        return null; // TODO : Should this be null?
    }

}
