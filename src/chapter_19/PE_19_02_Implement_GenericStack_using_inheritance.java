package chapter_19;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * (Implement GenericStack using inheritance) In Listing 19.1, GenericStack is
 * implemented using composition. Define a new stack class that extends ArrayList.
 * Draw the UML diagram for the classes and then implement GenericStack.
 * Write a test program that prompts the user to enter five strings and displays them in
 * reverse order.
 */
public class PE_19_02_Implement_GenericStack_using_inheritance {
    public static void main(String[] args) {
        GenericStack<String> stack = promptUserForStrings(5);
        printReverse(stack);
    }

    private static void printReverse(GenericStack<?> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    private static GenericStack<String> promptUserForStrings(int numberOfStrings) {
        if (numberOfStrings < 0) throw new InputMismatchException("Negative number not allowed");
        Scanner scanner = new Scanner(System.in);
        GenericStack<String> strings = new GenericStack<>();
        if (numberOfStrings == 0) return strings;
        System.out.println("Enter a String: ");
        strings.push(scanner.nextLine());
        for (int i = 1; i < numberOfStrings; i++) {
            System.out.println("Enter another String: ");
            strings.push(scanner.nextLine());
        }
        return strings;
    }

    static class GenericStack<E> extends ArrayList<E> {

        public int getSize() {
            return size();
        }

        public boolean isEmpty() {
            return super.isEmpty();
        }

        public E peek() {
            return get(getSize() - 1);
        }

        public E pop() {
            E o = get(getSize() - 1);
            remove(getSize() - 1);
            return o;
        }

        public void push(E o) {
            add(o);
        }

        @Override
        public String toString() {
            return "stack: " + super.toString();
        }
    }
}