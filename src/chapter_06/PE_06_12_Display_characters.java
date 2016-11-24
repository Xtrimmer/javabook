package chapter_06;

/**
 * (Display characters) Write a method that prints characters using the following
 * header:
 *
 *      public static void printChars(char ch1, char ch2, int numberPerLine)
 *
 * This method prints the characters between ch1 and ch2 with the specified numbers
 * per line. Write a test program that prints ten characters per line from 1 to Z.
 * Characters are separated by exactly one space.
 */
public class PE_06_12_Display_characters {
    public static void main(String[] args) {
        printChars('1', 'z', 10);
    }

    public static void printChars(char ch1, char ch2, int numberPerLine) {
        int count = 0;
        for (int i = ch1; i <= ch2; i++) {
            System.out.print((char) i + " ");
            count++;
            if (count == numberPerLine) {
                System.out.println();
                count = 0;
            }
        }
    }
}
