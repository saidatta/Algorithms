package leetcode.math.numbertheory;

/**
 * https://leetcode.com/problems/power-of-three/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
        if(n < 1) {
            return false;
        }

        while(n % 3 == 0 ) {
            n /= 3;
        }

        return n == 1;
    }
}
