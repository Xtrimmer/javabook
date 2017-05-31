package chapter_21;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * (Perform set operations on hash sets) Create two linked hash sets {"George",
 * "Jim", "John", "Blake", "Kevin", "Michael"} and {"George", "Katie",
 * "Kevin", "Michelle", "Ryan"} and find their union, difference, and intersection.
 * (You can clone the sets to preserve the original sets from being changed by
 * these set methods.)
 */
public class PE_21_01_Perform_set_operations_on_hash_sets {

    private static final HashSet<String> set1 = new HashSet<>(
            Arrays.asList("George", "Jim", "John", "Blake", "Kevin", "Michael")
    );
    private static final Set<String> set2 = new HashSet<>(
            Arrays.asList("George", "Katie", "Kevin", "Michelle", "Ryan")
    );

    public static void main(String[] args) {
        Set<String> union = (Set<String>) set1.clone();
        union.addAll(set2);
        System.out.println("Union:        " + union);

        Set<String> difference = (Set<String>) set1.clone();
        difference.removeAll(set2);
        System.out.println("Difference:   " + difference);

        Set<String> intersection = (Set<String>) set1.clone();
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);
    }
}
