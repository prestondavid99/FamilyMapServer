package requestresult;

/**
 * Outcome of the Login Request
 */
public class LoginResult {
    /* Success */
    private String authtoken;
    private String username;
    private String personID;
    private boolean success;

    /* Error */
    private String message;
    // boolean success;
}
