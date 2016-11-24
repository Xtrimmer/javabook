package chapter_03;
import javax.swing.JOptionPane;
/**
 * (Game: learn addition) Write a program that generates two integers under 100
 * and prompts the user to enter the sum of these two integers. The program then
 * reports true if the answer is correct, false otherwise. The program is similar to
 * Listing 3.1.
 */
public class PE_03_10_Game_addition_quiz {
    public static void main(String[] args) {
        int number1 = (int)(Math.random() * 100) + 1;
        int number2 = (int)(Math.random() * 100) + 1;

        String input = JOptionPane.showInputDialog("What is " + number1 + " + " + number2 + "?");
        int answer = Integer.parseInt(input);

        JOptionPane.showMessageDialog(null, number1 + " + " + number2 + " = " + answer + " is " +
                                    (number1 + number2 == answer));
    }
}
