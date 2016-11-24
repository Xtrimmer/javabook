package chapter_15;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * (Pick four cards) Write a program that lets the user click the Refresh button to
 * display four cards from a deck of 52 cards, as shown in Figure 15.24a. (See the
 * hint in Programming Exercise 14.3 on how to obtain four random cards.)
 */
public class PE_15_01_Pick_four_cards extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane pane = new BorderPane();
        FourCardPane cardPane = new FourCardPane();
        Button button = new Button("Refresh");
        button.setOnAction(event -> cardPane.refreshCards());
        pane.setTop(cardPane);
        pane.setBottom(button);
        BorderPane.setAlignment(button, Pos.CENTER);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise15_01");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class FourCardPane extends HBox{
        private final List cardList = generateRandomNumberList();
        private final ImageView card1 = new ImageView();
        private final ImageView card2 = new ImageView();
        private final ImageView card3 = new ImageView();
        private final ImageView card4 = new ImageView();


        public FourCardPane() {
            setSpacing(10);
            setPadding(new Insets(10));
            getChildren().addAll(card1, card2, card3, card4);
            refreshCards();
        }

        public void refreshCards() {
            Collections.shuffle(cardList);
            card1.setImage(new Image("image/card/" + cardList.get(0) + ".png"));
            card2.setImage(new Image("image/card/" + cardList.get(1) + ".png"));
            card3.setImage(new Image("image/card/" + cardList.get(2) + ".png"));
            card4.setImage(new Image("image/card/" + cardList.get(3) + ".png"));
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
}
