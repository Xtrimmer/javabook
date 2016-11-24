package chapter_07;

import java.util.Scanner;

/**
 * (Assign grades) Write a program that reads student scores, gets the best score,
 * and then assigns grades based on the following scheme:
 *
 *      Grade is A if score is >= best - 10
 *      Grade is B if score is >= best - 20;
 *      Grade is C if score is >= best - 30;
 *      Grade is D if score is >= best - 40;
 *      Grade is F otherwise.
 *
 * The program prompts the user to enter the total number of students, then prompts
 * the user to enter all of the scores, and concludes by displaying the grades. Here
 * is a sample run:
 *
 *      Enter the number of students: 4 (enter)
 *      Enter 4 scores: 40 55 70 58 (enter)
 *      Student 0 score is 40 and grade is C
 *      Student 1 score is 55 and grade is B
 *      Student 2 score is 70 and grade is A
 *      Student 3 score is 58 and grade is B
 */
public class PE_07_01_Assign_grades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int count = scanner.nextInt();
        System.out.print("Enter " + count + " scores: ");

        int[] scores = new int[count];
        int best = scores[0] = scanner.nextInt();
        for (int i = 1; i < count; i++) {
            scores[i] = scanner.nextInt();
            if (scores[i] > best) best = scores[i];
        }
        for (int i = 0; i < scores.length; i++) {
            char grade = getGrade(scores[i], best);
            System.out.println("Student " + i + " score is " + scores[i] + " and grade is " + grade);
        }
    }

    public static char getGrade(int score, int best){
        if (score >= best - 10) return 'A';
        if (score >= best - 20) return 'B';
        if (score >= best - 30) return 'C';
        if (score >= best - 40) return 'D';
        else return 'F';
    }
}
