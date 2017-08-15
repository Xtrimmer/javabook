package chapter_22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * (Game: multiple Sudoku solutions) The complete solution for the Sudoku problem
 * is given in Supplement VI.A. A Sudoku problem may have multiple solutions.
 * Modify Sudoku.java in Supplement VI.A to display the total number of
 * the solutions. Display two solutions if multiple solutions exist.
 */
public class PE_22_20_Game_multiple_Sudoku_solutions {
    public static void main(String[] args) {
        // Read a Sudoku puzzle
        int[][] grid = readAPuzzle();

        if (!isValid(grid))
            System.out.println("Invalid input");
            System.exit(0);
        List<int[][]> solutions = search(grid);
        if (solutions.isEmpty()) {
            System.out.println("No solution");
        } else {
            System.out.printf("There are %d solutions found:%n", solutions.size());
            printGrid(solutions.get(0));
            if (solutions.size() > 1) {
                System.out.println();
                printGrid(solutions.get(1));
            }
        }
    }

    /** Obtain a list of free cells from the puzzle */
    private static List<int[]> getFreeCellList(int[][] grid) {
        // Store free cell positions into freeCellList
        List<int[]> freeCellList = new ArrayList<>();
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] == 0) {
                    int[] freeCellPosition = {i, j};
                    freeCellList.add(freeCellPosition);
                }
        return freeCellList;
    }

    private static int[][] gridCopy(int[][] grid) {
        int[][] copy = new int[grid.length][];
        for (int i = 0; i < grid.length; i++)
            copy[i] = grid[i].clone();
        return copy;
    }

    /** Check whether the fixed cells are valid in the grid */
    private static boolean isValid(int[][] grid) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (grid[i][j] < 0 || grid[i][j] > 9 ||
                        (grid[i][j] != 0 && !isValid(i, j, grid)))
                    return false;

        return true; // The fixed cells are valid
    }

    /** Check whether grid[i][j] is valid in the grid */
    private static boolean isValid(int i, int j, int[][] grid) {
        // Check whether grid[i][j] is valid at the i's row
        for (int column = 0; column < 9; column++)
            if (column != j && grid[i][column] == grid[i][j])
                return false;

        // Check whether grid[i][j] is valid at the j's column
        for (int row = 0; row < 9; row++)
            if (row != i && grid[row][j] == grid[i][j])
                return false;

        // Check whether grid[i][j] is valid in the 3 by 3 box
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++)
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++)
                if (row != i && col != j && grid[row][col] == grid[i][j])
                    return false;

        return true; // The current value at grid[i][j] is valid
    }

    /** Print the values in the grid */
    private static void printGrid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++)
                System.out.print(grid[i][j] + " ");
            System.out.println();
        }
    }

    /** Read a Sudoku puzzle from the keyboard */
    private static int[][] readAPuzzle() {
        // Create a Scanner
        Scanner input = new Scanner(System.in);

        System.out.println("Enter a Sudoku puzzle:");
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                grid[i][j] = input.nextInt();

        return grid;
    }

    /** Search for a solution */
    private static List<int[][]> search(int[][] grid) {
        List<int[][]> solutions = new ArrayList<>();
        List<int[]> freeCellList = getFreeCellList(grid); // Free cells
        if (freeCellList.isEmpty()) {
            solutions.add(gridCopy(grid)); // "No free cells");
            return solutions;
        }
        int k = 0; // Start from the first free cell
        while (true) {
            int i = freeCellList.get(k)[0];
            int j = freeCellList.get(k)[1];
            if (grid[i][j] == 0)
                grid[i][j] = 1; // Fill the free cell with number 1

            if (isValid(i, j, grid)) {
                if (k + 1 == freeCellList.size()) { // No more free cells
                    solutions.add(gridCopy(grid)); // A solution is found
                } else { // Move to the next free cell
                    k++;
                    continue;
                }
            }
            if (grid[i][j] < 9) {
                // Fill the free cell with the next possible value
                grid[i][j] = grid[i][j] + 1;
            } else { // free cell grid[i][j] is 9, backtrack
                while (grid[i][j] == 9) {
                    if (k == 0) {
                        return solutions; // No possible value
                    }
                    grid[i][j] = 0; // Reset to free cell
                    k--; // Backtrack to the preceding free cell
                    i = freeCellList.get(k)[0];
                    j = freeCellList.get(k)[1];
                }
                // Fill the free cell with the next possible value,
                // search continues from this free cell at k
                grid[i][j] = grid[i][j] + 1;
            }
        }
    }
}
