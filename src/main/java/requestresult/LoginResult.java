package requestresult;

/**
 * Outcome of the Login Request
 */
public class  LoginResult extends Result {
    private String authtoken;
    private String username;
    private String personID;

    public LoginResult(String authtoken, String username, String personID, boolean success, String message) {
        super(message, success);
        this.authtoken = authtoken;
        this.username = username;
        this.personID = personID;
    }

    public LoginResult(String message, boolean success) {
        super(message, success);
    }
}
