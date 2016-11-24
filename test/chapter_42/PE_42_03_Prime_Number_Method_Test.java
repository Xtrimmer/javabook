package chapter_42;

import Textbook_Examples.PrimeNumberMethod;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Write a test class to test the method isPrime in Listing 5.7
 * PrimeNumberMethod.Java.
 */
public class PE_42_03_Prime_Number_Method_Test {

    PrimeNumberMethod primeNumberMethod;

    @Before
    public void before(){
        primeNumberMethod = new PrimeNumberMethod();
    }

    @Test
    public void isPrimeTest(){
        assertTrue(primeNumberMethod.isPrime(2));
        assertTrue(primeNumberMethod.isPrime(3));
        assertFalse(primeNumberMethod.isPrime(4));
        assertTrue(primeNumberMethod.isPrime(5));
        assertFalse(primeNumberMethod.isPrime(6));
        assertTrue(primeNumberMethod.isPrime(7));
        assertFalse(primeNumberMethod.isPrime(8));
        assertFalse(primeNumberMethod.isPrime(9));
        assertFalse(primeNumberMethod.isPrime(10));
        assertTrue(primeNumberMethod.isPrime(11));
        assertTrue(primeNumberMethod.isPrime(Integer.MAX_VALUE));
    }
}
