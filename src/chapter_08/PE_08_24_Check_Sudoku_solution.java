package chapter_08;

import java.util.Scanner;

/**
 * (Check Sudoku solution) Listing 8.4 checks whether a solution is valid by checking
 * whether every number is valid in the board. Rewrite the program by checking
 * whether every row, every column, and every small box has the numbers 1 to 9.
 * Here is a sample run:
 *
 *      Enter a Sudoku puzzle solution
 *      9 6 3 1 7 4 2 5 8 (enter)
 *      1 7 8 3 2 5 6 4 9 (enter)
 *      2 5 4 6 8 9 7 3 1 (enter)
 *      8 2 1 4 3 7 5 9 6 (enter)
 *      4 9 6 8 5 2 3 1 7 (enter)
 *      7 3 5 9 6 1 8 2 4 (enter)
 *      5 8 9 7 1 3 4 6 2 (enter)
 *      3 1 7 2 4 6 9 8 5 (enter)
 *      6 4 2 5 9 8 1 7 3 (enter)
 *      Valid solution
 */
public class PE_08_24_Check_Sudoku_solution {
    public static void main(String[] args) {
        // Read a Sudoku solution
        int[][] grid = readASolution();
        System.out.println(isValid(grid) ? "Valid solution" :
                "Invalid solution");
    }

    /**
     * Read a Sudoku solution from the console
     */
    private static int[][] readASolution() {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a Sudoku puzzle solution:");
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                grid[i][j] = input.nextInt();

        return grid;
    }

    /**
     * Check whether a solution is valid
     */
    private static boolean isValid(int[][] grid) {
        return isRowsValid(grid) && isColumnsValid(grid) && isSubRegionsValid(grid);
    }

    private static boolean isSubRegionsValid(int[][] grid) {
        for (int subRegionStartX = 0; subRegionStartX < grid.length; subRegionStartX += 3) {
            for (int subRegionStartY = 0; subRegionStartY < grid.length; subRegionStartY += 3) {
                boolean[] values = new boolean[9];
                boolean isValid = true;
                for (int xOffset = 0; xOffset < 3; xOffset++) {
                    for (int yOffset = 0; yOffset < 3; yOffset++) {
                        values[grid[subRegionStartX + xOffset][subRegionStartY + yOffset] - 1] = true;
                    }
                }
                for (boolean number : values) isValid &= number;
                if (!isValid) return false;
            }
        }
        //System.out.println("sub-regions checked");
        return true;
    }

    private static boolean isColumnsValid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            boolean[] values = new boolean[9];
            boolean isValid = true;
            for (int j = 0; j < grid[i].length; j++) {
                values[grid[j][i] - 1] = true;
            }
            for (boolean number : values) isValid &= number;
            if (!isValid) return false;
        }
        //System.out.println("Columns checked");
        return true;
    }

    private static boolean isRowsValid(int[][] grid) {
        for (int[] row : grid) {
            boolean[] values = new boolean[9];
            boolean isValid = true;
            for (int cell : row) {
                values[cell - 1] = true;
            }
            for (boolean number : values) isValid &= number;
            if (!isValid) return false;
        }
        //System.out.println("Rows Checked");
        return true;
    }
}