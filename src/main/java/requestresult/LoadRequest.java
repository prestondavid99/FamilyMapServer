package requestresult;

import model.Event;
import model.Person;
import model.User;

/**
 * Load Request from the Handler
 */
public class LoadRequest extends Request {
    private User[] users;
    private Person[] persons;
    private Event[] events;

    public User[] getUsers() {
        return users;
    }

    public Person[] getPersons() {
        return persons;
    }

    public Event[] getEvents() {
        return events;
    }
}
