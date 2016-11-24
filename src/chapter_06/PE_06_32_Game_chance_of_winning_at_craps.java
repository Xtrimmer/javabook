package chapter_06;

/**
 * (Game: chance of winning at craps) Revise Exercise 6.30 to run it 10,000 times
 * and display the number of winning games.
 */
public class PE_06_32_Game_chance_of_winning_at_craps {
    public static void main(String[] args) {
        int winCount = 0;
        for (int i = 0; i < 10000; i++) {
            int point = 0;
            do {
                int roll1 = rollDice();
                int roll2 = rollDice();
                int rollTotal = roll1 + roll2;
                System.out.println("You rolled " + roll1 + " + " + roll2 + " = " + rollTotal);
                point = play(rollTotal, point);
                if (point == 0) System.out.println("You Lose");
                if (point == 1) {
                    System.out.println("You win");
                    winCount++;
                }
            } while (point > 1);
        }
        System.out.println("The number of winning games is " + winCount + "/10000");
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
