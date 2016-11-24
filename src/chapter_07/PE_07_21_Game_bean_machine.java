package chapter_07;

import java.util.Scanner;

/**
 * (Game: bean machine) The bean machine, also known as a quincunx or the Galton
 * box, is a device for statistics experiments named after English scientist Sir
 * Francis Galton. It consists of an upright board with evenly spaced nails (or pegs)
 * in a triangular form, as shown in Figure 7.13.
 *
 * FIGURE 7.13 Each ball takes a random path and falls into a slot. (p. 280)
 *
 * Balls are dropped from the opening of the board. Every time a ball hits a nail, it
 * has a 50% chance of falling to the left or to the right. The piles of balls are accumulated
 * in the slots at the bottom of the board.
 * Write a program that simulates the bean machine. Your program should prompt
 * the user to enter the number of the balls and the number of the slots in the machine.
 * Simulate the falling of each ball by printing its path. For example, the path for
 * the ball in Figure 7.13b is LLRRLLR and the path for the ball in Figure 7.13c is
 * RLRRLRR. Display the final buildup of the balls in the slots in a histogram. Here
 * is a sample run of the program:
 *
 *      Enter the number of balls to drop: 5 (enter)
 *      Enter the number of slots in the bean machine: 8 (enter)
 *
 *      LRLRLRR
 *      RRLLLRR
 *      LLRLLRR
 *      RRLLLLL
 *      LRLRRLR
 *
 *        O
 *        O
 *      OOO
 *
 * (Hint: Create an array named slots. Each element in slots stores the number
 * of balls in a slot. Each ball falls into a slot via a path. The number of Rs in
 * a path is the position of the slot where the ball falls. For example, for the path
 * LRLRLRR, the ball falls into slots[4], and for the path is RRLLLLL, the ball
 * falls into slots[2].)
 */
public class PE_07_21_Game_bean_machine {
    public static void main(String[] args) {
        int balls = getBallCount();
        int slots = getSlotCount();
        System.out.println();
        beanMachineSim(balls, slots);
    }

    public static int getBallCount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of balls: ");
        return scanner.nextInt();
    }

    public static int getSlotCount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of slots: ");
        return scanner.nextInt();
    }

    public static void beanMachineSim(int numberOfBalls, int numberOfSlots) {
        int[] slots = new int[numberOfSlots];
        int slot;
        for (int i = 0; i < numberOfBalls; i++) {
            slot = 0;
            for (int j = 0; j < numberOfSlots - 1; j++) {
                if (Math.random() < 0.5) {
                    System.out.print("L");
                } else {
                    System.out.print("R");
                    slot++;
                }
            }
            System.out.println();
            slots[slot]++;
        }
        System.out.println();
        printSlots(slots);
    }

    public static void printSlots(int[] slots) {
        int max = max(slots);
        for (int i = max; i >0; i--) {
            for (int slot : slots) {
                if (slot >= i) System.out.print("O");
                else System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static int max(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) max = array[i];
        }
        return max;
    }
}
