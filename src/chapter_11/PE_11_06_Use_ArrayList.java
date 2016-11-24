package chapter_11;

import javafx.scene.shape.Circle;
import utility.Loan;

import java.util.ArrayList;
import java.util.Date;

/**
 * (Use ArrayList) Write a program that creates an ArrayList and adds a Loan
 * object, a Date object, a string, and a Circle object to the list, and use a loop
 * to display all the elements in the list by invoking the object’s toString()
 * method.
 */
public class PE_11_06_Use_ArrayList {
    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();

        objects.add(new Loan());
        objects.add(new Date());
        objects.add("This is a String");
        objects.add(new Circle());

        for (Object object : objects) {
            System.out.println(object);
        }
    }
}
