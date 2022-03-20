package model;

/**
 * A model for Users in the database
 */
public class User {
    /**
     * the user's username
     */
    private String username;
    /**
     * the user's password
     */
    private String password;
    /**
     * the user's email
     */
    private String email;
    /**
     * the user's first name
     */
    private String firstName;
    /**
     * the user's last name
     */
    private String lastName;
    /**
     * the user's gender
     */
    private String gender;
    /**
     * the user's person ID
     */
    private String personID;

    /**
     * Creates a User object with associated details about that user
     */
    public User(String username, String password, String email, String firstName, String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public User(String username, String password, String email, String firstName, String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public boolean equals(Object o) {

        // Checks if o is null OR if it is not equal to the class
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        if (o == this) {
            return true;
        }

        User u = (User) o;
        return (u.getUsername().equals(this.getUsername()) && u.getPassword().equals(this.getPassword()) && u.getEmail().equals(this.getEmail()) &&
                u.getFirstName().equals(this.getFirstName()) && u.getLastName().equals(this.getLastName()) && u.getGender().equals(this.getGender()) &&
                u.getPersonID().equals(this.getPersonID())
        );
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }
}
