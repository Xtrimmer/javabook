package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Alternate two messages) Write a program to display the text Java is fun
 * and Java is powerful alternately with a mouse click.
 */
public class PE_15_06_Alternate_two_messages extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String message1 = "Java is fun";
        String message2 = "Java is powerful";
        Text text = new Text(message1);
        text.setOnMouseClicked(event -> {
            if (text.getText().equals(message1)) {
                text.setText(message2);
            } else {
                text.setText(message1);
            }
        });

        StackPane pane = new StackPane(text);
        Scene scene = new Scene(pane, 400, 200);

        primaryStage.setTitle("Exercise15_06");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
