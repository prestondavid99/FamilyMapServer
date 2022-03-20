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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterServiceTest {
    private ClearService clearService;
    EventService eventService;
    private EventIdService eventIdService;
    private RegisterService registerService;
    private LoginService loginService;
    private PersonIdService personIdService;
    private RegisterRequest r;
    private RegisterRequest r2;
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
        this.r2 = new RegisterRequest("wow", "nice", "@@", "Gerald", "Thomas", "m");
        this.l = new LoginRequest("username", "password");
        clearService.clear();
    }



    @Test
    public void registerPassOne() throws DataAccessException {
        RegisterResult result = registerService.register(r);
        assertTrue(result.isSuccess());
    }

    @Test
    public void registerPassTwo() throws DataAccessException {
        RegisterResult result = registerService.register(r2);
        assertTrue(result.isSuccess());

    }
}