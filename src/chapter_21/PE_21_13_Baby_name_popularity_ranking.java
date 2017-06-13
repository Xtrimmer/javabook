package chapter_21;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * (Baby name popularity ranking) Revise Programming Exercise 21.11 to prompt
 * the user to enter year, gender, and name and display the ranking for the name.
 * Prompt the user to enter another inquiry or exit the program. Here is a sample
 * run:
 *
 *      Enter the year: 2010 (Enter)
 *      Enter the gender: M (Enter)
 *      Enter the name: Javier (Enter)
 *      Boy name Javier is ranked #190 in year 2010
 *      Enter another inquiry? Y (Enter)
 *      Enter the year: 2001 (Enter)
 *      Enter the gender: F (Enter)
 *      Enter the name: Emily (Enter)
 *      Girl name Emily is ranked #1 in year 2001
 *      Enter another inquiry? N (Enter)
 */
public class PE_21_13_Baby_name_popularity_ranking {
    public static void main(String[] args) {
        Inquiry inquiry = new BabyNameInquiry();
        Scanner scanner = new Scanner(System.in);
        do {
            inquiry.inquire();
            System.out.print("Enter another inquiry? ");
        } while (scanner.next().toLowerCase().charAt(0) == 'y');
    }

    private interface Inquiry {
        void inquire();
    }

    private static class BabyNameInquiry implements Inquiry {
        private final Scanner scanner = new Scanner(System.in);

        @Override
        public void inquire() {
            int year = promptYear();
            char gender = promptGender();
            String name = promptName();
            Integer rank = BabyNames.getNameRank(year, gender, name);
            if (rank != null) {
                System.out.printf("%s name %s is ranked #%d in year %d%n",
                        gender == 'm' ? "Boy" : "Girl", name, rank, year);
            } else {
                System.out.println("The name you entered is not ranked.");
            }
        }

        private char promptGender() {
            char gender = ' ';
            boolean valid;
            do {
                System.out.print("Enter the gender: ");
                valid = false;
                if (scanner.hasNext()) {
                    gender = scanner.next().toLowerCase().charAt(0);
                } else {
                    scanner.nextLine();
                }
                if (gender == 'm' || gender == 'f') {
                    valid = true;
                } else {
                    System.out.println("Error: Invalid gender");
                }
            } while (!valid);
            return gender;
        }

        private String promptName() {
            System.out.print("Enter the name: ");
            return scanner.next();
        }

        private int promptYear() {
            int year = 0;
            boolean valid;
            do {
                System.out.print("Enter the year: ");
                valid = false;
                if (scanner.hasNextInt()) {
                    year = scanner.nextInt();
                } else {
                    scanner.nextLine();
                }
                if (year >= 2001 && year <= 2010) {
                    valid = true;
                } else {
                    System.out.println("Error: Invalid year");
                }
            } while (!valid);
            return year;
        }
    }

    private static class BabyNames {
        private static Map<String, Integer>[] boysNames;
        private static Map<String, Integer>[] girlsNames;

        static {
            initializeNameArrays();
        }

        public static Integer getNameRank(int year, char gender, String name) {
            int arrayIndex = year - 2001;
            Integer rank;
            if (gender == 'm') {
                rank = boysNames[arrayIndex].get(name.toLowerCase());
            } else {
                rank = girlsNames[arrayIndex].get(name.toLowerCase());
            }
            return rank;
        }

        private static void initializeNameArrays() {
            boysNames = new Map[10];
            girlsNames = new Map[10];
            for (int i = 0; i < 10; i++) {
                boysNames[i] = new HashMap<>();
                girlsNames[i] = new HashMap<>();
                populateMapData(i);
            }
        }

        private static void populateMapData(int i) {
            String urlPrefix = "http://www.cs.armstrong.edu/liang/data/babynamesranking";
            String urlPostfix = ".txt";
            int year = i + 2001;
            URL url;
            try {
                url = new URL(urlPrefix + year + urlPostfix);
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    int rank = scanner.nextInt();
                    boysNames[i].put(scanner.next().toLowerCase(), rank);
                    scanner.next();
                    girlsNames[i].put(scanner.next().toLowerCase(), rank);
                    scanner.next();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
