package academicmanagement.service;

import academicmanagement.Exception.CourseFullException;
import academicmanagement.Exception.StudentAlreadyEnrolledException;
import academicmanagement.model.Course;
import academicmanagement.model.Instructor;
import academicmanagement.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UniversityManager {
    private Map<String, Student> students;
    private Map<String, Course> courses;
    private Map<String, Instructor> instructors;

    public UniversityManager() {
        students = new HashMap<>();
        courses = new HashMap<>();
        instructors = new HashMap<>();
    }

    public void registerStudent(Student s) {
        students.put(s.getStudentID(), s);
    }

    public void createCourse(Course c) {
        courses.put(c.getCode(), c);
    }


    public void addInstructor(Instructor i) {
        instructors.put(i.getId(), i);
    }

    public void enroll(String studentID, String courseCode)
            throws CourseFullException, StudentAlreadyEnrolledException {

        Student s = students.get(studentID);
        Course c = courses.get(courseCode);

        if (s == null || c == null) {
            throw new IllegalArgumentException("Invalid student or course!");
        }

        if (c.isFull()) {
            throw new CourseFullException("Course " + courseCode + " is full!");
        }

        if (c.hasStudent(studentID)) {
            throw new StudentAlreadyEnrolledException(
                    "Student " + studentID + " already in " + courseCode);
        }

        c.addStudent(studentID);
        s.addGrade(courseCode, 0.0);
    }

    public Student getStudent(String id) {
        return students.get(id);
    }

    public Course getCourse(String code) {
        return courses.get(code);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }
    public List<Student> getDeansList() {
        List<Student> list = new ArrayList<>();
        for (Student s : students.values()) {
            if (s.getGpa() > 3.5) {
                list.add(s);
            }
        }
        return list;
    }

    public double getAvgGPA(String department) {
        List<Student> deptStudents = new ArrayList<>();
        for (Student s : students.values()) {
        }

        if (deptStudents.isEmpty()) return 0;

        double sum = 0;
        for (Student s : deptStudents) {
            sum = sum + s.getGpa();
        }
        return sum / deptStudents.size();
    }

    public Student getTopStudent() {
        Student top = null;
        for (Student s : students.values()) {
            if (top == null || s.getGpa() > top.getGpa()) {
                top = s;
            }
        }
        return top;
    }
}