package Chapter_10;

import utility.Tax;

/**
 * (Financial: the Tax class) Programming Exercise 8.12 writes a program for
 * computing taxes using arrays. Design a class named Tax to contain the following
 * instance data fields:
 *
 * - int filingStatus: One of the four tax-filing statuses: 0—single filer, 1—
 *   married filing jointly or qualifying widow(er), 2—married filing separately,
 *   and 3—head of household. Use the public static constants SINGLE_FILER
 *   (0), MARRIED_JOINTLY_OR_QUALIFYING_WIDOW(ER) (1), MARRIED_
 *   SEPARATELY (2), HEAD_OF_HOUSEHOLD (3) to represent the statuses.
 * - int[][] brackets: Stores the tax brackets for each filing status.
 * - double[] rates: Stores the tax rates for each bracket.
 * - double taxableIncome: Stores the taxable income.
 *
 * Provide the getter and setter methods for each data field and the getTax()
 * method that returns the tax. Also provide a no-arg constructor and the constructor
 * Tax(filingStatus, brackets, rates, taxableIncome).
 *
 * Draw the UML diagram for the class and then implement the class. Write a test
 * program that uses the Tax class to print the 2001 and 2009 tax tables for taxable
 * income from $50,000 to $60,000 with intervals of $1,000 for all four statuses.
 * The tax rates for the year 2009 were given in Table 3.2. The tax rates for 2001
 * are shown in Table 10.1.
 *
 *      TABLE 10.1 2001 United States Federal Personal Tax Rates
 *      ________________________________________________________________________________________
 *                                    Married filing jointly    Married filing
 *      Tax rate   Single filers      or qualifying widow(er)   separately       Head of household
 *      ________________________________________________________________________________________
 *      15%        Up to $27,050      Up to $45,200             Up to $22,600     Up to $36,250
 *      27.5%      $27,051–$65,550    $45,201–$109,250          $22,601–$54,625    $36,251–$93,650
 *      30.5%      $65,551–$136,750   $109,251–$166,500         $54,626–$83,250    $93,651–$151,650
 *      35.5%      $136,751–$297,350  $166,501–$297,350         $83,251–$148,675   $151,651–$297,350
 *      39.1%      $297,351 or more   $297,351 or more          $148,676 or more  $297,351 or more
 */
public class PE_10_08_Financial_the_Tax_class {
    public static void main(String[] args) {
        Tax tax1 = new Tax();
        printTaxTable(tax1, "2009");
        System.out.println();
        int[][] brackets = {
                {27050, 65550, 136750, 297350},   // Single filer
                {45200, 109250, 166500, 297350},  // Married jointly
                // -or qualifying widow(er)
                {22600, 54625, 83250, 148675},   // Married separately
                {36250, 93650, 151650, 297350}  // Head of household
        };
        double[] rates =
                {0.15, 0.275, 0.305, 0.355, 0.391};
        Tax tax2 = new Tax(0, brackets, rates, 0);
        printTaxTable(tax2, "2001");
    }

    private static void printTaxTable(Tax tax, String s) {

        System.out.println("---------------------------------------- " + s + " -----------------------------------------------");
        System.out.println("                                 Married filing jointly    Married filing");
        System.out.println("Taxable Income   Single filers   or qualifying widow(er)   separately       Head of household");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (int taxableIncome = 50000; taxableIncome <= 60000; taxableIncome += 1000) {
            tax.setTaxableIncome(taxableIncome);
            System.out.printf("$%-16d", taxableIncome);
            tax.setFilingStatus(0);
            System.out.printf("$%9.2f      ", tax.getTax());
            tax.setFilingStatus(1);
            System.out.printf("$%9.2f                ", tax.getTax());
            tax.setFilingStatus(2);
            System.out.printf("$%9.2f       ", tax.getTax());
            tax.setFilingStatus(3);
            System.out.printf("$%9.2f", tax.getTax());
            System.out.println();
        }
    }
}
