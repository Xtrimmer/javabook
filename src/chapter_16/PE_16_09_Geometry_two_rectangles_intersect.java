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
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * (Geometry: two rectangles intersect?) Write a program that enables the user to
 * specify the location and size of the rectangles and displays whether the two rectangles
 * intersect, as shown in Figure 16.39b. Enable the user to point the mouse
 * inside a rectangle and drag it. As the rectangle is being dragged, the rectangle’s
 * center coordinates in the text fields are updated.
 */
public class PE_16_09_Geometry_two_rectangles_intersect extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        SquarePane pane = new SquarePane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise16_09");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class SquareControlPane extends BorderPane {
        public static final int X = 0;
        public static final int Y = 1;
        public static final int WIDTH = 2;
        public static final int HEIGHT = 3;
        private final String squareName;
        private TextField[] textFields;

        public SquareControlPane(String squareName) {
            this.squareName = squareName;
            setStyle("-fx-border-color: black");
            StackPane title = createTitlePane();
            GridPane body = createBodyPane();
            setCenter(body);
            setTop(title);
        }

        public void setValues(double x, double y, double width, double height) {
            textFields[X].setText(String.format("%.1f", x));
            textFields[Y].setText(String.format("%.1f", y));
            textFields[WIDTH].setText(String.format("%.1f", width));
            textFields[HEIGHT].setText(String.format("%.1f", height));
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

            textFields = new TextField[4];
            for (int i = 0; i < textFields.length; i++) {
                textFields[i] = new TextField("0");
                textFields[i].setAlignment(Pos.BASELINE_RIGHT);
                textFields[i].setPrefColumnCount(4);
            }

            gridPane.addColumn(0, new Label("X:"), new Label("Y:"), new Label("Width:"), new Label("Height:"));
            gridPane.addColumn(1, textFields[X], textFields[Y], textFields[WIDTH], textFields[HEIGHT]);

            return gridPane;
        }

        private StackPane createTitlePane() {
            Label labelTitle = new Label("Enter Rectangle " + squareName + " info");
            StackPane stackPane = new StackPane(labelTitle);
            stackPane.setPadding(new Insets(5));
            return stackPane;
        }

    }

    private class SquarePane extends BorderPane {
        final SquareControlPane controlPane1 = new SquareControlPane("1");
        final SquareControlPane controlPane2 = new SquareControlPane("2");
        final Rectangle rectangle1 = new Rectangle();
        final Rectangle rectangle2 = new Rectangle();
        final Label title = new Label();
        final String message = "Two rectangles intersect?";

        public SquarePane() {
            initializeRectangles();
            title.setText(message + " No");
            Pane RectanglePane = createRectanglePane();
            BorderPane controlPane = createControlPane();
            StackPane titlePane = new StackPane(title);
            setTop(titlePane);
            setCenter(RectanglePane);
            setBottom(controlPane);
            setPadding(new Insets(5));
        }

        private Pane createRectanglePane() {
            Pane pane = new Pane(rectangle1, rectangle2);
            pane.setPrefWidth(300);
            pane.setPrefHeight(200);

            rectangle1.setY(50);
            rectangle1.setX(50);
            rectangle1.setWidth(100);
            rectangle1.setHeight(100);

            rectangle2.setY(50);
            rectangle2.setX(170);
            rectangle2.setWidth(100);
            rectangle2.setHeight(100);

            controlPane1.setValues(rectangle1.getX(), rectangle1.getY(), rectangle1.getWidth(), rectangle1.getHeight());
            controlPane2.setValues(rectangle2.getX(), rectangle2.getY(), rectangle2.getWidth(), rectangle2.getHeight());
            return pane;
        }

        private void initializeRectangles() {
            String style = "-fx-fill: transparent; -fx-stroke: black";
            rectangle1.setStyle(style);
            rectangle2.setStyle(style);
            rectangle1.setOnMouseDragged(event -> dragRectangle(controlPane1, event));
            rectangle2.setOnMouseDragged(event -> dragRectangle(controlPane2, event));
        }

        private BorderPane createControlPane() {
            HBox hBox = new HBox(5);
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(controlPane1, controlPane2);
            Button button = new Button("Redraw Rectangles");
            button.setOnAction(event -> redrawRectangles());
            StackPane stackPane = new StackPane(button);
            stackPane.setPadding(new Insets(5, 0, 0, 0));
            BorderPane borderPane = new BorderPane(hBox);
            borderPane.setBottom(stackPane);
            return borderPane;
        }

        private void redrawRectangles() {
            rectangle1.setX(controlPane1.getValue(SquareControlPane.X));
            rectangle1.setY(controlPane1.getValue(SquareControlPane.Y));
            rectangle1.setWidth(controlPane1.getValue(SquareControlPane.WIDTH));
            rectangle1.setHeight(controlPane1.getValue(SquareControlPane.HEIGHT));
            rectangle2.setX(controlPane2.getValue(SquareControlPane.X));
            rectangle2.setY(controlPane2.getValue(SquareControlPane.Y));
            rectangle2.setWidth(controlPane2.getValue(SquareControlPane.WIDTH));
            rectangle1.setHeight(controlPane1.getValue(SquareControlPane.HEIGHT));
            title.setText(message + (overlaps() ? " Yes" : " No"));
        }

        private void dragRectangle(SquareControlPane pane, MouseEvent event) {
            Rectangle rectangle = ((Rectangle) event.getSource());
            rectangle.setX(event.getX());
            rectangle.setY(event.getY());
            pane.setValues(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
            title.setText(message + (overlaps() ? " Yes" : " No"));
        }

        private boolean overlaps() {
            return rectangle1.contains(rectangle2.getX(), rectangle2.getY())
                    || rectangle1.contains(rectangle2.getX(), rectangle2.getY() + rectangle2.getHeight())
                    || rectangle1.contains(rectangle2.getX() + rectangle2.getWidth(), rectangle2.getY())
                    || rectangle1.contains(rectangle2.getX() + rectangle2.getWidth(), rectangle2.getY() + rectangle2.getHeight());
        }
    }
}
