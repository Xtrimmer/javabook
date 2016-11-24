package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * (Display a 3 X 3 grid) Write a program that displays a 3 X 3 grid, as shown in
 Figure 14.47c. Use red color for vertical lines and blue for horizontals. The lines
 are automatically resized when the window is resized.
 */
public class PE_14_16_Display_a_3_X_3_grid extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();

        Line h1 = new Line(0, 100, 300, 100);
        Line h2 = new Line(0, 200, 300, 200);
        Line v1 = new Line(100, 0, 100, 300);
        Line v2 = new Line(200, 0, 200, 300);

        h1.setStroke(Color.BLUE);
        h2.setStroke(Color.BLUE);
        v1.setStroke(Color.RED);
        v2.setStroke(Color.RED);

        h1.startYProperty().bind(pane.heightProperty().multiply((1 / 3.0)));
        h1.endYProperty().bind(pane.heightProperty().multiply((1 / 3.0)));
        h1.endXProperty().bind(pane.widthProperty());

        h2.startYProperty().bind(pane.heightProperty().multiply((2 / 3.0)));
        h2.endYProperty().bind(pane.heightProperty().multiply((2 / 3.0)));
        h2.endXProperty().bind(pane.widthProperty());

        v1.startXProperty().bind(pane.widthProperty().multiply((1 / 3.0)));
        v1.endXProperty().bind(pane.widthProperty().multiply((1 / 3.0)));
        v1.endYProperty().bind(pane.heightProperty());

        v2.startXProperty().bind(pane.widthProperty().multiply((2 / 3.0)));
        v2.endXProperty().bind(pane.widthProperty().multiply((2 / 3.0)));
        v2.endYProperty().bind(pane.heightProperty());

        pane.getChildren().addAll(h1, h2, v1, v2);
        Scene scene = new Scene(pane, 300, 300);

        primaryStage.setTitle("Exercise14_16");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
