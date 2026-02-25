package academicmanagement;

import java.io.*;
import java.util.*;

public class FileManager {
    public void saveStudents(List<Student> list) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("students.txt"));
            for (Student s : list) {
                String type = s.getClass().getSimpleName();
                writer.println(type + "|" + s.getId() + "|" + s.getName() + "|" +
                        s.getStudentID() + "|" + s.getGpa());
            }
            writer.close();
            System.out.println("Saved!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Student> loadStudents() {
        List<Student> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("students.txt"));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ");
                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                String studentID = parts[3];

                Student s;
                if (type.equals("UndergraduateStudent")) {
                    s = new UndergraduateStudent(id, name, studentID);
                } else {
                    s = new GraduateStudent(id, name, studentID, false);
                }
                s.setGpa(Double.parseDouble(parts[4]));
                list.add(s);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("No saved data found.");
        }
        return list;
    }

    public void saveCourses(List<Course> list) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("courses.txt"));
            for (Course c : list) {
                writer.println(c.getCode() + "|" + c.getName() + "|" + c.getMaxSize());
            }
            writer.close();
            System.out.println("Courses saved!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<Course> loadCourses() {
        List<Course> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File("courses.txt"));
            while (scanner.hasNextLine()) {
                String[] parts = scanner.nextLine().split(" ");
                Course c = new Course(parts[0], parts[1], Integer.parseInt(parts[2]));
                list.add(c);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("No saved courses found.");
        }
        return list;
    }
}