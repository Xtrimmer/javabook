package codility;

/**
 * Created by jtrimmer on 10/16/2016.
 */
public class PassingCars {

    public static void main(String[] args) {
        PassingCars passingCars = new PassingCars();
        int[] A = {1,1,1,1,1,1};
        int passingCarCount = passingCars.solution(A);
        System.out.println(passingCarCount);
    }

    public int solution(int[] A) {
        final int MAX_PASSING_CARS_ALLOWED = 1000000000;
        int length = A.length;
        int countOfOnes = 0;
        int passingCarCount = 0;
        
        for (int i = length - 1; i >= 0; i--) {
            if (A[i] == 1) {
                ++countOfOnes;
            } else {
                passingCarCount += countOfOnes;
                if (passingCarCount > MAX_PASSING_CARS_ALLOWED) {
                    return -1;
                }
            }
        }
        return passingCarCount;
    }
}
