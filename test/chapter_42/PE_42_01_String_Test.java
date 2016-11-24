package chapter_42;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Write a test class to test the methods length, charAt, substring,
 * and indexOf in the java.lang.String class.
 */
public class PE_42_01_String_Test {

    private String stringOne;
    private String stringTwo;
    private String stringThree;

    @Before
    public void before() {
        stringOne = "";
        stringTwo = "2";
        stringThree = "Three";
    }

    @Test
    public void lengthTest() {
        assertEquals(stringOne.length(), 0);
        assertEquals(stringTwo.length(), 1);
        assertEquals(stringThree.length(), 5);
    }

    @Test
    public void chatAtTest() {
        assertEquals(stringTwo.charAt(0), '2');
        assertEquals(stringThree.charAt(0), 'T');
        assertEquals(stringThree.charAt(3), 'e');
    }

    @Test
    public void substringTest() {
        assertEquals("unhappy".substring(2), "happy");
        assertEquals("Harbison".substring(3), "bison");
        assertEquals("emptiness".substring(9), "");

        assertEquals("hamburger".substring(4, 8), "urge");
        assertEquals("smiles".substring(1, 5), "mile");
    }

    @Test
    public void indexOfTest() {
        assertTrue(stringTwo.indexOf('2') == 0);
        assertTrue(stringThree.indexOf('T') == 0);
        assertTrue(stringThree.indexOf('e') == 3);

        assertTrue("Mississippi".indexOf('i', 3) == 4);
        assertTrue("Mississippi".indexOf('i', 4) == 4);
        assertTrue("Mississippi".indexOf('i', 5) == 7);
        assertTrue(stringThree.indexOf('T', 0) == 0);
        assertTrue(stringThree.indexOf('e', 3) == 3);
        assertTrue(stringThree.indexOf('e', 4) == 4);
    }
}
