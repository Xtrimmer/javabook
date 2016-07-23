package utility;

/**
 * Created for Chapter 11 Exercise 02.
 */
public class Person {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public Person() {
        this("default name", "default address", "555-555-5555", "defaultname@domain.com");
    }

    public Person(String name) {
        this(name, "default address", "555-555-5555", "defaultname@domain.com");
    }

    public Person(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
