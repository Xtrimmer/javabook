package chapter_11;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * (The Course class) Rewrite the Course class in Listing 10.6. Use an ArrayList
 * to replace an array to store students. Draw the new UML diagram for the class.
 * You should not change the original contract of the Course class (i.e., the definition
 * of the constructors and methods should not be changed, but the private
 * members may be changed.)
 */
public class PE_11_05_The_Course_class {
    public static void main(String[] args) {
        Course course = new Course("Class");
        course.addStudent("Allen");
        course.addStudent("Bob");
        course.addStudent("Charlie");
        course.addStudent("David");
        course.addStudent("Ed");
        course.addStudent("Frank");
        course.addStudent("George");
        course.addStudent("Harini");
        course.dropStudent("David");
        System.out.println("Course: " + course.getCourseName());
        System.out.println("Students: " + Arrays.toString(course.getStudents()));
        System.out.println("Student count: " + course.getNumberOfStudents());
    }

    static class Course {
        private final String courseName;
        private final ArrayList<String> students = new ArrayList<>();

        public Course(String courseName) {
            this.courseName = courseName;
        }

        public void addStudent(String student) {
            students.add(student);
        }

        public void dropStudent(String student) {
            students.remove(student);
        }

        public String getCourseName() {
            return courseName;
        }

        public int getNumberOfStudents() {
            return students.size();
        }

        public String[] getStudents() {
            return students.toArray(new String[students.size()]);
        }
    }
}