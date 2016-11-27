package chapter_10;

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
                {27050, 65550, 136750, 297350, 297350},   // Single filer
                {45200, 109250, 166500, 297350, 297350},  // Married jointly
                // -or qualifying widow(er)
                {22600, 54625, 83250, 148675, 148675},   // Married separately
                {36250, 93650, 151650, 297350, 297350}  // Head of household
        };
        double[] rates =
                {0.15, 0.275, 0.305, 0.355, 0.391, 0.391};
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

    private static class Tax {
        public static final int SINGLE_FILER = 0;
        public static final int MARRIED_JOINTLY_OR_QUALIFYING_WIDOWER = 1;
        public static final int MARRIED_SEPARATELY = 2;
        public static final int HEAD_OF_HOUSEHOLD = 3;

        // Default Brackets and rates reflect the 2009 tax year.
        private static final int[][] DEFAULT_BRACKETS = {
                {8350, 33950, 82250, 171550, 372950},   // Single filer
                {16700, 67900, 137050, 20885, 372950},  // Married jointly
                // -or qualifying widow(er)
                {8350, 33950, 68525, 104425, 186475},   // Married separately
                {11950, 45500, 117450, 190200, 372950}  // Head of household
        };
        private static final double[] DEFAULT_RATES =
                {0.10, 0.15, 0.25, 0.28, 0.33, 0.35};

        private int filingStatus;
        private int[][] brackets;
        private double[] rates;
        private double taxableIncome;

        Tax() {
            this(0, DEFAULT_BRACKETS, DEFAULT_RATES, 0);
        }

        Tax(int filingStatus, int[][] brackets, double[] rates, double taxableIncome) {
            this.filingStatus = filingStatus;
            this.brackets = brackets;
            this.rates = rates;
            this.taxableIncome = taxableIncome;
        }

        int getFilingStatus() {
            return filingStatus;
        }

        void setFilingStatus(int filingStatus) {
            this.filingStatus = filingStatus;
        }

        int[][] getBrackets() {
            return brackets;
        }

        void setBrackets(int[][] brackets) {
            this.brackets = brackets;
        }

        double[] getRates() {
            return rates;
        }

        void setRates(double[] rates) {
            this.rates = rates;
        }

        double getTaxableIncome() {
            return taxableIncome;
        }

        void setTaxableIncome(double taxableIncome) {
            this.taxableIncome = taxableIncome;
        }

        double getTax() {
            double tax;
            if (taxableIncome <= brackets[filingStatus][0])
                tax = taxableIncome * rates[0];
            else if (taxableIncome <= brackets[filingStatus][1])
                tax = brackets[filingStatus][0] * rates[0]
                        + (taxableIncome - brackets[filingStatus][0]) * rates[1];
            else if (taxableIncome <= brackets[filingStatus][2])
                tax = brackets[filingStatus][0] * rates[0]
                        + (brackets[filingStatus][1] - brackets[filingStatus][0]) * rates[1]
                        + (taxableIncome - brackets[filingStatus][1]) * rates[2];
            else if (taxableIncome <= brackets[filingStatus][3])
                tax = brackets[filingStatus][0] * rates[0]
                        + (brackets[filingStatus][1] - brackets[filingStatus][0]) * rates[1]
                        + (brackets[filingStatus][2] - brackets[filingStatus][1]) * rates[2]
                        + (taxableIncome - brackets[filingStatus][2]) * rates[3];
            else if (taxableIncome <= brackets[filingStatus][4])
                tax = brackets[filingStatus][0] * rates[0]
                        + (brackets[filingStatus][1] - brackets[filingStatus][0]) * rates[1]
                        + (brackets[filingStatus][2] - brackets[filingStatus][1]) * rates[2]
                        + (brackets[filingStatus][3] - brackets[filingStatus][2]) * rates[3]
                        + (taxableIncome - brackets[filingStatus][3]) * rates[4];
            else
                tax = brackets[filingStatus][0] * rates[0]
                        + (brackets[filingStatus][1] - brackets[filingStatus][0]) * rates[1]
                        + (brackets[filingStatus][2] - brackets[filingStatus][1]) * rates[2]
                        + (brackets[filingStatus][3] - brackets[filingStatus][2]) * rates[3]
                        + (brackets[filingStatus][4] - brackets[filingStatus][3]) * rates[4]
                        + (taxableIncome - brackets[filingStatus][4]) * rates[5];
            return tax;
        }
    }
}
