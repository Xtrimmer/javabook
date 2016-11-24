package chapter_08;

import java.util.Scanner;

/**
 * (Financial tsunami) Banks lend money to each other. In tough economic times,
 * if a bank goes bankrupt, it may not be able to pay back the loan. A bank’s
 * total assets are its current balance plus its loans to other banks. The diagram in
 * Figure 8.8 shows five banks. The banks’ current balances are 25, 125, 175, 75,
 * and 181 million dollars, respectively. The directed edge from node 1 to node 2
 * indicates that bank 1 lends 40 million dollars to bank 2.
 *
 *      FIGURE 8.8 Banks lend money to each other. (p.312)
 *
 * If a bank’s total assets are under a certain limit, the bank is unsafe. The money it
 * borrowed cannot be returned to the lender, and the lender cannot count the loan in
 * its total assets. Consequently, the lender may also be unsafe, if its total assets are
 * under the limit. Write a program to find all the unsafe banks. Your program reads
 * the input as follows. It first reads two integers n and limit, where n indicates the
 * number of banks and limit is the minimum total assets for keeping a bank safe. It
 * then reads n lines that describe the information for n banks with IDs from 0 to n-1.
 * The first number in the line is the bank’s balance, the second number indicates
 * the number of banks that borrowed money from the bank, and the rest are pairs
 * of two numbers. Each pair describes a borrower. The first number in the pair
 * is the borrower’s ID and the second is the amount borrowed. For example, the
 * input for the five banks in Figure 8.8 is as follows (note that the limit is 201):
 *
 *      5 201
 *      25 2 1 100.5 4 320.5
 *      125 2 2 40 3 85
 *      175 2 0 125 3 75
 *      75 1 0 125
 *      181 1 2 125
 *
 * The total assets of bank 3 are (75 + 125), which is under 201, so bank 3 is
 * unsafe. After bank 3 becomes unsafe, the total assets of bank 1 fall below
 * (125 + 40). Thus, bank 1 is also unsafe. The output of the program should be
 *
 *      Unsafe banks are 3 1
 *
 * (Hint: Use a two-dimensional array borrowers to represent loans.
 * borrowers[i][j] indicates the loan that bank i loans to bank j. Once bank j
 * becomes unsafe, borrowers[i][j] should be set to 0.)
 */
public class PE_08_17_Financial_tsunami {
    public static void main(String[] args) {
        // read in information
        Scanner scanner = new Scanner(System.in);
        int bankCount = scanner.nextInt();
        int limit = scanner.nextInt();

        double[] bankBalances = new double[bankCount];
        double[][] borrowers = new double[bankCount][bankCount];
        boolean[] unsafe = new boolean[bankCount];

        for (int i = 0; i < bankCount; i++) {
            bankBalances[i] = scanner.nextDouble();
            int borrowerCount = scanner.nextInt();
            for (int j = 0; j < borrowerCount; j++) {
                int borrowerID = scanner.nextInt();
                borrowers[i][borrowerID] = scanner.nextDouble();
            }
        }

        //determine unsafe banks
        boolean hasChange;
        do {
            hasChange = false;
            for (int i = 0; i < bankCount; i++) {
                double assets = bankBalances[i];
                for (int j = 0; j < bankCount; j++) {
                    assets += borrowers[i][j];
                }
                if (assets < limit) {
                    unsafe[i] = true;
                    for (int j = 0; j < bankCount; j++) {
                        if (borrowers[j][i] != 0) {
                            borrowers[j][i] = 0;
                            hasChange = true;
                        }
                    }
                }
            }
        } while (hasChange);

        //print unsafe banks
        System.out.print("Unsafe banks are ");
        for (int i = 0; i < bankCount; i++) {
            if (unsafe[i]) System.out.print(i + " ");
        }
    }
}
