package chapter_20;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * (Game: the 24-point card game) Improve Programming Exercise 20.13
 * to enable the computer to display the expression if one exists, as shown in
 * Figure 20.20. Otherwise, report that the expression does not exist. Place the
 * label for verification result at the bottom of UI. The expression must use all
 * four cards and evaluates to 24.
 */
public class PE_20_15_Game_the_24_point_card_game extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new GamePane(4);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise20_13");
        primaryStage.setScene(scene);
        primaryStage.show();
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

        public List<Integer> getOperands(String expression) {
            List<Integer> operands = new ArrayList<>();
            expression = insertBlanks(expression);
            String[] tokens = expression.split(" ");
            for (String token : tokens) {
                if (isNumeric(token)) {
                    operands.add(new Integer(token));
                }
            }
            return operands;
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

        private boolean isNumeric(String str) {
            return str.matches("\\d+");  //match a number with optional '-' and decimal.
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

    private class GamePane extends BorderPane {

        private static final String IMAGE_DIRECTORY = "image/card/";
        private final int count;
        ImageView[] imageViews;
        List<Integer> cardNumbers;
        private Label labelMessage;
        private TextField textFieldExpression;
        private TextField textFieldSolution;

        public GamePane(int cardCount) {
            this.count = cardCount;
            populateCardNumbers();
            setTop(createShufflePane());
            setBottom(createExpressionPane());
            setCenter(createCardPane());
        }

        private Node createCardPane() {
            imageViews = new ImageView[count];
            HBox hBox = new HBox(5);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(0, 10, 0, 10));
            for (int i = 0; i < count; i++) {
                imageViews[i] = new ImageView();
                hBox.getChildren().add(imageViews[i]);
            }
            shuffleCards();
            return hBox;
        }

        private Node createExpressionPane() {
            Label label = new Label("Enter an expression:");
            textFieldExpression = new TextField();
            Button buttonVerify = new Button("Verify");
            buttonVerify.setOnAction(event -> verifyExpression());
            HBox hBox1 = new HBox(5, label, textFieldExpression, buttonVerify);
            hBox1.setAlignment(Pos.CENTER);
            labelMessage = new Label();
            labelMessage.setMinHeight(45);
            labelMessage.setTextAlignment(TextAlignment.CENTER);
            HBox hBox2 = new HBox(labelMessage);
            hBox2.setAlignment(Pos.CENTER);
            VBox vBox = new VBox(5, hBox1, hBox2);
            vBox.setPadding(new Insets(10));
            return vBox;
        }

        private Node createShufflePane() {
            ArithmeticExpressionGenerator aeg = new ArithmeticExpressionGenerator();
            Button buttonSolution = new Button("Find Solution");
            textFieldSolution = new TextField();
            Button buttonShuffle = new Button("Shuffle");
            buttonSolution.setOnAction(event -> textFieldSolution.setText(aeg.generateExpression(24, getCardFaceValues())));
            buttonShuffle.setOnAction(event -> shuffleCards());
            HBox hBox = new HBox(5, buttonSolution, textFieldSolution, buttonShuffle);
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(10));
            return hBox;
        }

        private List<Integer> getCardFaceValues() {
            List<Integer> cardValues = cardNumbers.subList(0, count);
            List<Integer> modulatedCardValues = new ArrayList<>(4);
            for (Integer cardValue : cardValues) {
                modulatedCardValues.add((cardValue - 1) % 13 + 1);
            }
            return modulatedCardValues;
        }

        private boolean operandsMatchCardValues(String expression, ExpressionEvaluator expressionEvaluator) {
            List<Integer> cardValues = getCardFaceValues();
            List<Integer> operands = expressionEvaluator.getOperands(expression);
            Collections.sort(operands);
            Collections.sort(cardValues);
            return cardValues.equals(operands);
        }

        private void populateCardNumbers() {
            cardNumbers = new ArrayList<>(52);
            for (int i = 1; i <= 52; i++) {
                cardNumbers.add(i);
            }
        }

        private void shuffleCards() {
            textFieldExpression.clear();
            textFieldSolution.clear();
            labelMessage.setText("");
            Collections.shuffle(cardNumbers);
            for (int i = 0; i < count; i++) {
                imageViews[i].setImage(new Image(IMAGE_DIRECTORY + cardNumbers.get(i) + ".png"));
            }
        }

        private void verifyExpression() {
            String expression = textFieldExpression.getText();
            ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
            if (operandsMatchCardValues(expression, expressionEvaluator))
                try {
                    if (expressionEvaluator.evaluate(expression) == 24) {
                        labelMessage.setText("Correct");
                    } else {

                        labelMessage.setText("Incorrect result");
                    }
                } catch (Exception e) {
                    labelMessage.setText("Invalid Expression");
                }
            else {
                labelMessage.setText("The numbers in the expression don't\nmatch the numbers in the set");
            }
        }
    }
}