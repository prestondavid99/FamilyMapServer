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
import service.ClearService;
import service.LoginService;
import service.PersonIdService;
import service.RegisterService;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class ClearServiceTest {
    private ClearService clearService;
    private RegisterService registerService;
    private LoginService loginService;
    private RegisterRequest r;
    private LoginRequest l;

    @BeforeEach
    public void setUp() throws DataAccessException {
        registerService = new RegisterService();
        clearService = new ClearService();
        loginService = new LoginService();
        this.r = new RegisterRequest("username", "password", "email", "Gerald", "Thomas", "m");
        this.l = new LoginRequest("username", "password");
        clearService.clear();
    }

    @Test
    public void clearPass() throws DataAccessException {
        PersonIdService personIdService = new PersonIdService();
        registerService.register(r);
        LoginResult loginResult = loginService.login(l);
        ClearResult result = clearService.clear();

        assertTrue(result.isSuccess());
    }

    @Test
    public void clearEmpty() throws DataAccessException {
        ClearResult result = clearService.clear();

        assertTrue(result.isSuccess());
    }
}
