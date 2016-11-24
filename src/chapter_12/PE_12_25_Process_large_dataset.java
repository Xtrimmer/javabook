package chapter_12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * (Process large dataset) A university posts its employees’ salaries at http://
 * cs.armstrong.edu/liang/data/Salary.txt. Each line in the file consists of a faculty
 * member’s first name, last name, rank, and salary (see Programming Exercise
 * 12.24). Write a program to display the total salary for assistant professors,
 * associate professors, full professors, and all faculty, respectively, and display
 * the average salary for assistant professors, associate professors, full professors,
 * and all faculty, respectively.
 */
public class PE_12_25_Process_large_dataset {
    private static String[] ranks = {"assistant","associate","full"};
    private static final int RANK = 2, SALARY = 3;
    private static final int ASSISTANT = 0, ASSOCIATE = 1, FULL = 2, ALL = 3;
    private static final int TOTALS = 0, AVERAGES = 1;

    public static void main(String[] args) {
        URL url = getURL("http://cs.armstrong.edu/liang/data/Salary.txt");
        ArrayList<String[]> data = parseDataFromUrl(url);
        double [][] salaryInfo = processData(data);
        displaySalaryInfo(salaryInfo);
    }

    private static void displaySalaryInfo(double[][] salaryInfo) {
        System.out.println("Salary Totals:");
        System.out.printf("   Assistant: $%,.2f%n", salaryInfo[TOTALS][ASSISTANT]);
        System.out.printf("   Associate: $%,.2f%n", salaryInfo[TOTALS][ASSOCIATE]);
        System.out.printf("   Full:      $%,.2f%n", salaryInfo[TOTALS][FULL]);
        System.out.printf("   All:       $%,.2f%n", salaryInfo[TOTALS][ALL]);
        System.out.println("Salary Averages:");
        System.out.printf("   Assistant: $%,.2f%n", salaryInfo[AVERAGES][ASSISTANT]);
        System.out.printf("   Associate: $%,.2f%n", salaryInfo[AVERAGES][ASSOCIATE]);
        System.out.printf("   Full:      $%,.2f%n", salaryInfo[AVERAGES][FULL]);
        System.out.printf("   All:       $%,.2f%n", salaryInfo[AVERAGES][ALL]);
    }

    private static double[][] processData(ArrayList<String[]> data) {
        double[][] info = new double[2][4];
        int[] counts = new int[4];
        double[] sums = new double[4];
        for (String[] dataLine : data) {
            if (dataLine[RANK].equals(ranks[ASSISTANT])) {
                counts[ASSISTANT]++;
                sums[ASSISTANT] += Double.valueOf(dataLine[SALARY]);
            } else if (dataLine[RANK].equals(ranks[ASSOCIATE])) {
                counts[ASSOCIATE]++;
                sums[ASSOCIATE] += Double.valueOf(dataLine[SALARY]);
            } else if (dataLine[RANK].equals(ranks[FULL])) {
                counts[FULL]++;
                sums[FULL] += Double.valueOf(dataLine[SALARY]);
            }
        }
        counts[ALL] = counts[ASSISTANT] + counts[ASSOCIATE] + counts[FULL];
        sums[ALL] = sums[ASSISTANT] + sums[ASSOCIATE] + sums[FULL];
        System.arraycopy(sums, 0, info[TOTALS], 0, sums.length);
        for (int rank = ASSISTANT; rank <= ALL; rank++) {
            info[AVERAGES][rank] = sums[rank] / counts[rank];
        }
        return info;
    }

    private static ArrayList<String[]> parseDataFromUrl(URL url) {
        ArrayList<String[]> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(url.openStream())) {
            while (scanner.hasNext()) {
                data.add(scanner.nextLine().split(" "));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return data;
    }

    private static URL getURL(String s) {
        try {
            return new URL(s);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
/*
Salary Totals:
   Assistant: $20,246,511.91
   Associate: $28,844,146.58
   Full:      $35,678,051.41
   All:       $84,768,709.90
Salary Averages:
   Assistant: $65,949.55
   Associate: $83,849.26
   Full:      $102,229.37
   All:       $84,768.71
 */