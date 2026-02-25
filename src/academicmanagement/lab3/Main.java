package academicmanagement;

import academicmanagement.Exception.CourseFullException;
import academicmanagement.Exception.StudentAlreadyEnrolledException;
import academicmanagement.model.Course;
import academicmanagement.model.GraduateStudent;
import academicmanagement.model.Student;
import academicmanagement.model.UndergraduateStudent;
import academicmanagement.service.FileManager;
import academicmanagement.service.UniversityManager;

import java.util.*;

public class Main {
    static UniversityManager manager = new UniversityManager();
    static FileManager fileManager = new FileManager();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        menu();
    }

    static void menu() {
        while (true) {
            System.out.println("\n====== UNIVERSITY SYSTEM ======");
            System.out.println("1. Register Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View Student Record");
            System.out.println("5. Assign Grade");
            System.out.println("6. Generate Dean's List");
            System.out.println("7. Show Department Average GPA");
            System.out.println("8. Show Top Student");
            System.out.println("9. Show All Courses");
            System.out.println("10. Save and Exit");
            System.out.print("Choose: ");

            int choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 1: registerStudent(); break;
                case 2: createCourse(); break;
                case 3: enrollStudent(); break;
                case 4: viewStudent(); break;
                case 5: assignGrade(); break;
                case 6: deansList(); break;
                case 7: deptGPA(); break;
                case 8: topStudent(); break;
                case 9: showCourses(); break;
                case 10: saveAndExit(); return;
                default: System.out.println("Invalid choice!");
            }
        }
    }

    static void registerStudent() {
        System.out.println("\n--- Register Student ---");
        System.out.print("Type (1=Undergrad, 2=Grad): ");
        int type = scan.nextInt();
        scan.nextLine();

        System.out.print("ID: ");
        String id = scan.nextLine();
        System.out.print("Name: ");
        String name = scan.nextLine();
        System.out.print("Student ID: ");
        String studentID = scan.nextLine();

        Student s;
        if (type == 1) {
            s = new UndergraduateStudent(id, name, studentID);
        } else {
            s = new GraduateStudent(id, name, studentID, false);
        }

        manager.registerStudent(s);
        System.out.println( name +" is registered!");
        System.out.println("Tuition: $" + s.calculateTuition());
    }

    static void createCourse() {
        System.out.println("\n--- Create Course ---");
        System.out.print("Course Code: ");
        String code = scan.nextLine();
        System.out.print("Course Name: ");
        String name = scan.nextLine();
        System.out.print("Max Size: ");
        int max = scan.nextInt();
        scan.nextLine();

        Course c = new Course(code, name, max);
        manager.createCourse(c);
        System.out.println("Course created!");
    }

    static void enrollStudent() {
        System.out.println("\n--- Enroll Student ---");
        System.out.print("Student ID: ");
        String studentID = scan.nextLine();
        System.out.print("Course Code: ");
        String courseCode = scan.nextLine();

        try {
            manager.enroll(studentID, courseCode);
            System.out.println("Enrolled successfully!");
        } catch (CourseFullException e) {
            System.out.println("ERROR: Course is full!");
        } catch (StudentAlreadyEnrolledException e) {
            System.out.println("ERROR: Already enrolled!");
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    static void viewStudent() {
        System.out.println("\n--- View Student ---");
        System.out.print("Student ID: ");
        String studentID = scan.nextLine();

        Student s = manager.getStudent(studentID);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.println("\nName: " + s.getName());
        System.out.println("ID: " + s.getStudentID());
        System.out.println("Type: " + s.getPersonType());
        System.out.println("GPA: " + s.getGpa());
        System.out.println("Tuition: $" + s.calculateTuition());
        System.out.println("Courses: " + s.getStudentID());
    }

    static void assignGrade() {
        System.out.println("\n--- Assign Grade ---");
        System.out.print("Student ID: ");
        String studentID = scan.nextLine();
        System.out.print("Course Code: ");
        String courseCode = scan.nextLine();
        System.out.print("Grade (0-4): ");
        double grade = scan.nextDouble();
        scan.nextLine();

        Student s = manager.getStudent(studentID);
        if (s != null) {
            s.addGrade(courseCode, grade);
            s.calculateGPA();
            System.out.println("Grade assigned! New GPA: " + s.getGpa());
        } else {
            System.out.println("Student not found!");
        }
    }

    static void deansList() {
        System.out.println("\n--- Dean's List (GPA > 3.5) ---");
        List<Student> list = manager.getDeansList();
        if (list.isEmpty()) {
            System.out.println("No students on Dean's List.");
        } else {
            for (Student s : list) {
                System.out.println(s.getName() + " - GPA: " + s.getGpa());
            }
        }
    }

    static void deptGPA() {
        System.out.println("\n--- Department GPA ---");
        System.out.print("Department: ");
        String dept = scan.nextLine();
        double avg = manager.getAvgGPA(dept);
        System.out.println("Average GPA: " + avg);
    }

    static void topStudent() {
        System.out.println("\n--- Top Student ---");
        Student top = manager.getTopStudent();
        if (top == null) {
            System.out.println("No students found.");
        } else {
            System.out.println("Name: " + top.getName());
            System.out.println("GPA: " + top.getGpa());
        }
    }

    static void showCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> list = manager.getAllCourses();
        for (Course c : list) {
            System.out.println(c.getCode() + " - " + c.getName() +
                    " (" + c.getStudents().size() + "/" + c.getMaxSize() + ")");
        }
    }

    static void saveAndExit() {
        System.out.println("\nSaving data...");
        fileManager.saveStudents(manager.getAllStudents());
        fileManager.saveCourses(manager.getAllCourses());
        System.out.println("Goodbye!");
    }

    static void loadData() {
        System.out.println("Loading data...");
        List<Course> courses = fileManager.loadCourses();
        for (Course c : courses) {
            manager.createCourse(c);
        }

        List<Student> students = fileManager.loadStudents();
        for (Student s : students) {
            manager.registerStudent(s);
        }

        if (students.isEmpty()) {
            System.out.println("Creating sample data...");
            createSampleData();
        }
    }

    static void createSampleData() {
        Student s1 = new UndergraduateStudent("S001", "John Doe", "U001");
        Student s2 = new GraduateStudent("S002", "Jane Smith", "G001", true);

        manager.registerStudent(s1);
        manager.registerStudent(s2);

        Course c1 = new Course("CS101", "Intro to Java", 30);
        Course c2 = new Course("CS201", "Data Structures", 25);

        manager.createCourse(c1);
        manager.createCourse(c2);

        System.out.println("Sample data created!");
    }
}