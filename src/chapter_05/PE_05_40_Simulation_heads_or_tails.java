package chapter_05;

/**
 * (Simulation: heads or tails) Write a program that simulates flipping a coin one
 * million times and displays the number of heads and tails.
 */
public class PE_05_40_Simulation_heads_or_tails {
    public static void main(String[] args) {
        int heads = 0;
        for (int i = 0; i < 1000000; i++) {
            heads += (int) (Math.random() * 2);
        }
        System.out.printf("Heads: %,d%nTails: %,d", heads, (1000000 - heads));
    }
}
