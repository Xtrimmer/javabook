package Chapter_11;

import utility.Course_11_05;

import java.util.Arrays;

/**
 * (The Course class) Rewrite the Course class in Listing 10.6. Use an ArrayList
 * to replace an array to store students. Draw the new UML diagram for the class.
 * You should not change the original contract of the Course class (i.e., the definition
 * of the constructors and methods should not be changed, but the private
 * members may be changed.)
 *
 * (New class named Course_11_05)
 */
public class PE_11_05_The_Course_class {
    public static void main(String[] args) {
        Course_11_05 course = new Course_11_05("Class");
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
}
