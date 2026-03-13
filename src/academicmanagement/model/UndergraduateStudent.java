package academicmanagement.model;

public class UndergraduateStudent extends Student {

    @Override
    public String getPersonType() {
        return "undergraduate";
    }

    public UndergraduateStudent(String id, String name, String studentID) {
        super(id, name, studentID);
    }

    public double calculateTuition() {
        return 5000.0;
    }
}