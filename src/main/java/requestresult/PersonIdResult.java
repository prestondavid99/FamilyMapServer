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
}
