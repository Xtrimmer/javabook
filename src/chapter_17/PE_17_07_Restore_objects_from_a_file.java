package chapter_17;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * (Restore objects from a file) Suppose a file named Exercise17_07.dat has been
 * created using the ObjectOutputStream. The file contains Loan objects. The
 * Loan class in Listing 10.2 does not implement Serializable. Rewrite the
 * Loan class to implement Serializable. Write a program that reads the Loan
 * objects from the file and displays the total loan amount. Suppose you don’t
 * know how many Loan objects are there in the file, use EOFException to end
 * the loop.
 */
public class PE_17_07_Restore_objects_from_a_file {

    public static void main(String[] args) {
        File file = new File("resources/data/Exercise17_07.dat");
        List<Loan> loans = restoreLoansFromFile(file);
        displayTotalLoanAmounts(loans);
    }

    private static void displayTotalLoanAmounts(List<Loan> loans) {
        for (int i = 0; i < loans.size(); i++) {
            System.out.print("Loan " + i + " total amount: ");
            System.out.println(loans.get(i).getTotalPayment());
        }
    }

    private static ArrayList<Loan> restoreLoansFromFile(File file) {
        ArrayList<Loan> loans = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                loans.add((Loan) inputStream.readObject());
            }
        } catch (EOFException e) {
            System.out.println("All objects were read in.");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return loans;
    }

    private static class Loan implements Serializable {
        private double annualInterestRate;
        private int numberOfYears;
        private double loanAmount;
        private java.util.Date loanDate;

        /** Default constructor */
        public Loan() {
            this(2.5, 1, 1000);
        }

        /** Construct a loan with specified annual interest rate,
         number of years and loan amount
         */
        public Loan(double annualInterestRate, int numberOfYears,
                    double loanAmount) {
            this.annualInterestRate = annualInterestRate;
            this.numberOfYears = numberOfYears;
            this.loanAmount = loanAmount;
            loanDate = new java.util.Date();
        }

        /** Return annualInterestRate */
        public double getAnnualInterestRate() {
            return annualInterestRate;
        }

        /** Set a new annualInterestRate */
        public void setAnnualInterestRate(double annualInterestRate) {
            this.annualInterestRate = annualInterestRate;
        }

        /** Return loanAmount */
        public double getLoanAmount() {
            return loanAmount;
        }

        /** Set a newloanAmount */
        public void setLoanAmount(double loanAmount) {
            this.loanAmount = loanAmount;
        }

        /** Return loan date */
        public java.util.Date getLoanDate() {
            return loanDate;
        }

        /** Find monthly payment */
        public double getMonthlyPayment() {
            double monthlyInterestRate = annualInterestRate / 1200;
            double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                    (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));
            return monthlyPayment;
        }

        /** Return numberOfYears */
        public int getNumberOfYears() {
            return numberOfYears;
        }

        /** Set a new numberOfYears */
        public void setNumberOfYears(int numberOfYears) {
            this.numberOfYears = numberOfYears;
        }

        /** Find total payment */
        public double getTotalPayment() {
            double totalPayment = getMonthlyPayment() * numberOfYears * 12;
            return totalPayment;
        }
    }
}

