package chapter_18;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * (Koch snowflake fractal) The text presented the Sierpinski triangle fractal. In
 * this exercise, you will write a program to display another fractal, called the Koch
 * snowflake, named after a famous Swedish mathematician. A Koch snowflake is
 * created as follows:
 *
 * 1. Begin with an equilateral triangle, which is considered to be the Koch fractal
 *    of order (or level) 0, as shown in Figure 18.14a.
 * 2. Divide each line in the shape into three equal line segments and draw an outward
 *    equilateral triangle with the middle line segment as the base to create a
 *    Koch fractal of order 1, as shown in Figure 18.14b.
 * 3. Repeat Step 2 to create a Koch fractal of order 2, 3, . . . , and so on, as shown
 *    in Figure 18.14c–d.
 */
public class PE_18_27_Koch_snowflake_fractal extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SnowflakePane pane = new SnowflakePane();
        Scene scene = new Scene(pane, 300, 325);

        primaryStage.setTitle("Exercise18_27");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class SnowflakePane extends BorderPane {
        private Pane canvas;
        private TextField textField;

        public SnowflakePane() {
            setCenter(generateCanvasPane());
            setBottom(generateControlPane());
        }

        private void displayFractal(ActionEvent actionEvent) {
            Point2D[] points = new Point2D[3];
            for (int i = 0; i < 3; i++) {
                double size = Math.min(canvas.getHeight(), canvas.getWidth());
                double x = ((canvas.getWidth() / 2) + (size / 2 - 20)
                        * Math.cos((2 * i * Math.PI - Math.toRadians(90)) / 3));
                double y = ((canvas.getHeight() / 2) - (size / 2 - 20)
                        * Math.sin((2 * i * Math.PI - Math.toRadians(90)) / 3));
                points[i] = new Point2D(x, y);
            }
            canvas.getChildren().clear();
            TextField textField = (TextField) actionEvent.getSource();
            int order = parseInt(textField, 0);
            drawSide(points[0], points[1], order);
            drawSide(points[1], points[2], order);
            drawSide(points[2], points[0], order);
        }

        private void drawSide(Point2D p1, Point2D p5, int order) {
            if (order == 0) {
                Line line = new Line(p1.getX(), p1.getY(), p5.getX(), p5.getY());
                canvas.getChildren().add(line);
            } else {
                Point2D p2 = new Point2D(p1.getX() * (2 / 3.0) + p5.getX()
                        * (1 / 3.0), p1.getY() * (2 / 3.0) + p5.getY() * (1 / 3.0));
                Point2D p4 = new Point2D(p1.getX() * (1 / 3.0) + p5.getX()
                        * (2 / 3.0), p1.getY() * (1 / 3.0) + p5.getY() * (2 / 3.0));
                Point2D p3 = findThirdPoint(p2, p4);
                drawSide(p1, p2, order - 1);
                drawSide(p2, p3, order - 1);
                drawSide(p3, p4, order - 1);
                drawSide(p4, p5, order - 1);
            }
        }

        private Point2D findThirdPoint(Point2D p1, Point2D p2) {
            Point2D p2Prime = new Point2D(p2.getX() - p1.getX(), p2.getY() - p1.getY());
            double p3PrimeX = (p2Prime.getX() * Math.cos(Math.toRadians(60)))
                    - (p2Prime.getY() * Math.sin(Math.toRadians(60)));
            double p3PrimeY = (p2Prime.getX() * Math.sin(Math.toRadians(60)))
                    + (p2Prime.getY() * Math.cos(Math.toRadians(60)));
            return new Point2D(p3PrimeX + p1.getX(), p3PrimeY + p1.getY());
        }

        private Pane generateCanvasPane() {
            canvas = new Pane();
            return canvas;
        }

        private HBox generateControlPane() {
            textField = new TextField();
            textField.setPrefColumnCount(4);
            textField.setOnAction(this::displayFractal);
            Label label = new Label("Enter an order:");
            HBox hBox = new HBox(5, label, textField);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10));
            return hBox;
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                return (int) Double.parseDouble(textField.getText());
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }
    }
}
