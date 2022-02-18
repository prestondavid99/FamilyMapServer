package dataaccess;

public class DataAccessException extends Exception {

    public DataAccessException(String s) {
        super(s);
    }

    public DataAccessException(String s, Throwable throwable) {
        super(s, throwable);
    }
}