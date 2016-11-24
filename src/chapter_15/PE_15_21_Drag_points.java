package chapter_15;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * (Drag points) Draw a circle with three random points on the circle. Connect
 * the points to form a triangle. Display the angles in the triangle. Use the mouse
 * to drag a point along the perimeter of the circle. As you drag it, the triangle and
 * angles are redisplayed dynamically, as shown in Figure 15.30b. For computing
 * angles in a triangle, see Listing 4.1.
 */
public class PE_15_21_Drag_points extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        DynamicTrianglePane pane = new DynamicTrianglePane();
        Scene scene = new Scene(pane, 400, 300);

        primaryStage.setTitle("Exercise15_21");
        primaryStage.setScene(scene);
        primaryStage.show();

        pane.resizeTriangle();
    }

    private class DynamicTrianglePane extends Pane {
        private final Circle circle = new Circle();
        private final double radius = 10;
        private final Circle[] vertices;
        private final Line[] lines;
        private final Text[] angles;

        public DynamicTrianglePane() {
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            vertices = createVertices(3);
            lines = createLines(vertices.length);
            angles = createAngles(vertices.length);
            getChildren().add(circle);
            getChildren().addAll(vertices);
            getChildren().addAll(lines);
            getChildren().addAll(angles);
            for (int i = 0; i < vertices.length; i++) {
                int index = i;
                vertices[i].setOnMouseDragged(event -> {
                    moveVertex(event);
                    updateConnectingLines(index);
                    updateAngles();
                });
            }
        }

        private void updateConnectingLines(int currentIndex) {
            int nextIndex = (currentIndex + 1) % vertices.length;
            int previousIndex = (currentIndex - 1);
            if (previousIndex < 0) {
                previousIndex += vertices.length;
            }
            drawLineBetweenCircles(vertices[currentIndex], vertices[nextIndex], lines[currentIndex]);
            drawLineBetweenCircles(vertices[currentIndex], vertices[previousIndex], lines[previousIndex]);
        }

        private void moveVertex(MouseEvent event) {
            Circle vertex = (Circle) event.getSource();
            double distance = Math.sqrt(Math.pow(event.getX() - circle.getCenterX(), 2)
                    + Math.pow(event.getY() - circle.getCenterY(), 2));
            double x = circle.getCenterX() + ((circle.getRadius() / distance) * (event.getX() - circle.getCenterX()));
            double y = circle.getCenterY() + ((circle.getRadius() / distance) * (event.getY() - circle.getCenterY()));
            vertex.setCenterX(x);
            vertex.setCenterY(y);
        }

        private void updateAngle(int currentIndex) {
            int nextIndex = (currentIndex + 1) % vertices.length;
            int previousIndex = (currentIndex - 1);
            if (previousIndex < 0) {
                previousIndex += vertices.length;
            }
            double x1 = vertices[previousIndex].getCenterX();
            double y1 = vertices[previousIndex].getCenterY();
            double x2 = vertices[currentIndex].getCenterX();
            double y2 = vertices[currentIndex].getCenterY();
            double x3 = vertices[nextIndex].getCenterX();
            double y3 = vertices[nextIndex].getCenterY();
            double a = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
            double b = Math.sqrt((x1 - x3) * (x1 - x3) + (y1 - y3) * (y1 - y3));
            double c = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
            double B = Math.round(Math.toDegrees(Math.acos((b * b - a * a - c * c) / (-2 * a * c))) * 100) / 100.0;
            angles[currentIndex].setX(x2);
            angles[currentIndex].setY(y2 - (radius * 1.2));
            angles[currentIndex].setText(B + "");
        }

        private Text[] createAngles(int count) {
            Text[] texts = new Text[count];
            for (int i = 0; i < count; i++) {
                texts[i] = new Text();
            }
            return texts;
        }

        private Line[] createLines(int count) {
            Line[] lines = new Line[count];
            for (int i = 0; i < count; i++) {
                lines[i] = new Line();
            }
            return lines;
        }

        private Circle[] createVertices(int count) {
            Circle[] circles = new Circle[count];
            for (int i = 0; i < count; i++) {
                circles[i] = new Circle(radius);
            }
            return circles;
        }

        public void resizeTriangle() {
            double radius = Math.min(getWidth(), getHeight()) * 0.8 / 2.0;
            double paneCenterX = getWidth() / 2.0;
            double paneCenterY = getHeight() / 2.0;
            circle.setCenterX(paneCenterX);
            circle.setCenterY(paneCenterY);
            circle.setRadius(radius);
            int vertexCount = vertices.length;
            for (int i = 0; i < vertexCount; i++) {
                vertices[i].setCenterX(paneCenterX + radius * Math.cos(2 * i * (Math.PI / vertexCount) + Math.toRadians(90)));
                vertices[i].setCenterY(paneCenterY - radius * Math.sin(2 * i * (Math.PI / vertexCount) + Math.toRadians(90)));
            }
            for (int i = 0; i < vertexCount; i++) {
                drawLineBetweenCircles(vertices[i], vertices[(i + 1) % vertexCount], lines[i]);
                updateAngles();
            }
        }

        private void updateAngles() {
            for (int i = 0; i < vertices.length; i++) {
                updateAngle(i);
            }
        }

        private void drawLineBetweenCircles(Circle circle1, Circle circle2, Line line) {
            double distance = Math.sqrt(
                    Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2)
                            + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2)
            );
            double lineStartX = circle1.getCenterX() + ((radius / distance)
                    * (circle2.getCenterX() - circle1.getCenterX()));
            double lineStartY = circle1.getCenterY() + ((radius / distance)
                    * (circle2.getCenterY() - circle1.getCenterY()));
            double lineEndX = circle2.getCenterX() - ((radius / distance)
                    * (circle2.getCenterX() - circle1.getCenterX()));
            double lineEndY = circle2.getCenterY() - ((radius / distance)
                    * (circle2.getCenterY() - circle1.getCenterY()));
            line.setStartX(lineStartX);
            line.setStartY(lineStartY);
            line.setEndX(lineEndX);
            line.setEndY(lineEndY);
        }
    }
}
