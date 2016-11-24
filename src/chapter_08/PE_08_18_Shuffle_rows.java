package chapter_08;

/**
 * (Shuffle rows) Write a method that shuffles the rows in a two-dimensional int
 * array using the following header:
 *
 *      public static void shuffle(int[][] m)
 *
 * Write a test program that shuffles the following matrix:
 *
 *      int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
 */
public class PE_08_18_Shuffle_rows {
    public static void main(String[] args) {
        int[][] m = {{1, 2}, {3, 4}, {5, 6}, {7, 8}, {9, 10}};
        shuffle(m);
        printArray(m);
    }

    public static void shuffle(int[][] m) {
        for (int i = 0; i < m.length; i++) {
            int swapSpot = (int)(Math.random() * m.length);
            int[] temp = m[i];
            m[i] = m[swapSpot];
            m[swapSpot] = temp;
        }
    }

    public static void printArray(int[][] m) {
        System.out.print("{");
        for (int i = 0; i < m.length; i++) {
            System.out.print("{");
            for (int j = 0; j < m[i].length; j++) {
                if (j == m[i].length - 1) {
                    System.out.print(m[i][j]);
                } else {
                    System.out.print(m[i][j] + ", ");
                }
            }
            if (i == m.length - 1) {
                System.out.print("}");
            } else {
                System.out.print("},");
            }
        }
        System.out.println("}");
    }
}
