package chapter_04;

/**
 * (Random character) Write a program that displays a random uppercase letter
 * using the Math.random() method.
 */
public class PE_04_16_Random_character {
    public static void main(String[] args) {
        System.out.println((char)(Math.random() * 26 + 'A'));
    }
}
