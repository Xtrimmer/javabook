package chapter_18;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Game: Knight’s Tour) The Knight’s Tour is an ancient puzzle. The objective is
 * to move a knight, starting from any square on a chessboard, to every other square
 * once, as shown in Figure 18.15a. Note that the knight makes only L-shaped
 * moves (two spaces in one direction and one space in a perpendicular direction).
 * As shown in Figure 18.15b, the knight can move to eight squares. Write
 * a program that displays the moves for the knight, as shown in Figure 18.15c.
 * When you click a cell, the knight is placed at the cell. This cell will be starting
 * point for the knight. Click the Solve button to display the path for a solution.
 *
 * (a) A knight traverses all squares once. (b) A knight makes an L-shaped
 * move. (c) A program displays a Knight’s Tour path.
 *
 * (Hint: A brute-force approach for this problem is to move the knight from one
 * square to another available square arbitrarily. Using such an approach, your
 * program will take a long time to finish. A better approach is to employ some
 * heuristics. A knight has two, three, four, six, or eight possible moves, depending
 * on its location. Intuitively, you should attempt to move the knight to the least
 * accessible squares first and leave those more accessible squares open, so there
 * will be a better chance of success at the end of the search.)
 */
public class PE_18_32_Game_Knights_Tour extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Board pane = new Board(8, 8);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise18_32");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class Board extends BorderPane {
    private static final double SIZE = 35;
    private Button button;
    private Cell[][] cells;
    private Cell knightCell;
    private List<Cell> path = new ArrayList<>();

    public Board(int width, int height) {
        initializeCells(width, height);
        setCenter(generateBoard());
        setBottom(createButtonPane());
    }

    private int calculateCellPriority(int row, int column) {
        int value = 0;
        if (row > 0 && row < 7) value++;
        if (column > 0 && column < 7) value++;
        if (row > 1 && row < 6) value++;
        if (column > 1 && column < 6) value++;
        return value;
    }

    private Node createButtonPane() {
        button = new Button("Solve");
        button.setOnAction(event -> solveKnightsTour(knightCell));
        HBox hBox = new HBox(button);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    private Node generateBoard() {
        GridPane gridPane = new GridPane();
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                gridPane.add(cells[row][column], column, row);
            }
        }
        gridPane.setVgap(1);
        gridPane.setHgap(1);
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(10, 10, 0, 10));
        return gridPane;
    }

    private List<Cell> getValidMoves(Cell cell) {
        int cellRow = GridPane.getRowIndex(cell);
        int cellColumn = GridPane.getColumnIndex(cell);
        List<Cell> validMoves = new ArrayList<>();
        for (int row = -2; row <= 2; row++) {
            for (int column = -2; column <= 2; column++) {
                if (isValidMove(cellRow + row, cellColumn + column)) {
                    validMoves.add(cells[cellRow + row][cellColumn + column]);
                }
            }
        }
        return validMoves;
    }

    private void initializeCells(int width, int height) {
        cells = new Cell[height][width];
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                cells[row][column] = new Cell(SIZE, SIZE);
                cells[row][column].setOnMouseClicked(this::setStart);
                cells[row][column].setPriority(calculateCellPriority(row, column));
            }
        }
        knightCell = cells[0][0];
    }

    private boolean isOutOfBounds(int row, int column) {
        return row < 0 || row > 7 || column < 0 || column > 7;
    }

    private boolean isSolved() {
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                if (!cells[row][column].isVisited()) return false;
            }
        }
        return true;
    }

    private boolean isValidMove(int row, int column) {
        return Math.abs(row) + Math.abs(column) == 3
                && !isOutOfBounds(row, column)
                && !cells[row][column].isVisited();
    }

    private void setStart(MouseEvent mouseEvent) {
        knightCell.setBackground(null);
        knightCell = (Cell) mouseEvent.getSource();
        knightCell.setBackground(Cell.CHESS_KNIGHT);
    }

    private void solveKnightsTour(Cell location) {
        location.setVisited(true);
        path.add(location);
        if (isSolved()) return;
        List<Cell> validMoves = getValidMoves(location);
        if (validMoves.isEmpty()) {
            location.setVisited(false);
            path.remove(location);
            return;
        }
        Collections.sort(validMoves);
        for (Cell validMove : validMoves) {
            solveKnightsTour(validMove);
        }
    }
}

class Cell extends Pane implements Comparable<Cell> {
    public static final Background CHESS_KNIGHT = new Background(
            new BackgroundImage(
                    new Image("image/knight.jpg"),
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1, 1,
                            true, true,
                            false, false
                    )
            )
    );
    private boolean isVisited;
    private int priority;

    public Cell(double width, double height) {
        this.setPrefWidth(width);
        this.setPrefHeight(height);
    }

    @Override
    public int compareTo(Cell that) {
        Integer thisCellPriority = this.getPriority();
        Integer thatCellPriority = that.getPriority();
        return thisCellPriority.compareTo(thatCellPriority);
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}