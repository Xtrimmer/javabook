package chapter_20;

import java.util.LinkedList;

/**
 * (Postfix notation) Postfix notation is a way of writing expressions without
 * using parentheses. For example, the expression (1 + 2) * 3 would be
 * written as 1 2 + 3 *. A postfix expression is evaluated using a stack. Scan a
 * postfix expression from left to right. A variable or constant is pushed into the
 * stack. When an operator is encountered, apply the operator with the top two
 * operands in the stack and replace the two operands with the result. The following
 * diagram shows how to evaluate 1 2 + 3 *.
 *
 * Write a program to evaluate postfix expressions. Pass the expression as a
 * command-line argument in one string.
 */
public class PE_20_14_Postfix_notation {

    private static final LinkedList<Double> stack = new LinkedList<>();

    public static void main(String[] args) {
        validateArgCount(args, 1);
        String postfixExpression = args[0];
        Double result = null;
        try {
            result = evaluate(postfixExpression);
        } catch (Exception e) {
            System.out.println("Invalid Expression");
        }
        System.out.println(postfixExpression + " = " + result);
    }

    private static void calculate(Double operand2, Double operand1, String operator) {
        switch (operator) {
            case "+":
                stack.push(operand1 + operand2);
                break;
            case "-":
                stack.push(operand1 - operand2);
                break;
            case "*":
                stack.push(operand1 * operand2);
                break;
            case "/":
                stack.push(operand1 / operand2);
                break;
        }
    }

    private static Double evaluate(String postfixExpression) {
        String[] tokens = postfixExpression.split(" ");
        for (String token : tokens) {
            if (isNumeric(token)) stack.push(new Double(token));
            else if (isOperator(token)) calculate(stack.pop(), stack.pop(), token);
        }
        return stack.pop();
    }

    private static boolean isNumeric(String string) {
        return string.matches("-?\\d+(\\.\\d+)?");
    }

    private static boolean isOperator(String string) {
        return string.equals("+") || string.equals("-") ||
                string.equals("*") || string.equals("/");
    }

    private static void validateArgCount(String[] args, int size) {
        if (args.length != size) {
            System.out.println("Wrong number of arguments");
            System.exit(0);
        }
    }
}
