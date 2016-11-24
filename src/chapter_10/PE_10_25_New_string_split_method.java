package chapter_10;

import java.util.Arrays;

/**
 * (New string split method) The split method in the String class returns an
 * array of strings consisting of the substrings split by the delimiters. However, the
 * delimiters are not returned. Implement the following new method that returns
 * an array of strings consisting of the substrings split by the matching delimiters,
 * including the matching delimiters.
 *
 *      public static String[] split(String s, String regex)
 *
 * For example, split("ab#12#453", "#") returns ab, #, 12, #, 453 in an
 * array of String, and split("a?b?gf#e", "[?#]") returns a, b, ?, b, gf,
 * #, and e in an array of String.
 */
public class PE_10_25_New_string_split_method {
    public static void main(String[] args) {
        String[] array = split("a?b?gf#e", "[?#]");
        System.out.println(Arrays.toString(array));
    }

    public static String[] split(String s, String regex) {
        String[] delimiters = getDelimiters(regex);
        String[] splitStrings = new String[s.length()];
        StringBuilder stringBuilder = new StringBuilder(s);
        int position = 0;
        while (stringBuilder.length() > 0) {
            if (!containsDelimiters(stringBuilder, delimiters)) {
                splitStrings[position++] = stringBuilder.toString();
                break;
            }
            int minIndex = stringBuilder.length();
            int delimiterLength = 1;
            for (String delimiter : delimiters) {
                int index = stringBuilder.indexOf(delimiter);
                if (index == 0) {
                    minIndex = 0;
                    break;
                }
                else if (index > 0 && index < minIndex) {
                    minIndex = index;
                    delimiterLength = delimiter.length();
                }
            }
            if (minIndex != 0) splitStrings[position++] = stringBuilder.substring(0, minIndex);
            splitStrings[position++] = stringBuilder.substring(minIndex, minIndex + delimiterLength);
            stringBuilder.delete(0, minIndex + delimiterLength);
        }
        String[] array = new String[position];
        System.arraycopy(splitStrings, 0, array, 0, position);
        return array;
    }

    private static boolean containsDelimiters(StringBuilder s, String[] delimiters) {
        boolean hasDelimiters = false;
        for (String delimiter : delimiters) {
            hasDelimiters |= s.indexOf(delimiter) >= 0;
        }
        return hasDelimiters;
    }

    private static String[] getDelimiters(String regex) {
        int bracketStart = regex.indexOf('[');
        int bracketEnd = regex.indexOf(']');
        int lastPosition = regex.length() - 1;
        String[] delimiters;
        if (bracketStart == 0 && bracketEnd == lastPosition) {
            delimiters = new String[(bracketEnd - bracketStart - 1)];
            for (int i = 0; i < regex.length() - 2; i++) {
                delimiters[i] = "" + regex.charAt(i + 1);
            }
        } else {
            delimiters = new String[1];
            delimiters[0] = regex;
        }
        return delimiters;
    }
}
