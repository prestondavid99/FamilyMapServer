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

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
