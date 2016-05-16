package Chapter_03;
import javax.swing.JOptionPane;
/**
 * (Algebra: solve quadratic equations) The two roots of a quadratic equation
 * ax^2 + bx + c = 0 can be obtained using the following formula:
 *              __________                          __________
 *  r1 = -b + √(b^2 - 4ac) / 2a   and   r2 = -b - √(b^2 - 4ac) / 2a
 *
 * b^2 - 4ac is called the discriminant of the quadratic equation. If it is positive, the
 * equation has two real roots. If it is zero, the equation has one root. If it is negative,
 * the equation has no real roots.
 * Write a program that prompts the user to enter values for a, b, and c and displays
 * the result based on the discriminant. If the discriminant is positive, display two
 * roots. If the discriminant is 0, display one root. Otherwise, display “The equation
 * has no real roots”.
 */
public class PE_03_01_Algebra_solve_quadratic_equations {
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter a value for a:");
        double a = Double.parseDouble(input);
        input = JOptionPane.showInputDialog("Enter a value for b:");
        double b = Double.parseDouble(input);
        input = JOptionPane.showInputDialog("Enter a value for c:");
        double c = Double.parseDouble(input);

        double discriminant = Math.pow(b, 2) - (4 * a * c);

        //JOptionPane.showMessageDialog(null, "The discriminant is: " + discriminant);

        double root1;
        double root2;
        if (discriminant > 0){
            root1 = (-b - Math.sqrt(discriminant)) / (2 * a);
            root2 = (-b + Math.sqrt(discriminant)) / (2 * a);
            JOptionPane.showMessageDialog(null, "The roots are:\n[" + root1 + ", " + root2 + "]");
        } else if (discriminant == 0) {
            root1 = -b / (2 * a);
            JOptionPane.showMessageDialog(null, "The root is:\n[" + root1 + "]");
        } else {
            JOptionPane.showMessageDialog(null, "There are no real roots.");
        }
    }
}
