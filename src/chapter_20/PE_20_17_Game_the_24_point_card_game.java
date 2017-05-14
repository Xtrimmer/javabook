package chapter_20;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * (Game: the 24-point card game) This exercise is a variation of the 24-point
 card game described in Programming Exercise 20.13. Write a program to
 check whether there is a 24-point solution for the four specified numbers. The
 program lets the user enter four values, each between 1 and 13, as shown in
 Figure 20.21. The user can then click the Solve button to display the solution or
 display “No solution” if none exist.
 */
public class PE_20_17_Game_the_24_point_card_game extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new GamePane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise20_17");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class GamePane extends BorderPane {
        private static final int MIN_OPERAND_VALUE = 1;
        private static final int MAX_OPERAND_VALUE = 13;
        private final TextField textFieldExpression = new TextField();
        private final TextField[] inputTextFields = initializeInputTextFields(4);
        private final Button buttonSolve = new Button("Solve");

        public GamePane() {
            setTop(createSolverPane());
            setCenter(createInputPane());
        }

        private Node createInputPane() {
            HBox hBox = new HBox(5);
            for (TextField inputTextField : inputTextFields) {
                hBox.getChildren().add(inputTextField);
            }
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(0, 10, 10, 10));
            return hBox;
        }

        private Node createSolverPane() {
            textFieldExpression.setEditable(false);
            buttonSolve.setOnAction(event -> solve());
            HBox hBox = new HBox(5, textFieldExpression, buttonSolve);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10));
            return hBox;
        }

        private TextField[] initializeInputTextFields(int size) {
            TextField[] textFields = new TextField[size];
            for (int i = 0; i < size; i++) {
                textFields[i] = new TextField("1");
                textFields[i].setPrefColumnCount(2);
                textFields[i].setFont(Font.font(24));
                textFields[i].setAlignment(Pos.CENTER);
                int finalI = i;
                textFields[i].focusedProperty().addListener(
                        (observable, oldValue, newValue) -> validateNumber(textFields[finalI])
                );
            }
            return textFields;
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                int number = (int) Double.parseDouble(textField.getText());
                textField.setText(number + "");
                return number;
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }

        private void solve() {
            List<Integer> operands = new ArrayList<>();
            for (TextField inputTextField : inputTextFields) {
                operands.add(parseInt(inputTextField, 1));
            }
            ArithmeticExpressionGenerator expressionGenerator = new ArithmeticExpressionGenerator();
            String expression = expressionGenerator.generateExpression(24, operands);
            textFieldExpression.setText(expression);
        }

        private void validateNumber(TextField textField) {
            int value = parseInt(textField, 1);
            if (value < MIN_OPERAND_VALUE) textField.setText(MIN_OPERAND_VALUE + "");
            if (value > MAX_OPERAND_VALUE) textField.setText(MAX_OPERAND_VALUE + "");
        }
    }

    private class ArithmeticExpressionGenerator {
        private final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
        private final char[] operators = {'+', '-', '*', '/'};


        public String generateExpression(int goal, List<Integer> operands) {
            List<List<Integer>> operandPermutations = permuteList(operands);
            List<String> finalExpressions = new ArrayList<>();
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
                            finalExpressions.add(s);
                        }
                    }
                }
            }
            if (finalExpressions.isEmpty()) return "No Solution";
            Collections.shuffle(finalExpressions);
            String chosenExpression = finalExpressions.get(0);
            return chosenExpression.substring(1, chosenExpression.length() - 1);
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

    private class ExpressionEvaluator {

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
