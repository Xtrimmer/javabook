package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Display 54 cards) Expand Exercise 14.3 to display all 54 cards (including two
 * jokers), nine per row. The image files are jokers and are named 53.jpg and 54.jpg.
 */
public class PE_14_08_Display_54_cards extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List list = generateRandomNumberList();
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5);
        pane.setVgap(5);
        pane.setStyle("-fx-background-color: #888888;");
        for (int row = 0; row < 6; row++) {
            for (int column = 0; column < 9; column++) {
                int cardNumber = row * 9 + column;
                pane.add(new ImageView("image/card/" + list.get(cardNumber) + ".png"), column, row);
            }
        }
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Welcome to Java");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private List generateRandomNumberList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 54; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }
}
