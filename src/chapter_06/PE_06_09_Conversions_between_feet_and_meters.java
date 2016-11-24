package chapter_06;

/**
 * (Conversions between feet and meters) Write a class that contains the following
 * two methods:
 *
 *      //Convert from feet to meters
 *      public static double footToMeter(double foot)
 *
 *      //Convert from meters to feet
 *      public static double meterToFoot(double meter)
 *
 * The formula for the conversion is:
 * meter = 0.305 * foot
 * foot = 3.279 * meter
 * Write a test program that invokes these methods to display the following tables:
 *
 *      Feet    Meters    |    Meters    Feet
 *      ----------------------------------------
 *       1.0    0.305     |    20.0       65.574
 *       2.0    0.610     |    25.0       81.967
 *       ...
 *       9.0    2.745     |    60.0      196.721
 *      10.0    3.050     |    65.0      213.115
 */
public class PE_06_09_Conversions_between_feet_and_meters {
    public static void main(String[] args) {
        System.out.println("Feet    Meters    |    Meters    Feet");
        System.out.println("----------------------------------------");
        for (int i = 1, j = 20; i <= 10; i++, j += 5){
            System.out.printf("%4.1f    %-9.3f |    %-9.1f  %7.3f%n", i + 0.0, footToMeter(i), j + 0.0, meterToFoot(j));
        }
    }

    //Convert from feet to meters
    public static double footToMeter(double foot){
        return 0.305 * foot;
    }

    //Convert from meters to feet
    public static double meterToFoot(double meter){
        return 3.279 * meter;
    }
}
