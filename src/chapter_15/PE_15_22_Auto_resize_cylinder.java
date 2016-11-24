package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * (Auto resize cylinder) Rewrite Programming Exercise 14.10 so that the cylinder’s
 * width and height are automatically resized when the window is resized.
 */
public class PE_15_22_Auto_resize_cylinder extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        String style = "-fx-fill: white; -fx-stroke: black;";

        //Create cylinder top ellipse
        Ellipse ellipse = new Ellipse(250, 175, 150, 75);
        ellipse.setStyle(style);
        ellipse.centerXProperty().bind(pane.widthProperty().multiply(1 / 2.0));
        ellipse.centerYProperty().bind(pane.heightProperty().multiply(7 / 24.0));
        ellipse.radiusXProperty().bind(pane.widthProperty().multiply(3 / 10.0));
        ellipse.radiusYProperty().bind(pane.heightProperty().multiply(1 / 8.0));

        //Create cylinder side lines
        Line rightLine = new Line(100, 175, 100, 425);
        rightLine.startXProperty().bind(pane.widthProperty().multiply(1 / 5.0));
        rightLine.startYProperty().bind(pane.heightProperty().multiply(7 / 24.0));
        rightLine.endXProperty().bind(pane.widthProperty().multiply(1 / 5.0));
        rightLine.endYProperty().bind(pane.heightProperty().multiply(17 / 24.0));
        Line leftLine = new Line(400, 175, 400, 425);
        leftLine.startXProperty().bind(pane.widthProperty().multiply(4 / 5.0));
        leftLine.startYProperty().bind(pane.heightProperty().multiply(7 / 24.0));
        leftLine.endXProperty().bind(pane.widthProperty().multiply(4 / 5.0));
        leftLine.endYProperty().bind(pane.heightProperty().multiply(17 / 24.0));

        //Create cylinder bottom arcs
        Arc arcSolid = new Arc(250, 425, 150, 75, 180, 180);
        arcSolid.setStyle(style);
        arcSolid.centerXProperty().bind(pane.widthProperty().multiply(1 / 2.0));
        arcSolid.centerYProperty().bind(pane.heightProperty().multiply(17 / 24.0));
        arcSolid.radiusXProperty().bind(pane.widthProperty().multiply(3 / 10.0));
        arcSolid.radiusYProperty().bind(pane.heightProperty().multiply(1 / 8.0));
        Arc arcDashed = new Arc(250, 425, 150, 75, 0, 180);
        arcDashed.setStyle(style);
        arcDashed.centerXProperty().bind(pane.widthProperty().multiply(1 / 2.0));
        arcDashed.centerYProperty().bind(pane.heightProperty().multiply(17 / 24.0));
        arcDashed.radiusXProperty().bind(pane.widthProperty().multiply(3 / 10.0));
        arcDashed.radiusYProperty().bind(pane.heightProperty().multiply(1 / 8.0));
        arcDashed.getStrokeDashArray().addAll(6.0, 21.0);

        pane.getChildren().addAll(ellipse, rightLine, leftLine, arcSolid, arcDashed);
        Scene scene = new Scene(pane, 500, 600);

        primaryStage.setTitle("Exercise15_22");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
