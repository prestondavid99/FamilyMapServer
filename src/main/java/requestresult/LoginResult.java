package requestresult;

/**
 * Outcome of the Login Request
 */
public class LoginResult extends Result {
    /* Success */
    private String authtoken;
    private String username;
    private String personID;

    public LoginResult(String message, boolean success) {
        super(message, success);
    }
    // boolean success;
}
