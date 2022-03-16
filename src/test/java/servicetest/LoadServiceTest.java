package servicetest;

import com.google.gson.Gson;
import data.FemaleNames;
import dataaccess.*;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requestresult.*;
import service.*;

import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LoadServiceTest {
    private PersonService personService;
    private LoadService loadService;
    private FillService fillService;
    private ClearService clearService;
    private EventService eventService;
    private EventIdService eventIdService;
    private RegisterService registerService;
    private LoginService loginService;
    private PersonIdService personIdService;
    private RegisterRequest r;
    private LoginRequest l;
    private LoadRequest loadRequest;
    @BeforeEach
    public void setUp() throws DataAccessException {

        loadRequest = new LoadRequest();
        loadService = new LoadService();
        fillService = new FillService();
        personIdService = new PersonIdService();
        personService = new PersonService();
        eventService = new EventService();
        eventIdService = new EventIdService();
        registerService = new RegisterService();
        clearService = new ClearService();
        loginService = new LoginService();
        this.r = new RegisterRequest("username", "password", "email", "Gerald", "Thomas", "m");
        this.l = new LoginRequest("username", "password");
        clearService.clear();
        Gson gson = new Gson();
        Reader reader = null;
        try {
            reader = new FileReader("passoffFiles/LoadData.json");
            loadRequest = gson.fromJson(reader, LoadRequest.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



    @Test
    public void loadPass() throws DataAccessException {
        LoadResult result = loadService.load(loadRequest);
        assertTrue(result.isSuccess());
    }

    @Test
    public void loadPassEmpty() throws DataAccessException {
        Event[] events = new Event[0];
        User[] users = new User[0];
        Person[] people = new Person[0];

        LoadRequest request = new LoadRequest();
        request.setUsers(users);
        request.setPersons(people);
        request.setEvents(events);

        LoadResult result = loadService.load(loadRequest);
        assertTrue(result.isSuccess());
    }
}