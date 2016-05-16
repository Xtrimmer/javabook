package Chapter_03;
import javax.swing.JOptionPane;
/**
 * (Sort three integers) Write a program that sorts three integers. The integers are
 * entered from the input dialogs and stored in variables num1, num2, and num3,
 * respectively. The program sorts the numbers so that num1 <= num2 <= num3.
 */
public class PE_03_08_Sort_three_integers {
    public static void main(String[] args) {
        int num1;
        int num2;
        int num3;
        int temp;

        String input = JOptionPane.showInputDialog("Enter the first number.");
        num1 = Integer.parseInt(input);
        input = JOptionPane.showInputDialog("Enter the second number.");
        num2 = Integer.parseInt(input);
        input = JOptionPane.showInputDialog("Enter the third number.");
        num3 = Integer.parseInt(input);

        JOptionPane.showMessageDialog(null, "[" + num1 + ", " + num2 + ", " + num3 + "]");
        if (num1 > num2) {
            temp = num2;
            num2 = num1;
            num1 = temp;
        }
        if (num2 > num3) {
            temp = num3;
            num3 = num2;
            num2 = temp;
        }
        if (num1 > num2) {
            temp = num2;
            num2 = num1;
            num1 = temp;
        }
        JOptionPane.showMessageDialog(null, "[" + num1 + ", " + num2 + ", " + num3 + "]");

    }
}
