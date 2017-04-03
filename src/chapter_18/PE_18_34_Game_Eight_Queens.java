package chapter_18;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Game: Eight Queens) The Eight Queens problem is to find a solution to place
 * a queen in each row on a chessboard such that no two queens can attack each
 * other. Write a program to solve the Eight Queens problem using recursion and
 * display the result as shown in Figure 18.17.
 */
public class PE_18_34_Game_Eight_Queens extends Application {

    private static final String QUEEN_IMAGE = "image/queen.jpg";
    private static final Background QUEEN_BACKGROUND = new Background(
            new BackgroundImage(
                    new Image(QUEEN_IMAGE),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1, 1,
                            true, true,
                            false, false
                    )
            )
    );

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        QueensPane pane = new QueensPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise18_34");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class QueensPane extends GridPane {
        private static final double CELL_SIZE = 50;
        private static final int BOARD_SIZE = 8;
        Cell[][] cells;
        int queenCount;

        public QueensPane() {
            initializeBoard();
            solveEightQueens();
        }

        private List<Cell> getValidCells() {
            List<Cell> validCells = new ArrayList<>();
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    if (isValidCell(cell)) {
                        validCells.add(cell);
                    }
                }
            }
            Collections.shuffle(validCells);
            return validCells;
        }

        private void initializeBoard() {
            cells = new Cell[BOARD_SIZE][BOARD_SIZE];
            for (int row = 0; row < cells.length; row++) {
                for (int column = 0; column < cells[row].length; column++) {
                    cells[row][column] = new Cell(CELL_SIZE, CELL_SIZE);
                    add(cells[row][column], column, row);
                }
            }
            setVgap(1);
            setHgap(1);
            setGridLinesVisible(true);
        }

        private boolean isValidCell(Cell cell) {
            return !(cell.isAttackable() || cell.isQueen);
        }

        private void removeQueen(Cell cell) {
            queenCount--;
            cell.setBackground(null);
            cell.setQueen(false);
            resetAttackPaths();
        }

        private void resetAttackPaths() {
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    cell.setAttackable(false);
                }
            }
            for (Cell[] row : cells) {
                for (Cell cell : row) {
                    if (cell.isQueen) setQueenAttackPaths(cell);
                }
            }
        }

        private void setQueen(Cell cell) {
            queenCount++;
            cell.setBackground(QUEEN_BACKGROUND);
            cell.setQueen(true);
            setQueenAttackPaths(cell);
        }

        private void setQueenAttackPaths(Cell cell) {
            int cellRow = GridPane.getRowIndex(cell);
            int cellColumn = GridPane.getColumnIndex(cell);
            for (int row = 0; row < cells.length; row++) {
                for (int column = 0; column < cells[row].length; column++) {
                    if (cellRow == row || cellColumn == column
                            || (cellRow - cellColumn) == (row - column)
                            || (cellRow + cellColumn) == (row + column)) {
                        cells[row][column].setAttackable(true);
                    }
                }
            }
        }

        private void solveEightQueens() {
            if (queenCount == BOARD_SIZE) return;
            List<Cell> validCells = getValidCells();
            for (Cell validCell : validCells) {
                setQueen(validCell);
                solveEightQueens();
                if (queenCount == BOARD_SIZE) return;
                removeQueen(validCell);
            }
        }
    }

    private class Cell extends Pane {
        boolean isQueen;
        boolean isAttackable;

        public Cell(double width, double height) {
            setPrefWidth(width);
            setPrefHeight(height);
        }

        public boolean isAttackable() {
            return isAttackable;
        }

        public void setAttackable(boolean attackable) {
            isAttackable = attackable;
        }

        public void setQueen(boolean queen) {
            isQueen = queen;
        }

        @Override
        public String toString() {
            return "Cell[" + GridPane.getRowIndex(this) + ", " + GridPane.getColumnIndex(this) + "]";
        }
    }
}
