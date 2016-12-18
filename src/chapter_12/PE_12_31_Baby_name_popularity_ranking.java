package chapter_12;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * (Baby name popularity ranking) The popularity ranking of baby names from
 * years 2001 to 2010 is downloaded from www.ssa.gov/oact/babynames and stored
 * in files named babynameranking2001.txt, babynameranking2002.txt, . . . ,
 * babynameranking2010.txt. Each file contains one thousand lines. Each line
 * contains a ranking, a boy’s name, number for the boy’s name, a girl’s name,
 * and number for the girl’s name. For example, the first two lines in the file
 * babynameranking2010.txt are as follows:
 *
 *      1     Jacob     21,875     Isabella     22,731
 *      2     Ethan     17,866     Sophia       20,477
 *
 * So, the boy’s name Jacob and girl’s name Isabella are ranked #1 and the boy’s
 * name Ethan and girl’s name Sophia are ranked #2. 21,875 boys are named
 * Jacob and 22,731 girls are named Isabella. Write a program that prompts the
 * user to enter the year, gender, and followed by a name, and displays the ranking
 * of the name for the year. Here is a sample run:
 *
 *      Enter the year: 2010 (enter)
 *      Enter the gender: M (enter)
 *      Enter the name: Javier (enter)
 *      Javier is ranked #190 in year 2010
 *
 *      Enter the year: 2010 (enter)
 *      Enter the gender: F (enter)
 *      Enter the name: ABC (enter)
 *      The name ABC is not ranked in year 2010
 */
public class PE_12_31_Baby_name_popularity_ranking {
    private static final String FILE_PATH_PREFIX = "resources\\text\\babynamesranking";
    private static final String FILE_EXTENSION = ".txt";

    public static void main(String[] args) {
        int year = promptYearValue("Enter the Year: ", 2001, 2010);
        char gender = promptGenderValue("Enter the gender: ");
        String name = promptStringValue("Enter the name: ");
        ArrayList<String[]> data = getDataByYear(year);
        int rank = getRank(name, gender, data);
        displayNameInfo(name, rank, year);
    }

    private static void displayNameInfo(String name, int rank, int year) {
        if (rank > 0) {
            System.out.println(name + " is ranked #" + rank + " in year " + year);
        } else {
            System.out.println("The name " + name + " is not ranked in year " + year);
        }
    }

    private static int getRank(String name, char gender, ArrayList<String[]> data) {
        int genderIndex = gender == 'M' ? 1 : 3;
        for (String[] line : data) {
            if (line[genderIndex].equals(name)) {
                try {
                    return Integer.parseInt(line[0]);
                } catch (NumberFormatException e) {
                    System.out.println("There was a problem reading the data.\nThe string cannot be parsed as an integer.");
                }
            }
        }
        return 0;
    }

    private static ArrayList<String[]> getDataByYear(int year) {
        String filePath = FILE_PATH_PREFIX + year + FILE_EXTENSION;
        File file = validateFile(filePath);
        return parseDataFromFile(file);
    }

    private static ArrayList<String[]> parseDataFromFile(File file) {
        ArrayList<String[]> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                data.add(scanner.nextLine().split("\\s+"));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
        return data;
    }

    private static File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("The file denoted by this pathname does not exist.");
            System.exit(0);
        }
        if (!file.isFile()) {
            System.out.println("The file denoted by this pathname is not a normal file.");
            System.exit(0);
        }
        if (!file.canRead()) {
            System.out.println("The application cannot read the file denoted by this pathname.");
            System.exit(0);
        }
        return file;
    }

    private static String promptStringValue(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static char promptGenderValue(String s) {
        Scanner scanner = new Scanner(System.in);
        char gender;
        boolean valid;
        do {
            valid = true;
            System.out.print(s);
            gender = scanner.nextLine().toUpperCase().charAt(0);
            if (gender != 'M' && gender != 'F') {
                System.out.println("Cannot recognize gender.\nPlease enter M for male or F for female.");
                valid = false;
            }
        } while (!valid);
        return gender;
    }

    public static int promptYearValue(String s, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int year = 0;
        boolean valid;
        do {
            valid = true;
            System.out.print(s);
            try {
                year = scanner.nextInt();
                if (year < min || year > max) {
                    System.out.println("Value out of range.\nPlease enter a year between 2001 and 2010 inclusive.");
                    valid = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Value not recognized as a year.");
                scanner.nextLine();
                valid = false;
            }
        } while (!valid);
        return year;
    }
}
