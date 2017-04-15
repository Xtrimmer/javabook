package chapter_18;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

/**
 * (Sierpinski triangle) Write a program that lets the user to enter the order and
 * display the filled Sierpinski triangles as shown in Figure 18.18.
 */
public class PE_18_36_Sierpinski_triangle extends Application {
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        SierpinskiTrianglePane trianglePane = new SierpinskiTrianglePane();
        Label label = new Label("Enter an Order: ");
        TextField textField = new TextField();
        textField.setPrefColumnCount(4);
        textField.setOnAction(event -> changeOrder(trianglePane, parseInt(textField, 0, 6)));
        HBox hBox = new HBox(5, label, textField);
        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(trianglePane);
        borderPane.setBottom(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(borderPane, 200, 210);
        primaryStage.setTitle("Exercise18_36"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage

        trianglePane.setOrder(0);
        scene.widthProperty().addListener(ov -> trianglePane.paint());
        scene.heightProperty().addListener(ov -> trianglePane.paint());
    }

    private void changeOrder(SierpinskiTrianglePane trianglePane, int change) {
        if (change >= 0) {
            trianglePane.setOrder(change);
        }
    }

    private int parseInt(TextField textField, int minValue, int maxValue) {
        try {
            int value = (int) Double.parseDouble(textField.getText());
            if (value < minValue || value > maxValue) throw new NumberFormatException();
            return value;
        } catch (NumberFormatException e) {
            textField.setText(minValue + "");
            return minValue;
        }
    }

    /** Pane for displaying triangles */
    static class SierpinskiTrianglePane extends Pane {
        private int order = 0;

        SierpinskiTrianglePane() {
        }

        /** Set a new order */
        public void setOrder(int order) {
            this.order = order;
            paint();
        }

        private void displayTriangles(int order, Point2D p1,
                                      Point2D p2, Point2D p3) {
            if (order == 0) {
                // Draw a triangle to connect three points
                Polygon triangle = new Polygon();
                triangle.getPoints().addAll(p1.getX(), p1.getY(), p2.getX(),
                        p2.getY(), p3.getX(), p3.getY());
                triangle.setStroke(Color.BLACK);
                triangle.setFill(Color.BLACK);

                this.getChildren().add(triangle);
            } else {
                // Get the midpoint on each edge in the triangle
                Point2D p12 = p1.midpoint(p2);
                Point2D p23 = p2.midpoint(p3);
                Point2D p31 = p3.midpoint(p1);

                // Recursively display three triangles
                displayTriangles(order - 1, p1, p12, p31);
                displayTriangles(order - 1, p12, p2, p23);
                displayTriangles(order - 1, p31, p23, p3);
            }
        }

        protected void paint() {
            // Select three points in proportion to the panel size
            Point2D p1 = new Point2D(getWidth() / 2, 10);
            Point2D p2 = new Point2D(10, getHeight() - 10);
            Point2D p3 = new Point2D(getWidth() - 10, getHeight() - 10);

            this.getChildren().clear(); // Clear the pane before redisplay

            displayTriangles(order, p1, p2, p3);
        }
    }
}
