package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * (Draw an arrow line) Write a static method that draws an arrow line from a starting
 * point to an ending point in a pane using the following method header:
 *
 *      public static void drawArrowLine(double startX, double startY,
 *          double endX, double endY, Pane pane)
 *
 * Write a test program that randomly draws an arrow line, as shown in Figure 14.49a.
 */
public class PE_14_20_Draw_an_arrow_line extends Application {

    public static void main(String[] args) {
        Application.launch(args);

    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        drawArrowLine(
                Math.random() * 400,
                Math.random() * 400,
                Math.random() * 400,
                Math.random() * 400,
                pane
        );

        primaryStage.setTitle("Exercise14_20");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void drawArrowLine(double startX, double startY, double endX, double endY, Pane pane) {

        double rise = endY - startY;
        double run = endX - startX;
        double angle = Math.atan2(rise, run);
        double x1 = endX + 10 * Math.sin(angle - Math.toRadians(45));
        double y1 = endY - 10 * Math.cos(angle - Math.toRadians(45));
        double x2 = endX + 10 * Math.sin(angle - Math.toRadians(135));
        double y2 = endY - 10 * Math.cos(angle - Math.toRadians(135));

        Line line1 = new Line(startX, startY, endX, endY);
        Line line2 = new Line(endX, endY, x1, y1);
        Line line3 = new Line(endX, endY, x2, y2);

        pane.getChildren().addAll(line1, line2, line3);
    }
}
