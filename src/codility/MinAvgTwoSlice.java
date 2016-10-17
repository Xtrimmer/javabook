package codility;

import java.util.Arrays;

/**
 * Created by jtrimmer on 10/16/2016.
 */
public class MinAvgTwoSlice {
    public static void main(String[] args) {
        MinAvgTwoSlice minAvgTwoSlice = new MinAvgTwoSlice();
        int[] A = {-3, 1, 2, -5, 2, -5, -6};
        int minAvg = minAvgTwoSlice.solution(A);
        System.out.println(minAvg);
    }

    public int solution(int[] A) {
        int lastIndex = A.length - 1;
        Arrays.sort(A);
        return Math.max(A[0] * A[1] * A[lastIndex], A[lastIndex] * A[lastIndex - 1] * A[lastIndex - 2]);
    }
}
