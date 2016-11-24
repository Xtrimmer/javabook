package chapter_06;

/**
 * (Game: craps) Craps is a popular dice game played in casinos. Write a program
 * to play a variation of the game, as follows:
 *
 * Roll two dice. Each die has six faces representing values 1, 2, …, and 6, respectively.
 * Check the sum of the two dice. If the sum is 2, 3, or 12 (called craps), you
 * lose; if the sum is 7 or 11 (called natural), you win; if the sum is another value
 * (i.e., 4, 5, 6, 8, 9, or 10), a point is established. Continue to roll the dice until either
 * a 7 or the same point value is rolled. If 7 is rolled, you lose. Otherwise, you win.
 *
 * Your program acts as a single player. Here are some sample runs.
 *
 *      You rolled 5 + 6 = 11
 *      You win
 *
 *      You rolled 1 + 2 = 3
 *      You lose
 *
 *      You rolled 4 + 4 = 8
 *      point is 8
 *      You rolled 6 + 2 = 8
 *      You win
 *
 *      You rolled 3 + 2 = 5
 *      point is 5
 *      You rolled 2 + 5 = 7
 *      You lose
 */
public class PE_06_30_Game_craps {
    public static void main(String[] args) {
        int point = 0;
        do {
            int roll1 = rollDice();
            int roll2 = rollDice();
            int rollTotal = roll1 + roll2;
            System.out.println("You rolled " + roll1 + " + " + roll2 + " = " + rollTotal);
            point = play(rollTotal, point);
            if (point == 0) System.out.println("You Lose");
            if (point == 1) System.out.println("You win");
        } while (point > 1);

    }

    public static int rollDice() {
        return (int)(Math.random() * 6 + 1);
    }

    public static int play(int roll, int point) {
        if (point > 1) {
            if (roll == point) return 1;
            else if (roll == 7) return 0;
            else return point;
        } else {
            switch (roll) {
                case 2:
                case 3:
                case 12:
                    return 0;
                case 7:
                case 11:
                    return 1;
                default:
                    return roll;
            }
        }
    }
}
