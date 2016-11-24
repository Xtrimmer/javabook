package chapter_02;

import javax.swing.JOptionPane;

/**
 * (Financial application: payroll) Rewrite Exercise 2.25 using GUI input and
 * output dialog boxes.
 */
public class PE_02_27 {
    public static void main(String[] args) {


        String employeeName = JOptionPane.showInputDialog(null,
                "Enter employee's name:",
                "Enter employee's name",
                JOptionPane.QUESTION_MESSAGE);
        String input = JOptionPane.showInputDialog(null,
                "Enter number of hours worked in a week:",
                "Enter number of hours worked in a week",
                JOptionPane.QUESTION_MESSAGE);
        double hours = Double.parseDouble(input);
        input = JOptionPane.showInputDialog(null,
                "Enter hourly pay rate:",
                "Enter hourly pay rate",
                JOptionPane.QUESTION_MESSAGE);
        double payRate = Double.parseDouble(input);
        input = JOptionPane.showInputDialog(null,
                "Enter federal tax withholding rate:",
                "Enter federal tax withholding rate",
                JOptionPane.QUESTION_MESSAGE);
        double federalTaxRate = Double.parseDouble(input);
        input = JOptionPane.showInputDialog(null,
                "Enter state tax withholding rate:",
                "Enter state tax withholding rate",
                JOptionPane.QUESTION_MESSAGE);
        double stateTaxRate = Double.parseDouble(input);

        double grossPay = Math.round(hours * payRate * 100) / 100d;
        double federalWithholding = Math.round(grossPay * federalTaxRate * 100) / 100d;
        double stateWithholding = Math.round(grossPay * stateTaxRate * 100) / 100d;
        double totalWithholding = federalWithholding + stateWithholding;

        String output = "Employee Name: " + employeeName +
                        "\nHours Worked: " + hours +
                        "\nPay Rate: $" + payRate +
                        "\nGross Pay: $" + grossPay +
                        "\nDeductions: " +
                        "\n     Federal Withholding (" + federalTaxRate * 100 + "%): $" + federalWithholding +
                        "\n     State Withholding (" + stateTaxRate * 100 + "%): $" + stateWithholding +
                        "\n     Total Deduction: $" + totalWithholding +
                        "\nNet Pay: $" + (grossPay - totalWithholding);
        JOptionPane.showMessageDialog(null,output);
    }
}
