package chapter_14;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Plot the sine and cosine functions) Write a program that plots the sine function
 * in red and cosine in blue, as shown in Figure 14.48c.
 * Hint: The Unicode for p is \u03c0. To display -2p, use Text(x, y, "-2\u03c0").
 * For a trigonometric function like sin(x), x is in radians. Use the following loop
 * to add the points to a polyline:
 *
 *      Polyline polyline = new Polyline();
 *      ObservableList<Double> list = polyline.getPoints();
 *      double scaleFactor = 50;
 *      for (int x = -170; x <= 170; x++) {
 *          list.add(x + 200.0);
 *          list.add(100 – 50 * Math.sin((x / 100.0) * 2 * Math.PI));
 *      }
 */
public class PE_14_19_Plot_the_sine_and_cosine_functions extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 200);

        Polyline sinWave = new Polyline();
        sinWave.setStroke(Color.RED);
        ObservableList<Double> sinList = sinWave.getPoints();
        for (int x = -170; x <= 170; x++) {
            sinList.add(x + 200.0);
            sinList.add(100 - 50 * Math.sin((x / 100.0) * 2 * Math.PI));
        }

        Polyline cosWave = new Polyline();
        cosWave.setStroke(Color.BLUE);
        ObservableList<Double> cosList = cosWave.getPoints();
        for (int x = -170; x <= 170; x++) {
            cosList.add(x + 200.0);
            cosList.add(100 - 50 * Math.cos((x / 100.0) * 2 * Math.PI));
        }

        Line yLine1 = new Line(200,10,190,20);
        Line yLine2 = new Line(200,10,210,20);
        Line yLine3 = new Line(200,10,200,200);

        Line xLine1 = new Line(10,100,390,100);
        Line xLine2 = new Line(390,100,380,110);
        Line xLine3 = new Line(390,100,380,90);

        Text textX = new Text(380, 70, "X");
        Text textY = new Text(220, 20, "Y");
        Text text3Pin = new Text(50, 115, "-3\u03c0");
        Text text2Pin = new Text(100, 115, "-2\u03c0");
        Text text1Pin = new Text(150, 115, "-\u03c0");
        Text text3Pi = new Text(250, 115, "\u03c0");
        Text text2Pi = new Text(300, 115, "2\u03c0");
        Text text1Pi = new Text(350, 115, "3\u03c0");
        Text zero = new Text(205, 115, "0");

        pane.getChildren().addAll(sinWave, cosWave,
                yLine1, yLine2, yLine3, xLine1, xLine2, xLine3,
                textX, textY, text3Pin, text2Pin, text1Pin,
                text1Pi, text2Pi, text3Pi, zero);

        primaryStage.setTitle("Exercise14_19");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}