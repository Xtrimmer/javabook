package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * (Display a rectanguloid) Write a program that displays a rectanguloid, as shown
 * in Figure 14.47a. The cube should grow and shrink as the window grows or
 * shrinks.
 */
public class PE_14_14_Display_a_rectanguloid extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        CubePane pane = new CubePane();
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise14_14");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

class CubePane extends Pane {
    private void paint() {
        double width = getWidth();
        double height = getHeight();
        String style = "-fx-stroke: black; -fx-fill: transparent";

        Rectangle front = new Rectangle(
                width * (1 / 18.0),
                height * (1 / 3.0),
                width * (11 / 18.0),
                height * (11 / 18.0)
        );
        front.setStyle(style);
        Rectangle back = new Rectangle(
                width * (1 / 3.0),
                height * (1 / 18.0),
                width * (11 / 18.0),
                height * (11 / 18.0)
        );
        back.setStyle(style);

        Line topLeft = new Line(
                width * (1 / 18.0),
                height * (1 / 3.0),
                width * (1 / 3.0),
                height * (1 / 18.0)
        );
        Line topRight = new Line(
                width * (2 / 3.0),
                height * (1 / 3.0),
                width * (17 / 18.0),
                height * (1 / 18.0)
        );
        Line bottomLeft = new Line(
                width * (1 / 18.0),
                height * (17 / 18.0),
                width * (1 / 3.0),
                height * (2 / 3.0)
        );
        Line bottomRight = new Line(
                width * (2 / 3.0),
                height * (17 / 18.0),
                width * (17 / 18.0),
                height * (2 / 3.0)
        );

        getChildren().clear();
        getChildren().addAll(front, back, topLeft, topRight, bottomLeft, bottomRight);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        paint();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        paint();
    }
}