package chapter_10;

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

    private static class BMI {
        static final double KILOGRAMS_PER_POUND = 0.45359237;
        static final double METERS_PER_INCH = 0.0254;
        private final String name;
        private final int age;
        private final double weight; // in pounds
        private final double height; // in inches

        BMI(String name, int age, double weight, double feet,
            double inches) {
            this.name = name;
            this.age = age;
            this.weight = weight;
            this.height = (feet * 12) + inches;
        }

        public String getStatus() {
            double bmi = getBMI();
            if (bmi < 18.5)
                return "Underweight";
            else if (bmi < 25)
                return "Normal";
            else if (bmi < 30)
                return "Overweight";
            else
                return "Obese";
        }

        double getBMI() {
            double bmi = weight * KILOGRAMS_PER_POUND /
                    ((height * METERS_PER_INCH) * (height * METERS_PER_INCH));
            return Math.round(bmi * 100) / 100.0;
        }

        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }

        double getWeight() {
            return weight;
        }

        double getHeight() {
            return height;
        }
    }
}
