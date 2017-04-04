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
 * (H-tree fractal) An H-tree (introduced at the beginning of this chapter in
 * Figure 18.1) is a fractal defined as follows:
 *
 * 1. Begin with a letter H. The three lines of the H are of the same length, as
 *    shown in Figure 18.1a.
 * 2. The letter H (in its sans-serif form, H) has four endpoints. Draw an H centered
 *    at each of the four endpoints to an H-tree of order 1, as shown in
 *    Figure 18.1b. These Hs are half the size of the H that contains the four
 *    endpoints.
 * 3. Repeat Step 2 to create an H-tree of order 2, 3, . . . , and so on, as shown in
 *    Figure 18.1c–d.
 *
 * Write a program that draws an H-tree, as shown in Figure 18.1.
 */
public class PE_18_35_H_tree_fractal extends Application {
    private static final double SIZE = 450;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane pane = new HTreePane();
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise18_35");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private class HTreePane extends BorderPane {
        private Pane pane;
        private TextField textField;

        public HTreePane() {
            setCenter(createCanvas());
            setBottom(createControlBar());
        }

        private Node createCanvas() {
            pane = new Pane();
            pane.setPrefSize(SIZE, SIZE);
            return pane;
        }

        private Node createControlBar() {
            Label label = new Label("Enter an Order: ");
            textField = new TextField();
            textField.setPrefColumnCount(4);
            textField.setOnAction(event -> drawHTree());
            HBox hBox = new HBox(5, label, textField);
            hBox.setPadding(new Insets(10));
            hBox.setAlignment(Pos.CENTER);
            return hBox;
        }

        private void drawHTree(int order, double x, double y, double size) {
            if (order > 0) {
                double halfSize = size / 2;
                order = order - 1;
                drawHTree(order, x, y, halfSize);
                drawHTree(order, x + halfSize, y, halfSize);
                drawHTree(order, x, y + halfSize, halfSize);
                drawHTree(order, x + halfSize, y + halfSize, halfSize);
            }
            Line horizontalLine = new Line(
                    x + size * 0.25, y + size * 0.5, x + size * 0.75, y + size * 0.5
            );
            Line leftVerticalLine = new Line(
                    x + size * 0.25, y + size * 0.25, x + size * 0.25, y + size * 0.75
            );
            Line rightVerticalLine = new Line(
                    x + size * 0.75, y + size * 0.25, x + size * 0.75, y + size * 0.75
            );
            pane.getChildren().addAll(horizontalLine, leftVerticalLine, rightVerticalLine);

        }

        private void drawHTree() {
            pane.getChildren().clear();
            int order = parseInt(0, 6);
            drawHTree(order, 0, 0, SIZE);
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
