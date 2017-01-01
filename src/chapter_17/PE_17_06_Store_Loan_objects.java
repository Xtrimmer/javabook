package chapter_17;

import java.io.*;

/**
 * (Store Loan objects) The Loan class in Listing 10.2 does not implement
 * Serializable. Rewrite the Loan class to implement Serializable. Write
 * a program that creates five Loan objects and stores them in a file named
 * Exercise17_06.dat.
 */
public class PE_17_06_Store_Loan_objects {
    private static final int NUMBER_OF_LOANS = 5;

    public static void main(String[] args) {
        Loan[] loans = generateLoanArray(NUMBER_OF_LOANS);
        File file = new File("resources/data/Exercise17_06.dat");
        storeLoansToFile(loans, file);
    }

    private static Loan[] generateLoanArray(int quantity) {
        Loan[] loans = new Loan[quantity];
        for (int i = 0; i < quantity; i++) {
            loans[i] = new Loan(i * 0.5 + 2, i * 5 + 5, i * 5000 + 5000);
        }
        return loans;
    }

    private static void storeLoansToFile(Loan[] loans, File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Loan loan : loans) {
                outputStream.writeObject(loan);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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

