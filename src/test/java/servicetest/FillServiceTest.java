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

public class FillServiceTest {
    private FillService fillService;
    private ClearService clearService;
    private EventService eventService;
    private EventIdService eventIdService;
    private RegisterService registerService;
    private LoginService loginService;
    private PersonIdService personIdService;
    private RegisterRequest r;
    private LoginRequest l;
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
        clearService.clear();
    }



    @Test
    public void fillPass() throws DataAccessException {
        registerService.register(r);
        FillResult result = fillService.fill("username", 3);
        assertTrue(result.isSuccess());
    }

    @Test
    public void fillFail() throws DataAccessException {
        FillResult result = fillService.fill("yomama", 3);
        assertFalse(result.isSuccess());
    }
}