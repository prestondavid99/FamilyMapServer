package requestresult;

import model.Person;

/**
 * Outcome of the Person Request
 */
public class PersonResult extends Result {
    private Person[] data;

    public PersonResult(String message, boolean success) {
        super(message, success);
    }
    public PersonResult(Person[] data, String message, boolean success) {
        super(message, success);
        this.data = data;
    }

    public Person[] getData() {
        return data;
    }

    public void setData(Person[] data) {
        this.data = data;
    }
}
