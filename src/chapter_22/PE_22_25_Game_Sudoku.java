package chapter_22;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * (Game: Sudoku) Revise Programming Exercise 22.21 to display all solutions
 * for the Sudoku game, as shown in Figure 22.17a. When you click the Solve
 * button, the program stores all solutions in an ArrayList. Each element in the
 * list is a two-dimensional 9-by-9 grid. If the program has multiple solutions, the
 * Next button appears as shown in Figure 22.17b. You can click the Next button
 * to display the next solution and also a label to show the solution count. When
 * the Clear button is clicked, the cells are cleared and the Next button is hidden
 * as shown in Figure 22.17c.
 */
public class PE_22_25_Game_Sudoku extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) {
        Pane pane = new SudokuSolverPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise22_25");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class SudokuSolverPane extends BorderPane {
        private static final int SPACING = 5;
        private final Label label = new Label();
        private final Button buttonSolve = new Button("Solve");
        private final Button buttonClear = new Button("Clear");
        private final Button buttonNext = new Button("Next");
        private final SudokuPane sudokuPane = new SudokuPane();
        private final SudokuSolver sudokuSolver = new SudokuSolver();
        private int solutionIndex = 0;
        private List<int[][]> solutions;

        public SudokuSolverPane() {
            setTop(createMessagePane());
            setCenter(sudokuPane);
            setBottom(createButtonPane());
            setButtonActions();
        }

        private void clear() {
            label.setText(null);
            buttonNext.setVisible(false);
            solutionIndex = 0;
            sudokuPane.setSudokuMatrix(new int[9][9]);
            sudokuPane.disableConstants(new int[9][9]);
        }

        private Node createButtonPane() {
            buttonNext.setVisible(false);
            HBox hBox = new HBox(SPACING, buttonSolve, buttonClear, buttonNext);
            hBox.setPadding(new Insets(SPACING));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private Node createMessagePane() {
            StackPane stackPane = new StackPane(label);
            stackPane.setPadding(new Insets(SPACING));
            return stackPane;
        }

        private void next() {
            solutionIndex = ++solutionIndex % solutions.size();
            sudokuPane.setSudokuMatrix(solutions.get(solutionIndex));
            label.setText(String.format("%d/%d solutions", solutionIndex + 1, solutions.size()));
        }

        private void setButtonActions() {
            buttonSolve.setOnAction(actionEvent -> solve());
            buttonClear.setOnAction(actionEvent -> clear());
            buttonNext.setOnAction(actionEvent -> next());
        }

        private void solve() {
            label.setText(null);
            int[][] grid = sudokuPane.getSudokuMatrix();
            solutions = sudokuSolver.search(grid);
            if (solutions.isEmpty()) label.setText("Invalid Input");
            else {
                label.setText(String.format("%d/%d solutions", solutionIndex + 1, solutions.size()));
                sudokuPane.setSudokuMatrix(solutions.get(solutionIndex));
                sudokuPane.disableConstants(grid);
            }
            if (solutions.size() > 1) buttonNext.setVisible(true);
        }
    }

    class SudokuPane extends GridPane {
        private final MatrixPane[][] matrixPanes = new MatrixPane[3][3];

