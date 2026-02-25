package academicmanagement;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String code;
    private String name;
    private int maxSize;
    private List<String> students; // List of student IDs

    public Course() {
        students = new ArrayList<>();
    }

    public Course(String code, String name, int maxSize) {
        this.code = code;
        this.name = name;
        this.maxSize = maxSize;
        this.students = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public List<String> getStudents() {
        return students;
    }

    public boolean isFull() {
        return students.size() >= maxSize;
    }

    public boolean addStudent(String studentID) {
        if (isFull()) return false;
        if (students.contains(studentID)) return false;
        students.add(studentID);
        return true;
    }

    public boolean hasStudent(String studentID) {
        return students.contains(studentID);
    }
}
