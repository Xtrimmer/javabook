package chapter_06;

/**
 * (Conversions between Celsius and Fahrenheit) Write a class that contains the following
 * two methods:
 *
 *      //Convert from Celsius to Fahrenheit
 *      public static double celsiusToFahrenheit(double celsius)
 *
 *      //Convert from Fahrenheit to Celsius
 *      public static double fahrenheitToCelsius(double fahrenheit)
 *
 * The formula for the conversion is:
 *
 *      fahrenheit = (9.0 / 5) * celsius + 32
 *      celsius = (5.0 / 9) * (fahrenheit – 32)
 *
 * Write a test program that invokes these methods to display the following tables:
 *
 *      Celsius Fahrenheit | Fahrenheit Celsius
 *      ---------------------------------------
 *      40.0    104.0      | 120.0      48.89
 *      39.0    102.2      | 110.0      43.33
 *      ...
 *      32.0    89.6       | 40.0       4.44
 *      31.0    87.8       | 30.0       -1.11
 */
public class PE_06_08_Conversions_between_Celsius_and_Fahrenheit {
    public static void main(String[] args) {
        System.out.println("Celsius Fahrenheit | Fahrenheit Celsius");
        System.out.println("---------------------------------------");
        for (int i = 40, j = 120; i >= 31; i--, j -= 10){
            System.out.printf("%-8.1f%-10.1f | %-11.1f%.2f%n", i + 0.0, celsiusToFahrenheit(i), j + 0.0, fahrenheitToCelsius(j));
        }
    }

    //Convert from Celsius to Fahrenheit
    public static double celsiusToFahrenheit(double celsius){
        return (9.0 / 5) * celsius + 32;
    }

    //Convert from Fahrenheit to Celsius
    public static double fahrenheitToCelsius(double fahrenheit){
        return (5.0 / 9) * (fahrenheit - 32);
    }
}
