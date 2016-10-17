package codility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jtrimmer on 10/15/2016.
 */
public class OddOccurrencesInArray {
    public static void main(String[] args) {
        int[] A = {9,3,5,9,3,9,7,9,7};
        OddOccurrencesInArray S = new OddOccurrencesInArray();
        int answer = S.solution(A);
        System.out.println(answer);
    }

    public int solution(int[] A) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                int value = map.get(A[i]);
                value++;
                map.put(A[i], value);
            } else {
                map.put(A[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (integerIntegerEntry.getValue() % 2 != 0) {
                return integerIntegerEntry.getKey();
            }
        }
        return -1;
    }
}
