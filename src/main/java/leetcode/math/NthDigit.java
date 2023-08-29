package leetcode.math;

/**
 * https://leetcode.com/problems/nth-digit/#/description
 * <p>
 * Created by venkatamunnangi on 5/15/17.
 */
public class NthDigit {

    /**
     * 1 - find the length of the number where the nth digit is from
     * 2 - find the actual number where the nth digit is from
     * 3 - find the nth digit and return
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while (n > len * count) {
            n -= len * count;
            len += 1;
            count *= 10;
            start *= 10;
        }

        start += (n - 1) / len;
        String s = Integer.toString(start);
        return Character.getNumericValue(s.charAt((n - 1) % len));
    }
}
