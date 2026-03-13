package academicmanagement.Exception;

public class CourseFullException extends Exception {
    public CourseFullException() {
        super("Course is full!");
    }

    public CourseFullException(String message) {
        super(message);
    }
}