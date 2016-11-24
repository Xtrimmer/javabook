package chapter_04;

import java.util.Scanner;

/**
 * Created by jtrimmer on 7/10/2015.
 */
public class PE_04_17_Days_of_a_month {
    public static void main(String[] args) {
        final Scanner SCANNER = new Scanner(System.in);


        System.out.print("Enter a year [e.g. 2016]: ");
        int year = SCANNER.nextInt();
        SCANNER.nextLine();
        System.out.print("Enter a month [e.g. Jan]: ");
        String month = SCANNER.nextLine().toUpperCase().substring(0,3);
        int days = 0;
        if (month.equals("JAN") || month.equals("MAR") || month.equals("MAY") || month.equals("JUL")
                || month.equals("AUG") || month.equals("OCT") || month.equals("DEC")){
            days = 31;
        } else if (month.equals("APR") || month.equals("JUN") || month.equals("SEP") || month.equals("NOV")) {
            days = 30;
        } else if (month.equals("FEB")){
            if (year % 4 == 0 && (year % 100 != 0  || year % 400 == 0)){
                days = 29;
            } else {
                days = 28;
            }
        }
        System.out.println(month + " " + year + " has " + days + " days.");
    }
}
