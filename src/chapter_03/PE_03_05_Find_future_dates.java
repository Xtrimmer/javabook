package chapter_03;

import java.util.Scanner;

/**
 * (Game: addition quiz) Listing 3.3, SubtractionQuiz.java, randomly generates a
 * subtraction question. Revise the program to randomly generate an addition question
 * with two integers less than 100.
 */
public class PE_03_05_Find_future_dates {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);

        System.out.print("Enter today's day: ");
        int today = SCANNER.nextInt();
        System.out.print("Enter number of days elapsed since today: ");
        int elapsed = SCANNER.nextInt();
        int futureDay = (today + elapsed) % 7;

        String weekday = "";
        String futureWeekday = "";
        switch (today) {
            case 0:
                weekday = "Sunday";
                break;
            case 1:
                weekday = "Monday";
                break;
            case 2:
                weekday = "Tuesday";
                break;
            case 3:
                weekday = "Wednesday";
                break;
            case 4:
                weekday = "Thursday";
                break;
            case 5:
                weekday = "Friday";
                break;
            case 6:
                weekday = "Saturday";
                break;
            default:
                System.out.println("Error: invalid status");
                System.exit(1);
        }
        switch (futureDay) {
            case 0:
                futureWeekday = "Sunday";
                break;
            case 1:
                futureWeekday = "Monday";
                break;
            case 2:
                futureWeekday = "Tuesday";
                break;
            case 3:
                futureWeekday = "Wednesday";
                break;
            case 4:
                futureWeekday = "Thursday";
                break;
            case 5:
                futureWeekday = "Friday";
                break;
            case 6:
                futureWeekday = "Saturday";
                break;
            default:
                System.out.println("Error: invalid status");
                System.exit(1);
        }
        System.out.println("Today is " + weekday + " and the future day is " + futureWeekday);
    }
}
