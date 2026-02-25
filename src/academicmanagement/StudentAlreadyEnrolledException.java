package academicmanagement;

public class StudentAlreadyEnrolledException extends Exception {
    public StudentAlreadyEnrolledException() {
        super("Student already enrolled!");
    }

    public StudentAlreadyEnrolledException(String message) {
        super(message);
    }
}