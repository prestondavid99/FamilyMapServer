package servicetest;

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
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {
    private ClearService clearService;
    EventService eventService;
    private EventIdService eventIdService;
    private RegisterService registerService;
    private LoginService loginService;
    private PersonIdService personIdService;
    private RegisterRequest r;
    private LoginRequest l;
    @BeforeEach
    public void setUp() throws DataAccessException {
        personIdService = new PersonIdService();
        eventService = new EventService();
        eventIdService = new EventIdService();
        registerService = new RegisterService();
        clearService = new ClearService();
        loginService = new LoginService();
        this.r = new RegisterRequest("username", "password", "email", "Gerald", "Thomas", "m");
        this.l = new LoginRequest("username", "password");
        clearService.clear();
    }



    @Test
    public void eventPass() throws DataAccessException {
        registerService.register(r);
        LoginResult loginResult = loginService.login(l);
        EventResult eventResult = eventService.getEvents(loginResult.getAuthtoken());

        assertTrue(eventResult.isSuccess());
    }

    @Test
    public void eventFail() throws DataAccessException {
        registerService.register(r);
        LoginResult loginResult = loginService.login(l);
        EventResult eventResult = eventService.getEvents("Invalid");

        assertFalse(eventResult.isSuccess());
    }
}