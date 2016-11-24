package chapter_14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Plot the square function) Write a program that draws a diagram for the function
 * f(x) = x^2 (see Figure 14.48b).
 *
 * Hint: Add points to a polyline using the following code:
 *
 *      Polyline polyline = new Polyline();
 *      ObservableList<Double> list = polyline.getPoints();
 *      double scaleFactor = 0.0125;
 *      for (int x = -100; x <= 100; x++) {
 *          list.add(x + 200.0);
 *          list.add(scaleFactor * x * x);
 *      }
 */
public class PE_14_18_Plot_the_square_function extends Application{

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Polyline polyline = new Polyline();
        ObservableList<Double> list = polyline.getPoints();
        double scaleFactor = 0.0125;
        for (int x = -100; x <= 100; x++) {
            list.add(x + 200.0);
            list.add(-scaleFactor * x * x + 200);
        }
        Line hLine1 = new Line(10, 200, 390, 200);
        Line hLine2 = new Line(390, 200, 380, 190);
        Line hLine3 = new Line(390, 200, 380, 210);

        Line vLine1 = new Line(200, 250, 200, 20);
        Line vLine2 = new Line(200, 20, 190, 30);
        Line vLine3 = new Line(200, 20, 210, 30);

        Text textX = new Text(380, 170, "X");
        Text textY = new Text(230, 30, "Y");

        Pane pane = new Pane();
        pane.getChildren().addAll(polyline, hLine1, hLine2, hLine3,
                vLine1, vLine2, vLine3, textX, textY);
        Scene scene = new Scene(pane, 400, 250);

        primaryStage.setTitle("Exercise14_18");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
