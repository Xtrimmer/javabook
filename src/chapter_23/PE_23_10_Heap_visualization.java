package chapter_23;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;

import static javafx.beans.binding.Bindings.*;

/**
 * (Heap visualization) Write a program that displays a heap graphically, as shown
 * in Figure 23.10. The program lets you insert and delete an element from the heap.
 */
public class PE_23_10_Heap_visualization extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HeapPane pane = new HeapPane();
        Scene scene = new Scene(pane, 800, 400);

        primaryStage.setTitle("Exercise23_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class HeapPane extends BorderPane {
        private final TextField textField = new TextField();
        private final Button buttonInsert = new Button("Insert");
        private final Button buttonRemove = new Button("Remove the root");
        private final SimpleHeapPane simpleHeapPane = new SimpleHeapPane();
        private final Heap<Integer> heap = new Heap<>();

        public HeapPane() {
            StackPane stackPane = new StackPane(simpleHeapPane);
            stackPane.setPadding(new Insets(20));
            setCenter(stackPane);
            setBottom(generateControlPane());
            setButtonActions();
        }

        private Node generateControlPane() {
            Label label = new Label("Enter a key:");
            textField.setPrefColumnCount(2);
            HBox hBox = new HBox(5, label, textField, buttonInsert, buttonRemove);
            hBox.setAlignment(Pos.CENTER);
            hBox.setPadding(new Insets(10));
            return hBox;
        }

        private int parseInt(TextField textField, int defaultValue) {
            try {
                int number = (int) Double.parseDouble(textField.getText());
                textField.setText(number + "");
                return number;
            } catch (NumberFormatException e) {
                textField.setText(defaultValue + "");
                return defaultValue;
            }
        }

        private void setButtonActions() {
            buttonInsert.setOnAction(event -> {
                Integer integer = parseInt(textField, 0);
                heap.add(integer);
                simpleHeapPane.setHeap(heap);
            });
            buttonRemove.setOnAction(event -> {
                heap.remove();
                simpleHeapPane.setHeap(heap);
            });
        }
    }

    class SimpleHeapPane extends Pane {
        private Heap<Integer> heap;

        public void setHeap(Heap<Integer> heap) {
            this.heap = heap;
            getChildren().clear();
            display(null, 1, 2, 1, 0);
        }

        private void display(Circle parent, int row, int width, int position, int index) {
            if (index > heap.size() - 1) return;
            Circle circle = generateCircle(row, width, position);
            Label label = generateLabel(row, width, position, index);
            getChildren().addAll(circle, label);
            if (parent != null) {
                Line line = generateLine(parent, circle);
                getChildren().add(line);
            }
            display(circle, row + 1, width * 2, position * 2 - 1, index * 2 + 1);
            display(circle, row + 1, width * 2, position * 2 + 1, index * 2 + 2);
        }

        private Circle generateCircle(int row, double width, int position) {
            Circle circle = new Circle();
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.radiusProperty().bind(min(widthProperty(), heightProperty()).multiply(0.07));
            circle.centerXProperty().bind(widthProperty().multiply(position / width));
            circle.centerYProperty().bind(heightProperty().multiply(row / (heap.getHeight() + 1.0)));
            return circle;
        }

        private Label generateLabel(int row, double width, int position, int index) {
            Label label = new Label(heap.get(index) + "");
            label.layoutXProperty().bind(widthProperty().multiply(position / width).subtract(label.widthProperty().divide(2)));
            label.layoutYProperty().bind(heightProperty().multiply(row / (heap.getHeight() + 1.0)).subtract(label.heightProperty().divide(2)));
            return label;
        }

        private Line generateLine(Circle parent, Circle child) {
            Line line = new Line();
            DoubleProperty parentRadius = parent.radiusProperty();
            DoubleProperty childRadius = child.radiusProperty();
            DoubleProperty x1 = parent.centerXProperty();
            DoubleProperty x2 = child.centerXProperty();
            DoubleProperty y1 = parent.centerYProperty();
            DoubleProperty y2 = child.centerYProperty();
            DoubleBinding distance = createDoubleBinding(() ->
                    Math.sqrt(Math.pow(x2.doubleValue() - x1.doubleValue(), 2) + Math.pow(y2.doubleValue() - y1.doubleValue(), 2)
                    ), x2, x1, y2, y1);
            line.startXProperty().bind(add(x1, multiply(divide(parentRadius, distance), subtract(x2, x1))));
            line.startYProperty().bind(add(y1, multiply(divide(parentRadius, distance), subtract(y2, y1))));
            line.endXProperty().bind(subtract(x2, multiply(divide(childRadius, distance), subtract(x2, x1))));
            line.endYProperty().bind(subtract(y2, multiply(divide(childRadius, distance), subtract(y2, y1))));

            return line;
        }
    }

    class Heap<E extends Comparable<E>> {
        private final Comparator<? super E> comparator = Comparator.naturalOrder();
        private final ArrayList<E> list = new java.util.ArrayList<>();
        private int height = 0;

        public Heap<E> add(E newObject) {
            list.add(newObject);
            int currentIndex = list.size() - 1;

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                if (comparator.compare(list.get(currentIndex), list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                } else break;
                currentIndex = parentIndex;
            }
            height = setHeight();
            return this;
        }

        public E get(int index) {
            return list.get(index);
        }

        public int getHeight() {
            return height;
        }

        public E remove() {
            if (list.size() == 0) return null;

            E removedObject = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;

                if (leftChildIndex >= list.size()) break; // The tree is a heap
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (comparator.compare(list.get(maxIndex), list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }
                if (comparator.compare(list.get(currentIndex), list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                } else break;
            }
            height = setHeight();
            return removedObject;
        }

        public int size() {
            return list.size();
        }

        private int setHeight() {
            return (int) Math.ceil(Math.log(list.size() + 1) / Math.log(2));
        }
    }
}
