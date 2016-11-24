package chapter_11;

import utility.MyStack;

import java.util.Scanner;

/**
 * (Implement MyStack using inheritance) In Listing 11.10, MyStack is implemented
 * using composition. Define a new stack class that extends ArrayList.
 * Draw the UML diagram for the classes and then implement MyStack. Write
 * a test program that prompts the user to enter five strings and displays them in
 * reverse order.
 */
public class PE_11_10_Implement_MyStack_using_inheritance {
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter String " + i + ": ");
            stack.add(scanner.nextLine());
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}
