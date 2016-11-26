package chapter_42;

import textbook_listings.BMI;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Write a test class to test the methods getBMI and getStatus in the
 * BMI class in Listing 10.4.
 */
public class PE_42_04_BMI_Test {

    private final BMI bmiA = new BMI("Aaron", 100, 72);
    private final BMI bmiB = new BMI("Bob", 150, 72);
    private final BMI bmiC = new BMI("Charles", 175, 72);
    private final BMI bmiD = new BMI("David", 200, 72);
    private final BMI bmiE = new BMI("Ed", 250, 72);

    @Test
    public void getBmiTest() {
        assertTrue(bmiA.getBMI() == 13.56);
        assertTrue(bmiB.getBMI() == 20.34);
        assertTrue(bmiC.getBMI() == 23.73);
        assertTrue(bmiD.getBMI() == 27.12);
        assertTrue(bmiE.getBMI() == 33.91);
    }

    @Test
    public void getStatusTest() {
        assertEquals(bmiA.getStatus(), "Underweight");
        assertEquals(bmiB.getStatus(), "Normal");
        assertEquals(bmiC.getStatus(), "Normal");
        assertEquals(bmiD.getStatus(), "Overweight");
        assertEquals(bmiE.getStatus(), "Obese");
    }
}
