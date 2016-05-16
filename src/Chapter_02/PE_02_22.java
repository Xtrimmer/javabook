package Chapter_02;

/**
 * (Random character) Write a program that displays a random uppercase letter
 * using the System.CurrentTimeMillis() method.
 */
public class PE_02_22 {
    public static void main(String[] args) {
        long millis = System.currentTimeMillis();
        char ch = (char) (millis % 26 + 'A');
        System.out.print(ch + ", ");
    }
}
