package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * (Display random 0 or 1) Write a program that displays a 10-by-10 square matrix,
 * as shown in Figure 14.45a(p.579). Each element in the matrix is 0 or 1, randomly generated.
 * Display each number centered in a text field. Use TextField’s setText
 * method to set value 0 or 1 as a string.
 */
public class PE_14_07_Display_random_0_or_1 extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(3));
        pane.setHgap(3);
        pane.setVgap(3);
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 10; column++) {
                TextField textField = new TextField();
                textField.setPrefColumnCount(1);
                textField.setAlignment(Pos.CENTER);
                textField.setText("" + ((int)(Math.random() * 2)));
                pane.add(textField, column, row);
            }
        }
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise14_07");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
