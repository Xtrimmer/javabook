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
        //Application.launch(args);
        ArithmeticExpressionGenerator aeg = new ArithmeticExpressionGenerator();
        //List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        //System.out.println(aeg.generateExpression(0, list, new ArrayList<>()));
        aeg.printOperatorCombinations();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new GamePane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise20_13");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static class ArithmeticExpressionGenerator {
        private ExpressionEvaluator expressionEvaluator;
        private char[] operators = {'+', '-', '*', '/'};


        public String generateExpression(int goal, List<Integer> operands, List<Character> operators) {
            List<List<Integer>> operatorPermutations = permuteOperands(operands);
            return operatorPermutations.toString();
        }

        public void printOperatorCombinations() {
            int operatorCount = operators.length;
            int combos = (int) Math.pow(operatorCount, operatorCount);
            for (int i = 0; i < combos; i++) {
                int index = i;
                for (int j = 0; j < operatorCount; j++) {
                    System.out.print(operators[index % operatorCount] + " ");
                    index /= operatorCount;
                }
                System.out.println();
            }
        }

        private <E> List<List<E>> permuteOperands(List<E> operands) {
            List<List<E>> permutations = new ArrayList<>();
            permuteOperands(permutations, new ArrayList<>(operands.size()), operands);
            return permutations;
        }

        private <E> void permuteOperands(List<List<E>> permutations, List<E> permutation, List<E> operands) {
            if (operands.isEmpty()) permutations.add(permutation);
            for (E operand : operands) {
                List<E> permutation2 = new ArrayList<>(permutation);
                permutation2.add(operand);
                List<E> operands2 = new ArrayList<>(operands);
                operands2.remove(operand);
                permuteOperands(permutations, permutation2, operands2);
            }
        }
    }

    private class GamePane extends BorderPane {

        private static final String IMAGE_DIRECTORY = "image/card/";
        ImageView[] imageViews;
        List<Integer> cardNumbers;
        private Label labelMessage;
        private TextField textFieldExpression;

        public GamePane() {
            populateCardNumbers();
            setTop(createShufflePane());
            setCenter(createCardPane(4));
            setBottom(createExpressionPane());
        }

        private Node createCardPane(int count) {
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
            HBox hBox = new HBox(5, label, textFieldExpression, buttonVerify);
            hBox.setPadding(new Insets(10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private Node createShufflePane() {
            labelMessage = new Label();
            Button buttonShuffle = new Button("Shuffle");
            buttonShuffle.setOnAction(event -> shuffleCards());
            HBox hBox = new HBox(5, labelMessage, buttonShuffle);
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(10));
            return hBox;
        }

        private boolean operandsMatchCardValues(String expression, ExpressionEvaluator expressionEvaluator) {
            List<Integer> cardValues = cardNumbers.subList(0, 4);
            List<Integer> moddedCardValues = new ArrayList<>();
            for (Integer cardValue : cardValues) {
                moddedCardValues.add((cardValue - 1) % 13 + 1);
            }
            List<Integer> operands = expressionEvaluator.getOperands(expression);
            Collections.sort(operands);
            Collections.sort(moddedCardValues);
            return moddedCardValues.equals(operands);
        }

        private void populateCardNumbers() {
            cardNumbers = new ArrayList<>(52);
            for (int i = 1; i <= 52; i++) {
                cardNumbers.add(i);
            }
        }

        private void shuffleCards() {
            Collections.shuffle(cardNumbers);
            for (int i = 0; i < 4; i++) {
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

    private class ExpressionEvaluator {

        public int evaluate(String expression) {
            LinkedList<Integer> operandStack = new LinkedList<>();
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
                    operandStack.push(new Integer(token));
                }
            }
            while (!operatorStack.isEmpty()) {
                processAnOperator(operandStack, operatorStack);
            }
            int result = operandStack.pop();
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
                LinkedList<Integer> operandStack, LinkedList<Character> operatorStack) {
            char operator = operatorStack.pop();
            int operand1 = operandStack.pop();
            int operand2 = operandStack.pop();
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
