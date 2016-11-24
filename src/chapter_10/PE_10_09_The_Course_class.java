package chapter_10;

import utility.Course;

/**
 * (The Course class) Revise the Course class as follows:
 *
 * - The array size is fixed in Listing 10.6. Improve it to automatically increase
 *   the array size by creating a new larger array and copying the contents of the
 *   current array to it.
 * - Implement the dropStudent method.
 * - Add a new method named clear() that removes all students from the
 *   course.
 *
 * Write a test program that creates a course, adds three students, removes one,
 * and displays the students in the course.
 */
public class PE_10_09_The_Course_class {
    public static void main(String[] args) {
        Course course = new Course("Test Course");
        course.addStudent("Aaron");
        course.addStudent("Brett");
        course.addStudent("Charlie");

        course.dropStudent("Brett");

        for (int i = 0; i < course.getNumberOfStudents(); i++) {
            System.out.println("Student " + i + ": " + course.getStudents()[i]);
        }
    }
}
