package chapter_10;

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
            args = MyStringUtility.split(args[0], "[+-*/]");
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

    private static class MyStringUtility {
        static String[] split(String s, String regex) {
            String[] delimiters = getDelimiters(regex);
            String[] splitStrings = new String[s.length()];
            StringBuilder stringBuilder = new StringBuilder(s);
            int position = 0;
            while (stringBuilder.length() > 0) {
                if (!containsDelimiters(stringBuilder, delimiters)) {
                    splitStrings[position++] = stringBuilder.toString();
                    break;
                }
                int minIndex = stringBuilder.length();
                int delimiterLength = 1;
                for (String delimiter : delimiters) {
                    int index = stringBuilder.indexOf(delimiter);
                    if (index == 0) {
                        minIndex = 0;
                        break;
                    } else if (index > 0 && index < minIndex) {
                        minIndex = index;
                        delimiterLength = delimiter.length();
                    }
                }
                if (minIndex != 0) splitStrings[position++] = stringBuilder.substring(0, minIndex);
                splitStrings[position++] = stringBuilder.substring(minIndex, minIndex + delimiterLength);
                stringBuilder.delete(0, minIndex + delimiterLength);
            }
            String[] array = new String[position];
            System.arraycopy(splitStrings, 0, array, 0, position);
            return array;
        }

        private static boolean containsDelimiters(StringBuilder s, String[] delimiters) {
            boolean hasDelimiters = false;
            for (String delimiter : delimiters) {
                hasDelimiters |= s.indexOf(delimiter) >= 0;
            }
            return hasDelimiters;
        }

        private static String[] getDelimiters(String regex) {
            int bracketStart = regex.indexOf('[');
            int bracketEnd = regex.indexOf(']');
            int lastPosition = regex.length() - 1;
            String[] delimiters;
            if (bracketStart == 0 && bracketEnd == lastPosition) {
                delimiters = new String[(bracketEnd - bracketStart - 1)];
                for (int i = 0; i < regex.length() - 2; i++) {
                    delimiters[i] = "" + regex.charAt(i + 1);
                }
            } else {
                delimiters = new String[1];
                delimiters[0] = regex;
            }
            return delimiters;
        }
    }
}
