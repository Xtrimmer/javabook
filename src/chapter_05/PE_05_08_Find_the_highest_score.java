package chapter_05;

import java.util.Scanner;

/**
 * (Find the highest score) Write a program that prompts the user to enter the number
 * of students and each student’s name and score, and finally displays the name
 * of the student with the highest score.
 */
public class PE_05_08_Find_the_highest_score {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String studentName;
        int studentScore;
        System.out.print("Enter the number of students: ");
        int num = scanner.nextInt();
        studentName = scanner.next();
        studentScore = scanner.nextInt();
        for (int i = 1; i < num; i++) {
            String name = scanner.next();
            int score = scanner.nextInt();
            if (score > studentScore){
                studentName = name;
                studentScore = score;
            }
        }
        System.out.println("The student with the highest score is: " + studentName);
    }
}
