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

public class LoginServiceTest {
    private FillService fillService;
    private ClearService clearService;
    private EventService eventService;
    private EventIdService eventIdService;
    private RegisterService registerService;
    private LoginService loginService;
    private PersonIdService personIdService;
    private RegisterRequest r;
    private LoginRequest l;
    private LoginRequest badL;
    @BeforeEach
    public void setUp() throws DataAccessException {
        fillService = new FillService();
        personIdService = new PersonIdService();
        eventService = new EventService();
        eventIdService = new EventIdService();
        registerService = new RegisterService();
        clearService = new ClearService();
        loginService = new LoginService();
        this.r = new RegisterRequest("username", "password", "email", "Gerald", "Thomas", "m");
        this.l = new LoginRequest("username", "password");
        this.badL = new LoginRequest("xxx", "xxxx");
        clearService.clear();
    }



    @Test
    public void loginPass() throws DataAccessException {
        registerService.register(r);
        LoginResult result = loginService.login(l);
        assertTrue(result.isSuccess());
    }

    @Test
    public void loginFail() throws DataAccessException {
        registerService.register(r);
        LoginResult result = loginService.login(badL);
        assertFalse(result.isSuccess());
    }
}