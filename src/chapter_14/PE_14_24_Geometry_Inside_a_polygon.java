package chapter_14;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

/**
 * (Geometry: Inside a polygon?) Write a program that prompts the user to enter
 * the coordinates of five points from the command line. The first four points form a
 * polygon, and the program displays the polygon and a text that indicates whether
 * the fifth point is inside the polygon, as shown in Figure 14.51a.
 *
 * Hint: Use the Node’s contains method to test whether a point is inside a node.
 */
public class PE_14_24_Geometry_Inside_a_polygon extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        final Parameters parameters = getParameters();
        final List<String> args = parameters.getRaw();

        validateParameterCount(args);
        double[] points = parsePointsFromArgs(args);
        Polygon polygon = new Polygon(
                points[0], points[1],
                points[2], points[3],
                points[4], points[5],
                points[6], points[7]
        );
        polygon.setStyle("-fx-fill: transparent; -fx-stroke: black");
        Circle circle = new Circle(points[8], points[9], 10);
        String message = polygon.contains(points[8], points[9]) ?
                "The point is inside the polygon" : "The point isn't inside the polygon";

        Pane shapePane = new Pane(polygon, circle);
        BorderPane pane = new BorderPane();
        pane.setTop(shapePane);
        pane.setBottom(new PE_14_24_MessagePane(message));
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane, 400, 400);

        primaryStage.setTitle("Exercise14_24");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private double[] parsePointsFromArgs(List<String> args) {
        int size = args.size();
        double[] values = new double[size];
        for (int i = 0; i < size; i++) {
            try {
                values[i] = Double.parseDouble(args.get(i));
            } catch (NumberFormatException e) {
                System.out.println("One or more of the arguments is of an invalid type.");
                System.exit(0);
            }
        }
        return values;
    }

    private void validateParameterCount(List<String> list) {
        if (list.size() != 10) {
            System.out.println("Invalid number of arguments");
            System.exit(0);
        }
    }
}

class PE_14_24_MessagePane extends StackPane {
    public PE_14_24_MessagePane(String message) {
        Text text = new Text(message);
        setPadding(new Insets(10, 0, 0, 0));
        getChildren().add(text);
    }
}