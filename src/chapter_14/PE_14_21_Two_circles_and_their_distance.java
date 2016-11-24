package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Two circles and their distance) Write a program that draws two filled circles
 * with radius 15 pixels, centered at random locations, with a line connecting the
 * two circles. The distance between the two centers is displayed on the line, as
 * shown in Figure 14.49b.
 */
public class PE_14_21_Two_circles_and_their_distance extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        double radius = 15;
        double diameter = radius * 2;
        double x1 = Math.random() * (pane.getWidth() - diameter) + radius;
        double x2 = Math.random() * (pane.getWidth() - diameter) + radius;
        double y1 = Math.random() * (pane.getHeight() - diameter) + radius;
        double y2 = Math.random() * (pane.getHeight() - diameter) + radius;
        double textX = (x1 + x2) / 2.0;
        double textY = (y1 + y2) / 2.0;

        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        Text text = new Text(textX, textY, distance + "");
        Circle circle1 = new Circle(x1, y1, radius);
        Circle circle2 = new Circle(x2, y2, radius);
        Line line = new Line(x1, y1, x2, y2);

        pane.getChildren().addAll(circle1, circle2, line, text);
        primaryStage.setTitle("Welcome to Java");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
