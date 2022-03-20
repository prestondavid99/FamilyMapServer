package servicetest;

import dataaccess.*;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import requestresult.*;
import service.*;

public class PersonIdServiceTest {
    private ClearService clearService;
    private PersonService personService;
    private EventIdService eventIdService;
    private RegisterService registerService;
    private LoginService loginService;
    private PersonIdService personIdService;
    private RegisterRequest r;
    private LoginRequest l;
    @BeforeEach
    public void setUp() throws DataAccessException {
        personIdService = new PersonIdService();
        personService = new PersonService();
        eventIdService = new EventIdService();
        registerService = new RegisterService();
        clearService = new ClearService();
        loginService = new LoginService();
        this.r = new RegisterRequest("username", "password", "email", "Gerald", "Thomas", "m");
        this.l = new LoginRequest("username", "password");
        clearService.clear();
    }



    @Test
    public void personIdPass() throws DataAccessException {
        registerService.register(r);
        LoginResult loginResult = loginService.login(l);
        PersonResult personResult = personService.getPeople(loginResult.getAuthtoken());
        String personID = personResult.getData()[0].getPersonID();

        PersonIdResult result = personIdService.getPerson(loginResult.getAuthtoken(), personID);
        assertTrue(result.isSuccess());
    }

    @Test
    public void personIdFail() throws DataAccessException {
        registerService.register(r);
        LoginResult loginResult = loginService.login(l);
        PersonResult personResult = personService.getPeople(loginResult.getAuthtoken());
        String personID = personResult.getData()[0].getPersonID();
        clearService.clear();

        PersonIdResult result = personIdService.getPerson(loginResult.getAuthtoken(), personID);
        assertFalse(result.isSuccess());
    }
}
