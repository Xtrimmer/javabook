package Chapter_10;

import static Chapter_10.PE_10_25_New_string_split_method.*;

/**
 * (Calculator) Revise Listing 7.9, Calculator.java, to accept an expression as
 * a string in which the operands and operator are separated by zero or more
 * spaces. For example, 3+4 and 3 + 4 are acceptable expressions. A sample run
 * is shown on p. 407.
 */
public class PE_10_26_Calculator {
    /**
     * Main method
     */
    public static void main(String[] args) {
        // Check number of strings passed
        if (args.length == 1)
            args = split(args[0], "[+-*/]");
        if (args.length != 3) {
            System.out.println(
                    "Usage: java Calculator operand1 operator operand2");
            System.exit(0);
        }
        // The result of the operation
        int result = 0;
        // Determine the operator
        switch (args[1].charAt(0)) {
            case '+':
                result = Integer.parseInt(args[0]) +
                        Integer.parseInt(args[2]);
                break;
            case '-':
                result = Integer.parseInt(args[0]) -
                        Integer.parseInt(args[2]);
                break;
            case '.':
                result = Integer.parseInt(args[0]) *
                        Integer.parseInt(args[2]);
                break;
            case '/':
                result = Integer.parseInt(args[0]) /
                        Integer.parseInt(args[2]);
        }
        // Display result
        System.out.println(args[0] + ' ' + args[1] + ' ' + args[2]
                + " = " + result);
    }
}
