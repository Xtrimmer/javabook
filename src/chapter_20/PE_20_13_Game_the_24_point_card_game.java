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
 * (Game: the 24-point card game) The 24-point game is to pick any 4 cards
 * from 52 cards, as shown in Figure 20.19. Note that the Jokers are excluded.
 * Each card represents a number. An Ace, King, Queen, and Jack represent 1,
 * 13, 12, and 11, respectively. You can click the Shuffle button to get four new
 * cards. Enter an expression that uses the four numbers from the four selected
 * cards. Each number must be used once and only once. You can use the operators
 * (addition, subtraction, multiplication, and division) and parentheses in the
 * expression. The expression must evaluate to 24. After entering the expression,
 * click the Verify button to check whether the numbers in the expression are currently
 * selected and whether the result of the expression is correct. Display the
 * verification in a label before the Shuffle button. Assume that images are stored
 * in files named 1.png, 2.png, . . . , 52.png, in the order of spades, hearts, diamonds,
 * and clubs. So, the first 13 images are for spades 1, 2, 3, . . . , and 13.
 */
public class PE_20_13_Game_the_24_point_card_game extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new GamePane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise20_13");
        primaryStage.setScene(scene);
        primaryStage.show();
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
