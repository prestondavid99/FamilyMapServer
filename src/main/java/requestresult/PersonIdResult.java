package requestresult;

/**
 * Outcome of the PersonId Request
 */
public class PersonIdResult extends Result {
    private String associatedUsername;
    private String personID;
    private String firstName;
    private String lastName;
    private String gender;
    private String fatherID;
    private String motherID;
    private String spouseID;

    public PersonIdResult(String message, boolean success) {
        super(message, success);
    }

    public PersonIdResult(String associatedUsername, String personID, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID, String message, boolean success) {
        super(message, success);
        this.associatedUsername = associatedUsername;
        this.personID = personID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.fatherID = fatherID;
        this.motherID = motherID;
        this.spouseID = spouseID;

    }
}
