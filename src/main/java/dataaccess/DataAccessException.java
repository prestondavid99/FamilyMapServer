package dataaccess;

/**
 * If something goes wrong with the data retrieval, DataAccessException will be thrown
 */
public class DataAccessException extends Exception {

    public DataAccessException(String s) {
        super(s);
    }

    public DataAccessException(String s, Throwable throwable) {
        super(s, throwable);
    }
}