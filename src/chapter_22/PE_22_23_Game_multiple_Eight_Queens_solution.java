package chapter_22;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.LinkedHashSet;

/**
 * (Game: multiple Eight Queens solution) Write a program to display all possible
 * solutions for the Eight Queens puzzle in a scroll pane, as shown in Figure 22.16.
 * For each solution, put a label to denote the solution number. (Hint: Place all
 * solution panes into an HBox and place this one pane into a ScrollPane.)
 */
public class PE_22_23_Game_multiple_Eight_Queens_solution extends Application {

    private static final int SIZE = 8;
    private final int[] queens = {-1, -1, -1, -1, -1, -1, -1, -1};

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        MultipleSolutionEightQueensPane pane = new MultipleSolutionEightQueensPane();
        LinkedHashSet<int[]> solutions = search();
        for (int[] solution : solutions) {
            pane.addSolution(solution);
        }
        Scene scene = new Scene(new ScrollPane(pane), 55 * SIZE, 55 * SIZE + 60);

        primaryStage.setTitle("Exercise22_23");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int findPosition(int k) {
        int start = queens[k] + 1;
        for (int j = start; j < SIZE; j++) {
            if (isValid(k, j)) return j;
        }
        return -1;
    }

    /** Return true if a queen can be placed at (row, column) */
    private boolean isValid(int row, int column) {
        for (int i = 1; i <= row; i++) {
            if (queens[row - i] == column
                    || queens[row - i] == column - i
                    || queens[row - i] == column + i) {
                return false;
            }
        }
        return true;
    }

    /** Search for a solution */
    private LinkedHashSet<int[]> search() {
        LinkedHashSet<int[]> solutions = new LinkedHashSet<>();
        int k = 0;
        while (k >= 0) {
            int j = findPosition(k);
            if (j < 0) {
                queens[k] = -1;
                k--;
            } else {
                queens[k] = j;
                k++;
            }
            if (k == SIZE) {
                solutions.add(queens.clone());
                k--;
            }
        }
        return solutions;
    }

    class MultipleSolutionEightQueensPane extends HBox {
        private int count = 0;

        public MultipleSolutionEightQueensPane() {
            setSpacing(5);
            setPadding(new Insets(5));
        }

        public void addSolution(int[] solution) {
            count++;
            Label label = new Label("Solution " + count);
            StackPane stackPane = new StackPane(label);
            stackPane.setPadding(new Insets(5));
            ChessBoard chessBoard = new ChessBoard(SIZE);
            chessBoard.setQueens(solution);
            VBox vBox = new VBox(stackPane, chessBoard);
            getChildren().add(vBox);
        }
    }

    class ChessBoard extends GridPane {
        private final Label[][] labels;

        public ChessBoard(int size) {
            setAlignment(Pos.CENTER);
            labels = new Label[size][size];
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    add(labels[i][j] = new Label(), j, i);
                    labels[i][j].setStyle("-fx-border-color: black");
                    labels[i][j].setPrefSize(55, 55);
                }
            }
        }

        public void setQueens(int[] queens) {
            Image image = new Image("image/queen.jpg");
            for (int i = 0; i < labels.length; i++)
                labels[i][queens[i]].setGraphic(new ImageView(image));
        }
    }
}
