package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Enter and display a string) Write a program that receives a string from the
 * keyboard and displays it on a pane. The Enter key signals the end of a string.
 * Whenever a new string is entered, it is displayed on the pane.
 */
public class PE_15_10_Enter_and_display_a_string extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        StringBuilder stringBuilder = new StringBuilder();
        Text text = new Text();
        StackPane pane = new StackPane(text);
        Scene scene = new Scene(pane, 400, 100);
        pane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                text.setText(stringBuilder.toString());
                stringBuilder.delete(0, stringBuilder.length());
            } else {
                stringBuilder.append(event.getText());
            }
        });

        primaryStage.setTitle("Exercise15_10");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.requestFocus();
    }
}
