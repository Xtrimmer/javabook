package chapter_23;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
        heap.add(2).add(43).add(56).add(78).add(34).add(15).add(23).add(4).add(1);
        pane.setHeap(heap);

        Scene scene = new Scene(pane);

        primaryStage.setTitle("Exercise23_10");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    class HeapPane extends Pane {

        public void setHeap(Heap<Integer> heap) {
            List<Integer> list = heap.getList();
            int height = getHeapHeight(list.size());
            int index = 0;
            for (int row = 0; row < height; row++) {
                int width = (int) Math.pow(2, row);
                for (int col = 0, place = 1; col < width; col++, place += 2) {
                    Circle circle = getCircle(height, row, width, place);
                    getChildren().add(circle);
                }
            }
        }

        private Circle getCircle(double height, int row, double width, int place) {
            Circle circle = new Circle(15);
            circle.centerXProperty().bind(widthProperty().multiply(place / (width * 2)));
            circle.centerYProperty().bind(heightProperty().multiply((row + 1) / (height + 1)));
            return circle;
        }

        private int getHeapHeight(int size) {
            return (int) Math.ceil(Math.log(size) / Math.log(2));
        }
    }

    class Heap<E extends Comparable<E>> {
        private final Comparator<? super E> comparator = Comparator.naturalOrder();
        private final java.util.ArrayList<E> list = new java.util.ArrayList<>();

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
            return this;
        }

        public ArrayList<E> getList() {
            return list;
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
            return removedObject;
        }
    }
}
