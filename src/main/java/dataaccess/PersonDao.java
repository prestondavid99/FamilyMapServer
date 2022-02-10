package dataaccess;

import model.Event;
import model.Person;

import java.sql.Connection;
import java.util.Set;

public class PersonDao {
    private Connection connection;

    public PersonDao(Connection connection) {
        this.connection = connection;
    }

    public Set<Event> generateEvent() {
        return null;
    }

    public void createPerson(Person person) {

    }

    public void clearTable() {

    }

    public Person getPerson(String personID) {
        return null;
    }
}
