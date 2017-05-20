package chapter_20;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * (Nonrecursive Tower of Hanoi) Implement the moveDisks method in Listing
 * 18.8 using a stack instead of using recursion.
 */
public class PE_20_22_Nonrecursive_Tower_of_Hanoi {

    private static final Deque<Move> stack = new LinkedList<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter number of disks: ");
        int n = input.nextInt();
        System.out.println("The moves are:");
        moveDisks(n);
    }

    /** The method for finding the solution to move n disks
     from fromTower to toTower with auxTower */
    private static void moveDisks(int discCount) {
        stack.push(new Move(discCount, 'A', 'B', 'C', false));
        while (!stack.isEmpty()) {
            Move move = stack.pop();
            if (move.doPrint || move.n == 1) {
                System.out.println(move);
            } else {
                stack.push(new Move(move.n - 1, move.auxTower, move.toTower, move.fromTower, false));
                stack.push(new Move(move.n, move.fromTower, move.toTower, move.auxTower, true));
                stack.push(new Move(move.n - 1, move.fromTower, move.auxTower, move.toTower, false));
            }
        }
    }

    private static class Move {
        final int n;
        final char fromTower;
        final char toTower;
        final char auxTower;
        final boolean doPrint;

        public Move(int n, char fromTower, char toTower, char auxTower, boolean doPrint) {
            this.n = n;
            this.fromTower = fromTower;
            this.toTower = toTower;
            this.auxTower = auxTower;
            this.doPrint = doPrint;
        }

        @Override
        public String toString() {
            return String.format("Move disk %d from %c to %c", n, fromTower, toTower);
        }
    }
}
