package utility;

/**
 * Created for Chapter 11 Exercise 02.
 */
public class Staff extends Employee {
    private String title = "default title";

    public Staff() {
    }

    public Staff(String name) {
        super(name);
    }

    public Staff(String name, String title) {
        super(name);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
