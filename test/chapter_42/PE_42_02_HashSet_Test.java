package chapter_42;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Write a test class to test the methods add, remove, addAll,
 * removeAll, size, isEmpty, and contains in the java.util.HashSet
 * class.
 */
public class PE_42_02_HashSet_Test {

    private HashSet<Object> hashSet;

    @Before
    public void before(){
        System.out.println("Before");
        hashSet = new HashSet<>();
    }

    @Test
    public void addTest(){
        System.out.println("addTest");
        assertTrue(hashSet.add("One"));
        assertFalse(hashSet.add("One"));

        assertTrue(hashSet.add("Two"));
        assertFalse(hashSet.add("Two"));

        assertTrue(hashSet.add(null));
        assertFalse(hashSet.add(null));
    }

    @Test
    public void removeTest(){
        hashSet.add("One");
        hashSet.add("Two");
        hashSet.add(null);

        System.out.println("removeTest");
        assertTrue(hashSet.remove("One"));
        assertFalse(hashSet.remove("One"));

        assertTrue(hashSet.remove("Two"));
        assertFalse(hashSet.remove("Two"));

        assertTrue(hashSet.remove(null));
        assertFalse(hashSet.remove(null));
    }

    @Test
    public void addAllTest(){
        System.out.println("addAllTest");
        List<String> list1 = new ArrayList<>();
        list1.add("One");
        list1.add("Two");
        list1.add("Three");

        List<String> list2 = new ArrayList<>();
        list2.add("One");
        list2.add("Two");
        list2.add("Four");

        List<String> list3 = new ArrayList<>();
        list3.add("One");
        list3.add("Two");
        list3.add("Three");

        List list4 = new ArrayList();

        assertTrue(hashSet.addAll(list1));
        assertTrue(hashSet.addAll(list2));
        assertFalse(hashSet.addAll(list3));
        assertFalse(hashSet.addAll(list4));
    }

    @Test
    public void removeAllTest(){
        System.out.println("removeAllTest");
        hashSet.add("One");
        hashSet.add("Two");
        hashSet.add("Three");
        hashSet.add("Four");

        List<String> list1 = new ArrayList<>();
        list1.add("One");
        list1.add("Two");
        list1.add("Three");

        List<String> list2 = new ArrayList<>();
        list2.add("One");
        list2.add("Two");
        list2.add("Four");

        List<String> list3 = new ArrayList<>();
        list3.add("One");
        list3.add("Two");
        list3.add("Three");

        List list4 = new ArrayList();

        assertTrue(hashSet.removeAll(list1));
        assertTrue(hashSet.removeAll(list2));
        assertFalse(hashSet.removeAll(list3));
        assertFalse(hashSet.removeAll(list4));
    }

    @Test
    public void sizeTest(){
        System.out.println("sizeTest");
        assertTrue(hashSet.size() == 0);
        hashSet.add("One");
        assertTrue(hashSet.size() == 1);
        hashSet.add("Two");
        assertTrue(hashSet.size() == 2);
        hashSet.add("Three");
        assertTrue(hashSet.size() == 3);
        hashSet.add("Four");
        assertTrue(hashSet.size() == 4);
        hashSet.remove("One");
        assertTrue(hashSet.size() == 3);
    }

    @Test
    public void isEmptyTest(){
        System.out.println("isEmptyTest");
        assertTrue(hashSet.isEmpty());
        hashSet.add("One");
        assertFalse(hashSet.isEmpty());
        hashSet.add("Two");
        assertFalse(hashSet.isEmpty());
        hashSet.remove("One");
        assertFalse(hashSet.isEmpty());
        hashSet.remove("Two");
        assertTrue(hashSet.isEmpty());
    }

    @Test
    public void containsTest(){
        System.out.println("containsTest");
        hashSet.add("One");
        hashSet.add("Two");
        hashSet.add("Three");
        hashSet.add("Four");

        assertTrue(hashSet.contains("One"));
        assertTrue(hashSet.contains("Three"));
        assertFalse(hashSet.contains("Five"));
        assertFalse(hashSet.contains(null));
    }
}
