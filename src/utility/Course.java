package utility;

/**
 * Added for Chapter 10 Exercise 09.
 */
public class Course {
    private String courseName;
    private String[] students = new String[100];
    private int numberOfStudents;

    public Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
        if (numberOfStudents >= students.length) {
            String[] temp = new String[students.length * 2];
            System.arraycopy(students, 0, temp, 0, students.length);
            students = temp;
        }
        students[numberOfStudents] = student;
        numberOfStudents++;
    }

    public String[] getStudents() {
        return students;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public String getCourseName() {
        return courseName;
    }

    public void dropStudent(String student) {
        int index = getIndexOfStudent(student);
        if (index >= 0) {
            System.arraycopy(students, index + 1, students, index, numberOfStudents - index - 1);
        }
        students[--numberOfStudents] = null;
    }

    public int getIndexOfStudent(String student) {
        for (int i = 0; i < numberOfStudents; i++) {
            if (students[i].equals(student)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        students = new String[100];
        numberOfStudents = 0;
    }
}
