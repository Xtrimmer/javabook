package chapter_22;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * (Largest block) The problem for finding a largest block is described in Programming
 * Exercise 8.35. Design a dynamic programming algorithm for solving
 * this problem in O(n^2) time. Write a test program that displays a 10-by-10
 * square matrix, as shown in Figure 22.14a. Each element in the matrix is 0 or
 * 1, randomly generated with a click of the Refresh button. Display each number
 * centered in a text field. Use a text field for each entry. Allow the user to change
 * the entry value. Click the Find Largest Block button to find a largest square
 * submatrix that consists of 1s. Highlight the numbers in the block, as shown in
 * Figure 22.14b. See www.cs.armstrong.edu/liang/animation/FindLargestBlock.html
 * for an interactive test.
 */
public class PE_22_19_Largest_block extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new LargestBlockPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise22_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class LargestBlockPane extends BorderPane {
        private final Button buttonRefresh = new Button("Refresh");
        private final Button buttonFindLargestBlock = new Button("Find Largest Block");
        private final MatrixPane matrixPane = new MatrixPane(10, 10);

        public LargestBlockPane() {
            setCenter(matrixPane);
            setBottom(createButtonPane());
            refreshMatrix();
            setButtonActions();
        }

        private void colorBlock(TextField[][] fields, BlockLocation blockLocation) {
            for (int row = blockLocation.row; row < blockLocation.row + blockLocation.size; row++) {
                for (int column = blockLocation.column; column < blockLocation.column + blockLocation.size; column++) {
                    fields[row][column].setStyle("-fx-background-color: red");
                }
            }
        }

        private Node createButtonPane() {
            HBox hBox = new HBox(buttonRefresh, buttonFindLargestBlock);
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private void displayLargestBlock() {
            TextField[][] fields = matrixPane.getTextFields();
            int[][] matrix = new int[fields.length][fields[0].length];
            for (int row = 0; row < fields.length; row++) {
                for (int column = 0; column < fields[0].length; column++) {
                    TextField field = fields[row][column];
                    field.setEditable(false);
                    matrix[row][column] = parseInt(field, 1);
                }
            }
            BlockLocation blockLocation = findLargestBlock(matrix);
            colorBlock(fields, blockLocation);
        }

        private BlockLocation findLargestBlock(int[][] matrix) {
            BlockLocation blockLocation = new BlockLocation();
            int[][] auxiliaryMatrix = new int[matrix.length][matrix[0].length];
            for (int i = 0; i < matrix.length; i++) {
                auxiliaryMatrix[i][0] = matrix[i][0];
                auxiliaryMatrix[0][i] = matrix[0][i];
            }
            for (int row = 1; row < matrix.length; row++) {
                for (int column = 1; column < matrix[0].length; column++) {
                    if (matrix[row][column] == 1) {
                        auxiliaryMatrix[row][column] = min(
                                auxiliaryMatrix[row - 1][column],
                                auxiliaryMatrix[row][column - 1],
                                auxiliaryMatrix[row - 1][column - 1]
                        );
                        auxiliaryMatrix[row][column]++;
                        if (auxiliaryMatrix[row][column] > blockLocation.size) {
                            blockLocation.size = auxiliaryMatrix[row][column];
                            blockLocation.row = row - blockLocation.size + 1;
                            blockLocation.column = column - blockLocation.size + 1;
                        }
                    } else {
                        auxiliaryMatrix[row][column] = 0;
                    }
                }
            }
            return blockLocation;
        }

        private int min(int... values) {
            int min = Integer.MAX_VALUE;
            for (int value : values) {
                min = Math.min(min, value);
            }
            return min;
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                int number = (int) Double.parseDouble(textField.getText());
                if (number >= 1) number = 1;
                else number = 0;
                textField.setText(number + "");
                return number;
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }

        private void refreshMatrix() {
            TextField[][] fields = matrixPane.getTextFields();
            for (TextField[] row : fields) {
                for (TextField textField : row) {
                    textField.setEditable(true);
                    textField.setText("" + (int) (Math.random() * 2));
                    textField.setStyle(null);
                }
            }
        }

        private void setButtonActions() {
            buttonFindLargestBlock.setOnAction(event -> displayLargestBlock());
            buttonRefresh.setOnAction(event -> refreshMatrix());
        }
    }

    class MatrixPane extends GridPane {
        private static final int HORIZONTAL_GAP = 3;
        private static final int VERTICAL_GAP = 3;
        private static final int TEXTFIELD_WIDTH = 1;
        private final TextField[][] textFields;

        public MatrixPane(int rows, int columns) {
            setPadding(new Insets(3));
            setHgap(HORIZONTAL_GAP);
            setVgap(VERTICAL_GAP);
            textFields = new TextField[rows][columns];
            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    TextField textField = new TextField();
                    textFields[row][column] = textField;
                    textField.setPrefColumnCount(TEXTFIELD_WIDTH);
                    textField.setAlignment(Pos.CENTER);
                    add(textField, column, row);
                }
            }
        }

        public TextField[][] getTextFields() {
            return textFields;
        }
    }

    class BlockLocation {
        int row = 0;
        int column = 0;
        int size = 0;
    }
}
