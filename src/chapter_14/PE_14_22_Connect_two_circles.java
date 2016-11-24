package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Connect two circles) Write a program that draws two circles with radius
 * 15 pixels, centered at random locations, with a line connecting the two circles.
 * The line should not cross inside the circles, as shown in Figure 14.49c.
 */
public class PE_14_22_Connect_two_circles extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String style = "-fx-fill: transparent; -fx-stroke: black";

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        double radius = 15;
        double diameter = radius * 2;
        double x1 = Math.random() * (pane.getWidth() - diameter) + radius;
        double x2 = Math.random() * (pane.getWidth() - diameter) + radius;
        double y1 = Math.random() * (pane.getHeight() - diameter) + radius;
        double y2 = Math.random() * (pane.getHeight() - diameter) + radius;
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double x3 = x1 + ((radius / distance) * (x2 - x1));
        double y3 = y1 + ((radius / distance) * (y2 - y1));
        double x4 = x2 - ((radius / distance) * (x2 - x1));
        double y4 = y2 - ((radius / distance) * (y2 - y1));


        Circle circle1 = new Circle(x1, y1, radius);
        circle1.setStyle(style);
        Text text1 = new Text(x1, y1, "1");

        Circle circle2 = new Circle(x2, y2, radius);
        circle2.setStyle(style);
        Text text2 = new Text(x2, y2, "2");

        Line line = new Line(x3, y3, x4, y4);

        pane.getChildren().addAll(circle1, text1, circle2, text2, line);
        primaryStage.setTitle("Exercise14_21");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
