package chapter_21;

import java.util.*;

/**
 * (Revise Listing 21.9, CountOccurrenceOfWords.java) Rewrite Listing 21.9 to
 * display the words in ascending order of occurrence counts.
 *
 * (Hint: Create a class named WordOccurrence that implements the Comparable
 * interface. The class contains two fields, word and count. The compareTo
 * method compares the counts. For each pair in the hash set in Listing 21.9, create
 * an instance of WordOccurrence and store it in an array list. Sort the array list
 * using the Collections.sort method. What would be wrong if you stored the
 * instances of WordOccurrence in a tree set?)
 */
public class PE_21_07_Revise_Listing_21_9_CountOccurrenceOfWords {
    public static void main(String[] args) {
        String text = "Good morning. Have a good class. " +
                "Have a good visit. Have fun!";
        Map<String, Integer> map = new TreeMap<>();
        String[] words = text.split("[\\s+\\p{P}]");
        for (String word : words) {
            String key = word.toLowerCase();
            if (key.length() > 0) {
                if (!map.containsKey(key)) {
                    map.put(key, 1);
                } else {
                    int value = map.get(key);
                    value++;
                    map.put(key, value);
                }
            }
        }
        List<WordOccurrence> list = new ArrayList<>();
        map.forEach((k, v) -> list.add(new WordOccurrence(k, v)));
        Collections.sort(list);
        list.forEach(wo -> System.out.println(wo.word + '\t' + wo.count));
    }

    private static class WordOccurrence implements Comparable<WordOccurrence> {

        private final String word;
        private final Integer count;

        public WordOccurrence(String word, Integer count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public int compareTo(WordOccurrence that) {
            return this.count.compareTo(that.count);
        }
    }
}
