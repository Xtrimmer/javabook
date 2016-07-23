package utility;

/**
 * Created for Chapter 11 Exercise 02.
 */
public class Employee extends Person {
    private String office = "default office";
    private double salary = 40000.0;
    private MyDate dateHired = new MyDate();

    public Employee() {
    }

    public Employee(String name) {
        super(name);
    }

    public Employee(String name, String office, double salary, MyDate dateHired) {
        super(name);
        this.office = office;
        this.salary = salary;
        this.dateHired = dateHired;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public MyDate getDateHired() {
        return dateHired;
    }

    public void setDateHired(MyDate dateHired) {
        this.dateHired = dateHired;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
