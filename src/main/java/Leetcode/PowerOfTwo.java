package Leetcode;

/**
 * https://leetcode.com/problems/power-of-two/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0 || n % 2 != 0) {
            return false;
        }

        // 0001000 - n
        // 0000111 - n-1
        return (n & (n-1)) == 0;
    }
}
