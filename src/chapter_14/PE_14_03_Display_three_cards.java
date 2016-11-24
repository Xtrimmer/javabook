package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Display three cards) Write a program that displays three cards randomly
 * selected from a deck of 52, as shown in Figure 14.43c. The card image files
 * are named 1.png, 2.png, …, 52.png and stored in the image/card directory.
 * All three cards are distinct and selected randomly. Hint: You can select random
 * cards by storing the numbers 1–52 to an array list, perform a random shuffle
 * introduced in Section 11.12, and use the first three numbers in the array list as
 * the file names for the image.
 */
public class PE_14_03_Display_three_cards extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        List numberList = generateRandomNumberList();
        ImageView card1 = new ImageView("image/card/" + numberList.get(0) + ".png");
        ImageView card2 = new ImageView("image/card/" + numberList.get(1) + ".png");
        ImageView card3 = new ImageView("image/card/" + numberList.get(2) + ".png");

        HBox hbox = new HBox(card1, card2, card3);
        hbox.setPadding(new Insets(5));
        hbox.setSpacing(5);
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise14_03");
        primaryStage.show();
    }

    private List generateRandomNumberList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }
}
