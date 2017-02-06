package chapter_18;

import java.util.Scanner;

/**
 * (Tower of Hanoi) Modify Listing 18.8, TowerOfHanoi.java, so that the program
 * finds the number of moves needed to move n disks from tower A to tower B.
 * (Hint: Use a static variable and increment it every time the method is called.)
 */
public class PE_18_18_Tower_of_Hanoi {
    private static int methodCalls = 0;

    public static void main(String[] args) {
        // Create a Scanner
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = input.nextInt();

        // Find the solution recursively
        System.out.println("The moves are:");
        moveDisks(n, 'A', 'B', 'C');
        System.out.println("Number of method calls: " + methodCalls);
    }

    /** The method for finding the solution to move n disks
     from fromTower to toTower with auxTower */
    private static void moveDisks(int n, char fromTower,
                                  char toTower, char auxTower) {
        methodCalls++;
        if (n == 1) // Stopping condition
            System.out.println("Move disk " + n + " from " +
                    fromTower + " to " + toTower);
        else {
            moveDisks(n - 1, fromTower, auxTower, toTower);
            System.out.println("Move disk " + n + " from " +
                    fromTower + " to " + toTower);
            moveDisks(n - 1, auxTower, toTower, fromTower);
        }
    }
}
