package chapter_20;

import textbook_listings.Circle;
import textbook_listings.GeometricObject;
import textbook_listings.GeometricObjectComparator;
import textbook_listings.Rectangle;

import java.util.Comparator;

/**
 * (Use Comparator) Write the following generic method using selection sort
 * and a comparator.
 *
 *      public static <E> void selectionSort(E[] list,
 *      Comparator<? super E> comparator)
 *
 * Write a test program that creates an array of 10 GeometricObjects and
 * invokes this method using the GeometricObjectComparator introduced in
 * Listing 20.4 to sort the elements. Display the sorted elements. Use the following
 * statement to create the array.
 *
 *      GeometricObject[] list = {new Circle(5), new Rectangle(4, 5),
 *          new Circle(5.5), new Rectangle(2.4, 5), new Circle(0.5),
 *          new Rectangle(4, 65), new Circle(4.5), new Rectangle(4.4, 1),
 *          new Circle(6.5), new Rectangle(4, 5)};
 */
public class PE_20_21_Use_Comparator {

    public static void main(String[] args) {
        GeometricObject[] list = {
                new Circle(5),
                new Rectangle(4, 5),
                new Circle(5.5),
                new Rectangle(2.4, 5),
                new Circle(0.5),
                new Rectangle(4, 65),
                new Circle(4.5),
                new Rectangle(4.4, 1),
                new Circle(6.5),
                new Rectangle(4, 5)
        };
        Comparator<GeometricObject> comparator = new GeometricObjectComparator();
        printAreas(list);
        selectionSort(list, comparator);
        System.out.println();
        printAreas(list);
    }

    private static void printAreas(GeometricObject[] list) {
        System.out.print("[");
        for (GeometricObject geometricObject : list) {
            if (geometricObject != list[list.length - 1])
                System.out.printf("%.2f, ", geometricObject.getArea());
            else
                System.out.printf("%.2f", geometricObject.getArea());
        }
        System.out.print("]\n");
    }

    public static <E> void selectionSort(E[] list, Comparator<? super E> comparator) {
        for (int destinationIndex = 0; destinationIndex < list.length; destinationIndex++) {
            int minIndex = destinationIndex;
            for (int i = destinationIndex + 1; i < list.length; i++) {
                if (comparator.compare(list[minIndex], list[i]) > 0) minIndex = i;
            }
            E temp = list[destinationIndex];
            list[destinationIndex] = list[minIndex];
            list[minIndex] = temp;
        }
    }
}
