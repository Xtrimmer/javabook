package chapter_22;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * (Game: multiple Sudoku solutions) The complete solution for the Sudoku problem
 * is given in Supplement VI.A. A Sudoku problem may have multiple solutions.
 * Modify Sudoku.java in Supplement VI.A to display the total number of
 * the solutions. Display two solutions if multiple solutions exist.
 */
public class PE_22_20_Game_multiple_Sudoku_solutions extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage){
        Pane pane = new SudokuPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise22_20");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class SudokuPane extends GridPane{
        MatrixPane[][] matrixPanes = new MatrixPane[3][3];

        public SudokuPane() {
            this.setGridLinesVisible(true);
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    MatrixPane matrixPane = new MatrixPane(3,3);
                    matrixPanes[row][column] = matrixPane;
                    add(matrixPane,column,row);
                }
            }
        }

        public int[][] getSudokuMatrix(){
            int[][] sudokuMatrix = new int[9][9];
            for (int outerRow = 0; outerRow < 3; outerRow++) {
                for (int outerColumn = 0; outerColumn < 3; outerColumn++) {
                    TextField[][] textFields =  matrixPanes[outerRow][outerColumn].getTextFields();
                    for (int innerRow = 0; innerRow < 3; innerRow++) {
                        for (int innerColumn = 0; innerColumn < 3; innerColumn++) {
                            int number = parseInt(textFields[innerRow][innerColumn], 0);
                            sudokuMatrix[outerRow * 3 + innerRow][outerColumn * 3 + innerColumn] = number;
                        }
                    }
                }
            }
            return sudokuMatrix;
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
}
