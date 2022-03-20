package service;

import dataaccess.*;
import model.AuthToken;
import model.Event;
import requestresult.EventIdResult;

/**
 * Returns the single Event object with the specified ID (if the event is associated with the current user). The current user is determined by the provided authtoken.
 */
public class EventIdService {
    public EventIdResult getEvent(String authtoken, String eventID) throws DataAccessException {
        Database db = new Database();
        Event event;
        try {
            AuthToken authTokenObj = new AuthTokenDAO(db.getConnection()).find(authtoken);
            event = new EventDAO(db.getConnection()).find(eventID);
            if (authTokenObj != null && event.getAssociatedUsername().equals(authTokenObj.getUsername())) {
                db.closeConnection(true);

                EventIdResult result = new EventIdResult(event.getAssociatedUsername(), event.getEventID(),
                        event.getPersonID(), event.getLatitude(), event.getLongitude(), event.getCountry(),
                        event.getCity(), event.getEventType(), event.getYear(), null, true);
                return result;
            } else {
                db.closeConnection(false);
                EventIdResult result = new EventIdResult("Error: Couldn't find authtoken", false);
                return result;
            }

        }
        catch (DataAccessException e) {
            e.printStackTrace();

            db.closeConnection(false);

            EventIdResult result = new EventIdResult("Error: Find Event Failed.", false);
            return result;
        }
    }
}
