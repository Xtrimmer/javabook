package chapter_16;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * (Geometry: two circles intersect?) Write a program that enables the user to
 * specify the location and size of the circles and displays whether the two circles
 * intersect, as shown in Figure 16.39a. Enable the user to point the mouse inside a
 * circle and drag it. As the circle is being dragged, the circle’s center coordinates
 * in the text fields are updated.
 */
public class PE_16_08_Geometry_two_circles_intersect extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        CirclePane pane = new CirclePane();

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_08");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private class CircleControlPane extends BorderPane {
        public static final int X = 0;
        public static final int Y = 1;
        public static final int RADIUS = 2;
        private final String circleName;
        private TextField[] textFields;

        public CircleControlPane(String circleName) {
            this.circleName = circleName;
            setStyle("-fx-border-color: black");
            StackPane title = createTitlePane();
            GridPane body = createBodyPane();
            setCenter(body);
            setTop(title);
        }

        public void setValues(double x, double y, double radius) {
            textFields[X].setText(String.format("%.1f", x));
            textFields[Y].setText(String.format("%.1f", y));
            textFields[RADIUS].setText(String.format("%.1f", radius));
        }

        public double getValue(int field) {
            try {
                return Double.parseDouble(textFields[field].getText());
            } catch (Exception e) {
                textFields[field].setText("0");
                return 0;
            }
        }

        private GridPane createBodyPane() {
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            gridPane.setPadding(new Insets(5));
            gridPane.setHgap(5);
            gridPane.setVgap(5);

            textFields = new TextField[3];
            for (int i = 0; i < textFields.length; i++) {
                textFields[i] = new TextField("0");
                textFields[i].setAlignment(Pos.BASELINE_RIGHT);
                textFields[i].setPrefColumnCount(4);
            }

            gridPane.addColumn(0, new Label("Center X:"), new Label("Center Y:"), new Label("Radius:"));
            gridPane.addColumn(1, textFields[X], textFields[Y], textFields[RADIUS]);

            return gridPane;
        }

        private StackPane createTitlePane() {
            Label labelTitle = new Label("Enter circle " + circleName + " info");
            StackPane stackPane = new StackPane(labelTitle);
            stackPane.setPadding(new Insets(5));
            return stackPane;
        }

    }

    private class CirclePane extends BorderPane {
        final CircleControlPane controlPane1 = new CircleControlPane("1");
        final CircleControlPane controlPane2 = new CircleControlPane("2");
        final Circle circle1 = new Circle();
        final Circle circle2 = new Circle();
        final Label title = new Label();
        final String message = "Two circles intersect?";

        public CirclePane() {
            initializeCircles();
            title.setText(message + " Yes");
            Pane circlePane = createCirclePane();
            BorderPane controlPane = createControlPane();
            StackPane titlePane = new StackPane(title);
            setTop(titlePane);
            setCenter(circlePane);
            setBottom(controlPane);
            setPadding(new Insets(5));
        }

        private Pane createCirclePane() {
            Pane pane = new Pane(circle1, circle2);
            pane.setPrefWidth(300);
            pane.setPrefHeight(200);

            circle1.setCenterY(100);
            circle1.setCenterX(100);
            circle1.setRadius(60);

            circle2.setCenterY(100);
            circle2.setCenterX(200);
            circle2.setRadius(60);

            controlPane1.setValues(circle1.getCenterX(), circle1.getCenterY(), circle1.getRadius());
            controlPane2.setValues(circle2.getCenterX(), circle2.getCenterY(), circle2.getRadius());
            return pane;
        }

        private void initializeCircles() {
            String style = "-fx-fill: transparent; -fx-stroke: black";
            circle1.setStyle(style);
            circle2.setStyle(style);
            circle1.setOnMouseDragged(event -> dragCircle(controlPane1, event));
            circle2.setOnMouseDragged(event -> dragCircle(controlPane2, event));
        }

        private BorderPane createControlPane() {
            HBox hBox = new HBox(5);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(controlPane1, controlPane2);
            Button button = new Button("Redraw Circles");
            button.setOnAction(event -> redrawCircles());
            StackPane stackPane = new StackPane(button);
            stackPane.setPadding(new Insets(5, 0, 0, 0));
            BorderPane borderPane = new BorderPane(hBox);
            borderPane.setBottom(stackPane);
            return borderPane;
        }

        private void redrawCircles() {
            circle1.setCenterX(controlPane1.getValue(CircleControlPane.X));
            circle1.setCenterY(controlPane1.getValue(CircleControlPane.Y));
            circle1.setRadius(controlPane1.getValue(CircleControlPane.RADIUS));
            circle2.setCenterX(controlPane2.getValue(CircleControlPane.X));
            circle2.setCenterY(controlPane2.getValue(CircleControlPane.Y));
            circle2.setRadius(controlPane2.getValue(CircleControlPane.RADIUS));
            title.setText(message + (overlaps() ? " Yes" : " No"));
        }

        private void dragCircle(CircleControlPane pane, MouseEvent event) {
            Circle circle = ((Circle) event.getSource());
            circle.setCenterX(event.getX());
            circle.setCenterY(event.getY());
            pane.setValues(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
            title.setText(message + (overlaps() ? " Yes" : " No"));
        }

        private boolean contains() {
            return distance() <= Math.abs(circle1.getRadius() - circle2.getRadius());
        }

        private boolean overlaps() {
            return distance() <= circle1.getRadius() + circle2.getRadius()
                    && !contains();
        }

        private double distance() {
            return Math.sqrt(Math.pow(circle2.getCenterX() - circle1.getCenterX(), 2)
                    + Math.pow(circle2.getCenterY() - circle1.getCenterY(), 2));
        }
    }
}
