package chapter_16;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Template for JavaFX Application
 */
public class Template extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
