package requestresult;

/**
 * Outcome of the Register Request
 */
public class RegisterResult {
    /* Success */
    private String authtoken;
    private String username;
    private String personID;
    private boolean success;

    /* Error */
    private String message;
}
