package chapter_18;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

/**
 * (Dragging the tree) Revise Programming Exercise 18.38 to move the tree to
 * where the mouse is dragged.
 */
public class PE_18_39_Dragging_the_tree extends Application {
    private static final double SIZE = 450;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new TreePane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise18_39");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class TreePane extends BorderPane {
        private final double angleOffset = Math.toRadians(35);
        private final double reductionFactor = 0.60;
        private Pane pane;
        private TextField textField;

        public TreePane() {
            setCenter(createCanvas());
            setBottom(createControlBar());
        }

        private Node createCanvas() {
            pane = new Pane();
            pane.setPrefSize(SIZE, SIZE);
            pane.setOnMouseDragged(event -> {
                pane.getChildren().clear();
                drawTree(parseInt(0, 10), event.getX(), event.getY(),
                        pane.getHeight() / 3, Math.toRadians(270));
            });
            return pane;
        }

        private Node createControlBar() {
            Label label = new Label("Enter an Order: ");
            textField = new TextField();
            textField.setPrefColumnCount(4);
            textField.setOnAction(event -> drawTree(parseInt(0, 10)));
            HBox hBox = new HBox(5, label, textField);
            hBox.setPadding(new Insets(10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private void drawTree(int order) {
            pane.getChildren().clear();
            drawTree(order, pane.getWidth() / 2, pane.getHeight(),
                    pane.getHeight() / 3, Math.toRadians(270));
        }

        private void drawTree(int order, double x, double y, double length, double angle) {
            if (order == 0) {
                return;
            }
            Line line = new Line(x, y,
                    x + Math.cos(angle) * length,
                    y + Math.sin(angle) * length);
            pane.getChildren().add(line);
            drawTree(order - 1, line.getEndX(), line.getEndY(), length * reductionFactor, angle + angleOffset);
            drawTree(order - 1, line.getEndX(), line.getEndY(), length * reductionFactor, angle - angleOffset);
        }

        private int parseInt(int minValue, int maxValue) {
            try {
                int value = (int) Double.parseDouble(textField.getText());
                if (value < minValue || value > maxValue) throw new NumberFormatException();
                return value;
            } catch (NumberFormatException e) {
                textField.setText(minValue + "");
                return minValue;
            }
        }
    }
}
