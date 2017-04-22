package chapter_20;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.LinkedList;

/**
 * (Store numbers in a linked list) Write a program that lets the user enter numbers
 * from a graphical user interface and displays them in a text area, as shown in
 * Figure 20.17a. Use a linked list to store the numbers. Do not store duplicate numbers.
 * Add the buttons Sort, Shuffle, and Reverse to sort, shuffle, and reverse the list.
 */
public class PE_20_02_Store_numbers_in_a_linked_list extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new LinkedNumberPane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise20_02");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class LinkedNumberPane extends BorderPane {
        private final LinkedList<Integer> list = new LinkedList<>();
        private final TextField textField = new TextField();
        private final TextArea textArea = new TextArea();

        public LinkedNumberPane() {
            setTop(createInputBar());
            setCenter(createTextPane());
            setBottom(createButtonBar());
        }

        private void addNumberToList() {
            Integer integer = null;
            try {
                integer = (int) Double.parseDouble(textField.getText());
                textField.setText(integer + "");
            } catch (NumberFormatException e) {
                textField.setText("");
            }
            if (integer != null && !list.contains(integer)) {
                list.add(integer);
                displayNumbers();
            }

        }

        private Node createButtonBar() {
            Button sortButton = new Button("Sort");
            Button shuffleButton = new Button("Shuffle");
            Button reverseButton = new Button("Reverse");

            HBox hBox = new HBox(5, sortButton, shuffleButton, reverseButton);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10));

            sortButton.setOnAction(event -> sort());
            shuffleButton.setOnAction(event -> shuffle());
            reverseButton.setOnAction(event -> reverse());

            return hBox;
        }

        private Node createInputBar() {
            Label label = new Label("Enter a Number: ");

            HBox hBox = new HBox(5, label, textField);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10));

            textField.setOnAction(event -> addNumberToList());

            return hBox;
        }

        private Node createTextPane() {
            return new ScrollPane(textArea);
        }

        private void displayNumbers() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer integer : list) {
                stringBuilder.append(integer);
                stringBuilder.append(" ");
            }
            textArea.setText(stringBuilder.toString());
        }

        private void reverse() {
            list.sort(Collections.reverseOrder());
            displayNumbers();
        }

        private void shuffle() {
            Collections.shuffle(list);
            displayNumbers();
        }

        private void sort() {
            Collections.sort(list);
            displayNumbers();
        }
    }
}
