package chapter_18;

import java.util.Scanner;

/**
 * (Fibonacci series) Modify Listing 18.2, ComputeFibonacci.java, so that the program
 * finds the number of times the fib method is called. (Hint: Use a static
 * variable and increment it every time the method is called.)
 */
public class PE_18_07_Fibonacci_series {

    private static int callCount = 0;

    /**
     * Main method
     */
    public static void main(String args[]) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter an index for the Fibonacci number: ");
        int index = input.nextInt();

        // Find and display the Fibonacci number
        System.out.println("Fibonacci number at index " + index + " is " + fib(index));

        // Display the method call count
        System.out.println("Call count = " + callCount);
    }

    /**
     * The method for finding the Fibonacci number
     */
    private static long fib(long index) {
        callCount++;
        if (index == 0) // Base case
            return 0;
        else if (index == 1) // Base case
            return 1;
        else  // Reduction and recursive calls
            return fib(index - 1) + fib(index - 2);
    }
}
