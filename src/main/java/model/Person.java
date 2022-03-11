package model;

/**
 * A model for Persons in the database.
 */
public class Person {
    /**
     * the ID of the person
     */
    private String personID;
    /**
     * the person's associated username
     */
    private String associatedUsername;
    /**
     * the person's first name
     */
    private String firstName;
    /**
     * the person's last name
     */
    private String lastName;
    /**
     * the person's gender
     */
    private String gender;
    /**
     * the person's father's ID
     */
    private String fatherID;
    /**
     * the person's mother's ID
     */
    private String motherID;
    /**
     * the person's spouse's ID
     */
    private String spouseID;



    /**
     * Creates a Person object with the person's details
     */
    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;
    }

    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender, String fatherID, String motherID) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
    }

    public Person(String personID, String associatedUsername, String firstName, String lastName, String gender) {
        this.personID = personID;
        this.associatedUsername = associatedUsername;
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

        Person p = (Person) o;
        return (p.getFatherID().equals(this.getFatherID()) && p.getMotherID().equals(this.getMotherID()) && p.getSpouseID().equals(this.getSpouseID()) &&
                p.getFirstName().equals(this.getFirstName()) && p.getLastName().equals(this.getLastName()) && p.getGender().equals(this.getGender()) &&
                p.getPersonID().equals(this.getPersonID()) && p.getAssociatedUsername().equals(this.getAssociatedUsername())
        );

    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getAssociatedUsername() {
        return associatedUsername;
    }

    public void setAssociatedUsername(String associatedUsername) {
        this.associatedUsername = associatedUsername;
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

    public String getFatherID() {
        return fatherID;
    }

    public void setFatherID(String fatherID) {
        this.fatherID = fatherID;
    }

    public String getMotherID() {
        return motherID;
    }

    public void setMotherID(String motherID) {
        this.motherID = motherID;
    }

    public String getSpouseID() {
        return spouseID;
    }

    public void setSpouseID(String spouseID) {
        this.spouseID = spouseID;
    }
}
