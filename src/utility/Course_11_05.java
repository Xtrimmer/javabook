package utility;

import java.util.ArrayList;

/**
 * Added for Chapter 11 Exercise 05.
 */
public class Course_11_05 {
    private String courseName;
    private ArrayList<String> students = new ArrayList<>();

    public Course_11_05(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
        students.add(student);
    }

    public String[] getStudents() {
        return students.toArray(new String[students.size()]);
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public String getCourseName() {
        return courseName;
    }

    public void dropStudent(String student) {
        students.remove(student);
    }
}
