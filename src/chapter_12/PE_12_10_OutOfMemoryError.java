package chapter_12;

/**
 * (OutOfMemoryError) Write a program that causes the JVM to throw an
 * OutOfMemoryError and catches and handles this error.
 */
public class PE_12_10_OutOfMemoryError {
    public static void main(String[] args) {
        try {
            long[] longs = new long[Integer.MAX_VALUE];
        } catch (OutOfMemoryError error) {
            System.out.println("Error: " + error.getMessage());
        }
    }
}
