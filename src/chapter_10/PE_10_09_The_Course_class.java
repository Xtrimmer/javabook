package chapter_10;

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

    private static class Course {
        private final String courseName;
        private String[] students = new String[100];
        private int numberOfStudents;

        Course(String courseName) {
            this.courseName = courseName;
        }

        void addStudent(String student) {
            if (numberOfStudents >= students.length) {
                String[] temp = new String[students.length * 2];
                System.arraycopy(students, 0, temp, 0, students.length);
                students = temp;
            }
            students[numberOfStudents] = student;
            numberOfStudents++;
        }

        String[] getStudents() {
            return students;
        }

        int getNumberOfStudents() {
            return numberOfStudents;
        }

        String getCourseName() {
            return courseName;
        }

        void dropStudent(String student) {
            int index = getIndexOfStudent(student);
            if (index >= 0) {
                System.arraycopy(students, index + 1, students, index, numberOfStudents - index - 1);
            }
            students[--numberOfStudents] = null;
        }

        int getIndexOfStudent(String student) {
            for (int i = 0; i < numberOfStudents; i++) {
                if (students[i].equals(student)) {
                    return i;
                }
            }
            return -1;
        }

        void clear() {
            students = new String[100];
            numberOfStudents = 0;
        }
    }
}
