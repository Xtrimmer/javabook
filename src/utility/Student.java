package utility;

/**
 * Created for Chapter 11 Exercise 02.
 */
public class Student extends Person {
    public static final String FRESHMAN = "Freshman";
    public static final String SOPHOMORE = "Sophomore";
    public static final String JUNIOR = "Junior";
    public static final String SENIOR = "Senior";

    private String status = FRESHMAN;

    public Student() {
    }

    public Student(String name) {
        super(name);
    }

    public Student(String name, String status) {
        super(name);
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
