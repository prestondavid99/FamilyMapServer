package dataaccess;

import model.User;

import java.sql.Connection;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void createUser(User user) {

    }

    public boolean validate(String username, String password) {
        return false;
    }

    public void clearTable() {

    }

    public User getUserByID(String userID) {
        return null;
    }
}
