package model;

/**
 * A model for AuthTokens in the database
 */
public class AuthToken {
    private String authtoken;
    private String username;

    /**
     * Creates an AuthToken object with an authtoken String and username String
     *
     * @param authtoken the authtoken
     * @param username the username
     */
    public AuthToken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
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
}
