package chapter_42;

import textbook_listings.PrimeNumberMethod;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Write a test class to test the method isPrime in Listing 5.7
 * PrimeNumberMethod.Java.
 */
public class PE_42_03_Prime_Number_Method_Test {

    @Test
    public void isPrimeTest() {
        assertTrue(PrimeNumberMethod.isPrime(2));
        assertTrue(PrimeNumberMethod.isPrime(3));
        assertFalse(PrimeNumberMethod.isPrime(4));
        assertTrue(PrimeNumberMethod.isPrime(5));
        assertFalse(PrimeNumberMethod.isPrime(6));
        assertTrue(PrimeNumberMethod.isPrime(7));
        assertFalse(PrimeNumberMethod.isPrime(8));
        assertFalse(PrimeNumberMethod.isPrime(9));
        assertFalse(PrimeNumberMethod.isPrime(10));
        assertTrue(PrimeNumberMethod.isPrime(11));
        assertTrue(PrimeNumberMethod.isPrime(Integer.MAX_VALUE));
    }
}
