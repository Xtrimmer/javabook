package chapter_05;

import java.util.Scanner;

/**
 * (Financial application: find the sales amount) Rewrite Programming Exercise
 * 5.39 as follows:
 * - Use a for loop instead of a do-while loop.
 * - Let the user enter COMMISSION_SOUGHT instead of fixing it as a constant.
 */
public class PE_05_42_Financial_application_find_the_sales_amount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the commission sought: ");
        final double COMMISSION_SOUGHT = scanner.nextDouble();
        double commission;
        double sales;
        for (sales = 0.01; sales < 100000000;sales += 0.01){
            if (sales <= 5000){
                commission = sales * 0.08;
            }
            else if (sales <= 10000){
                commission = ((sales - 5000) * 0.1) + (5000 * 0.08);
            }
            else{
                commission = ((sales - 10000) * 0.12) + (5000 * 0.1) + (5000 * 0.08);
            }
            if (commission >= COMMISSION_SOUGHT - 5000) break;
        }
        System.out.printf("To earn $%,.2f sales must be at least $%,.2f",COMMISSION_SOUGHT, sales);
    }
}