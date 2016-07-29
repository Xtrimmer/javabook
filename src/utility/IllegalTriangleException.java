package utility;

/**
 * Created for Chapter 12 Exercise 05
 */
public class IllegalTriangleException extends Exception {

    public IllegalTriangleException(String message) {
        super("Invalid triangle: (" + message + ")");
    }
}
