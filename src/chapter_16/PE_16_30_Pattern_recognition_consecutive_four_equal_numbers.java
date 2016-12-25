package chapter_16;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * (Pattern recognition: consecutive four equal numbers) Write a GUI program for
 * Programming Exercise 8.19, as shown in Figure 16.49a–b. Let the user enter the
 * numbers in the text fields in a grid of 6 rows and 7 columns. The user can click
 * the Solve button to highlight a sequence of four equal numbers, if it exists. Initially,
 * the values in the text fields are filled with numbers from 0 to 9 randomly.
 */
public class PE_16_30_Pattern_recognition_consecutive_four_equal_numbers extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        NumberPane pane = new NumberPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_30");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class NumberPane extends BorderPane {
        private final int PATTERN_LENGTH = 4;
        private final int MIN_NUMBER_VALUE = 0;
        private final int MAX_NUMBER_VALUE = 9;
        private final int ROW_COUNT = 6;
        private final int COLUMN_COUNT = 7;
        private final Label label = new Label();
        private final GridPane gridPane = new GridPane();
        private final int[][] numberGrid = new int[ROW_COUNT][COLUMN_COUNT];
        private final TextField[][] textFields = new TextField[ROW_COUNT][COLUMN_COUNT];
        private final TextField[] pattern = new TextField[PATTERN_LENGTH];

        public NumberPane() {
            initializeNumberGrid();
            setupGridPane();
            setTop(createLabelPane());
            setCenter(gridPane);
            setBottom(createButtonPane());
        }

        private StackPane createButtonPane() {
            Button button = new Button("Solve");
            StackPane stackPane = new StackPane(button);
            stackPane.setPadding(new Insets(5));
            button.setOnAction(event -> solve());
            return stackPane;
        }

        private StackPane createLabelPane() {
            StackPane stackPane = new StackPane(label);
            stackPane.setPadding(new Insets(5));
            return stackPane;
        }

        private boolean hasHorizontalPattern() {
            int offset = (PATTERN_LENGTH - 1);
            boolean hasPattern;
            for (int row = 0; row < numberGrid.length; row++) {
                for (int col = 0; col < numberGrid[row].length - offset; col++) {
                    hasPattern = true;
                    pattern[0] = textFields[row][col];
                    for (int k = 1; k < PATTERN_LENGTH; k++) {
                        hasPattern &= numberGrid[row][col] == numberGrid[row][col + k];
                        pattern[k] = textFields[row][col + k];
                    }
                    if (hasPattern) return true;
                }
            }
            return false;
        }

        private boolean hasNegativeDiagonalPattern() {
            int offset = (PATTERN_LENGTH - 1);
            boolean hasPattern;
            for (int row = 0; row < numberGrid.length - offset; row++) {
                for (int col = 0; col < numberGrid[row].length - offset; col++) {
                    hasPattern = true;
                    pattern[0] = textFields[row][col];
                    for (int k = 1; k < PATTERN_LENGTH; k++) {
                        hasPattern &= numberGrid[row][col] == numberGrid[row + k][col + k];
                        pattern[k] = textFields[row + k][col + k];
                    }
                    if (hasPattern) return true;
                }
            }
            return false;
        }

        private boolean hasPositiveDiagonalPattern() {
            int offset = (PATTERN_LENGTH - 1);
            boolean hasPattern;
            for (int row = PATTERN_LENGTH - 1; row < numberGrid.length; row++) {
                for (int col = 0; col < numberGrid[row].length - offset; col++) {
                    hasPattern = true;
                    pattern[0] = textFields[row][col];
                    for (int k = 1; k < PATTERN_LENGTH; k++) {
                        hasPattern &= numberGrid[row][col] == numberGrid[row - k][col + k];
                        pattern[k] = textFields[row - k][col + k];
                    }
                    if (hasPattern) return true;
                }
            }
            return false;
        }

        private boolean hasVerticalPattern() {
            int offset = (PATTERN_LENGTH - 1);
            boolean hasPattern;
            for (int row = 0; row < numberGrid.length - offset; row++) {
                for (int col = 0; col < numberGrid[row].length; col++) {
                    hasPattern = true;
                    pattern[0] = textFields[row][col];
                    for (int k = 1; k < PATTERN_LENGTH; k++) {
                        hasPattern &= numberGrid[row][col] == numberGrid[row + k][col];
                        pattern[k] = textFields[row + k][col];
                    }
                    if (hasPattern) return true;
                }
            }
            return false;
        }

        private void highlightPattern() {
            for (TextField textField : pattern) {
                textField.setStyle("-fx-text-box-border: #FF0000; -fx-control-inner-background: #FFF0F0;");
            }
        }

        private void initializeNumberGrid() {
            for (int row = 0; row < ROW_COUNT; row++) {
                for (int col = 0; col < COLUMN_COUNT; col++) {
                    numberGrid[row][col] = (int) (Math.random() * (MAX_NUMBER_VALUE + 1)
                            + MIN_NUMBER_VALUE);
                }
            }
        }

        private boolean isPatternFound() {
            return hasHorizontalPattern()
                    || hasVerticalPattern()
                    || hasNegativeDiagonalPattern()
                    || hasPositiveDiagonalPattern();
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                int integer = (int) Double.parseDouble(textField.getText());
                integer = integer > MAX_NUMBER_VALUE ? MAX_NUMBER_VALUE : integer;
                integer = integer < MIN_NUMBER_VALUE ? MIN_NUMBER_VALUE : integer;
                textField.setText(integer + "");
                return integer;
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }

        private void resetTextFieldStyles() {
            for (TextField[] textField : textFields) {
                for (TextField field : textField) {
                    field.getStyleClass().clear();
                    field.setStyle(null);
                    field.getStyleClass().addAll("text-field", "text-input");
                }
            }
        }

        private void setNumber(ActionEvent event) {
            TextField textField = (TextField) event.getSource();
            int row = GridPane.getRowIndex(textField);
            int col = GridPane.getColumnIndex(textField);
            int number = parseInt(textField, MIN_NUMBER_VALUE);
            numberGrid[row][col] = number;
        }

        private void setupGridPane() {
            gridPane.setHgap(2);
            gridPane.setVgap(2);
            gridPane.setPadding(new Insets(0, 15, 0, 15));
            gridPane.setAlignment(Pos.CENTER);
            for (int row = 0; row < ROW_COUNT; row++) {
                for (int col = 0; col < COLUMN_COUNT; col++) {
                    TextField textField = new TextField();
                    textField.setPrefColumnCount(1);
                    textField.setOnAction(event -> setNumber(event));
                    textField.setText(numberGrid[row][col] + "");
                    gridPane.add(textField, col, row);
                    textFields[row][col] = textField;
                }
            }
        }

        private void solve() {
            resetTextFieldStyles();
            String prefix = "No";
            String text = " consecutive " + PATTERN_LENGTH + " found";
            if (isPatternFound()) {
                prefix = "A";
                highlightPattern();
            }
            label.setText(prefix + text);
        }
    }
}
