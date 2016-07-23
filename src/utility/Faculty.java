package utility;

/**
 * Created for Chapter 11 Exercise 02.
 */
public class Faculty extends Employee {
    private String officeHours = "8:00 AM - 2:00 PM";
    private String rank = "Level 5";

    public Faculty() {
    }

    public Faculty(String name) {
        super(name);
    }

    public Faculty(String name, String officeHours, String rank) {
        super(name);
        this.officeHours = officeHours;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
