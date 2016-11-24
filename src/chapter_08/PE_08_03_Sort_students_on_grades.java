package chapter_08;

/**
 * (Sort students on grades) Rewrite Listing 8.2, GradeExam.java, to display the
 * students in increasing order of the number of correct answers.
 */
public class PE_08_03_Sort_students_on_grades {
    public static void main(String[] args) {
        // Students' answers to the questions
        char[][] answers = {
                {'A', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'D', 'B', 'A', 'B', 'C', 'A', 'E', 'E', 'A', 'D'},
                {'E', 'D', 'D', 'A', 'C', 'B', 'E', 'E', 'A', 'D'},
                {'C', 'B', 'A', 'E', 'D', 'C', 'E', 'E', 'A', 'D'},
                {'A', 'B', 'D', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'B', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'B', 'B', 'A', 'C', 'C', 'D', 'E', 'E', 'A', 'D'},
                {'E', 'B', 'E', 'C', 'C', 'D', 'E', 'E', 'A', 'D'}};

        // Key to the questions
        char[] keys = {'D', 'B', 'D', 'C', 'C', 'D', 'A', 'E', 'A', 'D'};

        int[][] scores = new int[answers.length][2];

        // Grade all answers
        for (int i = 0; i < answers.length; i++) {
            // Grade one student
            int correctCount = 0;
            for (int j = 0; j < answers[i].length; j++) {
                if (answers[i][j] == keys[j])
                    correctCount++;
            }
            scores[i][0] = i;
            scores[i][1] = correctCount;
        }

        for (int i = 0; i < scores.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < scores.length; j++) {
                if (scores[min][1] > scores[j][1]) {
                    min = j;
                }
            }
            int temp = scores[i][0];
            scores[i][0] = scores[min][0];
            scores[min][0] = temp;
            temp = scores[i][1];
            scores[i][1] = scores[min][1];
            scores[min][1] = temp;
        }
        for (int[] score : scores) {
            System.out.println("Student " + score[0] + "'s correct count is " + score[1]);
        }
    }
}

