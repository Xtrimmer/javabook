package chapter_12;

/**
 * (NumberFormatException) Listing 7.9, Calculator.java, is a simple commandline
 * calculator. Note that the program terminates if any operand is non-numeric.
 * Write a program with an exception handler that deals with non-numeric operands;
 * then write another program without using an exception handler to achieve the
 * same objective. Your program should display a message that informs the user of
 * the wrong operand type before exiting (see Figure 12.12).
 *
 *      FIGURE 12.12(p.488) The program performs arithmetic operations and detects
 *      input errors.
 */
public class PE_12_01_Part_2_NumberFormatException {
    /**
     * Main method
     */
    public static void main(String[] args) {
        // Check number of strings passed
        if (args.length != 3) {
            System.out.println(
                    "Usage: java Calculator operand1 operator operand2");
            System.exit(0);
        }
        for (int i = 0; i < args.length; i += 2) {
            if (!isInteger(args[i])) {
                System.out.println("Wrong input: " + args[i]);
                System.exit(0);
            }
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

    public static boolean isInteger(String s){
        StringBuilder string = new StringBuilder(s);
        if (string.charAt(0) == '-') string.deleteCharAt(0);
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (!Character.isDigit(currentChar)) return false;
        }
        return true;
    }
}
