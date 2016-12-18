package chapter_12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * (Ranking summary) Write a program that uses the files described in Programming
 * Exercise 12.31 and displays a ranking summary table for the first five
 * girl’s and boy’s names as follows:
 *
 * -----------------------------------------------------------------------------------
 * Year Rank 1   Rank 2  Rank 3 Rank 4 Rank 5 Rank 1 Rank 2  Rank 3  Rank 4    Rank 5
 * -----------------------------------------------------------------------------------
 * 2010 Isabella Sophia  Emma   Olivia Ava    Jacob  Ethan   Michael Jayden    William
 * 2009 Isabella Emma    Olivia Sophia Ava    Jacob  Ethan   Michael Alexander William
 * ...
 * 2001 Emily    Madison Hannah Ashley Alexis Jacob  Michael Matthew Joshua    Christopher
 */
public class PE_12_32_Ranking_summary {
    private static final String FILE_PATH_PREFIX = "resources\\text\\babynamesranking";
    private static final String FILE_EXTENSION = ".txt";
    private static final int COLUMN_PADDING_SPACE = 1;

    public static void main(String[] args) {
        String[][] tableData = getTableData();
        displayTable(tableData);
    }

    private static void displayTable(String[][] tableData) {
        int[] columnWidths = getColumnWidths(tableData);
        int totalWidth = getTotalWidth(columnWidths);
        printRowDivider(totalWidth);
        printRow(tableData, columnWidths, 0);
        printRowDivider(totalWidth);
        for (int row = 1; row < tableData.length; row++) {
            printRow(tableData, columnWidths, row);
        }
    }

    private static void printRow(String[][] tableData, int[] columnWidths, int row) {
        for (int i = 0; i < tableData[0].length; i++) {
            String format = "%-" + columnWidths[i] + "s";
            System.out.printf(format, tableData[row][i]);
        }
        System.out.println();
    }

    private static void printRowDivider(int totalWidth) {
        for (int i = 0; i < totalWidth; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    private static int getTotalWidth(int[] columnWidths) {
        int totalWidth = 0;
        for (int columnWidth : columnWidths) {
            totalWidth += columnWidth;
        }
        return totalWidth;
    }

    private static int[] getColumnWidths(String[][] tableData) {
        int[] columnWidths = new int[tableData[0].length];
        for (int column = 0; column < columnWidths.length; column++) {
            columnWidths[column] = getMaxWidth(tableData, column);
        }
        return columnWidths;
    }

    private static int getMaxWidth(String[][] tableData, int column) {
        int maxWidth = 0;
        for (String[] row : tableData) {
            int thisWidth = row[column].length();
            if (thisWidth > maxWidth) {
                maxWidth = thisWidth;
            }
        }
        return maxWidth + COLUMN_PADDING_SPACE;
    }

    private static String[][] getTableData() {
        String[][] tableData = new String[11][11];
        populateHeaderInfo(tableData);
        populateYearColumn(tableData);
        populateNameInfo(tableData);
        return tableData;
    }

    private static void populateNameInfo(String[][] tableData) {
        for (int row = 1; row <= 10 ; row++) {
            File file = validateFile(FILE_PATH_PREFIX + tableData[row][0] + FILE_EXTENSION);
            Scanner scanner = openFile(file);
            fillRowData(tableData, row, scanner);
        }
    }

    private static void fillRowData(String[][] tableData, int row, Scanner scanner) {
        for (int i = 1; i <= 5; i++) {
            String[] fileData = scanner.nextLine().split("\\s+");
            tableData[row][i] = fileData[3];
            tableData[row][i+5] = fileData[1];
        }
    }

    private static Scanner openFile(File file) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("The file " + file.getName() + " cannot be found.");
            System.exit(0);
        }
        return scanner;
    }

    private static File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("The file " + file.getName() + " does not exist.");
            System.exit(0);
        }
        if (!file.isFile()) {
            System.out.println("The file " + file.getName() + " is not a normal file.");
            System.exit(0);
        }
        if (!file.canRead()) {
            System.out.println("The application cannot read the file " + file.getName());
            System.exit(0);
        }
        return file;
    }

    private static void populateYearColumn(String[][] tableData) {
        for (int i = 1, year = 2010; i <= 10; i++, year--) {
            tableData[i][0] = year + "";
        }
    }

    private static void populateHeaderInfo(String[][] tableData) {
        tableData[0][0] = "Year";
        for (int i = 1; i <= 5; i++) {
            tableData[0][i] = "Rank " + i;
            tableData[0][i+5] = "Rank " + i;
        }
    }
}