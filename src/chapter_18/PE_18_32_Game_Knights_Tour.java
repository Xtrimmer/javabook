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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

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

    public Board(int width, int height) {
        initializeCells(width, height);
        setCenter(generateBoard());
        setBottom(createButtonPane());
    }

    private Node createButtonPane() {
        button = new Button("Solve");
        button.setOnAction(event -> solveKnightsTour());
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

    private void initializeCells(int width, int height) {
        cells = new Cell[height][width];
        for (int row = 0; row < cells.length; row++) {
            for (int column = 0; column < cells[row].length; column++) {
                cells[row][column] = new Cell(SIZE, SIZE);
                cells[row][column].setOnMouseClicked(this::setStart);
            }
        }
        knightCell = cells[0][0];
    }

    private void setStart(MouseEvent mouseEvent) {
        double startX = knightCell.getLayoutX() + knightCell.getWidth() / 2;
        double startY = knightCell.getLayoutY() + knightCell.getHeight() / 2;
        knightCell.setBackground(null);
        knightCell = (Cell) mouseEvent.getSource();
        knightCell.setBackground(Cell.CHESS_KNIGHT);
        double endX = knightCell.getLayoutX() + knightCell.getWidth() / 2;
        double endY = knightCell.getLayoutY() + knightCell.getHeight() / 2;
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.ORANGE);
        line.setStrokeWidth(2);
        getChildren().add(line);
    }

    private void solveKnightsTour() {
        System.out.println("Solve button clicked");
    }
}

class Cell extends Pane {
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

    public Cell(double width, double height) {
        this.setPrefWidth(width);
        this.setPrefHeight(height);
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}