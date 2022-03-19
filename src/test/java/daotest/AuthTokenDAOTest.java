package daotest;

import dataaccess.AuthTokenDAO;
import dataaccess.DataAccessException;
import dataaccess.Database;
import model.AuthToken;
import model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

//We will use this to test that our insert method is working and failing in the right ways
public class AuthTokenDAOTest {
    private Database db;
    private AuthToken authToken;
    private AuthTokenDAO aDAO;

    @BeforeEach
    public void setUp() throws DataAccessException {
        // Here we can set up any classes or variables we will need for each test
        // lets create a new instance of the Database class
        db = new Database();
        // and a new event with random data
        authToken = new AuthToken("gottem", "ligma");

        // Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        // Then we pass that connection to the UserDAO, so it can access the database.
        aDAO = new AuthTokenDAO(conn);
        // Let's clear the database as well so any lingering data doesn't affect our tests
        aDAO.clear();
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        // Here we close the connection to the database file, so it can be opened again later.
        // We will set commit to false because we do not want to save the changes to the database
        // between test cases.
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        // Start by inserting an event into the database.
        aDAO.insert(authToken);
        // Let's use a find method to get the event that we just put in back out.
        AuthToken compareTest = aDAO.find(authToken.getAuthtoken());
        // First lets see if our find method found anything at all. If it did then we know that we got
        // something back from our database.
        assertNotNull(compareTest);
        // Now lets make sure that what we put in is the same as what we got out. If this
        // passes then we know that our insert did put something in, and that it didn't change the
        // data in any way.
        // This assertion works by calling the equals method in the User class.
        assertEquals(authToken, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        // Let's do this test again, but this time lets try to make it fail.
        // If we call the method the first time the event will be inserted successfully.
        aDAO.insert(authToken);

        // However, our sql table is set up so that the column "eventID" must be unique, so trying to insert
        // the same event again will cause the insert method to throw an exception, and we can verify this
        // behavior by using the assertThrows assertion as shown below.

        // Note: This call uses a lambda function. A lambda function runs the code that comes after
        // the "()->", and the assertThrows assertion expects the code that ran to throw an
        // instance of the class in the first parameter, which in this case is a DataAccessException.
        assertThrows(DataAccessException.class, () -> aDAO.insert(authToken));
    }

    @Test
    public void findPass() throws DataAccessException {
        aDAO.insert(authToken);
        AuthToken compareTest = aDAO.find(authToken.getAuthtoken());
        assertNotNull(compareTest);
        assertEquals(authToken, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        AuthToken compareTest = aDAO.find(authToken.getAuthtoken());
        assertNull(compareTest);
    }

    @Test
    public void clearPass() throws DataAccessException {
        aDAO.insert(authToken);
        aDAO.clear();
        AuthToken compareTest = aDAO.find(authToken.getAuthtoken());
        assertNull(compareTest);
    }
}