package chapter_23;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;

import static javafx.beans.binding.Bindings.min;

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

        Heap<Integer> heap = new Heap<>();
        heap.add(2).add(43).add(56).add(78).add(34).add(15);//.add(23).add(4);//.add(1);
        System.out.println(heap.size());
        pane.setHeap(heap);

        Scene scene = new Scene(pane, 950, 450);

        primaryStage.setTitle("Exercise23_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class HeapPane extends Pane {
        private Heap<Integer> heap;

        public void setHeap(Heap<Integer> heap) {
            this.heap = heap;
            display();
        }

        private void display() {
            display(null, 1, 2, 1, 0);
        }

        private void display(Circle parent, int row, int width, int position, int index) {
            if (index > heap.size() - 1) return;
            Circle circle = getCircle(heap.getHeight(), row, width, position);
            Text text = new Text(index + "");
            text.xProperty().bind(circle.centerXProperty());
            text.yProperty().bind(circle.centerYProperty());
            getChildren().addAll(circle, text);
            display(circle, row + 1, width * 2, position, index * 2 + 1);
            display(circle, row + 1, width * 2, position + 2, index * 2 + 2);
        }

        private Circle getCircle(double height, int row, double width, int position) {
            Circle circle = new Circle();
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.BLACK);
            circle.radiusProperty().bind(min(widthProperty(), heightProperty()).multiply(0.07));
            circle.centerXProperty().bind(widthProperty().multiply(position / width));
            circle.centerYProperty().bind(heightProperty().multiply(row / (height + 1)));
            return circle;
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
