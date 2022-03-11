package model;

/**
 * A model for AuthTokens in the database
 */
public class AuthToken {
    /**
     * the authtoken
     */
    private String authtoken;
    /**
     * the username
     */
    private String username;

    /**
     * Creates an AuthToken object with an authtoken String and username String
     */
    public AuthToken(String authtoken, String username) {
        this.authtoken = authtoken;
        this.username = username;
    }

    public boolean equals(Object o) {

        // Checks if o is null OR if it is not equal to the class
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        if (o == this) {
            return true;
        }

        AuthToken a = (AuthToken) o;
        return (a.getAuthtoken().equals(this.getAuthtoken()) && a.getUsername().equals(this.getUsername()));
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
