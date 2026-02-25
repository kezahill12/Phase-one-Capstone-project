package academicmanagement.model;

import java.util.Map;

public class GraduateStudent extends Student {
    private boolean hasFunding;

    @Override
    public String getPersonType() {
        return " ";
    }

    public GraduateStudent(String id, String name, String studentID, boolean hasFunding) {
        super(id, name, studentID);
        this.hasFunding = hasFunding;
    }

    public boolean getHasFunding() { return hasFunding; }
    public void setHasFunding(boolean hasFunding) { this.hasFunding = hasFunding; }

    public double calculateTuition() {
        int credits = getGrades().size() * 3;
        double tuition = (credits * 800) + 2000;

        if (hasFunding) {
            tuition = tuition * 0.75;
        }
        return tuition;
    }

    private Map<Object, Object> getGrades() {
        return null;
    }
}