        public SudokuPane() {
            this.setGridLinesVisible(true);
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    MatrixPane matrixPane = new MatrixPane(3, 3);
                    matrixPanes[row][column] = matrixPane;
                    add(matrixPane, column, row);
                }
            }
        }

        public void disableConstants(int[][] sudokuMatrix) {
            for (int outerRow = 0; outerRow < 3; outerRow++) {
                for (int outerColumn = 0; outerColumn < 3; outerColumn++) {
                    TextField[][] textFields = matrixPanes[outerRow][outerColumn].getTextFields();
                    for (int innerRow = 0; innerRow < 3; innerRow++) {
                        for (int innerColumn = 0; innerColumn < 3; innerColumn++) {
                            int number = sudokuMatrix[outerRow * 3 + innerRow][outerColumn * 3 + innerColumn];
                            if (number == 0) {
                                textFields[innerRow][innerColumn].setDisable(false);
                            } else {
                                textFields[innerRow][innerColumn].setDisable(true);
                            }
                        }
                    }
                }
            }
        }

        public int[][] getSudokuMatrix() {
            int[][] sudokuMatrix = new int[9][9];
            for (int outerRow = 0; outerRow < 3; outerRow++) {
                for (int outerColumn = 0; outerColumn < 3; outerColumn++) {
                    TextField[][] textFields = matrixPanes[outerRow][outerColumn].getTextFields();
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

        public void setSudokuMatrix(int[][] sudokuMatrix) {
            for (int outerRow = 0; outerRow < 3; outerRow++) {
                for (int outerColumn = 0; outerColumn < 3; outerColumn++) {
                    TextField[][] textFields = matrixPanes[outerRow][outerColumn].getTextFields();
                    for (int innerRow = 0; innerRow < 3; innerRow++) {
                        for (int innerColumn = 0; innerColumn < 3; innerColumn++) {
                            int number = sudokuMatrix[outerRow * 3 + innerRow][outerColumn * 3 + innerColumn];
                            if (number == 0) {
                                textFields[innerRow][innerColumn].setText("");
                            } else {
                                textFields[innerRow][innerColumn].setText(number + "");
                            }
                        }
                    }
                }
            }
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                int number = (int) Double.parseDouble(textField.getText());
                if (number > 9) number = 9;
                else if (number < 0) number = 0;
                if (number == 0) textField.clear();
                else textField.setText(number + "");
                return number;
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
    }

    class MatrixPane extends GridPane {
        private static final int SPACING = 3;
        private static final int TEXTFIELD_WIDTH = 1;
        private final TextField[][] textFields;

        public MatrixPane(int rows, int columns) {
            setPadding(new Insets(3));
            setHgap(SPACING);
            setVgap(SPACING);
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

    class SudokuSolver {
        /** Search for a solution */
        public List<int[][]> search(int[][] grid) {
            List<int[][]> solutions = new ArrayList<>();
            if (!isValid(grid)) return solutions;
            List<int[]> freeCellList = getFreeCellList(grid); // Free cells
            if (freeCellList.isEmpty()) {
                solutions.add(gridCopy(grid)); // "No free cells");
                return solutions;
            }
            int k = 0; // Start from the first free cell
            while (true) {
                int i = freeCellList.get(k)[0];
                int j = freeCellList.get(k)[1];
                if (grid[i][j] == 0)
                    grid[i][j] = 1; // Fill the free cell with number 1

                if (isValid(i, j, grid)) {
                    if (k + 1 == freeCellList.size()) { // No more free cells
                        solutions.add(gridCopy(grid)); // A solution is found
                    } else { // Move to the next free cell
                        k++;
                        continue;
                    }
                }
                if (grid[i][j] < 9) {
                    // Fill the free cell with the next possible value
                    grid[i][j] = grid[i][j] + 1;
                } else { // free cell grid[i][j] is 9, backtrack
                    while (grid[i][j] == 9) {
                        if (k == 0) {
                            grid[i][j] = 0;
                            return solutions; // No possible value
                        }
                        grid[i][j] = 0; // Reset to free cell
                        k--; // Backtrack to the preceding free cell
                        i = freeCellList.get(k)[0];
                        j = freeCellList.get(k)[1];
                    }
                    // Fill the free cell with the next possible value,
                    // search continues from this free cell at k
                    grid[i][j] = grid[i][j] + 1;
                }
            }
        }

        /** Obtain a list of free cells from the puzzle */
        private List<int[]> getFreeCellList(int[][] grid) {
            // Store free cell positions into freeCellList
            List<int[]> freeCellList = new ArrayList<>();
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    if (grid[i][j] == 0) {
                        int[] freeCellPosition = {i, j};
                        freeCellList.add(freeCellPosition);
                    }
            return freeCellList;
        }

        private int[][] gridCopy(int[][] grid) {
            int[][] copy = new int[grid.length][];
            for (int i = 0; i < grid.length; i++)
                copy[i] = grid[i].clone();
            return copy;
        }

        /** Check whether the fixed cells are valid in the grid */
        private boolean isValid(int[][] grid) {
            for (int i = 0; i < 9; i++)
                for (int j = 0; j < 9; j++)
                    if (grid[i][j] < 0 || grid[i][j] > 9 ||
                            (grid[i][j] != 0 && !isValid(i, j, grid)))
                        return false;

            return true; // The fixed cells are valid
        }

        /** Check whether grid[i][j] is valid in the grid */
        private boolean isValid(int i, int j, int[][] grid) {
            // Check whether grid[i][j] is valid at the i's row
            for (int column = 0; column < 9; column++)
                if (column != j && grid[i][column] == grid[i][j])
                    return false;

            // Check whether grid[i][j] is valid at the j's column
            for (int row = 0; row < 9; row++)
                if (row != i && grid[row][j] == grid[i][j])
                    return false;

            // Check whether grid[i][j] is valid in the 3 by 3 box
            for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
                for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                    if (row != i && col != j && grid[row][col] == grid[i][j])
                        return false;

            return true; // The current value at grid[i][j] is valid
        }
    }
}
