package chapter_05;

import java.util.Scanner;

/**
 * (Find the two highest scores) Write a program that prompts the user to enter the
 * number of students and each student’s name and score, and finally displays the
 * student with the highest score and the student with the second-highest score.
 */
public class PE_05_09_Find_the_two_highest_scores {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String FirstStudentName;
        int FirstStudentScore;
        String SecondStudentName = "unknown";
        System.out.print("Enter the number of students: ");
        int num = scanner.nextInt();
        FirstStudentName = scanner.next();
        FirstStudentScore = scanner.nextInt();
        for (int i = 1; i < num; i++) {
            String name = scanner.next();
            int score = scanner.nextInt();
            if (score > FirstStudentScore){
                SecondStudentName = FirstStudentName;
                FirstStudentName = name;
                FirstStudentScore = score;
            }
        }
        System.out.println("The student with the highest score is:     " + FirstStudentName);
        System.out.println("The student with the 2nd highest score is: " + SecondStudentName);
    }
}
