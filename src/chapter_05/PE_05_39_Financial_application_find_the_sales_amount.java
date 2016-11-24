package chapter_05;

/**
 * (Financial application: find the sales amount) You have just started a sales job
 * in a department store. Your pay consists of a base salary and a commission. The
 * base salary is $5,000. The scheme shown below is used to determine the commission
 * rate.
 *
 *      Sales Amount      Commission Rate
 *      ---------------------------------
 *      $0.01–$5,000            8 percent
 *      $5,000.01–$10,000      10 percent
 *      $10,000.01 and above   12 percent
 *
 * Note that this is a graduated rate. The rate for the first $5,000 is at 8%, the next
 * $5000 is at 10%, and the rest is at 12%. If the sales amount is 25,000, the commission
 * is 5,000 * 8% + 5,000 * 10% + 15,000 * 12% = 2,700.
 * Your goal is to earn $30,000 a year. Write a program that finds the minimum sales
 * you have to generate in order to make $30,000.
 */
public class PE_05_39_Financial_application_find_the_sales_amount {
    public static void main(String[] args) {
        double commision;
        double sales = 0;
        do {
            if (sales <= 5000){
                commision = sales * 0.08;
            }
            else if (sales <= 10000){
                commision = ((sales - 5000) * 0.1) + (5000 * 0.08);
            }
            else{
                commision = ((sales - 10000) * 0.12) + (5000 * 0.1) + (5000 * 0.08);
            }
            sales += 0.01;
        } while (commision <= 25000);
        System.out.printf("To earn $30,000 sales must be at least $%,.2f", sales);
    }
}
