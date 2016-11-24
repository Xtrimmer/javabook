package chapter_14;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * (Display a cylinder) Write a program that draws a cylinder, as shown in
 * Figure 14.45b. You can use the following method to set the dashed stroke for an arc:
 * arc.getStrokeDashArray().addAll(6.0, 21.0);
 */
public class PE_14_10_Display_a_cylinder extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //Create cylinder top ellipse
        Ellipse ellipse = new Ellipse(250, 175, 150, 75);
        ellipse.setStyle("-fx-fill: white; -fx-stroke: black;");

        //Create cylinder side lines
        Line rightLine = new Line(100, 175, 100, 425);
        Line leftLine = new Line(400, 175, 400, 425);

        //Create cylinder bottom arcs
        Arc arcSolid = new Arc(250, 425, 150, 75, 180, 180);
        arcSolid.setStyle("-fx-fill: white; -fx-stroke: black;");
        Arc arcDashed = new Arc(250, 425, 150, 75, 0, 180);
        arcDashed.setStyle("-fx-fill: white; -fx-stroke: black;");
        arcDashed.getStrokeDashArray().addAll(6.0, 21.0);

        Pane pane = new Pane();
        pane.getChildren().addAll(ellipse, rightLine, leftLine, arcSolid, arcDashed);
        Scene scene = new Scene(pane, 500, 600);

        primaryStage.setTitle("Exercise14_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
