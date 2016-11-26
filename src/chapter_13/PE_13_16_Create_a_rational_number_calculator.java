package chapter_13;

import textbook_listings.Rational;

/**
 * (Create a rational-number calculator) Write a program similar to Listing 7.9,
 * Calculator.java. Instead of using integers, use rationals, as shown in Figure 13.10a.
 * You will need to use the split method in the String class, introduced in
 * Section 10.10.3, Replacing and Splitting Strings, to retrieve the numerator string and
 * denominator string, and convert strings into integers using the Integer.parseInt
 * method.
 *
 * FIGURE 13.10(p.531)(a) The program takes three arguments (operand1, operator, and operand2)
 * from the command line and displays the expression and the result of the arithmetic
 * operation.
 */
public class PE_13_16_Create_a_rational_number_calculator {
    public static void main(String[] args) {
        // Check number of strings passed
        if (args.length != 3) {
            System.out.println(
                    "Usage: java Calculator operand1 operator operand2");
            System.exit(0);
        }

        // Parse the rational values from argument strings.
        String[] values = args[0].split("/");
        Rational rational1 = new Rational(Long.parseLong(values[0]), Long.parseLong(values[1]));
        values = args[2].split("/");
        Rational rational2 = new Rational(Long.parseLong(values[0]), Long.parseLong(values[1]));

        // The result of the operation
        Rational result = null;

        // Determine the operator
        switch (args[1].charAt(0)) {
            case '+': result = rational1.add(rational2);
                break;
            case '-': result = rational1.subtract(rational2);
                break;
            case '.': result = rational1.multiply(rational2);
                break;
            case '/': result = rational1.divide(rational2);
        }

        // Display result
        System.out.println(args[0] + ' ' + args[1] + ' ' + args[2]
                + " = " + result);
    }
}

