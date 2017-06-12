package chapter_21;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * (Name for both genders) Write a program that prompts the user to enter one of
 * the filenames described in Programming Exercise 12.31 and displays the names
 * that are used for both genders in the file. Use sets to store names and find common
 * names in two sets. Here is a sample run:
 *
 * Enter a file name for baby name ranking: babynamesranking2001.txt (Enter)
 * 69 names used for both genders
 * They are Tyler Ryan Christian ...
 */
public class PE_21_12_Name_for_both_genders {

    public static void main(String[] args) {
        File file = promptUserForFile();
        HashSet<String> boysNames = parseNameFile(file, 2);
        HashSet<String> girlsNames = parseNameFile(file, 4);
        HashSet<String> namesForBothGenders = getNamesForBothGenders(boysNames, girlsNames);
        displayResults(namesForBothGenders);
    }

    private static void displayResults(HashSet<String> namesForBothGenders) {
        System.out.printf("%d names used for both genders%n", namesForBothGenders.size());
        System.out.print("They are ");
        for (String name : namesForBothGenders) {
            System.out.print(name + " ");
        }
    }

    private static <E> HashSet<E> getNamesForBothGenders(Set<E> set1, Set<? extends E> set2) {
        HashSet<E> intersection = new HashSet<>();
        intersection.addAll(set1);
        intersection.retainAll(set2);
        return intersection;
    }

    private static <E> HashSet<E> parseNameFile(File file, int columnNumber) {
        HashSet<E> set = new HashSet<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                for (int i = 1; i < columnNumber; i++) {
                    scanner.next();
                }
                set.add((E) scanner.next());
                scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(1);
        }
        return set;
    }

    private static File promptUserForFile() {
        System.out.print("Enter a file name for baby name ranking: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        return new File(filePath);
    }
}
