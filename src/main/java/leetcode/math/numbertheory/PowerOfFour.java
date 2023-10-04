package leetcode.math.numbertheory;

/**
 * https://leetcode.com/problems/power-of-four/#/description
 *
 * Created by venkatamunnangi on 3/27/17.
 */
public class PowerOfFour {
    public boolean isPowerOfFour(int n) {
/**
 * (4^n - 1) % 3 == 0
 * another proof:
 * (1) 4^n - 1 = (2^n + 1) * (2^n - 1)
 * (2) among any 3 consecutive numbers, there must be one that is a multiple of 3
 * among (2^n-1), (2^n), (2^n+1), one of them must be a multiple of 3, and (2^n) cannot be the one,
 * therefore either (2^n-1) or (2^n+1) must be a multiple of 3, and 4^n-1 must be a multiple of 3 as well.
 */

    return n > 0 && (n & (n -1)) == 0 && ((n-1) % 3 == 0);}
}
