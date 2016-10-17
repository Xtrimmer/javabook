package codility;

/**
 * Created by jtrimmer on 10/15/2016.
 */
public class FrogJump {
    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();
        int X = 10;
        int Y = 55;
        int D = 30;
        int minJumps = frogJump.solution(X, Y, D);
        System.out.println(minJumps);
    }

    public int solution(int X, int Y, int D) {
        int distance = Y - X;
        int jumps = distance / D;
        if (distance % D > 0) {
            jumps++;
        }
        return jumps;
    }

}
