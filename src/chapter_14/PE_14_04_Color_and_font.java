package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Color and font) Write a program that displays five texts vertically, as shown in
 * Figure 14.44a. Set a random color and opacity for each text and set the font of
 * each text to Times Roman, bold, italic, and 22 pixels.
 */
public class PE_14_04_Color_and_font extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox hbox = new HBox(10);
        hbox.setPadding(new Insets(30, 0, 30, 0));
        for (int i = 0; i < 5; i++) {
            Text text = new Text("Java");
            text.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 22));
            text.setRotate(90);
            text.setFill(Color.color(Math.random(), Math.random(), Math.random(), Math.random()));
            hbox.getChildren().add(text);
        }
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Exercise14_04");
        primaryStage.show();
    }
}
