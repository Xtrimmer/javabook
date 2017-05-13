package chapter_20;

import java.util.LinkedList;

/**
 * (Convert infix to postfix) Write a method that converts an infix expression into
 * a postfix expression using the following header:
 *
 *      public static String infixToPostfix(String expression)
 *
 * For example, the method should convert the infix expression (1 + 2) * 3 to
 * 1 2 + 3 * and 2 * (1 + 3) to 2 1 3 + *.
 */
public class PE_20_16_Convert_infix_to_postfix {
    public static void main(String[] args) {
        String infixExpression = "(1+2*3-4)/(5*6)";
        String postfixExpression = infixToPostfix(infixExpression);
        System.out.println(postfixExpression);
    }

    public static String infixToPostfix(String infixExpression) {
        StringBuilder postfixExpression = new StringBuilder();
        LinkedList<Character> operatorStack = new LinkedList<>();
        infixExpression = insertBlanks(infixExpression);
        String[] tokens = infixExpression.split(" ");
        for (String token : tokens) {
            if (isWhitespace(token)) continue;
            else if (isAdditionOrSubtraction(token.charAt(0))) {
                while (!operatorStack.isEmpty() && isArithmeticOperator(operatorStack.peek())) {
                    postfixExpression.append(operatorStack.pop());
                }
                operatorStack.push(token.charAt(0));
            } else if (isMultiplicationOrDivision(token.charAt(0))) {
                while (!operatorStack.isEmpty() && isMultiplicationOrDivision(operatorStack.peek())) {
                    postfixExpression.append(operatorStack.pop());
                }
                operatorStack.push(token.charAt(0));
            } else if (token.trim().charAt(0) == '(') {
                operatorStack.push(token.trim().charAt(0));
            } else if (token.trim().charAt(0) == ')') {
                while (operatorStack.peek() != '(') {
                    postfixExpression.append(operatorStack.pop());
                }
                operatorStack.pop();
            } else {
                postfixExpression.append(token);
            }
        }
        while (!operatorStack.isEmpty()) {
            postfixExpression.append(operatorStack.pop());
        }
        return postfixExpression.toString();
    }

    private static String insertBlanks(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')' ||
                    s.charAt(i) == '+' || s.charAt(i) == '-' ||
                    s.charAt(i) == '*' || s.charAt(i) == '/')
                result += " " + s.charAt(i) + " ";
            else
                result += s.charAt(i);
        }
        return result;
    }

    private static boolean isAdditionOrSubtraction(Character c) {
        return c == '+' || c == '-';
    }

    private static boolean isArithmeticOperator(Character c) {
        return isAdditionOrSubtraction(c) || isMultiplicationOrDivision(c);
    }

    private static boolean isMultiplicationOrDivision(Character c) {
        return c == '*' || c == '/';
    }

    private static boolean isWhitespace(String token) {
        return token.length() == 0;
    }
}
