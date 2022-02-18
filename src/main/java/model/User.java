package model;

/**
 * A model for Users in the database
 */
public class User {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;
    private String personID;

    /**
     * Creates a User object with associated details about that user
     *
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email
     * @param firstName the user's first name
     * @param lastName the user's last name
     * @param gender the user's gender
     * @param personID the user's person ID
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
