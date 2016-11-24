package chapter_13;

import java.util.Arrays;

/**
 * (Enable the Course class cloneable) Rewrite the Course class in Listing 10.6
 * to add a clone method to perform a deep copy on the students field.
 */
public class PE_13_13_Enable_the_Course_class_cloneable {
    public static void main(String[] args) {
        PE_13_13_Course course1 = new PE_13_13_Course("Course1");
        course1.addStudent("Jeff");
        course1.addStudent("Harini");
        PE_13_13_Course course2 = (PE_13_13_Course)course1.clone();
        course2.addStudent("Amos");
        course2.addStudent("Jessica");
        course2.getStudents()[1] = "Mauricio";

        System.out.println("course1 = " + course1);
        System.out.println("   courseName = " + course1.getCourseName());
        System.out.println("   courseName = " + Arrays.toString(course1.getStudents()));

        System.out.println("course2 = " + course1);
        System.out.println("   courseName = " + course2.getCourseName());
        System.out.println("   courseName = " + Arrays.toString(course2.getStudents()));
    }

}

class PE_13_13_Course implements Cloneable{
    private String courseName;
    private String[] students = new String[100];
    private int numberOfStudents;

    public PE_13_13_Course(String courseName) {
        this.courseName = courseName;
    }

    public void addStudent(String student) {
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

    @Override
    public Object clone(){
        try {
            PE_13_13_Course clone = (PE_13_13_Course)super.clone();
            clone.students = this.students.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
