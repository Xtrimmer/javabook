package chapter_05;

/**
 * (Financial application: compute future tuition) Suppose that the tuition for a university
 * is $10,000 this year and increases 5% every year. In one year, the tuition
 * will be $10,500. Write a program that computes the tuition in ten years and the
 * total cost of four years’ worth of tuition after the tenth year.
 */
public class PE_05_07_Financial_application_compute_future_tuition {
    public static void main(String[] args) {
        final double percentageIncrease = 0.05;
        double tuition = 10000.00;
        double sum = 0;
        for (int i = 1; i <= 14; i++) {
            tuition *= (1 + percentageIncrease);
            if  (i == 10) System.out.printf("Tuition in 10 years: $%.2f%n", tuition);
            if (i <= 10) continue;
            sum += tuition;
        }
        System.out.printf("Total cost of four years' tuition after the 10th year: $%.2f", sum);
    }
}
