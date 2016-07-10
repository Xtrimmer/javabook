package Chapter_10;

import utility.BMI;

/**
 * (The BMI class) Add the following new constructor in the BMI class:
 *
 *      //Construct a BMI with the specified name, age, weight, feet, and inches
 *      public BMI(String name, int age, double weight, double feet,
 *      double inches)
 */
public class PE_10_02_The_BMI_class {
    public static void main(String[] args) {
        BMI bmi = new BMI("Jeff", 38, 185, 6, 0);
        System.out.printf("%s is age %s and is %s\" tall%nwith a weight of %s lbs.%nHis status is %s.",
                bmi.getName(), bmi.getAge(), bmi.getHeight(), bmi.getWeight(), bmi.getStatus());
    }
}
