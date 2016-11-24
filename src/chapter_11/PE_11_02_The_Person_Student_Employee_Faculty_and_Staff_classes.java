package chapter_11;

import utility.*;

/**
 * (The Person, Student, Employee, Faculty, and Staff classes) Design a
 * class named Person and its two subclasses named Student and Employee.
 * Make Faculty and Staff subclasses of Employee. A person has a name,
 * address, phone number, and email address. A student has a class status (freshman,
 * sophomore, junior, or senior). Define the status as a constant. An employee has
 * an office, salary, and date hired. Use the MyDate class defined in Programming
 * Exercise 10.14 to create an object for date hired. A faculty member has office
 * hours and a rank. A staff member has a title. Override the toString method in
 * each class to display the class name and the person’s name.
 *
 * Draw the UML diagram for the classes and implement them. Write a test program
 * that creates a Person, Student, Employee, Faculty, and Staff, and
 * invokes their toString() methods.
 */
public class PE_11_02_The_Person_Student_Employee_Faculty_and_Staff_classes {
    public static void main(String[] args) {
        Person[] people = new Person[5];

        people[0] = new Person("Allen Anderson");
        people[1] = new Student("Bill Brown");
        people[2] = new Employee("Charlie Chapman");
        people[3] = new Faculty("Dave Dyer");
        people[4] = new Staff("Edward Erickson");

        for (Person person: people) {
            System.out.println(person);
        }
    }
}
