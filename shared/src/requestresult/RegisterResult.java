package requestresult;

/**
 * Outcome of the Register Request
 */
public class RegisterResult extends Result {
    private String authtoken;
    private String username;
    private String personID;

    public RegisterResult(String message, boolean success) {
        super(message, success);
    }
    public RegisterResult(String authtoken, String username, String personID, String message, boolean success) {
        super(message, success);
        this.authtoken = authtoken;
        this.personID = personID;
        this.username = username;
    }
}
