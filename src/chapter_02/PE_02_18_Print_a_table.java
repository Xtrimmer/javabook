package chapter_02;

/**
 * (Print a table) Write a program that displays the following table:
 *
 *      a b pow(a, b)
 *      1 2 1
 *      2 3 8
 *      3 4 81
 *      4 5 1024
 *      5 6 15625
 */
public class PE_02_18_Print_a_table {
    public static void main(String[] args) {
        System.out.println("a     b     pow(a, b)");
        System.out.println("1     2     " + Math.pow(1, 2));
        System.out.println("2     3     " + Math.pow(2, 3));
        System.out.println("3     4     " + Math.pow(3, 4));
        System.out.println("4     5     " + Math.pow(4, 5));
        System.out.println("5     6     " + Math.pow(5, 6));
    }
}
