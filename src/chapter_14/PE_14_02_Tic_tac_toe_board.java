package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * (Tic-tac-toe board) Write a program that displays a tic-tac-toe board, as shown
 * in Figure 14.43b(p.578). A cell may be X, O, or empty. What to display at each cell is
 * randomly decided. The X and O are images in the files x.gif and o.gif.
 */
public class PE_14_02_Tic_tac_toe_board extends Application {

    private Image x = new Image("image/x.gif");
    private Image o = new Image("image/o.gif");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = generateBoard();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise14_02");
        primaryStage.show();
    }

    private GridPane generateBoard() {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10, 50, 10, 50));
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                ImageView imageView = new ImageView(getRandomImage());
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                pane.add(imageView, column, row);
            }
        }
        return pane;
    }

    private Image getRandomImage() {
        int random = (int)(Math.random() * 3);
        switch (random) {
            case 0:
                return x;
            case 1:
                return o;
            default:
                return null;
        }
    }
}
