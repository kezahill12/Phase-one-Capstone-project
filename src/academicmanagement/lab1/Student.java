package academicmanagement.model;

import java.util.HashMap;
import java.util.Map;

public abstract class Student extends Person {
    private String studentID;
    private double gpa;
    public Map<String, Double> grades;


    public Student(String id, String name, String studentID) {
        super(id, name);
        this.studentID = studentID;
        this.gpa = 0.0;
        this.grades = new HashMap<>();
    }

    public String getStudentID() { return studentID; }
    public void setStudentID(String studentID) { this.studentID = studentID; }

    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    public void addGrade(String course, double grade) {
        grades.put(course, grade);
    }

    public void calculateGPA() {
        if (grades.isEmpty()) {
            gpa = 0.0;
            return;
        }
        double sum = 0;
        for (double grade : grades.values()) {
            sum = sum + grade;
        }
        gpa = sum / grades.size();
    }

    public abstract double calculateTuition();
}