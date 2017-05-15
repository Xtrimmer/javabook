package chapter_20;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * (Game: solution ratio for 24-point game) When you pick four cards from a
 * deck of 52 cards for the 24-point game introduced in Programming Exercise
 * 20.13, the four cards may not have a 24-point solution. What is the number
 * of all possible picks of four cards from 52 cards? Among all possible picks,
 * how many of them have 24-point solutions? What is the success ratio—that is,
 * (number of picks with solutions)/ (number of all possible picks of four cards)?
 * Write a program to find these answers.
 */
public class PE_20_19_Game_solution_ratio_for_24_point_game {

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        int picks = displayCardPickCount();
        int solutions = displayCardPickWithSolutionCount();
        System.out.println();
        System.out.println("Picks with solutions: " + solutions);
        System.out.println("All possible picks  : " + picks);
        System.out.println("Success ratio       : " + (solutions / (double) picks * 100) + "%");
        System.out.println("Time Taken          : " + ((System.currentTimeMillis() - startTime) / 1000.0) + " Seconds");
    }

    private static int displayCardPickCount() {
        int count = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= j; k++) {
                    for (int l = 0; l <= k; l++) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int displayCardPickWithSolutionCount() {
        ArithmeticExpressionGenerator aeg = new ArithmeticExpressionGenerator();
        int count = 0;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= j; k++) {
                    for (int l = 0; l <= k; l++) {
                        List<Integer> operands = Arrays.asList(i + 1, j + 1, k + 1, l + 1);
                        if (aeg.hasSolution(24, operands)) {
                            System.out.printf("%-18s %16s%n", "Solution Found", operands);
                            count++;
                        } else {
                            System.out.printf("%-18s %16s%n", "Solution not found", operands);
                        }
                    }
                }
            }
        }
        return count;
    }

    private static class ArithmeticExpressionGenerator {
        private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        private final char[] operators = {'+', '-', '*', '/'};


        public boolean hasSolution(int goal, List<Integer> operands) {
            List<List<Integer>> operandPermutations = permuteList(operands);
            for (List<Integer> operandPermutation : operandPermutations) {
                for (int i = 0; i < Math.pow(operators.length, operators.length); i++) {
                    List<String> expression = new ArrayList<>();
                    String operatorCombo = getOperatorCombinations(operands.size() - 1, i);
                    expression.add(operandPermutation.get(0).toString());
                    for (int j = 0; j < operatorCombo.length(); j++) {
                        expression.add(operatorCombo.charAt(j) + "");
                        expression.add(operandPermutation.get(j + 1) + "");
                    }
                    List<String> expressions = permuteParenthesis(expression);
                    for (String s : expressions) {
                        Double answer;
                        try {
                            answer = expressionEvaluator.evaluate(s);
                        } catch (ArithmeticException e) {
                            continue;
                        }
                        if (answer.equals((double) goal)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        private String buildSpecialExpression(List<String> expression) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("((");
            for (int i = 0; i < 3; i++) stringBuilder.append(expression.get(i));
            stringBuilder.append(")");
            stringBuilder.append(expression.get(3));
            stringBuilder.append("(");
            for (int i = 4; i < 7; i++) stringBuilder.append(expression.get(i));
            stringBuilder.append("))");
            return stringBuilder.toString();
        }

        private String getOperatorCombinations(int length, int index) {
            int operatorCount = operators.length;
            StringBuilder stringBuilder = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                stringBuilder.append(operators[index % operatorCount]);
                index /= operatorCount;
            }
            return stringBuilder.toString();
        }

        private <E> void permuteList(List<List<E>> permutations, List<E> permutation, List<E> operands) {
            if (operands.isEmpty()) permutations.add(permutation);
            for (E operand : operands) {
                List<E> permutation2 = new ArrayList<>(permutation);
                permutation2.add(operand);
                List<E> operands2 = new LinkedList<>(operands);
                operands2.remove(operand);
                permuteList(permutations, permutation2, operands2);
            }
        }

        private <E> List<List<E>> permuteList(List<E> operands) {
            List<List<E>> permutations = new ArrayList<>();
            permuteList(permutations, new ArrayList<>(operands.size()), operands);
            return permutations;
        }

        private List<String> permuteParenthesis(List<String> expression) {
            List<String> permutations = new LinkedList<>();
            permuteParenthesis(permutations, new LinkedList<>(), expression, new LinkedList<>());
            String specialExpression = buildSpecialExpression(expression);
            permutations.add(specialExpression);
            return permutations;
        }

        private void permuteParenthesis(List<String> permutations, List<String> prefix, List<String> expression, List<String> suffix) {
            if (expression.size() < 3) return;
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : prefix) stringBuilder.append(s);
            stringBuilder.append("(");
            for (String s : expression) stringBuilder.append(s);
            stringBuilder.append(")");
            for (String s : suffix) stringBuilder.append(s);
            permutations.add(stringBuilder.toString());
            for (int i = 2; i < expression.size() - 2; i += 2) {
                for (int startIndex = 0, endIndex = expression.size() - i;
                     endIndex <= expression.size(); startIndex += 2, endIndex += 2) {
                    List<String> prefix2 = new LinkedList<>(prefix);
                    prefix2.add("(");
                    prefix2.addAll(expression.subList(0, startIndex));
                    List<String> suffix2 = new LinkedList<>(expression.subList(endIndex, expression.size()));
                    suffix2.add(")");
                    suffix2.addAll(suffix);
                    permuteParenthesis(permutations, prefix2, expression.subList(startIndex, endIndex), suffix2);
                }
            }
        }
    }

    private static class ExpressionEvaluator {

        public Double evaluate(String expression) {
            LinkedList<Double> operandStack = new LinkedList<>();
            LinkedList<Character> operatorStack = new LinkedList<>();
            expression = insertBlanks(expression);
            String[] tokens = expression.split(" ");

            for (String token : tokens) {
                if (isWhitespace(token)) continue;
                else if (token.charAt(0) == '+' || token.charAt(0) == '-') {
                    while (!operatorStack.isEmpty() &&
                            (operatorStack.peek() == '+' ||
                                    operatorStack.peek() == '-' ||
                                    operatorStack.peek() == '*' ||
                                    operatorStack.peek() == '/')) {
                        processAnOperator(operandStack, operatorStack);
                    }
                    operatorStack.push(token.charAt(0));
                } else if (token.charAt(0) == '*' || token.charAt(0) == '/') {
                    while (!operatorStack.isEmpty() &&
                            (operatorStack.peek() == '*' ||
                                    operatorStack.peek() == '/')) {
                        processAnOperator(operandStack, operatorStack);
                    }
                    operatorStack.push(token.charAt(0));
                } else if (token.trim().charAt(0) == '(') {
                    operatorStack.push('(');
                } else if (token.trim().charAt(0) == ')') {
                    while (operatorStack.peek() != '(') {
                        processAnOperator(operandStack, operatorStack);
                    }
                    operatorStack.pop();
                } else {
                    operandStack.push(new Double(token));
                }
            }
            while (!operatorStack.isEmpty()) {
                processAnOperator(operandStack, operatorStack);
            }
            Double result = operandStack.pop();
            if (!operandStack.isEmpty()) throw new IllegalArgumentException();
            return result;
        }

        private String insertBlanks(String s) {
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

        private boolean isWhitespace(String token) {
            return token.length() == 0;
        }

        private void processAnOperator(
                LinkedList<Double> operandStack, LinkedList<Character> operatorStack) {
            char operator = operatorStack.pop();
            Double operand1;
            Double operand2;
            operand1 = operandStack.pop();
            operand2 = operandStack.pop();
            if (operator == '+')
                operandStack.push(operand2 + operand1);
            else if (operator == '-')
                operandStack.push(operand2 - operand1);
            else if (operator == '*')
                operandStack.push(operand2 * operand1);
            else if (operator == '/')
                operandStack.push(operand2 / operand1);
        }
    }
}
