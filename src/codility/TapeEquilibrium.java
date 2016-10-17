package codility;

/**
 * Created by jtrimmer on 10/15/2016.
 */
public class TapeEquilibrium {
    public static void main(String[] args) {
        int[] A = {-1000,1000};
        TapeEquilibrium tapeEquilibrium = new TapeEquilibrium();
        int minDifference = tapeEquilibrium.solution(A);
        System.out.println("Minimum Difference: " + minDifference);
    }

    public int solution(int[] A) {
        int minDifference = Integer.MAX_VALUE;
        for (int P = 1; P < A.length; P++) {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < P; i++) {
                sum1 += A[i];
            }
            for (int i = P; i < A.length; i++) {
                sum2 += A[i];
            }
            int difference = Math.abs(sum1 - sum2);
            System.out.println(P + ": " + difference);
            minDifference = difference < minDifference ? difference : minDifference;
        }
        return minDifference;
    }
}
