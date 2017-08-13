package chapter_22;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * (Game: recursive Sudoku) Write a recursive solution for the Sudoku problem.
 */
public class PE_22_22_Game_recursive_Sudoku {
    public static void main(String[] args) {
        int[][] grid = readAPuzzle();
        if (!isValid(grid))
            System.out.println("Invalid input");
        else if (search(grid)) {
            System.out.println("The solution is found:");
            printGrid(grid);
        } else {
            System.out.println("No solution");
        }
    }

    /** Obtain a list of free cells from the puzzle */
    private static List<int[]> getFreeCellList(int[][] grid) {
        List<int[]> freeCellList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    int[] freeCellPosition = {i, j};
                    freeCellList.add(freeCellPosition);
                }
            }
        }
        return freeCellList;
    }

    /** Check whether grid[i][j] is valid in the grid */
    private static boolean isValid(int i, int j, int[][] grid) {
        // Check whether grid[i][j] is valid at the i's row
        for (int column = 0; column < 9; column++) {
            if (column != j && grid[i][column] == grid[i][j]) {
                return false;
            }
        }
        // Check whether grid[i][j] is valid at the j's column
        for (int row = 0; row < 9; row++) {
            if (row != i && grid[row][j] == grid[i][j]) {
                return false;
            }
        }
        // Check whether grid[i][j] is valid in the 3 by 3 box
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
                if (row != i && col != j && grid[row][col] == grid[i][j]) {
                    return false;
                }
            }
        }

        return true; // The current value at grid[i][j] is valid
    }

    /** Check whether the fixed cells are valid in the grid */
    private static boolean isValid(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] < 0 || grid[i][j] > 9 || (grid[i][j] != 0 && !isValid(i, j, grid))) {
                    return false;
                }
            }
        }
        return true;
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
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a Sudoku puzzle:");
        int[][] grid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = input.nextInt();
            }
        }
        return grid;
    }

    /** Search for a solution */
    private static boolean search(int[][] grid) {
        List<int[]> freeCellList = getFreeCellList(grid);
        return search(grid, freeCellList, 0);
    }

    private static boolean search(int[][] grid, List<int[]> freeCellList, int k) {
        if (k == freeCellList.size()) return true;
        int row = freeCellList.get(k)[0];
        int column = freeCellList.get(k)[1];
        while (++grid[row][column] <= 9) {
            if (isValid(row, column, grid) && search(grid, freeCellList, k + 1)) {
                return true;
            }
        }
        grid[row][column] = 0;
        return false;
    }
}
